package com.stepdefinitions;

import java.io.FileNotFoundException;

import com.APIRequest.LoginRequest;
import com.APIRequest.LogoutRequest;
import com.APIResponse.CommonResponseValidation;
import com.context.ScenarioContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class LogoutStepDefinition {
	
	ScenarioContext context = ScenarioContext.getInstance();
	CommonResponseValidation Validation = new CommonResponseValidation();
	private Response logoutResponse;
	LogoutRequest logout;
	
	public  LogoutStepDefinition() throws FileNotFoundException {
		logout = new LogoutRequest();
	}
	
	@Given("Admin creates Logout {string} Request")
	public void admin_creates_logout_request(String requestType) throws Exception {
		logout.setGetLogoutRequest(requestType);
	}

	@When("Admin sends GET HTTPS {string} Logout Request")
	public void admin_sends_get_https_logout_request(String Scenario) {
		logout.GetLogoutRequest(Scenario);
	}

	@Then("Admin receives status {string} for Logout request")
	public void admin_receives_status_for_logout_request(String Code) {
		logoutResponse = context.get("logoutResponse", Response.class);
		
		Validation.validateStatusCode(logoutResponse, logout.getStatusCode());
		Validation.validateStatusLine(logoutResponse, logout.getStatusLine());
		Validation.validateResponseTime(logoutResponse);
		Validation.assertAll();

	}

	

}
