package com.laurent.musicStore.service;

import java.util.List;

import com.laurent.musicStore.model.Site;


public interface SitesService {
	
	List<Site> getAllSites();
	
	Site getSite(int id);
	
	Site addSite(Site site);
	
	void deleteSite(int id);
	
	List<String> getAllPubs();
	
}
