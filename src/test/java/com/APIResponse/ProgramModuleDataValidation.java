package com.APIResponse;

import org.testng.asserts.SoftAssert;
import com.context.ScenarioContext;
import com.pojoclass.ProgramPojo;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ProgramModuleDataValidation {

	ScenarioContext context = ScenarioContext.getInstance();
	SoftAssert softAssert = new SoftAssert();

	public void DataValidation(Response response) {
		JsonPath jsonPath = response.jsonPath();
		ProgramPojo program = context.get("ProgramPojo", ProgramPojo.class);

		if (program == null) {
			System.out.println("ProgramPojo not found in ScenarioContext");
			return;
		}

		// Comparing Request Data with Response Data
		softAssert.assertEquals(program.getProgramName(), jsonPath.getString("ProgramName"));
		softAssert.assertEquals(program.getProgramDescription(), jsonPath.getString("ProgramDescription"));
		softAssert.assertEquals(program.getProgramStatus(), jsonPath.getString("ProgramStatus"));

	}



}
