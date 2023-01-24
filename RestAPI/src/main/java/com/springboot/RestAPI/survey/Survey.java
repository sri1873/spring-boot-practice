package com.springboot.RestAPI.survey;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Data

public class Survey {
	private String id;
	private String title;
	private String description;
	private List<Question> questions;

}
