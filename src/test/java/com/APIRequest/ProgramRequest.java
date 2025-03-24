package com.APIRequest;

import java.io.FileNotFoundException;

import com.EnumClass.APIResources;
import com.commonUtils.SpecificationClass;
import com.commonUtils.TestDataLoader;
import com.context.ScenarioContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.pojoclass.ProgramPojo;

import io.restassured.RestAssured;

import io.restassured.response.Response;

public class ProgramRequest extends SpecificationClass {
	Response response;
	ScenarioContext context;
	String paramForGetEndpoint;

	public ProgramRequest(ScenarioContext context) throws FileNotFoundException {
		super(context);
		this.context = context;
	}

	public int getProgramStatusCode() {
		ProgramPojo programStatusCode = (ProgramPojo) context.get("ProgramPojo");
		return programStatusCode.getStatusCode();
	}

	/**************** POST Request ********************/

	public void setNewProgramRequest(String requestType) throws Exception {
		ProgramPojo program = TestDataLoader.loadTestDatafor_Post_Put(requestType, ProgramPojo.class);
		System.out.println("Program sttaucode" + program.getStatusCode());
		context.set("ProgramPojo", program);
	}

	public void PostNewProgramRequest() {
		ProgramPojo program = context.get("ProgramPojo", ProgramPojo.class);
		System.out.println(program.toString());
		response = RestAssured.given().spec(requestHeadersWithTokenForJson()).body(program).log().all()
				.post(APIResources.valueOf("APIAddProgram").getResources());
		context.set("programResponse", response);
		System.out.println(response.prettyPrint());
		System.out.println("Status Code: " + response.getStatusCode());
		System.out.println("Response Headers: " + response.getHeaders());
		System.out.println("Response Body: " + response.getBody().asString());

	}

	public void PostNewProgramRequestInvalidMethod() {
		ProgramPojo program = context.get("ProgramPojo", ProgramPojo.class);
		System.out.println(program.toString());
		response = RestAssured.given().spec(requestHeadersWithTokenForJson()).body(program).log().all()
				.get(APIResources.valueOf("APIAddProgram").getResources());
		context.set("programResponse", response);
		System.out.println(response.prettyPrint());
		System.out.println("Status Code: " + response.getStatusCode());
		System.out.println("Response Headers: " + response.getHeaders());
		System.out.println("Response Body: " + response.getBody().asString());

	}

	/****************** GET without parameter Request *************************/

	public JsonNode setGetProgramRequest(String requestType) throws Exception {
		JsonNode getTestData = TestDataLoader.loadTestDatafor_Get(requestType);
		return getTestData;
	}

	public Response sendGetProgramReqWithOutBody() {
		Response response = RestAssured.given().spec(requestHeadersWithTokenForJson())
				.get(APIResources.valueOf("APIGetAllProgram").getResources());
		return response;
	}

}
