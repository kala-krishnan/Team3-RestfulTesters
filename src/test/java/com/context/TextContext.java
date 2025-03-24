package com.context;

	
	public  class TextContext {

		public static ScenarioContext scenarioContext = new ScenarioContext();

		public static String setToken(){	
			
			String retrievedToken =scenarioContext.getContext("LMStoken");
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

