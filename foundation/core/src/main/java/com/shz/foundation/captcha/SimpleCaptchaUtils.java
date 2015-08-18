package com.shz.foundation.captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import nl.captcha.Captcha;

public class SimpleCaptchaUtils {

	public static boolean rightCaptcha(HttpServletRequest request) {
		HttpSession session=request.getSession();
		Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);       
	    String answer = request.getParameter("captcha");    
	    return (captcha.isCorrect(answer)); 
	}
}
