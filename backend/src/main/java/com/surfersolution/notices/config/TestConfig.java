package com.surfersolution.notices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.surfersolution.notices.services.EmailService;
import com.surfersolution.notices.services.SmtpEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}

}
