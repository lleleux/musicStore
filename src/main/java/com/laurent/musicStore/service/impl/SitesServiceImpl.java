package com.laurent.musicStore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laurent.musicStore.dao.SiteDao;
import com.laurent.musicStore.model.Site;
import com.laurent.musicStore.service.SitesService;

@Service
public class SitesServiceImpl implements SitesService {
	
	@Autowired
	private SiteDao siteDao;

	@Override
	public List<Site> getAllSites() {
		return this.siteDao.list();
	}

	@Override
	public Site getSite(int id) {
		return this.siteDao.getById(id);
	}

	@Override
	public Site addSite(Site pub) {
		return this.siteDao.persist(pub);
	}

	@Override
	public void deleteSite(int id) {
		this.siteDao.delete(id);
	}

	@Override
	public List<String> getAllPubs() {
		List<String> pubs = new ArrayList<String>();
		for (Site s : this.siteDao.list()) {
			pubs.add(s.getPub());
		}
		return pubs;
	}
	
}
