package com.surfersolution.notices.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.surfersolution.notices.domain.Notice;

@Service
public interface EmailService {
	
	void sendNoticeEmail(Notice obj);

	void sendEmail(SimpleMailMessage msg);
	
	void sendNoticeHtmlEmail(Notice obj);
	
	void sendHtmlEmail(MimeMessage msg);
}
