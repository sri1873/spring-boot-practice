package com.springboot.RestAPI.survey;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(controllers = SurveyResource.class)
class SurveyResourceTest {
	private static final String SPECIFIC_QUESTION_URL = "/surveys/Survey1/questions/Question1";

	@MockBean
	private SurveyService surveyService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void retriveSpecificSurveyQuestions_basicScenario() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(SPECIFIC_QUESTION_URL)
				.accept(MediaType.APPLICATION_JSON);
		Question question1 = new Question("Question1", "Most Popular Cloud Platform Today",
				Arrays.asList("AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");
		when(surveyService.retriveSurveyQuestionById("Survey1", "Question1")).thenReturn(question1);

		String expectedResponse = "{\r\n\t\t\t\t\r\n\t\t\t\t\t\"id\":\"Question1\",\r\n\t\t\t\t\t\"description\":\"Most Popular Cloud Platform Today\",\r\n\t\t\t\t\t\"options\":[\"AWS\",\"Azure\",\"Google Cloud\",\"Oracle Cloud\"],\r\n\t\t\t\t\t\"correctAnswer\":\"AWS\"\r\n\t\t\t\t\r\n\t\t\t\t}";
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(200, result.getResponse().getStatus());
		JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);

	}

}
