package com.APIRequest;

import java.io.FileNotFoundException;
import java.util.Set;

import com.EnumClass.APIResources;
import com.commonUtils.SpecificationClass;
import com.commonUtils.TestDataLoader;
import com.context.ScenarioContext;
import com.context.TextContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.pojoclass.BatchPojo;
import com.pojoclass.ProgramPojo;

import io.restassured.RestAssured;

import io.restassured.response.Response;

public class ProgramRequest extends SpecificationClass {
	Response response;
	// ScenarioContext context;
	String paramForGetEndpoint;
	ScenarioContext context = ScenarioContext.getInstance();

	public ProgramRequest() throws FileNotFoundException {
		// super();
		// this.context = context;
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

	/*
	 * public void PostNewProgramRequest(String Scenario) {
	 * 
	 * String EndPoint = APIResources.valueOf("APIAddProgram").getResources(); if
	 * (Scenario.equals("CreateProgramInvalidEp")) { EndPoint = EndPoint +
	 * "Invalid"; // Append "Invalid" to regular endpoint } // if
	 * (Scenario.equals("CreateProgramInvalidEp")) // EndPoint =
	 * APIResources.valueOf("CreateProgramInvalidEp").getResources() + "Invalid";
	 * 
	 * ProgramPojo program = context.get("ProgramPojo", ProgramPojo.class);
	 * //System.out.println(program.toString()); response =
	 * RestAssured.given().spec(requestHeadersWithToken()).body(program).log().all()
	 * .post(EndPoint); context.set("programResponse", response); if
	 * (response.getStatusCode() == 201 ) { Integer programId =
	 * response.jsonPath().getInt("programId"); String programName =
	 * response.jsonPath().getString("programName"); context.set("programId",
	 * programId); context.set("programName", programName);
	 * 
	 * 
	 * // context.set("programId", response.jsonPath().get("programId")); //
	 * context.set("programName", response.jsonPath().getString("programName")); if
	 * (Scenario.equals("CreateProgramValid")) { // Setting ProgramId for chaining
	 * int storeProgramid = TextContext.getProgramId();
	 * TextContext.setProgramId(storeProgramid);
	 * System.out.println("checking chaining programid inside create program**"
	 * +storeProgramid); }} //String storeprogramname=TextContext.getProgramNmae();
	 * //context.set("programId", response.jsonPath().get("programId"));
	 * //context.set("programName", response.jsonPath().getString("programName"));
	 * //System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ " +
	 * context.get("programName"));
	 * //System.out.println("##################################################### "
	 * + context.get("programId")); }
	 */
	public void PostNewProgramRequest(String Scenario) {
		try {
			String EndPoint = APIResources.valueOf("APIAddProgram").getResources();
			if (Scenario.equals("CreateProgramInvalidEp")) {
				EndPoint = EndPoint + "Invalid";
			}

			ProgramPojo program = context.get("ProgramPojo", ProgramPojo.class);
			if (program == null) {
				throw new RuntimeException("ProgramPojo is null in context");
			}

			response = RestAssured.given().spec(requestHeadersWithToken()).body(program).log().all().post(EndPoint);

			context.set("programResponse", response);

			if (response.getStatusCode() == 201) {
				Integer programId = response.jsonPath().getInt("programId");
				String programName = response.jsonPath().getString("programName");

				context.set("programId", programId);
				context.set("programName", programName);

				// Only set in TextContext for CreateProgramValid scenario
				if (Scenario.equals("CreateProgramValid")) {
					TextContext.setProgramId(programId);
					System.out.println("Stored Program ID: " + programId);
				}
			}
		} catch (Exception e) {
			System.err.println("Error in PostNewProgramRequest: " + e.getMessage());
			throw e;
		}
	}

	public void PostNewProgramRequestInvalidMethod() {
		ProgramPojo program = context.get("ProgramPojo", ProgramPojo.class);
		System.out.println(program.toString());
		response = RestAssured.given().spec(requestHeadersWithToken()).body(program).log().all()
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

		Response response = RestAssured.given().spec(requestHeadersWithToken()).get(EndPoint);
		ProgramPojo program = new ProgramPojo();
		program.setStatusCode(response.getStatusCode());
		program.setStatusText(response.getStatusLine());
		context.set("ProgramPojo", program); // Store in context
		context.set("programResponse", response);
		return response;
	}

	/****************** GET by ProgramId Request ****************/

	public void sendGetProgrambyIdReqWithOutBody(String Scenario) {
		int programId = TextContext.getProgramId();
		String EndPoint = APIResources.valueOf("APIGetProgramByID").getResources();
		if (Scenario.equals("GetByProgramIDInValidEP")) {
			EndPoint = EndPoint + "Invalid";
		}

		response = RestAssured.given().spec(requestHeadersWithToken()).pathParam("programId", programId).log().all()
				.get(EndPoint);
		context.set("programResponse", response);
		// Store status in ProgramPojo for validation
		ProgramPojo program = new ProgramPojo();
		program.setStatusCode(response.getStatusCode());
		program.setStatusText(response.getStatusLine());
		context.set("ProgramPojo", program);
		TextContext.setProgramId(programId);

	}

	/****************** GET All Program With User ****************/

	public Response sendGetProgramReqWithUser(String Scenario) {
		String EndPoint = APIResources.valueOf("APIGetAllPrgmWithUser").getResources();
		if (Scenario.equals("GETAllProgramswithUsersProgramInValidEP"))
			EndPoint = APIResources.valueOf("APIGetAllProgram").getResources() + "Invalid";

		Response response = RestAssured.given().spec(requestHeadersWithToken()).get(EndPoint);
		ProgramPojo program = new ProgramPojo();
		program.setStatusCode(response.getStatusCode());
		program.setStatusText(response.getStatusLine());
		context.set("ProgramPojo", program); // Store in context
		context.set("programResponse", response);
		return response;
	}

	// --------------------------------------- UPDATE PROGRAM BY ID
	// -------------------------------------------
	/*
	 * public void PutProgramByIdRequest(String Scenario) { try { // Get program ID
	 * - will throw exception if not found int programId =
	 * TextContext.getProgramId();
	 * 
	 * String EndPoint = APIResources.valueOf("APIUpdateProgramByID").getResources()
	 * + programId;
	 * 
	 * if (Scenario.equals("PutProgramIDInvalidEp")) { EndPoint =
	 * APIResources.valueOf("APIUpdateProgramByID").getResources() + "Invalid" +
	 * programId; }
	 * 
	 * ProgramPojo program = context.get("ProgramPojo", ProgramPojo.class); if
	 * (program == null) { throw new
	 * IllegalStateException("ProgramPojo not found in context"); }
	 * 
	 * response =
	 * RestAssured.given().spec(requestHeadersWithToken()).body(program).log().all()
	 * .put(EndPoint);
	 * 
	 * context.set("programResponse", response);
	 * 
	 * if (response.getStatusCode() == 200 &&
	 * Scenario.equals("PutProgramValidProgramID")) { String updatedName =
	 * response.jsonPath().getString("programName");
	 * context.set("updatedProgramName", updatedName);
	 * System.out.println("Updated Program Name: " + updatedName); } } catch
	 * (Exception e) { throw new
	 * RuntimeException("Failed to execute PUT request for scenario: " + Scenario,
	 * e); } }
	 */

	
	public void PutProgramByIdRequest(String scenario) {
	    // Get program ID from TextContext
	    int programId = TextContext.getProgramId();
	    String programName = TextContext.getProgramName();
	    // Verify program exists first (skip for invalid ID scenarios
	    String endPoint = APIResources.valueOf("APIUpdateProgramByID").getResources();

	    ProgramPojo program = context.get("ProgramPojo", ProgramPojo.class);

	    if ("PutProgramIDInvalidEp".equals(scenario)) {
	        endPoint = endPoint.replace("{programId}", "999") + "Invalid";
	    } 
	    else if ("PutInvalidProgramID".equals(scenario)) {
	    	  programId = Integer.MAX_VALUE;
	    }
	    else if ("PutProgramValidProgramID".equals(scenario)) {	        // For valid updates, append reasonable suffix
	        String currentName = program.getProgramName();
	        program.setProgramName(currentName + "uzcbbbkk");
	    }
	    
	    program.setProgramId(programId);
	    if (program.getProgramDescription() == null) {
	        program.setProgramDescription("Default description");
	    }
	    if (program.getProgramStatus() == null || program.getProgramStatus().equalsIgnoreCase("inactive")) {
	        program.setProgramStatus("Active");
	    }


	
	    response = RestAssured.given()
	            .spec(requestHeadersWithToken())
	            .pathParam("programId", programId)
	            .body(program)
	            .log().all()
	            .put(endPoint);

	    System.out.println("Response Status Code:******* " + response.getStatusCode());
	   // System.out.println("Response Body: " + response.getBody().asString());

	    if (response.getStatusCode() == 200) {
	        TextContext.setProgramId(programId);
	        TextContext.setprogramName(program.getProgramName());
	        context.set("updatedProgramName", program.getProgramName());
	    }
	}

	// --------------------------------------- UPDATE PROGRAM BY NAME
	// ----------------//
	public void PutProgramByNameRequest(String scenario) {
	    String originalProgramName = TextContext.getProgramName(); // Store original name for URL
	    System.out.println("Getting PROGRAM IN PUT PROGRAMBYNAME+++" + originalProgramName);
	    
	    String endPoint = APIResources.valueOf("APIUpdateProgramByName").getResources();

	    // Get program data from context
	    ProgramPojo program = context.get("ProgramPojo", ProgramPojo.class);
	    String newProgramName = program.getProgramName(); // Initialize with current name

	    // Handle scenario-specific modifications
	    if ("PutProgramnameInvalidEp".equals(scenario)) {
	        endPoint = endPoint + "Invalid"; // Invalid endpoint
	    } 
	    else if ("PutInvalidProgramname".equals(scenario)) {
	        newProgramName = "abbb_abbbggg"; // Invalid program name
	    }
	    else if ("PutProgramValidProgramname".equals(scenario)) {
	        newProgramName = originalProgramName + "upooteoodppp"; // Modify name
	    }
	    else if ("PutProgramnameEmptyMandatory".equals(scenario)) {
	        program.setProgramStatus(""); // Empty mandatory field
	    }
	    else if ("PutProgramInactivePrgmname".equals(scenario)) {
	        program.setProgramStatus("Inactive"); // Invalid status
	    }
	    
	    // Update the program name in the payload (if changed)
	    if (!newProgramName.equals(program.getProgramName())) {
	        program.setProgramName(newProgramName);
	    }

	    // Ensure required fields are populated
	    if (program.getProgramDescription() == null) {
	        program.setProgramDescription("Default description");
	    }
	    if (program.getProgramStatus() == null || program.getProgramStatus().equalsIgnoreCase("inactive")) {
	        program.setProgramStatus("Active");
	    }

	    // Execute PUT request
	    response = RestAssured.given()
	            .spec(requestHeadersWithToken())
	            .pathParam("programName", originalProgramName) // Always use original name in URL
	            .body(program)
	            .log().all()
	            .put(endPoint);

	    System.out.println("Response Status Code:******* " + response.getStatusCode());

	    // Handle successful update
	    if (response.getStatusCode() == 200) {
	        context.set("updatedProgramName", newProgramName); // Store the new name
	    }
	}
}
