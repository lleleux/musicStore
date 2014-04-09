package com.laurent.musicStore.filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.laurent.musicStore.service.NotificationService;

@Component
public class NotificationFilter implements HandlerInterceptor {
	
	@Autowired
	private NotificationService notificationService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String login = (String) request.getSession().getAttribute("login");
		if (login != null) {
			request.setAttribute("notifications", this.notificationService.getUnreadFor(login));
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
