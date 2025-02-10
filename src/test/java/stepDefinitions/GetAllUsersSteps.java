package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.APIUtils;

public class GetAllUsersSteps {
	
	 private Response response;
	    private int expectedStatus;
	@Given("User is on the Get Request to Retrieve All Users API")
	public void user_is_on_the_get_request_to_retrieve_all_users_api() {
		System.out.println("User is on Get All Users API");
	}

	@When("user sends a GET request to retrieve all users")
	public void user_sends_a_get_request_to_retrieve_all_users() {
		 response = APIUtils.getAllUsers();
	        expectedStatus = 200;
	        System.out.println("respose code: " + response.getStatusCode());
	    }
	
	@Then("response status should match expected status")
	public void response_status_should_match_expected_status() {
		assertEquals(expectedStatus, response.getStatusCode(), "Status Code Mismatch");
	}

}
