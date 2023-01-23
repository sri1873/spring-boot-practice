package com.springboot.RestAPI.survey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class SurveyService {

	private static List<Survey> surveys = new ArrayList<>();
	static {
		Question question1 = new Question("Question1", "Most Popular Cloud Platform Today",
				Arrays.asList("AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");
		Question question2 = new Question("Question2", "Fastest Growing Cloud Platform",
				Arrays.asList("AWS", "Azure", "Google Cloud", "Oracle Cloud"), "Google Cloud");
		Question question3 = new Question("Question3", "Most Popular DevOps Tool",
				Arrays.asList("Kubernetes", "Docker", "Terraform", "Azure DevOps"), "Kubernetes");

		List<Question> questions = new ArrayList<>(Arrays.asList(question1, question2, question3));

		Survey survey = new Survey("Survey1", "My Favorite Survey", "Description of the Survey", questions);

		surveys.add(survey);
	}

	public List<Survey> retriveAllSurveys() {
		return surveys;
	}

	public Survey retriveSurveyById(String surveyId) {
		Predicate<? super Survey> predicate = survey -> survey.getId().equalsIgnoreCase(surveyId);
		Optional<Survey> survey = surveys.stream().filter(predicate).findFirst();
		if (survey.isEmpty())
			return null;
		return survey.get();
	}

	public List<Question> retriveSurveyQuestions(String surveyId) {
		Survey survey = retriveSurveyById(surveyId);
		if (survey == null)
			return null;
		return survey.getQuestions();
	}

	public Question retriveSurveyQuestionById(String surveyId, String questionId) {
		List<Question> questions = retriveSurveyQuestions(surveyId);
		if (questions == null)
			return null;
		Optional<Question> question = questions.stream().filter(q -> q.getId().equalsIgnoreCase(questionId))
				.findFirst();
		if (question.isEmpty())
			return null;
		return question.get();
	}

	public Question addNewSurveyQuestion(String surveyId, Question question) {
		List<Question> questions = retriveSurveyQuestions(surveyId);
		questions.add(question);
		return null;
	}

	public String deleteSurveyQuestionById(String surveyId, String questionId) {
		List<Question> questions = retriveSurveyQuestions(surveyId);
		if (questions == null)
			return null;
		Predicate<? super Question> predicate = q -> q.getId().equalsIgnoreCase(questionId);
		boolean removed = questions.removeIf(predicate);
		if (!removed)
			return null;
		return questionId;
	}

	public void updateSurveyQuestion(String surveyId, String questionId, Question question) {
		List<Question> questions = retriveSurveyQuestions(surveyId);
		questions.removeIf(q -> q.getId().equalsIgnoreCase(questionId));
		questions.add(question);
	}
}
