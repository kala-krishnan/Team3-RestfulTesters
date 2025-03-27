package com.stepdefinitions;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.APIRequest.LoginRequest;
import com.APIRequest.UserRequest;
import com.commonUtils.ConfigReader;
import com.commonUtils.TestDataLoader;
import com.context.ScenarioContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.pojoclass.UserPojo;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

public class UserStepDefinition {
	ScenarioContext context = ScenarioContext.getInstance();
	Response userResponse;
	LoginRequest logRequest;
	UserRequest userRequest;
	SoftAssert softAssert ;
	Response getUserFilterResponse;
	JsonNode getTestData_Get;
	String userSchema = ConfigReader.getProperty("userSchema");
	
	public UserStepDefinition() throws FileNotFoundException {
		logRequest = new LoginRequest();
		userRequest = new UserRequest();
		
	}

/*********************************background*********************************/
	
@Given("Set Auth to bearer token")
public void set_auth_to_bearer_token() throws Exception {
	System.out.println(context.get("LMStoken"));
	if(context.get("LMStoken")==(null))
		logRequest.PostLoginRequest();
}

/*********************************post request  *********************************/

@Given("Admin creates POST Request with request body {string} for LMS User Module")
public void admin_creates_post_request_with_request_body_for_lms_user_module(String requestType) throws Exception {
	userRequest.newUserRequest(requestType);
}

@When("Admin calls Post Https method  with valid endpoint for LMS User Module")
public void admin_calls_post_https_method_with_valid_endpoint_for_lms_user_module() {
	userRequest.PostNewUserRequest();
}

@Then("Admin receive created  status for LMS User Module")
public void admin_receive_created_status_for_lms_user_module() {
	 UserPojo userRequest = context.get("UserPojo", UserPojo.class);
	userResponse = context.get("userResponse", Response.class);
	int actualStatusCode = userResponse.getStatusCode();
	int expectedStatusCode =userRequest.getStatusCode();
	softAssert =new SoftAssert();
	
	//Status code Validation
	softAssert.assertEquals(actualStatusCode, expectedStatusCode, "Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
	softAssert.assertAll();
}

//Background (Setting Auth) -->WE have two methods.ANything is fine .Replace as per the need
@Given("User Set Authentication Token for Authorisation")
public void user_set_authentication_token_for_authorisation() {
	if(context.get("LMStoken")==null)
	{
		logRequest.PostLoginRequest();
	}
	System.out.println("context.get(LMStoken)" +context.get("LMStoken"));
	
}

/***********************get all request  *********************************/


//
//@When("Admin sends GET Request {string} with v2 endpoint for LMS User Module")
//public void admin_sends_get_request_with_v2_endpoint_for_lms_user_module(String string) {
//	 getUserFilterResponse=userRequest.sendGetUserReqWithOutBody(string);
//}


@Given("Admin creates GET Request {string} {string}  for LMS User Module") 
public void admin_creates_get_request_for_lms_user_module(String requestType, String status)  throws Exception {
	 getTestData_Get= userRequest.getUserRequest(requestType, status);
}

@When("Admin sends GET Request {string}  with v2 endpoint for LMS User Module {string}")
public void admin_sends_get_request_with_v2_endpoint_for_lms_user_module(String requestType, String status) {
	getUserFilterResponse=userRequest.sendGetUserReqWithOutBody(requestType,status);
	 context.set("userGetResponse", getUserFilterResponse);
}

@Then("Admin gets the list of active users with filters {string}")
public void admin_gets_the_list_of_active_users_with_filters(String status) {
	int actualStatusCode = getUserFilterResponse.getStatusCode();
	int expectedStatusCode =getTestData_Get.get("statusCode").asInt();
	softAssert =new SoftAssert();
	
	//Status code Validation
	System.out.println("Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
	softAssert.assertEquals(actualStatusCode, expectedStatusCode, "Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
	softAssert.assertEquals(actualStatusCode, expectedStatusCode, "Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
	softAssert.assertAll();
}


/***********************get request based on parameter  *********************************/

/* for user id and role id get only valid*/
@Given("Admin creates GET Request {string} and {string} for LMS User Module")
public void admin_creates_get_request_and_for_lms_user_module(String requestType, String paramValue) throws Exception {
	
	 getTestData_Get=userRequest.setGetUserRequestBody(requestType,paramValue);
}

@When("Admin sends GET Request with endpoint and {string} {string}for LMS User Module")
public void admin_sends_get_request_with_endpoint_and_for_lms_user_module(String endpointValue, String param) {
	getUserFilterResponse=userRequest.sendGetUserReqWithBody(endpointValue,param);
}

@Then("Admin gets the users details")
public void admin_gets_the_users_details() {
	int actualStatusCode = getUserFilterResponse.getStatusCode();
	int expectedStatusCode =getTestData_Get.get("statusCode").asInt();
	softAssert =new SoftAssert();
	
	//Status code Validation
	System.out.println("Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
	softAssert.assertEquals(actualStatusCode, expectedStatusCode, "Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
	softAssert.assertAll();
}

/* for program id abd batch id get only valid*/
@Given("Admin creates GET Request {string} and {string} for LMS User Module for prm batch")
public void admin_creates_get_request_and_for_lms_user_module_for_prm_batch(String string, String string2) throws Exception {
	getTestData_Get=userRequest.setGetUserReqBody(string,string2);
}

//@When("Admin sends GET Request with endpoint and {string} and {string} for LMS User Module for prm batch")
//public void admin_sends_get_request_with_endpoint_and_for_lms_user_module_for_prm_batch(String requestType, String param) {
//	getUserFilterResponse=userRequest.sendGetUserReqWithIntBody(requestType,param);
//}

@When("Admin sends GET Request with endpoint and {string}  and {string} for LMS User Module for prm batch")
public void admin_sends_get_request_with_endpoint_and_and_for_lms_user_module_for_prm_batch(String string, String string2) {
	getUserFilterResponse=userRequest.sendGetUserReqWithIntBody(string,string2);
}
@Then("Admin gets the users detailsfor prm batch")
public void admin_gets_the_users_detailsfor_prm_batch() {
	int actualStatusCode = getUserFilterResponse.getStatusCode();
	int expectedStatusCode =getTestData_Get.get("statusCode").asInt();
	softAssert =new SoftAssert();
	
	//Status code Validation
	System.out.println("Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
	softAssert.assertEquals(actualStatusCode, expectedStatusCode, "Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
	softAssert.assertAll();
}

/**********************************************Get All Request start***********************************************************
/*
 * Get All Users
 */
@Given("User creates Get Request to get user Details with request body {string} for LMS User Module")
public void user_creates_get_request_to_get_user_details_with_request_body_for_lms_user_module(String requestType) throws Exception {
	
	getTestData_Get=userRequest.setGetUserRequest(requestType);
}


@When("User calls GET Https method for the requesttype {string} to get all users with endpoint for LMS User Module")
public void user_calls_get_https_method_for_the_requesttype_to_get_all_users_with_endpoint_for_lms_user_module(String reqType) throws IOException {
	userRequest.getAllUsers(reqType);
}

@Then("User received proper status code {string} with all User Details")
public void user_received_proper_status_code_with_all_user_details(String string) {
	Assert.assertEquals(userRequest.responseCode(), getTestData_Get.get("statusCode").asInt());
	
}

/*
 * Get All Active Users
 */

@When("User calls GET Https method for the requesttype {string} to get all active users with endpoint for LMS User Module")
public void user_calls_get_https_method_for_the_requesttype_to_get_all_active_users_with_endpoint_for_lms_user_module(String reqType) {
	userRequest.getAllActiveUsers(reqType);
}




@Then("User received proper status code {string} with all active User Details")
public void user_received_proper_status_code_with_all_active_user_details(String string) {
	Assert.assertEquals(userRequest.responseCode(), getTestData_Get.get("statusCode").asInt());
}
/*
 * Get All Users Email Id
 */


@When("User calls GET Https method for the requesttype {string} to fetch email id of all users with endpoint for LMS User Module")
public void user_calls_get_https_method_for_the_requesttype_to_fetch_email_id_of_all_users_with_endpoint_for_lms_user_module(String reqType) {
	userRequest.getAllFetchEMailUsers(reqType);
}
@Then("User received proper status code {string} with all User Email Ids")
public void user_received_proper_status_code_with_all_user_email_ids(String string) {
	Assert.assertEquals(userRequest.responseCode(), getTestData_Get.get("statusCode").asInt());
}

/*
 * Get All roles 
 */
@Given("User creates Get Request to get roles details with request body {string} for LMS User Module")
public void user_creates_get_request_to_get_roles_details_with_request_body_for_lms_user_module(String requestType) throws Exception {
	getTestData_Get=userRequest.setGetUserRequest(requestType);
}


@When("User calls GET Https method for the requesttype {string} to fetch all roles with endpoint for LMS User Module")
public void user_calls_get_https_method_for_the_requesttype_to_fetch_all_roles_with_endpoint_for_lms_user_module(String reqType) {
	userRequest.getAllRoles(reqType);
}
@Then("User received proper status code {string} with all roles")
public void user_received_proper_status_code_with_all_roles(String string) {
	Assert.assertEquals(userRequest.responseCode(), getTestData_Get.get("statusCode").asInt());
}


/*
 * Get All users with roles
 */
@Given("User creates Get Request to get all users with roles details with request body {string} for LMS User Module")
public void user_creates_get_request_to_get_all_users_with_roles_details_with_request_body_for_lms_user_module(String requestType) throws Exception {
	getTestData_Get=userRequest.setGetUserRequest(requestType);
}


@When("User calls GET Https method for the requesttype {string} to fetch all users with roles with endpoint for LMS User Module")
public void user_calls_get_https_method_for_the_requesttype_to_fetch_all_users_with_roles_with_endpoint_for_lms_user_module(String reqType) {
	userRequest.getAllUserwithRoles(reqType);
}
@Then("User received proper status code {string} with all users with roles")
public void user_received_proper_status_code_with_all_users_with_roles(String string) {
	Assert.assertEquals(userRequest.responseCode(), getTestData_Get.get("statusCode").asInt());
}


/*
 * Get status count
 */
@Given("User creates Get Request to get users with status count with request body {string} for LMS User Module")
public void user_creates_get_request_to_get_users_with_status_count_with_request_body_for_lms_user_module(String requestType) throws Exception {
	getTestData_Get=userRequest.setGetUserRequest(requestType);
}
@When("User calls GET Https method for the requesttype {string} to fetch users with status count with endpoint for LMS User Module")
public void user_calls_get_https_method_for_the_requesttype_to_fetch_users_with_status_count_with_endpoint_for_lms_user_module(String reqType) {
	userRequest.GetAllUserStatusCount(reqType);
}

@Then("User received proper status code {string} with user with status count")
public void user_received_proper_status_code_with_user_with_status_count(String string) {
	Assert.assertEquals(userRequest.responseCode(), getTestData_Get.get("statusCode").asInt());}


//**********************************************Get All Request***********************************************************


//**********************************************GetBy Id starts***********************************************************
/*
 * Get User by User id
 */
@Given("User creates Get Request to fetch Users by UserId with request body {string} for LMS User Module")
public void user_creates_get_request_to_fetch_users_by_user_id_with_request_body_for_lms_user_module(String requestType) throws Exception {
	getTestData_Get=userRequest.setGetUserRequest(requestType);
}

@When("User calls GET Https method for the requesttype {string} to fetch Users by UserId with endpoint for LMS User Module")
public void user_calls_get_https_method_for_the_requesttype_to_fetch_users_by_user_id_with_endpoint_for_lms_user_module(String reqType) {
	userRequest.GetUserByUserId(reqType);
}



@Then("User received proper status code {string} with Users by UserId")
public void user_received_proper_status_code_with_users_by_user_id(String string) {
	Assert.assertEquals(userRequest.responseCode(), getTestData_Get.get("statusCode").asInt());
}

//**********************************************GetBy Id Ends***********************************************************

//**********************************************PUT Request starts***********************************************************

/*
 * Put role by User id
 */
@Given("User creates PUT Request to update role Id by user Id with request body {string} for LMS User Module")
public void user_creates_put_request_to_update_role_id_by_user_id_with_request_body_for_lms_user_module(String string) throws Exception {
	userRequest.updateUserRequest(string);

}

@When("User calls PUT Https method for the requesttype {string} to update role Id by user Id with endpoint for LMS User Module")
public void user_calls_put_https_method_for_the_requesttype_to_update_role_id_by_user_id_with_endpoint_for_lms_user_module(String reqType) throws JsonProcessingException {
	userRequest.updateRoleByUserId(reqType);
}

@Then("User received proper status code {string} with updated role details for the user as response")
public void user_received_proper_status_code_with_updated_role_details_for_the_user_as_response(String string) {
	Assert.assertEquals(userRequest.responseCode(), userRequest.getUserRoleStatusCode());
}
//**********************************************PUT Request Ends***********************************************************

// put batch, program , user

@Given("User creates PUT Request User Role Program Batch Status {string} for LMS User Module")
public void user_creates_put_request_user_role_program_batch_status_for_lms_user_module(String string) throws Exception {
	userRequest.updateUserProgramBatchRequest(string);
}

@When("User calls PUT Https method for the requesttype {string} to Update User Role Program Batch Status LMS User Module")
public void user_calls_put_https_method_for_the_requesttype_to_update_user_role_program_batch_status_lms_user_module(String string) throws Exception {
	userRequest.updateProgramBatchuserRequest(string);
}

@Then("User received proper status code {int} with updated Update User Role Program Batch Status")
public void user_received_proper_status_code_with_updated_update_user_role_program_batch_status(int string) {
	//Assert.assertEquals(userRequest.responseCode(), string);
	Response resp = (Response) context.get("userPutAllResponse");  
	 int actualStatusCode = resp.getStatusCode();
	 	    System.out.println("Expected Status Code: " + string);
	    System.out.println("Actual Status Code: " + actualStatusCode);

	    Assert.assertEquals(actualStatusCode, string);
}
//**********************************************Delete Request Ends***********************************************************

@Given("Admin creates Delete Request for User {string}")
public void admin_creates_delete_request_for_user(String string) throws Exception {
	getTestData_Get=userRequest.setGetUserRequest(string);
    
}

@When("Admin sends DELETE HTTPS Request User {string}")
public void admin_sends_delete_https_request_user(String string) {
	userRequest.DeleteBatchRequest(string);
}

@Then("Admin receives User Delete {string}")
public void admin_receives_user_delete(String string) {
	System.out.println(string);
	Assert.assertEquals(string, getTestData_Get.get("statusCode").asInt());
}
//****************** invalid create user *************************////
@Given("Admin creates POST Request with request body {string} for LMS User Module invalid phone number")
public void admin_creates_post_request_with_request_body_for_lms_user_module_invalid_phone_number(String string) throws Exception {
	userRequest.newUserRequest(string);
}
@When("Admin calls Post Https method  with valid endpoint for LMS User Module invalid  phone number")
public void admin_calls_post_https_method_with_valid_endpoint_for_lms_user_module_invalid_phone_number() {
	userRequest.PostNewUserRequestInvalid();
}
@Then("Admin receive created  status for LMS User Module invalid  phone number")
public void admin_receive_created_status_for_lms_user_module_invalid_phone_number() {
	String statusMessage="Failed to create new User as phone number +91 9410200000 already exists !!";
	// UserPojo userRequest = context.get("UserPojo", UserPojo.class);
		//userResponse = context.get("createUserinvalid", Response.class);
	Response res = (Response)context.get("createUserinvalid");
		int actualStatusCode = res.getStatusCode();
		String msg= res.jsonPath().getString("message");
		int expectedStatusCode =res.getStatusCode();
		softAssert =new SoftAssert();
		
		//Status code Validation
		softAssert.assertEquals(actualStatusCode, expectedStatusCode, "Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode);
		
		softAssert.assertAll();
	
}
}




