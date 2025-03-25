//package com.stepdefinitions;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//import org.testng.Assert;
//
//import com.APIRequest.LoginRequest;
//import com.APIRequest.UserAPIRequest_Kala;
//import com.context.ScenarioContext;
//import com.context.TextContext;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//
//public class UserStepDefinitions_Kala {
//	LoginRequest logReq;
//	UserAPIRequest_Kala userReq;
//	JsonNode TestData_Get;
//	private ScenarioContext context = ScenarioContext.getInstance();;
//	
//	public UserStepDefinitions_Kala() throws FileNotFoundException
//	{
//		logReq = new LoginRequest();
//		userReq = new UserAPIRequest_Kala();
//	}
//	
////
////@Given("User Set Authentication Token for Authorisation")
////public void user_set_authentication_token_for_authorisation() {
////	if(context.get("LMStoken")==null)
////	{
////		logReq.PostLoginRequest();
////	}
////	System.out.println("context.get(LMStoken)" +context.get("LMStoken"));
////	
////}
//
////**********************************************Get All Request start***********************************************************
///*
// * Get All Users
// */
//@Given("User creates Get Request to get user Details with request body {string} for LMS User Module")
//public void user_creates_get_request_to_get_user_details_with_request_body_for_lms_user_module(String requestType) throws Exception {
//	
//	TestData_Get=userReq.setGetUserRequest(requestType);
//}
//
//
//@When("User calls GET Https method for the requesttype {string} to get all users with endpoint for LMS User Module")
//public void user_calls_get_https_method_for_the_requesttype_to_get_all_users_with_endpoint_for_lms_user_module(String reqType) throws IOException {
//	userReq.getAllUsers(reqType);
//}
//
//@Then("User received proper status code {string} with all User Details")
//public void user_received_proper_status_code_with_all_user_details(String string) {
//	Assert.assertEquals(userReq.responseCode(), TestData_Get.get("statusCode").asInt());
//	
//}
//
///*
// * Get All Active Users
// */
//
//@When("User calls GET Https method for the requesttype {string} to get all active users with endpoint for LMS User Module")
//public void user_calls_get_https_method_for_the_requesttype_to_get_all_active_users_with_endpoint_for_lms_user_module(String reqType) {
//	userReq.getAllActiveUsers(reqType);
//}
//
//
//
//
//@Then("User received proper status code {string} with all active User Details")
//public void user_received_proper_status_code_with_all_active_user_details(String string) {
//	Assert.assertEquals(userReq.responseCode(), TestData_Get.get("statusCode").asInt());
//}
///*
// * Get All Users Email Id
// */
//
//
//@When("User calls GET Https method for the requesttype {string} to fetch email id of all users with endpoint for LMS User Module")
//public void user_calls_get_https_method_for_the_requesttype_to_fetch_email_id_of_all_users_with_endpoint_for_lms_user_module(String reqType) {
//	 userReq.getAllFetchEMailUsers(reqType);
//}
//@Then("User received proper status code {string} with all User Email Ids")
//public void user_received_proper_status_code_with_all_user_email_ids(String string) {
//	Assert.assertEquals(userReq.responseCode(), TestData_Get.get("statusCode").asInt());
//}
//
///*
// * Get All roles 
// */
//@Given("User creates Get Request to get roles details with request body {string} for LMS User Module")
//public void user_creates_get_request_to_get_roles_details_with_request_body_for_lms_user_module(String requestType) throws Exception {
//	TestData_Get=userReq.setGetUserRequest(requestType);
//}
//
//
//@When("User calls GET Https method for the requesttype {string} to fetch all roles with endpoint for LMS User Module")
//public void user_calls_get_https_method_for_the_requesttype_to_fetch_all_roles_with_endpoint_for_lms_user_module(String reqType) {
//	userReq.getAllRoles(reqType);
//}
//@Then("User received proper status code {string} with all roles")
//public void user_received_proper_status_code_with_all_roles(String string) {
//	Assert.assertEquals(userReq.responseCode(), TestData_Get.get("statusCode").asInt());
//}
//
//
///*
// * Get All users with roles
// */
//@Given("User creates Get Request to get all users with roles details with request body {string} for LMS User Module")
//public void user_creates_get_request_to_get_all_users_with_roles_details_with_request_body_for_lms_user_module(String requestType) throws Exception {
//	TestData_Get=userReq.setGetUserRequest(requestType);
//}
//
//
//@When("User calls GET Https method for the requesttype {string} to fetch all users with roles with endpoint for LMS User Module")
//public void user_calls_get_https_method_for_the_requesttype_to_fetch_all_users_with_roles_with_endpoint_for_lms_user_module(String reqType) {
//	userReq.getAllUserwithRoles(reqType);
//}
//@Then("User received proper status code {string} with all users with roles")
//public void user_received_proper_status_code_with_all_users_with_roles(String string) {
//	Assert.assertEquals(userReq.responseCode(), TestData_Get.get("statusCode").asInt());
//}
//
//
///*
// * Get status count
// */
//@Given("User creates Get Request to get users with status count with request body {string} for LMS User Module")
//public void user_creates_get_request_to_get_users_with_status_count_with_request_body_for_lms_user_module(String requestType) throws Exception {
//	TestData_Get=userReq.setGetUserRequest(requestType);
//}
//@When("User calls GET Https method for the requesttype {string} to fetch users with status count with endpoint for LMS User Module")
//public void user_calls_get_https_method_for_the_requesttype_to_fetch_users_with_status_count_with_endpoint_for_lms_user_module(String reqType) {
//	userReq.GetAllUserStatusCount(reqType);
//}
//
//@Then("User received proper status code {string} with user with status count")
//public void user_received_proper_status_code_with_user_with_status_count(String string) {
//	Assert.assertEquals(userReq.responseCode(), TestData_Get.get("statusCode").asInt());}
//
//
////**********************************************Get All Request***********************************************************
//
//
////**********************************************GetBy Id starts***********************************************************
///*
// * Get User by User id
// */
//@Given("User creates Get Request to fetch Users by UserId with request body {string} for LMS User Module")
//public void user_creates_get_request_to_fetch_users_by_user_id_with_request_body_for_lms_user_module(String requestType) throws Exception {
//	TestData_Get=userReq.setGetUserRequest(requestType);
//}
//
//@When("User calls GET Https method for the requesttype {string} to fetch Users by UserId with endpoint for LMS User Module")
//public void user_calls_get_https_method_for_the_requesttype_to_fetch_users_by_user_id_with_endpoint_for_lms_user_module(String reqType) {
//	userReq.GetUserByUserId(reqType);
//}
//
//
//
//@Then("User received proper status code {string} with Users by UserId")
//public void user_received_proper_status_code_with_users_by_user_id(String string) {
//	Assert.assertEquals(userReq.responseCode(), TestData_Get.get("statusCode").asInt());
//}
//
////**********************************************GetBy Id Ends***********************************************************
//
////**********************************************PUT Request starts***********************************************************
//
///*
// * Put role by User id
// */
//@Given("User creates PUT Request to update role Id by user Id with request body {string} for LMS User Module")
//public void user_creates_put_request_to_update_role_id_by_user_id_with_request_body_for_lms_user_module(String string) throws Exception {
//	userReq.updateUserRequest(string);
//
//}
//
//@When("User calls PUT Https method for the requesttype {string} to update role Id by user Id with endpoint for LMS User Module")
//public void user_calls_put_https_method_for_the_requesttype_to_update_role_id_by_user_id_with_endpoint_for_lms_user_module(String reqType) throws JsonProcessingException {
//	userReq.updateRoleByUserId(reqType);
//}
//
//@Then("User received proper status code {string} with updated role details for the user as response")
//public void user_received_proper_status_code_with_updated_role_details_for_the_user_as_response(String string) {
//	Assert.assertEquals(userReq.responseCode(), 200);
//}
////**********************************************PUT Request Ends***********************************************************
//
//
//}
//
