package com.springboot.RestAPI.survey;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SurveyResourrceIT {

	@Autowired
	private TestRestTemplate template;
	private static String QUESTION_URL = "/surveys/Survey1/questions";
	private static final String SPECIFIC_QUESTION_URL = "/surveys/Survey1/questions/Question1";

	String str = "{\"id\":\"Question1\",\"description\":\"Most Popular Cloud Platform Today\",\"options\":[\"AWS\",\"Azure\",\"Google Cloud\",\"Oracle Cloud\"],\"correctAnswer\":\"AWS\"}";

	@Test
	void retirveSpecificSurveyQuestion_basicScenario() throws JSONException {
		ResponseEntity<String> response = template.getForEntity(SPECIFIC_QUESTION_URL, String.class);
		assertTrue(response.getStatusCode().is2xxSuccessful());
		assertEquals("application/json", response.getHeaders().get("Content-Type").get(0));
		JSONAssert.assertEquals(str, response.getBody(), true);
	}

	@Test
	void retirveSurveyQuestions_basicScenario() throws JSONException {
		ResponseEntity<String> response = template.getForEntity(QUESTION_URL, String.class);
		String expectedResponse = "[{\"id\":\"Question1\"},{\"id\":\"Question2\"},{\"id\":\"Question3\"}]";
		assertTrue(response.getStatusCode().is2xxSuccessful());
		assertEquals("application/json", response.getHeaders().get("Content-Type").get(0));
		JSONAssert.assertEquals(expectedResponse, response.getBody(), false);
	}

	@Test
	void addNewSurveyQuestion_basicScenario() {
		String requestBody = "{\r\n" + "				  \"description\": \"Your Favorite Language\",\r\n"
				+ "				  \"options\": [\r\n" + "				    \"Java\",\r\n"
				+ "				    \"Python\",\r\n" + "				    \"JavaScript\",\r\n"
				+ "				    \"Haskell\"\r\n" + "				  ],\r\n"
				+ "				  \"correctAnswer\": \"Java\"\r\n" + "				}";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		HttpEntity httpEntity = new HttpEntity<String>(requestBody, headers);
		ResponseEntity<String> response = template.exchange(QUESTION_URL, HttpMethod.POST, httpEntity, String.class);
		assertTrue(response.getStatusCode().is2xxSuccessful());
		template.delete("/surveys/Survey1/questions");
	}

}
