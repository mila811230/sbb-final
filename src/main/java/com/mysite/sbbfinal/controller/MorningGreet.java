package com.mysite.sbbfinal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MorningGreet implements Greet {

	@GetMapping("/morning")
	@Override
	public void greeting() {
		System.out.println("좋은 아침입니다.");
	}

}
