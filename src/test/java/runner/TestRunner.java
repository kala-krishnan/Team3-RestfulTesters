package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



//@RunWith(Cucumber.class) 
@CucumberOptions(
		plugin = {"pretty","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm", //Allure Report
				"json:target/CucumberReports/RestAssuredJson.json","html:target/CucumberReports/RestAssured.html",}, //Cucumber Report
		monochrome=false,  //console output color
		tags="@LMS", //tags from feature file
		features = {"src/test/resources/Feature"}, //location of feature files
		glue= {"com.stepdefinitions"}) //location of step definition files

	public class TestRunner extends AbstractTestNGCucumberTests{
	/*
	 @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
        */
    
	
}

