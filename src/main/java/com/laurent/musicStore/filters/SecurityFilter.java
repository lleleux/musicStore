package com.laurent.musicStore.filters;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.laurent.musicStore.service.NotificationService;

@Component
public class SecurityFilter implements HandlerInterceptor {
	
	@Autowired
	private NotificationService notificationService;
	
	private static Set<String> allowedURI;
	
	static {
		allowedURI = new HashSet<String>();
		allowedURI.add("/musicStore");
		allowedURI.add("/musicStore/signIn");
		allowedURI.add("/musicStore/tracks");
		allowedURI.add("/musicStore/login");
		allowedURI.add("/musicStore/api/tracks");
		allowedURI.add("/musicStore/pub");
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String login = (String) request.getSession().getAttribute("login");
		if (login == null) {
			// Get URI
			String uri = request.getRequestURI();
			System.out.println("Before: " + uri);
			// Remove sessionid
			int index = uri.indexOf(";jsessionid");
			if (index != -1) {
				uri = uri.substring(0, index);
			}
			// Delete last / if necessary
			if (uri.charAt(uri.length() - 1) == '/') {
				uri = uri.substring(0, uri.length() - 1);
			}
			System.out.println("After: " + uri);
			// Check if URI allowed
			if (!allowedURI.contains(uri)) {
				response.sendError(403);
				return false;
			}
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
