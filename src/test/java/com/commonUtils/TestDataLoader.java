package com.commonUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

public class TestDataLoader {
	static String testDataFile = ConfigReader.getProperty("testDataFilePath");

	public static <T> T loadTestDatafor_Post_Put(String requestType, Class<T> clazz) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		// Load JSON file into a tree structure
		JsonNode rootNode = objectMapper.readTree(new File(testDataFile));
		JsonNode requests = rootNode.get("requests");

		for (JsonNode requestNode : requests) {
			Iterator<Map.Entry<String, JsonNode>> fields = requestNode.fields();

			while (fields.hasNext()) {
				Map.Entry<String, JsonNode> field = fields.next();

				if (field.getKey().equalsIgnoreCase(requestType)) {
					// Deserialize into the provided POJO class
					T pojo = objectMapper.treeToValue(field.getValue(), clazz);

					// Dynamically set fields
					setFieldDynamically(pojo, "statusCode", field.getValue(), int.class);
					setFieldDynamically(pojo, "statusText", field.getValue(), String.class);
					setFieldDynamically(pojo, "message", field.getValue(), String.class);
					return pojo;
				}
			}
		}
		throw new RuntimeException("Request type not found: " + requestType);
	}

	// Dynamically sets fields of different types
	private static <T> void setFieldDynamically(T pojo, String fieldName, JsonNode node, Class<?> fieldType) {
		if (node.has(fieldName)) {
			try {
				Method method;
				if (fieldType == int.class) {
					method = pojo.getClass().getMethod("set" + capitalize(fieldName), int.class);
					method.invoke(pojo, node.get(fieldName).asInt());
				} else if (fieldType == String.class) {
					method = pojo.getClass().getMethod("set" + capitalize(fieldName), String.class);
					method.invoke(pojo, node.get(fieldName).asText());
				}
			} catch (NoSuchMethodException e) {
				// No setter method, skip
			} catch (Exception e) {
				throw new RuntimeException("Failed to set " + fieldName + " on POJO", e);
			}
		}
	}

	// Utility method to capitalize the first letter of field names
	private static String capitalize(String str) {
		if (str == null || str.isEmpty()) {
			return str;
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static JsonNode loadTestDatafor_Get(String requestType) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		// Load JSON file into a tree structure
		JsonNode rootNode = objectMapper.readTree(new File(testDataFile));
		JsonNode requests = rootNode.get("requests");

		for (JsonNode requestNode : requests) {
			Iterator<Map.Entry<String, JsonNode>> fields = requestNode.fields();

			while (fields.hasNext()) {
				Map.Entry<String, JsonNode> field = fields.next();

				if (field.getKey().equalsIgnoreCase(requestType)) {
					// For GET requests, return the response directly as a JsonNode
					return field.getValue();
				}
			}
		}
		throw new RuntimeException("Request type not found: " + requestType);
	}
}

