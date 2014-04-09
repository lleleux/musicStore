package com.laurent.musicStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.laurent.musicStore.api.wrappers.Tracks;
import com.laurent.musicStore.model.Track;
import com.laurent.musicStore.service.TrackService;

@Controller
@RequestMapping("/api")
public class APIController {
	
	@Autowired
	private TrackService trackService;

	@RequestMapping(value = "/tracks", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody Tracks list(Model model) {
		return new Tracks(this.trackService.getAllTracks());
	}
	
	@RequestMapping(value = "/tracks/{trackId}/rating/{rating}", method = RequestMethod.GET)
	public @ResponseBody Track rate(Model model, @PathVariable int trackId, @PathVariable int rating, @RequestParam String login, @RequestParam String comment) {
		this.trackService.rate(trackId, login, rating, comment);
		return this.trackService.getTrack(trackId);
	}

}
