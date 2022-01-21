package com.surfersolution.notices.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.surfersolution.notices.domain.Notice;

public interface EmailService {
	
	void sendNoticeEmail(Notice obj);

	void sendEmail(SimpleMailMessage msg);
	
	void sendNoticeHtmlEmail(Notice obj);
	
	void sendHtmlEmail(MimeMessage msg);
}
