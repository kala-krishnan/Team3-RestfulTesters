package runner;


import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features= {"src/test/resources/Feature"},
//tags = "@userModule or @userlogin",
tags = "@userModule",
glue={"com.stepdefinitions"},
plugin = {
        "pretty",
        "html:target/cucumber-reports.html",
        "json:target/cucumber.json"
    })
public class TestRunner extends AbstractTestNGCucumberTests{
//	 @Override
//	    @DataProvider(parallel = false)
//	    public Object[][] scenarios() {
//	        return super.scenarios();
//	    }
}
