package com.springboot.RestAPI.survey;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor

@Data

public class Survey {
	private String id;
	private String title;
	private String description;
	private List<Question> questions;

}
