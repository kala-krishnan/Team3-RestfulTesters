package com.commonUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	

	public static Properties properties; 	 
	 static {
	 		try(FileInputStream fileinputstream = new FileInputStream("src/test/resources/config.properties")){
	 			
	 			properties = new Properties();
	 			properties.load(fileinputstream);
	 		} catch (FileNotFoundException e) {
	 			LoggerLoad.error("FileNotFoundException "+ e.getMessage());
	 			e.printStackTrace();
	 			} catch (IOException e1) {
	 				LoggerLoad.error("IOException "+ e1.getMessage());
	 			e1.printStackTrace();
	 		}	 	 
	 		
	 		
	 	}
	 public static String getProperty(String key) {
			 LoggerLoad.info( key + "  Data fetched from the Config.property file" );
		 	return properties.getProperty(key);
		 }
}
