package com.shz.project.domain.system.user;

import java.text.MessageFormat;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class ValidateEmailSender {

	public static final String SUBJECT="修改密码确认";
	
	public static final String TEMPLATE="请您点击以下链接跳转到修改密码页面：\n"
			+ "{0}";
	
	private JavaMailSenderImpl mailSender;
	/**应用访问地址模板，要求为MessageFormat格式，mailValidater为第一个参数*/
	private String template;
	
	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	
	public void sendMail(String address, String mailValidater) {
		SimpleMailMessage msg=new SimpleMailMessage();
		msg.setFrom(mailSender.getUsername());
		msg.setTo(address);
		msg.setSubject("修改密码确认");
		String content=MessageFormat.format(template, mailValidater);
		content=MessageFormat.format(TEMPLATE, content);
		msg.setText(content);
		mailSender.send(msg);
	}
}
