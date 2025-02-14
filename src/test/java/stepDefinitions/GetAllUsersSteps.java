package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import api.UserApi;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

@Feature("User API - Get All Users")
@Story("Retrieve all users from the system")
public class GetAllUsersSteps {

	private Response response;
	private int expectedStatus;

	@Step("Allure: User is on the Get Request to Retrieve All Users API")
	@Given("User is on the Get Request to Retrieve All Users API")
	public void user_is_on_the_get_request_to_retrieve_all_users_api() {
		System.out.println("User is on Get All Users API");
		Allure.step("Allure Step: User is on Get All Users API");
	}

	@Step("User sends a GET request to retrieve all users")
	@When("user sends a GET request to retrieve all users")
	public void user_sends_a_get_request_to_retrieve_all_users() {
		response = UserApi.getAllUsers();
		expectedStatus = 200;
		System.out.println("respose code: " + response.getStatusCode());
	}

	@Step("Response status should match expected status")
	@Then("response status should match expected status")
	public void response_status_should_match_expected_status() {
		assertEquals(expectedStatus, response.getStatusCode(), "Status Code Mismatch");
	}

}
