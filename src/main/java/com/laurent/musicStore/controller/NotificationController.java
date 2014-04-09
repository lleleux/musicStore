package com.laurent.musicStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.laurent.musicStore.service.NotificationService;

@Controller
@RequestMapping(value = "/notifications")
@SessionAttributes({"login"})
public class NotificationController {
	
	@Autowired
	private NotificationService notificationService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		String login = model.asMap().get("login").toString();
		model.addAttribute("notifications", this.notificationService.getUnreadFor(login));
		return "notifications";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String marAsRead(Model model, @PathVariable int id) {
		this.notificationService.markAsRead(id);
		return list(model);
	}
	
}
