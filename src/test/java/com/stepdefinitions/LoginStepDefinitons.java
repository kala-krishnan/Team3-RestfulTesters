package com.stepdefinitions;

import com.APIRequest.LoginRequest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefinitons {
	LoginRequest logRequest = new LoginRequest();
	
	@Given("Admin creates request with valid credentials")
	public void admin_creates_request_with_valid_credentials() {
	   
	}

	@When("Admin calls Post Https method  with valid endpoint")
	public void admin_calls_post_https_method_with_valid_endpoint() {
		logRequest.PostLoginRequest();
	}

	@Then("Admin receives {int} created with auto generated token")
	public void admin_receives_created_with_auto_generated_token(Integer int1) {
	    
	}



}
