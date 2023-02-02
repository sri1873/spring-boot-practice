package com.springboot.RestAPI.survey;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Question {
	private String id;
	private String description;
	private List<String> options;
	private String correctAnswer;

}
