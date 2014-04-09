package com.laurent.musicStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.laurent.musicStore.api.wrappers.Tracks;
import com.laurent.musicStore.model.Site;
import com.laurent.musicStore.service.SitesService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Controller
public class SitesController {
	
	@Autowired
	private SitesService sitesService;
	
	@RequestMapping(value = "/pub", method = RequestMethod.GET)
	public String showPub(Model model) {
		return "pub";
	}
	
	@RequestMapping(value = "/sites", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("pubs", this.sitesService.getAllPubs());
		model.addAttribute("sites", this.sitesService.getAllSites());
		return "sites";
	}
	
	@RequestMapping(value = "/sites", method = RequestMethod.POST)
	public String addPub(Model model, @RequestParam String name, @RequestParam String pub, @RequestParam String api) {
		this.sitesService.addSite(new Site(name, pub, api));
		return list(model);
	}
	
	@RequestMapping(value = "/sites/delete", method = RequestMethod.POST)
	public String deletePub(Model model, @RequestParam int siteId) {
		this.sitesService.deleteSite(siteId);
		return list(model);
	}
	
	@RequestMapping(value = "/sites/{siteId}/tracks", method = RequestMethod.GET)
	public String getTracks(Model model, @PathVariable int siteId) {
		Site site = this.sitesService.getSite(siteId);
		
		Client client = Client.create();
		WebResource webResource = client.resource(site.getApi() + "/tracks");
		try {
			ClientResponse response = webResource.accept("application/xml").get(ClientResponse.class);
			if (response.getStatus() == 200) {
				Tracks tracks = response.getEntity(Tracks.class);
				model.addAttribute("tracks", tracks.getTracks());
			} else {
				model.addAttribute("error", "Failed to load the site library\n" + response.getStatus() + " - " + response.getStatusInfo());
			}
		} catch(Exception e) {
			model.addAttribute("error", "Failed to load the site library - The API URL is not valid, or the response cannot be parsed");
		}        
        model.addAttribute("site", site);
		return "tracksOfSite";
	}
	
}
