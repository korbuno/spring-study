package com.example.jpa.web;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.jpa.domain.Answer;
import com.example.jpa.domain.AnswerRepository;
import com.example.jpa.domain.QuestionRepository;

@Controller
@RequestMapping("/questions/{questionNo}/answers")
public class AnswerController {

	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;

	@PostMapping("")
	public String create(@PathVariable Long questionNo, Answer answer, HttpSession session) {

		if (!HttpSessionUtils.isLoginUser(session)) {
			return "/users/loginForm";
		}
		answer.setWriter(HttpSessionUtils.getUserFromSession(session));
		answer.setQuestion(questionRepository.findOne(questionNo));
		answer.setCreateDate(LocalDateTime.now());

		answerRepository.save(answer);

		return String.format("redirect:/questions/%d", questionNo);
	}
}
