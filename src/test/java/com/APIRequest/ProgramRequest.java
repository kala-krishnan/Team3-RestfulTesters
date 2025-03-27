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
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProgramRequest extends SpecificationClass {
	Response response;
	String paramForGetEndpoint;
	ScenarioContext context = ScenarioContext.getInstance();

	public ProgramRequest() throws FileNotFoundException {

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
		context.set("ProgramPojo", program);
	}

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

			if (response.getStatusCode() == 201 && Scenario.equals("CreateProgramValid")) {
				Integer programId = response.jsonPath().getInt("programId");
				String programName = response.jsonPath().getString("programName");

				// Only set in TextContext for CreateProgramValid scenario
					TextContext.setProgramId(programId);
					TextContext.setprogramName(programName);
				
			}
		} catch (Exception e) {
			System.err.println("Error in PostNewProgramRequest: " + e.getMessage());
			throw e;
		}
	}

	public void PostNewProgramRequestInvalidMethod() {
		ProgramPojo program = context.get("ProgramPojo", ProgramPojo.class);

		response = RestAssured.given().spec(requestHeadersWithToken()).body(program).log().all()
				.get(APIResources.valueOf("APIAddProgram").getResources());
		context.set("programResponse", response);


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

public void PutProgramByIdRequest(String scenario) {
		int programId = TextContext.getProgramId();
		String programName = TextContext.getProgramName();
		String endPoint = APIResources.valueOf("APIUpdateProgramByID").getResources();

		ProgramPojo program = context.get("ProgramPojo", ProgramPojo.class);
		   if ("PutProgramIDInvalidEp".equals(scenario)) {
		        programId = 999;
		        endPoint = endPoint + "/Invalid"; // Append to endpoint instead of replacing
		    
		} else if ("PutInvalidProgramID".equals(scenario)) {

			programId = Integer.MAX_VALUE;
			System.out.println("Integer.MAX_VALUE+++++=" + programId);
		} else if ("PutProgramIDEmptyMandatory".equals(scenario)) { // For valid updates, append reasonable suffix
			program.setProgramStatus(""); // Clear the mandatory field
		} else if ("PutProgramValidProgramID".equals(scenario)) { // For valid updates, append reasonable suffix
			 program.setProgramName(program.getProgramName() );		}

		program.setProgramId(programId);
		if (program.getProgramDescription() == null) {
			program.setProgramDescription("Default description");
		}
		if (program.getProgramStatus() == null || program.getProgramStatus().equalsIgnoreCase("inactive")) {
			program.setProgramStatus("Active");
		}

		response = RestAssured.given().spec(requestHeadersWithToken()).pathParam("programId", programId).body(program)
				.log().all().put(endPoint);
		context.set("programResponse", response);

		if (response.getStatusCode() == 201) {

			TextContext.setprogramName(program.getProgramName());
			
			context.set("updatedProgramName", program.getProgramName());
		}
	}
	

	// --------------------------------------- UPDATE PROGRAM BY NAME
	public void PutProgramByNameRequest(String scenario) {
		String originalProgramName = TextContext.getProgramName(); // Store original name for URL
		String endPoint = APIResources.valueOf("APIUpdateProgramByName").getResources();

		ProgramPojo program = context.get("ProgramPojo", ProgramPojo.class);
		String newProgramName = program.getProgramName(); // Initialize with current name

		if ("PutProgramnameInvalidEp".equals(scenario)) {
			endPoint = endPoint + "Invalid";
		} else if ("PutInvalidProgramname".equals(scenario)) {
			newProgramName = "abbb_abbbggg";
		} else if ("PutProgramValidProgramname".equals(scenario)) {
			newProgramName = originalProgramName + "upooteoodppp";
		} else if ("PutProgramnameEmptyMandatory".equals(scenario)) {
			program.setProgramStatus("");
		} else if ("PutProgramInactivePrgmname".equals(scenario)) {
			program.setProgramStatus("Inactive");
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
		
		response = RestAssured.given().spec(requestHeadersWithToken()).pathParam("programName", originalProgramName)

				.body(program).log().all().put(endPoint);
		context.set("programResponse", response);


		// Handle successful update
		if (response.getStatusCode() == 200) {
			context.set("updatedProgramName", newProgramName); // Store the new name
		}
	}
	
	public void NoAuthPutProgramByNameRequest(String scenario) {
		String originalProgramName = TextContext.getProgramName(); // Store original name for URL
		ProgramPojo program = context.get("ProgramPojo", ProgramPojo.class);
		response = RestAssured.given().spec(requestHeadersWithoutToken()).pathParam("programName", originalProgramName)
				.body(program).log().all().put(APIResources.valueOf("APIUpdateProgramByName").getResources());
		context.set("programResponse", response);
	
	}

	
	// --------------------------------------- DELETE PROGRAM ID

	public void DeleteProgramIdhRequest(String Scenario) {
		int  programId = TextContext.getProgramId();

		String EndPoint = APIResources.valueOf("APIDeleteProgramByID").getResources();
		if (Scenario.equals("DeleteProgramIdInvalidEP"))
			EndPoint = APIResources.valueOf("APIDeleteProgramByID").getResources() + "Invalid";

		ProgramPojo program = context.get("ProgramPojo", ProgramPojo.class);
		response = RestAssured.given().spec(requestHeadersWithToken()).accept(ContentType.JSON). 
				log().all().
				pathParam("programId", programId)
				.delete(EndPoint);
		context.set("programResponse", response);
	}
	
	public void NoAuthDeleteProgramRequest(String Scenario) {
		int  programid = TextContext.getProgramId();

		ProgramPojo program = context.get("ProgramPojo", ProgramPojo.class);
		response = RestAssured.given().spec(requestHeadersWithoutToken()).log().all().
				pathParam("programid", programid).delete(APIResources.valueOf("APIDeleteProgramByID").getResources());
		context.set("programResponse", response);
	}
	//-----------NO AUTH------------------------------------------------
	
	public void NoAuthCreateProgram()
	{
		ProgramPojo program = context.get("ProgramPojo", ProgramPojo.class);
		response = RestAssured.given().spec(requestHeadersWithoutToken())
				.body(program).log().all()
				.post(APIResources.valueOf("APIAddProgram").getResources());       
		context.set("programResponse", response); 	
	}
	
	public void NoAuthGetAllProgram()
	{
		response = RestAssured.given().spec(requestHeadersWithoutToken())
				.log().all()
				.get(APIResources.valueOf("APIGetAllProgram").getResources());       
		context.set("programResponse", response); 
	}
	
	public void NoAuthGetProgramById()
	{
		int programId = TextContext.getProgramId();	
		response = RestAssured.given().spec(requestHeadersWithoutToken())
				.log().all()
				.pathParam("programId", programId)
				.get(APIResources.valueOf("APIGetProgramByID").getResources());       
		context.set("programResponse", response); 
	}
	public void NoAuthPUTProgramID()
	{
		int programId = TextContext.getProgramId();
		ProgramPojo program = context.get("ProgramPojo", ProgramPojo.class);
		response = RestAssured.given().spec(requestHeadersWithoutToken())
				.body(program).log().all()
				.pathParam("programId", programId)
				.put(APIResources.valueOf("APIUpdateProgramByID").getResources());       
		context.set("programResponse", response); 
	}
	public void NoAuthDeleteProgrambyId()
	{
		int programId = TextContext.getProgramId();
		response = RestAssured.given().spec(requestHeadersWithoutToken())
				.log().all()
				.pathParam("programId", programId)
				.delete(APIResources.valueOf("APIDeleteProgramByID").getResources());       
		context.set("programResponse", response); 
	}
	
	
}
