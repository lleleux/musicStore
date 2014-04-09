package com.laurent.musicStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.laurent.musicStore.model.Track;
import com.laurent.musicStore.service.TrackService;

@Controller
@RequestMapping("/tracks")
public class TrackController {

	@Autowired
	private TrackService trackService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		List<Track> tracks = this.trackService.getAllTracks();
		model.addAttribute("tracks", tracks);
		return "tracks";
	}
	
	@RequestMapping(value = "/{trackId}", method = RequestMethod.GET)
	public String getTrack(Model model, @PathVariable int trackId) {
		Track track = this.trackService.getTrack(trackId);
		if (track != null) {
			model.addAttribute("track", track);
			return "track";
		}
		return null;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String addTrack(Model model, @RequestParam String title, @RequestParam String artist, @RequestParam String album, @RequestParam String price, @RequestParam String path) {
		String error = "";
		// Check empty fields
		if (title.trim().isEmpty() || artist.trim().isEmpty() || album.trim().isEmpty() || price.trim().isEmpty() || path.trim().isEmpty()) {
			error += "\nAll fields are required !";
		}
		// Check if double
		double parsedPrice = 0;
		try {
			 parsedPrice = Double.parseDouble(price.replace(',', '.'));
		} catch (Exception e) {
			error += "\nPrice must be a number";
		}
		// If error, show it
		if (!error.isEmpty()) {
			model.addAttribute("error", error);
			model.addAttribute("formTitle", title);
			model.addAttribute("formArtist", artist);
			model.addAttribute("formAlbum", album);
			model.addAttribute("formPrice", price);
			model.addAttribute("formPath", path);
			return "addTrack";
		}
		// Add the track
		this.trackService.addTrack(new Track(title, artist, album, parsedPrice, path));
		model.addAttribute("success", "Track added");
		return list(model);
	}
	
	@RequestMapping(value = "/{trackId}", method = RequestMethod.DELETE)
	public String deleteTrack(Model model) {
		return ""; // TODO
	}
	
}
