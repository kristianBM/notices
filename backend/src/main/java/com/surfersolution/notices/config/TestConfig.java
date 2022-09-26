package com.surfersolution.notices.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.surfersolution.notices.services.DBService;
import com.surfersolution.notices.services.EmailService;
import com.surfersolution.notices.services.SmtpEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService db;
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		db.instantiateDb();
		
		return true;
	}
	
}
