package com.context;

import java.util.HashMap;
import java.util.Map;

import io.cucumber.java.Scenario;

public class ScenarioContext {

	private Map<String, String> scenarioData;
	private Scenario scenario;
	
	/************************ For accessing the obj and variables globally (Chaining) ************/
	 
	// for globally accessing the same object .Made it as static
	private static ScenarioContext instance;

	// making the constructor private to prevent obj creation from other class
	private ScenarioContext() {
		scenarioData = new HashMap<String, String>();
	}

	//provide a static method to access the  instance
	public static synchronized ScenarioContext getInstance() {
		if (instance == null) {
			instance = new ScenarioContext();
		}
		return instance;
	}
	
	
/*
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
	}*/

	/******* POST and PUT Request *****************/
	private Map<String, Object> context = new HashMap<>();

	public void set(String key, Object value) {
		context.put(key, value);
	}

	public Object get(String key) {
		return context.get(key);
	}

	public <T> T get(String key, Class<T> clazz) {
		return clazz.cast(context.get(key));
	}


}
