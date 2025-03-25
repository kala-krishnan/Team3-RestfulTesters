package com.APIRequest;

import java.io.FileNotFoundException;

import com.EnumClass.APIResources;
import com.commonUtils.SpecificationClass;
import com.commonUtils.TestDataLoader;
import com.context.ScenarioContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.pojoclass.BatchPojo;
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

	public String getProgramStatusLine() {
		ProgramPojo programStatusLine = (ProgramPojo) context.get("ProgramPojo");
		return programStatusLine.getStatusText();
	}

	public int getProgramId() {
		Integer id = context.get("programId", Integer.class);
		if (id != null) {
			return id;
		}
		ProgramPojo program = context.get("ProgramPojo", ProgramPojo.class);
		if (program == null) {
			throw new IllegalStateException("Neither programId nor ProgramPojo found in context");
		}
		return program.getProgramId();
	}

	/**************** POST Request ********************/

	public void setNewProgramRequest(String requestType) throws Exception {
		ProgramPojo program = TestDataLoader.loadTestDatafor_Post_Put(requestType, ProgramPojo.class);
		System.out.println("Program sttaucode" + program.getStatusCode());
		context.set("ProgramPojo", program);
	}

	public void PostNewProgramRequest(String Scenario) {

		String EndPoint = APIResources.valueOf("APIAddProgram").getResources();
		if (Scenario.equals("CreateProgramInvalidEp"))
			EndPoint = APIResources.valueOf("CreateProgramInvalidEp").getResources() + "Invalid";

		ProgramPojo program = context.get("ProgramPojo", ProgramPojo.class);
		System.out.println(program.toString());
		response = RestAssured.given().spec(requestHeadersWithTokenForJson()).body(program).log().all().post(EndPoint);
		context.set("programResponse", response);
		if (response.getStatusCode() == 201 && Scenario.equals("CreateProgramValid")) {
			LoginRequest.context.set("programId", response.jsonPath().get("programId"));
			LoginRequest.context.set("programName", response.jsonPath().getString("programName"));
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ " + context.get("programName"));
			System.out.println("##################################################### " + context.get("programId"));
		}

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

	/****************** GET ALL Request *************************/

	public JsonNode setGetProgramRequest(String requestType) throws Exception {
		JsonNode getTestData = TestDataLoader.loadTestDatafor_Get(requestType);
		return getTestData;
	}

	public Response sendGetProgramReqWithOutBody(String Scenario) {
		String EndPoint = APIResources.valueOf("APIGetAllProgram").getResources();
		if (Scenario.equals("GetAllProgramInValidEP"))
			EndPoint = APIResources.valueOf("APIGetAllProgram").getResources() + "Invalid";

		Response response = RestAssured.given().spec(requestHeadersWithTokenForJson()).get(EndPoint);
		ProgramPojo program = new ProgramPojo();
		program.setStatusCode(response.getStatusCode());
		program.setStatusText(response.getStatusLine());
		context.set("ProgramPojo", program); // Store in context
		context.set("programResponse", response);
		return response;
	}

	/****************** GET by ProgramId Request ****************/

	public void sendGetProgrambyIdReqWithOutBody(String Scenario) {
		int programId = getProgramId();
		String EndPoint = APIResources.valueOf("APIGetProgramByID").getResources();
		if (Scenario.equals("GetByProgramIDInValidEP")) {
			EndPoint = EndPoint + "Invalid";
		}

		response = RestAssured.given().spec(requestHeadersWithTokenForJson()).pathParam("programId", programId).log()
				.all().get(EndPoint);
		context.set("programResponse", response);
		// Store status in ProgramPojo for validation
		ProgramPojo program = new ProgramPojo();
		program.setStatusCode(response.getStatusCode());
		program.setStatusText(response.getStatusLine());
		context.set("ProgramPojo", program);
	}

}
