package api;

import java.util.ArrayList;
import java.util.List;

import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.User;

import static io.restassured.RestAssured.given;

public class UserApi extends BaseApi {
	
	
	 private static final List<Integer> createdUserIds = new ArrayList<>();
	 private static final List<String> createdFirstNames = new ArrayList<>();
	 
	 public static Response createUser(User user, boolean useAuth, boolean useInvalidEndpoint) {
	        String endpoint = useInvalidEndpoint ? "/uap/invalidendpoint" : ApiEndpoints.CREATE_USER.getEndpoint();

	        Response response = given()
	                .auth().basic(useAuth ? ConfigReader.getProperty("username") : "", 
	                              useAuth ? ConfigReader.getProperty("password") : "")
	                .contentType("application/json")
	                .body(user)
	                .log().body()
	                .post(endpoint);

	        if (response.getStatusCode() == 201) {
	            int createdUserId = response.jsonPath().getInt("user_id");
	            String createdFirstName = response.jsonPath().getString("user_first_name");
	            
	            createdUserIds.add(createdUserId);
	            createdFirstNames.add(createdFirstName);
	        }

	        return response;
	    }
	
	
	
	
	public static Response getAllUsers() {
        return RestAssured.given(requestSpec)                
                .get(ApiEndpoints.GET_ALL_USERS.getEndpoint());
    }
	 
	public static Response getUserById(int userId) {
        return RestAssured.given(requestSpec)
                .pathParam("userId", userId)
                .get(ApiEndpoints.GET_USER_BY_ID.getEndpoint());
    }

    public static Response getUserByFirstName(String firstName) {
        return RestAssured.given(requestSpec)
                .pathParam("firstName", firstName)
                .get(ApiEndpoints.GET_USER_BY_FIRSTNAME.getEndpoint());
    }    

    public static Response updateUser(int userId, User requestBody) {
        return RestAssured.given(requestSpec)
                .pathParam("userId", userId)
                .body(requestBody)
                .put(ApiEndpoints.UPDATE_USER.getEndpoint());
    }

    public static Response deleteUserById(int createdUserId) {
        return RestAssured.given(requestSpec)
                .pathParam("userId", createdUserId)
                .delete(ApiEndpoints.DELETE_USER_ID.getEndpoint());
    }
    
    public static Response deleteUserByFirstName(String createdFirstName) {
        return RestAssured.given(requestSpec)
                .pathParam("userfirstname", createdFirstName)
                .delete(ApiEndpoints.DELETE_USER_BY_FIRSTNAME.getEndpoint());
    }
    
    public static int getCreatedUserId(int index) {
		return createdUserIds.get(index);
	}

	public static String getCreatedUserFirstName(int index) {
		return createdFirstNames.get(index);
	}
    
}


