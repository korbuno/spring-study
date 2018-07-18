package com.example.jpa.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.jpa.domain.User;
import com.example.jpa.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping("")
	public String create(User user) {
		System.out.println(user);
		userRepository.save(user);
		return "redirect:/users";
	}

	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}

	@GetMapping("/form")
	public String form() {
		return "/user/form";
	}

	@GetMapping("/{userNo}/form")
	public String updateForm(@PathVariable Long userNo, Model model, HttpSession session) {

		if (HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginForm";
		}

		if (HttpSessionUtils.getUserFromSession(session).matchUserNo(userNo)) {
			throw new IllegalStateException("incorrect information error");
		}

		model.addAttribute("user", userRepository.findOne(userNo));
		return "/user/updateForm";
	}

	@PutMapping("/{userNo}")
	public String update(@PathVariable Long userNo, User updatedUser, HttpSession session) {

		if (HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginForm";
		}

		if (HttpSessionUtils.getUserFromSession(session).matchUserNo(userNo)) {
			throw new IllegalStateException("incorrect information error");
		}

		User user = userRepository.findOne(userNo);
		user.update(updatedUser);

		// id값이 기존에 있으면 update를 수행한다.
		userRepository.save(user);
		return "redirect:/users";
	}

	@GetMapping("/loginForm")
	public String loginForm() {
		return "/user/login";
	}

	@PostMapping("/login")
	public String login(String userID, String userPass, HttpSession session) {

		User user = userRepository.findByUserID(userID);
		if (user == null) {
			System.out.println("Login Fail!");
			return "redirect:/users/loginForm";
		}

		if (user.matchPassword(userPass)) {
			System.out.println("Login Fail!");
			return "redirect:/users/loginForm";
		}

		System.out.println("Login Success!");
		session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);

		return "redirect:/";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("sessionedUser");

		return "redirect:/";
	}

}
