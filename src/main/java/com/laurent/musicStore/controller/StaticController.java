package com.laurent.musicStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaticController {

	@RequestMapping("/addTrack")
	public String addTrack(Model model) {
		return "addTrack";
	}
	
	@RequestMapping("/")
	public String index(Model model) {
		return "index";
	}
	
}
