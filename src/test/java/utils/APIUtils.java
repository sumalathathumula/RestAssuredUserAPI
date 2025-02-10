package utils;
import models.User;
import config.ConfigReader;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;
public class APIUtils {
	
	private static int createdUserId;
	private static String createdFirstName;
	
	 private static final List<Integer> createdUserIds = new ArrayList<>();
	private static final List<String> createdFirstNames = new ArrayList<>();
	    
    public static Response createUser(User user, boolean useAuth, boolean useInvalidEndpoint) {
    	if (useInvalidEndpoint) {
            RestAssured.baseURI = ConfigReader.getProperty("baseUrl") + "/uap/invalidendpoint";
        } else {
            RestAssured.baseURI = ConfigReader.getProperty("baseUrl") + "/uap/createusers";
        }
       Response response = given()
                .auth().basic(useAuth ? ConfigReader.getProperty("username") : "", 
                              useAuth ? ConfigReader.getProperty("password") : "")
                .contentType("application/json")
                .body(user)
                .log().body() // Log the body being sent in the request
                .post();
       
       if (response.getStatusCode() == 201) {
           createdUserId = response.jsonPath().getInt("user_id");
           createdUserIds.add(createdUserId);
           createdFirstName = response.jsonPath().getString("user_first_name");
           createdFirstNames.add(createdFirstName);           
       }       
       
       return response;
    }
    
    public static Response getUserById(int userId) {
        return RestAssured.given()
            .auth().preemptive().basic(ConfigReader.getUsername(), ConfigReader.getPassword())
            .get(ConfigReader.getBaseUrl() + "/uap/user/" + userId);
    }
    
    public static Response getUserByFirstName(String firstName) {
        return RestAssured.given()
            .auth().preemptive().basic(ConfigReader.getUsername(), ConfigReader.getPassword())
            .get(ConfigReader.getBaseUrl() + "/uap/users/username/" + firstName);
    }
    
    public static int getCreatedUserId(int index) {
        return createdUserIds.get(index);
    }

    public static String getCreatedUserFirstName(int index) {
        return createdFirstNames.get(index);
    }
    public static Response getAllUsers() {
        return RestAssured.given()
            .auth().preemptive().basic(ConfigReader.getUsername(), ConfigReader.getPassword())
            .get(ConfigReader.getBaseUrl() + "/uap/users" );
    }
    public static Response deleteUserByFirstName(String createdFirstName) {
        return RestAssured.given()
            .auth().preemptive().basic(ConfigReader.getUsername(), ConfigReader.getPassword())
            .delete(ConfigReader.getBaseUrl() + "/uap/deleteuser/username/" +  createdFirstName);
    }
    public static Response deleteUserById(int userId) {
        return RestAssured.given()
            .auth().preemptive().basic(ConfigReader.getUsername(), ConfigReader.getPassword())
            .delete(ConfigReader.getBaseUrl() + "/users/" + userId);
    }

}
