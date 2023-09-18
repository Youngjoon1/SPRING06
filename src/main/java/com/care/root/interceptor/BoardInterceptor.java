package com.care.root.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.care.root.common.loginSession;

public class BoardInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			
		System.out.println("연결성공");
		HttpSession session = request.getSession();
		if(session.getAttribute(loginSession.login) == null) {
			String msg = "<script>alert('로그인 먼저 하세요');";
			msg += "location.href='/root/member/login';</script>";
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(msg);
			return false;
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	
}
