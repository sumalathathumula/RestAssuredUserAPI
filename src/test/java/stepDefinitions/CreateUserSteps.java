package stepDefinitions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import api.UserApi;
import config.ConfigReader;
import hooks.TestHooks;
import io.cucumber.java.en.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.User;
import utils.ResponseValidator;
import utils.UserMapper;

public class CreateUserSteps {
	private String username;
	private String password;
	private List<Response> responses = new ArrayList<>();
	private List<User> users = new ArrayList<>();
	
	@Given("User has valid credentials")
	public void user_has_valid_credentials() {
		this.username = ConfigReader.getProperty("username");
		this.password = ConfigReader.getProperty("password");
	}
	
	@Given("User is on the Post Request to Create User")
	public void user_is_on_the_post_request_to_create_user() {
		System.out.println("User is on Create User API - Context Set");
	}

	@When("user sends a create user request with data from row {string}")
	public void user_sends_create_request(String scenario) {
	    for (Map<String, String> row : TestHooks.testData) {  // Use preloaded data
	        if (row.get("Scenario").equalsIgnoreCase(scenario) && row.get("RequestType").equalsIgnoreCase("POST")) {
	            User user = UserMapper.mapRowToUser(row);
	            boolean useAuth = true;
	            boolean useInvalidEndpoint = false;

	            Response response = UserApi.createUser(user, useAuth, useInvalidEndpoint);
	            
	            System.out.println("respose code: " + response.getStatusCode());
	    		System.out.println("respose body: " + response.getBody().asString());
	    		Allure.step("User is on Get All Users API for : "+ scenario);
	    		
	            users.add(user);
	            responses.add(response);
	        }
	    }
	}
	

	@Then("the response status should be equal to ExpectedStatus")
	 @Step("Validating response status code for all created users")  // ✅ Ensure Allure captures this step
	public void the_response_status_should_be_equal_to_expected_status() {
		List<String> assertionErrors = new ArrayList<>(); // ✅ Store failures

	    for (int i = 0; i < users.size(); i++) {
	        User user = users.get(i);
	        Response response = responses.get(i);

	        for (Map<String, String> row : TestHooks.testData) {
	            if (user.getFirstName().equalsIgnoreCase(row.get("user_first_name")) && row.get("RequestType").equalsIgnoreCase("POST")) {
	                
	                // ✅ Log validation details before checking
	                Allure.step("Validating response for user: " + user.getFirstName() +
	                            ", Expected Status: " + row.get("ExpectedStatus") +
	                            ", Actual Status: " + response.getStatusCode());

	                try {
	                    ResponseValidator.validateResponse(row, user,response);
	                } catch (AssertionError e) {
	                    String errorMessage = "❌ Validation failed for user: " + user.getFirstName() +
	                                          ", Expected Status: " + row.get("ExpectedStatus") +
	                                          ", Actual Status: " + response.getStatusCode();
	                    Allure.step(errorMessage);
	                    assertionErrors.add(errorMessage + "\n" + e.getMessage()); // ✅ Store error, continue execution
	                }
	                
	                break; 
	            }
	        }
	    }

	    // ✅ Fail test at the end if there were any errors
	    if (!assertionErrors.isEmpty()) {
	        throw new AssertionError("Multiple assertions failed:\n" + String.join("\n", assertionErrors));
	    }
	}


}
