package com.springboot.RestAPI.survey;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class SurveyResource {

	private SurveyService surveyService;

	public SurveyResource(SurveyService surveyService) {
		super();
		this.surveyService = surveyService;
	}

	@RequestMapping("/surveys")
	public List<Survey> retriveAllSurveys() {
		return surveyService.retriveAllSurveys();
	}

	@RequestMapping("/surveys/{SurveyId}")
	public Survey retriveSurveyById(@PathVariable String SurveyId) {
		Survey survey = surveyService.retriveSurveyById(SurveyId);
		if (survey == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return survey;
	}

	@RequestMapping("/surveys/{SurveyId}/questions")
	public List<Question> retriveAllSurveyQuestions(@PathVariable String SurveyId) {
		List<Question> questions = surveyService.retriveSurveyQuestions(SurveyId);
		if (questions == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return questions;
	}

	@RequestMapping("/surveys/{SurveyId}/questions/{QuestionId}")
	public Question retriveAllSurveyQuestions(@PathVariable String SurveyId, @PathVariable String QuestionId) {
		Question question = surveyService.retriveSurveyQuestionById(SurveyId, QuestionId);
		if (question == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return question;
	}

	@RequestMapping(value = "/surveys/{SurveyId}/questions", method = RequestMethod.POST)
	public void AddNewSurveyQuestion(@PathVariable String SurveyId, @RequestBody Question Question) {
		Question question = surveyService.addNewSurveyQuestion(SurveyId, Question);
	}

	@RequestMapping(value = "/surveys/{SurveyId}/questions/{questionId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> DeleteSurveyQuestion(@PathVariable String SurveyId, @PathVariable String questionId) {
		String question = surveyService.deleteSurveyQuestionById(SurveyId, questionId);
		if (question == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/surveys/{SurveyId}/questions/{questionId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> UpdateSurveyQuestion(@PathVariable String SurveyId, @PathVariable String questionId,
			@RequestBody Question Question) {
		surveyService.updateSurveyQuestion(SurveyId, questionId, Question);
		return ResponseEntity.noContent().build();

	}
}
