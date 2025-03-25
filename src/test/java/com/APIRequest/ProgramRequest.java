package com.APIRequest;

import java.io.FileNotFoundException;
import java.util.Set;

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

	public int getProgramId() throws IllegalStateException {
		ProgramPojo programid = (ProgramPojo) context.get("ProgramPojo");
		return programid.getProgramId();
	}

	public String getProgramName() throws IllegalStateException {
		ProgramPojo programname = (ProgramPojo) context.get("ProgramPojo");
		return programname.getProgramName();
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

	/****************** GET All Program With User ****************/

	public Response sendGetProgramReqWithUser(String Scenario) {
		String EndPoint = APIResources.valueOf("APIGetAllPrgmWithUser").getResources();
		if (Scenario.equals("GETAllProgramswithUsersProgramInValidEP"))
			EndPoint = APIResources.valueOf("APIGetAllProgram").getResources() + "Invalid";

		Response response = RestAssured.given().spec(requestHeadersWithTokenForJson()).get(EndPoint);
		ProgramPojo program = new ProgramPojo();
		program.setStatusCode(response.getStatusCode());
		program.setStatusText(response.getStatusLine());
		context.set("ProgramPojo", program); // Store in context
		context.set("programResponse", response);
		return response;
	}

	// --------------------------------------- UPDATE PROGRAM BY ID
	// -------------------------------------------
	public void PutProgramByIdRequest(String Scenario) {
		try {
			// Get program ID - will throw exception if not found
			int programId = getProgramId();

			String EndPoint = APIResources.valueOf("APIUpdateProgramByID").getResources() + programId;

			if (Scenario.equals("PutProgramIDInvalidEp")) {
				EndPoint = APIResources.valueOf("APIUpdateProgramByID").getResources() + "Invalid" + programId;
			}

			ProgramPojo program = context.get("ProgramPojo", ProgramPojo.class);
			if (program == null) {
				throw new IllegalStateException("ProgramPojo not found in context");
			}

			response = RestAssured.given().spec(requestHeadersWithToken()).body(program).log().all().put(EndPoint);

			context.set("programResponse", response);

			if (response.getStatusCode() == 200 && Scenario.equals("PutProgramValidProgramID")) {
				String updatedName = response.jsonPath().getString("programName");
				context.set("updatedProgramName", updatedName);
				System.out.println("Updated Program Name: " + updatedName);
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed to execute PUT request for scenario: " + Scenario, e);
		}
	}

	// --------------------------------------- UPDATE PROGRAM BY NAME
	// ----------------//
	public void PutProgramByNameRequest(String Scenario) {
		try {
			// Get program ID - will throw exception if not found
			String programId = getProgramName();

			String EndPoint = APIResources.valueOf("APIUpdateProgramByName").getResources() + programId;

			if (Scenario.equals("PutProgramIDInvalidEp")) {
				EndPoint = APIResources.valueOf("APIUpdateProgramByName").getResources() + "Invalid" + programId;
			}

			ProgramPojo program = context.get("ProgramPojo", ProgramPojo.class);
			if (program == null) {
				throw new IllegalStateException("ProgramPojo not found in context");
			}

			response = RestAssured.given().spec(requestHeadersWithToken()).body(program).log().all().put(EndPoint);

			context.set("programResponse", response);

			if (response.getStatusCode() == 200 && Scenario.equals("PutProgramValidProgramID")) {
				String updatedName = response.jsonPath().getString("programName");
				context.set("updatedProgramName", updatedName);
				System.out.println("Updated Program Name: " + updatedName);
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed to execute PUT request for scenario: " + Scenario, e);
		}

	}
}
