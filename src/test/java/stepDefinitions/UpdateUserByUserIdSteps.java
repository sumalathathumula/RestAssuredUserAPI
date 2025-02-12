package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import api.UserApi;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import models.User;
import utils.ExcelReader;

public class UpdateUserByUserIdSteps {
	
	private User user; 
    private int statusCode;    
    private Response response;
    private static int createdUserId; // Store the created user ID
    
	@Given("User is on the put Request to update User with user id")
	public void user_is_on_the_put_request_to_update_user_with_user_id() {
		System.out.println("User is on Put request");
	}

	@When("user sends a put request with data from row {int}")
	public void user_sends_a_put_request_with_data_from_row(int rowNumber) {
		user = ExcelReader.getUserByRow(rowNumber);
		 if (createdUserId == 0) {
	            createdUserId = UserApi.getCreatedUserId(0); // Retrieve stored user ID from Create API response
	        }
	      response = UserApi.updateUser(createdUserId,  user);
	      statusCode = response.getStatusCode();
	      System.out.println("respose code: " + response.getStatusCode());
	      System.out.println("respose body: " + response.getBody().asString());
	}

	@Then("the updated user response status code should be {int}")
	public void the_updated_user_response_status_code_should_be(Integer int1) {
		assertEquals(response.statusCode(), statusCode);
	}


}
