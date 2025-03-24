package com.APIResponse;

import io.restassured.response.Response;

import org.testng.asserts.SoftAssert;

public class ProgramResponseValidation {

	private SoftAssert softAssert = new SoftAssert();

	// Validate status code
	public void validateStatusCode(Response response, int expectedStatusCode) {
		int actualStatusCode = response.getStatusCode();
		System.out.println("Expected status code: " + expectedStatusCode + ", Actual status code: " + actualStatusCode);
		softAssert.assertEquals(actualStatusCode, expectedStatusCode,
				"Status Code Mismatch: Expected - " + expectedStatusCode + ", but got - " + actualStatusCode);
	}

	// Validate response headers
	public void validateHeaders(Response response) {
		String contentType = response.getHeader("Content-Type");
		System.out.println("Content-Type: " + contentType);
		softAssert.assertTrue(contentType.contains("application/json"),
				"Content-Type is not as expected. Found: " + contentType);
	}

	// Validate response body - customize based on your API response
	public void validateResponseBody(Response response) {
		System.out.println("Response Body: " + response.asString());
		softAssert.assertTrue(response.jsonPath().getList("$").size() > 0, "Response body does not contain any data.");
	}

	// To perform all assertions at the end
	public void assertAll() {
		softAssert.assertAll();
	}
}