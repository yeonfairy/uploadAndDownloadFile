package com.spring.board;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import com.spring.board.member.model.service.MemberService;
import com.spring.board.member.model.vo.Member;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private MemberService mService;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Object user = request.getSession().getAttribute("loginUser");
		
		if ( user == null ) {
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			if ( loginCookie != null ) {
				String sessionId = loginCookie.getValue();
				Member loginUser = mService.loginBySession(sessionId);
				
				if ( loginUser != null ) {
					request.getSession().setAttribute("loginUser", loginUser);
					request.removeAttribute("sessionid");
					return "redirect:board.do";
				}
			} 
		} else if ( user != null ) {
			return "redirect:board.do";
		}
		
		return "login";
	}
	
}