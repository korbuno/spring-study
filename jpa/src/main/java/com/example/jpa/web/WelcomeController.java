package com.example.jpa.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

	@GetMapping("/helloworld")
	public String welcome(String name, boolean flag, Model model) {
		model.addAttribute("name", name);
		model.addAttribute("flag", flag);
		return "welcome";
	}
}
