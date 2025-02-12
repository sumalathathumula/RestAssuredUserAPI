package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import api.UserApi;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class GetUserByFirstNameSteps {

	private static int createdUserId; // Store the created user ID
	private static String createdFirstName;
	private Response response;
	private int expectedStatus;

	@Given("User is on the Get Request to Retrieve User by First Name API")
	public void user_is_on_the_get_request_to_retrieve_user_by_first_name_api() {
		System.out.println("User is on Get User by First Name API");
	}

	@When("user sends a GET request with the newly created user first name")
	public void user_sends_a_get_request_with_the_newly_created_user_first_name() {
		if (createdFirstName == null) {
			createdFirstName = UserApi.getCreatedUserFirstName(0); // Retrieve stored first name
		}
		response = UserApi.getUserByFirstName(createdFirstName);
		expectedStatus = 200;
		System.out.println("respose code: " + response.getStatusCode());
	}

	@Then("response status should match  the expected status")
	public void response_status_should_match_the_expected_status() {
		assertEquals(expectedStatus, response.getStatusCode(), "Status Code Mismatch");
	}

}
