package com.context;

	
	public  class TextContext {

		public static ScenarioContext scenarioContext = new ScenarioContext();

		public String setToken(){	
			
			String retrievedToken =scenarioContext.getContext("authtoken");
			return retrievedToken;
		}
//	    public TextContext() {
//	        scenarioContext = new ScenarioContext();
//	    }
//
//	    public ScenarioContext getScenarioContext() {
//	        return scenarioContext;
//	    }
	}

