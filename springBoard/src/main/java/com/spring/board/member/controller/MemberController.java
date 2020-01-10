package com.spring.board.member.controller;

import java.sql.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.board.member.model.service.MemberService;
import com.spring.board.member.model.vo.Member;

@Controller	
public class MemberController {
	
	@Autowired
	private MemberService mService;
	
	@RequestMapping("login.do")
	public String login(Member user, String loginC, HttpSession session, HttpServletResponse response) {
		
		Member loginUser = mService.loginUser(user);
		System.out.println("loginC : " + loginC);
		
		System.out.println(loginUser);
		
		if ( loginUser != null ) {
			session.setAttribute("loginUser", loginUser);
			
			if ( loginC != null  && loginC.equals("on") ) {
				//쿠키 생성
				Cookie cookie = new Cookie("loginCookie" , session.getId());
				cookie.setPath("/");
				cookie.setMaxAge(60*60*24*7);
				// 전송
				response.addCookie(cookie);
				
				Date sessionLimit = new Date(System.currentTimeMillis()+(1000*60*60*24*7));
				mService.keepLogin(loginUser.getUserId(), session.getId(), sessionLimit);
			}
			
			return "redirect:board.do";
		}
		return "redirect:login.do";
	}
}