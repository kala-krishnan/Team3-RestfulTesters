package com.stepdefinitions;

import org.testng.asserts.SoftAssert;

import com.APIRequest.LoginRequest;
import com.APIResponse.CommonResponseValidation;
import com.commonUtils.ConfigReader;
import com.commonUtils.SpecificationClass;
import com.context.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.FileNotFoundException;
import java.util.List;

public class LoginStepDefinitons {

	ScenarioContext context = ScenarioContext.getInstance();
	CommonResponseValidation Validation = new CommonResponseValidation();
	private Response loginResponse;
	LoginRequest login;
	
	String userLoginSchema = ConfigReader.getProperty("userLoginSchema");

	public LoginStepDefinitons() throws FileNotFoundException {

		login = new LoginRequest();
	}
	
	@Given("Admin creates Login Valid Request")
	public void admin_creates_login_valid_request() {
		context.set("LMStoken",null);
	}

	@When("Admin sends Post HTTPS Login Request")
	public void admin_sends_post_https_login_request() {
		login.PostLoginRequest();
	}

	@Then("Admin receives status with token")
	public void admin_receives_status_with_token() {
		loginResponse = context.get("loginResponse", Response.class);
		Validation.validateStatusCode(loginResponse, 200);
		Validation.validateStatusLine(loginResponse, "HTTP/1.1 200 OK");
		Validation.validateResponseTime(loginResponse);
		Validation.assertAll();
	}

	@Given("Admin creates Login {string} Request")
	public void admin_creates_login_request(String requestType) throws Exception {
		login.setLoginRequest(requestType);
	}

	@When("Admin sends {string} HTTPS Login Request")
	public void admin_sends_https_login_request(String Scenario) {
		login.postLoginRequestFromJson(Scenario);
	}

	@Then("Admin receives status {string} for Login request")
	public void admin_receives_status_for_login_request(String Code) {
		loginResponse = context.get("loginResponse", Response.class);
		Validation.validateStatusCode(loginResponse, login.getStatusCode());
		Validation.validateStatusLine(loginResponse, login.getStatusLine());
		Validation.validateResponseTime(loginResponse);	
		if(!Code.equals("404"))
			Validation.validateContentType(loginResponse);
		Validation.assertAll();
	}


}
