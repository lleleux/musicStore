package com.laurent.musicStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.laurent.musicStore.model.User;
import com.laurent.musicStore.service.UserService;

@Controller
@SessionAttributes({"login"})
public class UserController {
	
	public static final int PASSWORD_MIN_LENGTH = 6;

	@Autowired
	private UserService userService;
	
	@Autowired
	private StaticController staticController;
	
	@RequestMapping(value = "/signIn", method = RequestMethod.GET)
	public String signIn(Model model) {
		return "signIn";
	}
	
	@RequestMapping(value = "/signIn", method = RequestMethod.POST)
	public String addUser(Model model, @RequestParam String name, @RequestParam String lastName, @RequestParam String email, @RequestParam String login, @RequestParam String password, @RequestParam String password2) {
		String error = "";
		// Check empty fields
		if (name.trim().isEmpty() || lastName.trim().isEmpty() || login.trim().isEmpty() || password.trim().isEmpty() || password2.trim().isEmpty()) {
			error += "\nAll fields are required !";
		}
		// Check passwords matching
		if (!password.equals(password2)) {
			error += "\nPasswords don't match !";
		}
		// Check password length
		if (password.length() < PASSWORD_MIN_LENGTH) {
			error += "\nPassword must be at least " + PASSWORD_MIN_LENGTH + " characters !";
		}
		// If error, show it
		if (!error.isEmpty()) {
			model.addAttribute("error", error);
			model.addAttribute("formName", name);
			model.addAttribute("formLastName", lastName);
			model.addAttribute("formEmail", email);
			model.addAttribute("formLogin", login);
			model.addAttribute("formPassword", password);
			model.addAttribute("formPassword2", password2);
			return "signIn";
		}
		// Add the user
		User user = this.userService.addUser(new User(name, lastName, email, login, password));
		if (user == null) {
			model.addAttribute("error", "Unable to add this user, try with another username !");
			return "signIn";
		}
		model.addAttribute("success", "You are now connected !");
		model.addAttribute("login", login);
		return this.staticController.index(model);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, @RequestParam String login, @RequestParam String password) {
		if (this.userService.login(login, password)) {
			model.addAttribute("login", login);
			model.addAttribute("success", "You are now connected !");
			return this.staticController.index(model);
		}
		model.addAttribute("formLogin", login);
		model.addAttribute("error", "Login or password is invalid !");
		return "login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		model.addAttribute("success", "You are now disconnected !");
		return this.staticController.index(model);
	}
	
}
