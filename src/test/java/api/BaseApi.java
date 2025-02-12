package api;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import config.ConfigReader;

public class BaseApi {
	
	 protected static RequestSpecification requestSpec;

	    static {
	        RestAssured.baseURI = ConfigReader.getBaseUrl(); // Set Base URL Once
	        requestSpec = RestAssured.given()
	                .auth().preemptive().basic(ConfigReader.getUsername(), ConfigReader.getPassword())
	                .header("Content-Type", "application/json")
	                .header("Accept", "application/json");
	    }

}
