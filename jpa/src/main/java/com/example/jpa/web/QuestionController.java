package com.example.jpa.web;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.jpa.domain.Question;
import com.example.jpa.domain.QuestionRepository;
import com.example.jpa.domain.User;

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

		// question.setWriter(HttpSessionUtils.getUserFromSession(session).getUserName());
		// question.setUserID(HttpSessionUtils.getUserFromSession(session).getUserID());
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

	@GetMapping("/{questionNo}/form")
	public String updateForm(@PathVariable Long questionNo, Model model, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "/users/loginForm";
		}
		
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		Question question = questionRepository.findOne(questionNo);
		if(!question.isSameWriter(loginUser)) {
			return "/users/loginForm";
		}
		
		model.addAttribute("question", question);
		return "/qna/updateForm";
	}

	@PutMapping("/{questionNo}")
	public String update(@PathVariable Long questionNo, Question updateQuestion, HttpSession session) {
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		Question oldQuestion = questionRepository.findOne(questionNo);
		if(!oldQuestion.isSameWriter(loginUser)) {
			return "/users/loginForm";
		}
		
		questionRepository.save(questionRepository.findOne(questionNo).update(updateQuestion));
		return String.format("redirect:/questions/%d", questionNo);
	}

	@DeleteMapping("/{questionNo}")
	public String delete(@PathVariable Long questionNo, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "/users/loginForm";
		}
		
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		Question question = questionRepository.findOne(questionNo);
		if(!question.isSameWriter(loginUser)) {
			return "/users/loginForm";
		}
		
		
		questionRepository.delete(questionNo);
		return "redirect:/";
	}
}
