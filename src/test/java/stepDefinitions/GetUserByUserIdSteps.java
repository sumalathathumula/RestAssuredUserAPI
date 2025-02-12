package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import api.UserApi;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class GetUserByUserIdSteps {

	private Response response;
	private int expectedStatus;
	private static int createdUserId;

	@Given("User is on the Get Request to Retrieve User by ID API")
	public void user_is_on_the_get_request_to_retrieve_user_by_id_api() {
		System.out.println("User is on Get User by ID API");
	}

	@When("user sends a GET request with the newly created user ID")
	public void user_sends_a_get_request_with_the_newly_created_user_id() {

		if (createdUserId == 0) {
			createdUserId = UserApi.getCreatedUserId(0); // Retrieve stored user ID from Create API response
		}
		response = UserApi.getUserById(createdUserId);
		expectedStatus = 200; // Expected successful status
		System.out.println("respose code: " + response.getStatusCode());
	}

	@Then("response status code should match expected status")
	public void response_status_code_should_match_expected_status() {
		assertEquals(expectedStatus, response.getStatusCode(), "Status Code Mismatch");
	}

}
