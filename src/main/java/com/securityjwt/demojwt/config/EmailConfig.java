package com.securityjwt.demojwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {
    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("localhost");
        javaMailSender.setPort(1025); // Set your SMTP port
        javaMailSender.setUsername("hello"); // Set your email address
        javaMailSender.setPassword("hello"); // Set your email password
    return javaMailSender;
    }
}
