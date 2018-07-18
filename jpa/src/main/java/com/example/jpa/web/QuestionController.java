package com.example.jpa.web;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.jpa.domain.Question;
import com.example.jpa.domain.QuestionRepository;

@Controller
@RequestMapping("/questions")
public class QuestionController {

	@Autowired
	private QuestionRepository questionRepository;

	@GetMapping("/form")
	public String form(HttpSession session) {

		if (!HttpSessionUtils.isLoginUser(session)) {
			return "/users/loginForm";
		}
		return "/qna/form";
	}

	@PostMapping("")
	public String create(Question question, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "/users/loginForm";
		}

//		question.setWriter(HttpSessionUtils.getUserFromSession(session).getUserName());
//		question.setUserID(HttpSessionUtils.getUserFromSession(session).getUserID());
		question.setWriter(HttpSessionUtils.getUserFromSession(session));
		question.setCreateDate(LocalDateTime.now());
		
		System.out.println(question);
		questionRepository.save(question);

		return "redirect:/";
	}
	
	@GetMapping("/{questionNo}")
	public String show(@PathVariable Long questionNo, Model model) {
		model.addAttribute("question", questionRepository.findOne(questionNo));
		return "/qna/show";
	}

}
