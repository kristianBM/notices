package com.surfersolution.notices.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.surfersolution.notices.services.EmailService;
import com.surfersolution.notices.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}

}
