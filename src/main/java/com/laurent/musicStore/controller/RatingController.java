package com.laurent.musicStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.laurent.musicStore.service.TrackService;

@Controller
@RequestMapping("/tracks/{trackId}/rating")
@SessionAttributes({"login"})
public class RatingController {
	
	@Autowired
	private TrackService trackService;
	
	@Autowired
	private TrackController trackController;
	
	@RequestMapping(value = "/{rating}", method = RequestMethod.GET)
	public String rate(Model model, @PathVariable int trackId, @PathVariable int rating) {
		String login = model.asMap().get("login").toString();
		if (this.trackService.rate(trackId, login, rating, "")) {
			model.addAttribute("success", "Rating added");
		} else {
			model.addAttribute("error", "Unable to add rating. You have maybe already rate this track...");			
		}
		return trackController.list(model);
	}
	
}
