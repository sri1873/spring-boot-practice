package com.springpractice.example1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Example1Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Example1Application.class, args);
		//MarioGame game = new MarioGame();
		//		PacMan game = new PacMan();
		GameRunner runner = context.getBean(GameRunner.class);
		runner.run();
	}

}
