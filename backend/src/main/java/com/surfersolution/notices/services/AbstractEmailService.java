package com.surfersolution.notices.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.surfersolution.notices.domain.Notice;

@Service
public abstract class AbstractEmailService implements EmailService {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractEmailService.class);
	
	@Value("${default.sender}")
	private String sender;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendNoticeEmail(Notice obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromNotice(obj);
		sendEmail(sm);
	}
	
	public AbstractEmailService() {
		
	}
	
	protected SimpleMailMessage prepareSimpleMailMessageFromNotice(Notice obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
			
			sm.setTo(obj.getAuthor().getEmail());
			sm.setFrom(sender);
			sm.setSubject("Notice successfully uploaded:: " + obj.getTitle());
			sm.setText(obj.getNotice());
			
		return sm;
	}

	protected String htmlFromTemplateNotice(Notice obj) {
		Context context = new Context();
		context.setVariable("notice", obj);
		return templateEngine.process("email/noticeEmail", context);
	}

	@Override
	public void sendNoticeHtmlEmail(Notice obj) {
		try {
			LOG.info("--------------Sending email--------------");
			MimeMessage mm = prepareMimeMessageFromNotice(obj);
			sendHtmlEmail(mm);
			LOG.info("--------------Email sent--------------");
		} catch (MessagingException e) {
			sendNoticeEmail(obj);
		}

	}

	private MimeMessage prepareMimeMessageFromNotice(Notice obj) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		
			mmh.setTo(obj.getAuthor().getEmail());
			mmh.setFrom(sender);
			mmh.setSubject("Notice successfully uploaded: " + obj.getTitle());
			mmh.setText(htmlFromTemplateNotice(obj), true);
			
		return mimeMessage;
	}

}
