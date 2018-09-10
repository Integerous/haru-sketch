package com.harusketch.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	@GetMapping("/helloworld")
	public String welcome(String email, int age, Model model) {
		System.out.println("email : "+ email);
		System.out.println("age : "+age);
		model.addAttribute("email", email);
		model.addAttribute("age", age);
		return "welcome";
	}
}
