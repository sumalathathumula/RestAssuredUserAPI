package stepDefinitions;
import config.ConfigReader;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import models.User;
import utils.APIUtils;
import utils.ExcelReader;
import static org.junit.Assert.*;

public class CreateUserSteps {

	private String username;
	private String password;
	private User user; // User object to hold user data
    private int statusCode;
    private String responseMessage;
    private Response response;
	
	@Given("User has valid credentials")
	public void user_has_valid_credentials() {
		 this.username = ConfigReader.getProperty("username");
		 this.password = ConfigReader.getProperty("password");
	}

	@Given("User is on the Post Request to Create User")
	public void user_is_on_the_post_request_to_create_user() {
		System.out.println("User is on Create User API - Context Set");
	}
	
	@When("user sends a create user request with data from row {int}")
	public void user_sends_a_create_user_request_with_data_from_row(Integer rowNumber) {
		  user = ExcelReader.getUserByRow(rowNumber);  // Fetch user data from Excel
	      response = APIUtils.createUser(user, true, false);
	      statusCode = response.getStatusCode();
	      System.out.println("respose code: " + response.getStatusCode());
	      System.out.println("respose body: " + response.getBody().asString());
	}

	@Then("the response status should be {int}")
	public void the_response_status_should_be(int statusCode) {
		 assertEquals(response.statusCode(), statusCode);
	}
}
