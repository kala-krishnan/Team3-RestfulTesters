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
					setFieldDynamically(pojo, "id", field.getValue(), String.class);
					setFieldDynamically(pojo, "name", field.getValue(), String.class);

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



/*package com.commonUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojoclass.LoginPojo;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import com.fasterxml.jackson.databind.JsonNode;
import java.lang.reflect.Method;

public class TestDataLoader {
    static String testDataFile = ConfigReader.getProperty("testDataFilePath");

    public static <T> T loadTestData(String requestType, Class<T> clazz) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        // Load the entire JSON file into a JsonNode
        JsonNode rootNode = objectMapper.readTree(new File(testDataFile));

        JsonNode requests = rootNode.get("requests");

        for (JsonNode requestNode : requests) {
            Iterator<Map.Entry<String, JsonNode>> fields = requestNode.fields();

            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();

                if (field.getKey().equalsIgnoreCase(requestType)) {
                    // Deserialize into the provided POJO class
                    T pojo = objectMapper.treeToValue(field.getValue(), clazz);

                    // Dynamically set the statusCode if present
                    if (field.getValue().has("statusCode")) {
                        setStatusCodeDynamically(pojo, field.getValue().get("statusCode").asInt());
                    }

                    return pojo;
                }
            }
        }
        throw new RuntimeException("Request type not found: " + requestType);
    }

    // Dynamically sets the statusCode if the POJO has a setStatusCode method
    private static <T> void setStatusCodeDynamically(T pojo, int statusCode) {
        try {
            Method setStatusCodeMethod = pojo.getClass().getMethod("setStatusCode", int.class);
            if (setStatusCodeMethod != null) {
                setStatusCodeMethod.invoke(pojo, statusCode);
            }
        } catch (NoSuchMethodException e) {
            // No statusCode setter method, skip
        } catch (Exception e) {
            throw new RuntimeException("Failed to set statusCode on POJO", e);
        }
    }
}


/*
public class TestDataLoader {
	static String testDataFile = ConfigReader.getProperty("testDataFilePath");

    // Generic method to load test data and return the appropriate POJO
    public static <T> T loadTestData(String requestType, Class<T> clazz) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        // Load the entire JSON file into a JsonNode
        JsonNode rootNode = objectMapper.readTree(new File(testDataFile));

        // Iterate over the requests array
        JsonNode requests = rootNode.get("requests");

        for (JsonNode requestNode : requests) {
            Iterator<Map.Entry<String, JsonNode>> fields = requestNode.fields();

            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();

                // Match the request type
                if (field.getKey().equalsIgnoreCase(requestType)) {
                    // Deserialize the matching request into the specified POJO class
                    return objectMapper.treeToValue(field.getValue(), clazz);
                }
            }
        }
        throw new RuntimeException("Request type not found: " + requestType);
    }
}
 */