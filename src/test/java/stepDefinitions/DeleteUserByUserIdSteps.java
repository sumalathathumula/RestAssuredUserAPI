package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.APIUtils;

public class DeleteUserByUserIdSteps {
	
	private Response response;
    private int expectedStatus;
	@Given("User is on the DELETE Request to Delete User by ID API")
	public void user_is_on_the_delete_request_to_delete_user_by_id_api() {
		System.out.println("User is on Delete User by ID API");
	}

	@When("user sends a DELETE request with a valid user ID")
	public void user_sends_a_delete_request_with_a_valid_user_id() {
		int userId = APIUtils.getCreatedUserId(0);
        response = APIUtils.deleteUserById(userId);
        expectedStatus = 200;
	}
	@Then("response status code should be {int}")
	public void response_status_code_should_be(Integer int1) {
		 assertEquals(expectedStatus, response.getStatusCode(), "Status Code Mismatch");
	}


}
