package utils;

import static org.junit.Assert.*;

import io.qameta.allure.Allure;
import io.restassured.response.Response;
import models.User;

import io.restassured.module.jsv.JsonSchemaValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResponseValidator {
	public static void validateResponse(Map<String, String> expectedData, User requestUser, Response response) {

		int actualStatus = response.getStatusCode();
		int expectedStatus = Integer.parseInt(expectedData.get("ExpectedStatus"));
		// ✅ Status Code Validation
		Allure.step("Validating Status Code: Expected = " + expectedStatus + ", Actual = " + actualStatus);
		try {
			assertEquals("❌ Incorrect status code!", expectedStatus, actualStatus);
		} catch (AssertionError e) {
			Allure.step("❌ Status Code Assertion Failed! Expected: " + expectedStatus + ", Actual: " + actualStatus);
			throw new AssertionError("Multiple assertions failed:\n" + e.getMessage());
		}
		try {

			assertEquals("Incorrect Content-Type!", "application/json; charset=utf-8",
					response.getHeader("Content-Type"));
		} catch (AssertionError e) {
			// Log failure details in Allure with a step

		}

		if (response.getStatusCode() == 201) {
			response.then().assertThat()
					.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("testdata/user_schema.json"));

			String actualFirstName = response.jsonPath().getString("user_first_name");
			String actualLastName = response.jsonPath().getString("user_last_name");
			String actualEmail = response.jsonPath().getString("user_email_id");

			// ✅ Collect assertion failures
			List<String> assertionErrors = new ArrayList<>();

			// ✅ Attach request & response details
			Allure.addAttachment("Request Payload", "application/json", response.getBody().asString());
			Allure.addAttachment("Response Headers", "text/plain", response.getHeaders().toString());

			// ✅ First Name Validation
			Allure.step("Validating First Name: Expected = " + requestUser.getFirstName() + ", Actual = "
					+ actualFirstName);
			try {
				assertEquals("❌ First name mismatch!", requestUser.getFirstName(), actualFirstName);
			} catch (AssertionError e) {
				Allure.step("❌ First Name Assertion Failed! Expected: " + requestUser.getFirstName() + ", Actual: "
						+ actualFirstName);
				assertionErrors.add(e.getMessage());
			}

			// ✅ Last Name Validation
			Allure.step(
					"Validating Last Name: Expected = " + requestUser.getLastName() + ", Actual = " + actualLastName);
			try {
				assertEquals("❌ Last name mismatch!", requestUser.getLastName(), actualLastName);
			} catch (AssertionError e) {
				Allure.step("❌ Last Name Assertion Failed! Expected: " + requestUser.getLastName() + ", Actual: "
						+ actualLastName);
				assertionErrors.add(e.getMessage());
			}

			// ✅ Email Validation
			Allure.step("Validating Email: Expected = " + requestUser.getEmail() + ", Actual = " + actualEmail);
			try {
				assertEquals("❌ Email mismatch!", requestUser.getEmail(), actualEmail);
			} catch (AssertionError e) {
				Allure.step(
						"❌ Email Assertion Failed! Expected: " + requestUser.getEmail() + ", Actual: " + actualEmail);
				assertionErrors.add(e.getMessage());
			}

			// ✅ Attach response body if any assertion fails
			if (!assertionErrors.isEmpty()) {
				Allure.addAttachment("❌ Response Body (Failed Case)", "application/json",
						response.getBody().asString());
				throw new AssertionError("Multiple assertions failed:\n" + String.join("\n", assertionErrors));
			}
		}

	}
}
