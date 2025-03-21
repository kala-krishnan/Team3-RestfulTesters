package com.context;

import java.util.HashMap;
import java.util.Map;

import io.cucumber.java.Scenario;

public class ScenarioContext {
	
	private Map<String, String> scenarioData;
	private Scenario scenario;

	    public ScenarioContext() {
	        scenarioData = new HashMap<String, String>();
	    }

	    public String setContext(String key, String value) {
	        scenarioData.put(key, value);
			return value;
	    }

	    public String getContext(String key, String token) {
	         return scenarioData.get(key);
	    }

	    public void setScenario(Scenario scenario) {
	        this.scenario = scenario;
	    }

		public String getContext(String string) {
			return scenarioData.get(string);
		}

//	private static String bearerToken;
//
//    public static String getBearerToken() {
//        return bearerToken;
//    }
//
//    public static void setBearerToken(String token) {
//        bearerToken = token;
//    }


}
