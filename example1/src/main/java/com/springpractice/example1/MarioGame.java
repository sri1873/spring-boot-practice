package com.springpractice.example1;

import org.springframework.stereotype.Component;

@Component
public class MarioGame implements Console {

	public void up() {
		System.out.println("Up");
	}

	public void down() {
		System.out.println("down");
	}

	public void left() {
		System.out.println("left");
	}

	public void right() {
		System.out.println("right");
	}

}
