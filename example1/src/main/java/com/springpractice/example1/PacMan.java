package com.springpractice.example1;

import org.springframework.stereotype.Component;

//@Component
public class PacMan implements Console {

	public void up() {
		System.out.println("Pacman-Up");
	}

	public void down() {
		System.out.println("Pacman-down");
	}

	public void left() {
		System.out.println("Pacman-left");
	}

	public void right() {
		System.out.println("Pacman-right");
	}

}
