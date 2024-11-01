package com.mysite.sbbfinal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EveningGreet implements Greet {

	@GetMapping("/evening")
	@Override
	public void greeting() {
		System.out.println("편안한 밤입니다.");
	}

}