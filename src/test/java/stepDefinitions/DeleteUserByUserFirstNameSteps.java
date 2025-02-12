package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import api.UserApi;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class DeleteUserByUserFirstNameSteps {

	private Response response;
	private int expectedStatus;

	@Given("User is on the DELETE Request to Delete User by First Name API")
	public void user_is_on_the_delete_request_to_delete_user_by_first_name_api() {
		System.out.println("User is on Delete User by First Name API");
	}

	@When("user sends a DELETE request with a valid first name")
	public void user_sends_a_delete_request_with_a_valid_first_name() {
		String firstName = UserApi.getCreatedUserFirstName(1);
		response = UserApi.deleteUserByFirstName(firstName);
		expectedStatus = 200;

	}

	@Then("response status should be {int}")
	public void response_status_should_be(int expectedStatus) {
		assertEquals(expectedStatus, response.getStatusCode(), "Status Code Mismatch");
	}

}
