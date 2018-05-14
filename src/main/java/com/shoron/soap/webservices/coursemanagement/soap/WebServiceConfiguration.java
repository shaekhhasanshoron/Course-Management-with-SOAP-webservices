package com.shoron.soap.webservices.coursemanagement.soap;

import javax.servlet.ServletRegistration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

// 1. Enable Spring Web Services
@EnableWs
// 2. Spring Configuration
@Configuration
public class WebServiceConfiguration {
	// have to configure
	// MessageDispatcherServlet
	//for identifying endpoints
	
	// have to pass ApplicationContext
	//url -> /ws/*
	
	@Bean
	ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
		
		// map messageDispatcherServlet to url
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(context);
		messageDispatcherServlet.setTransformWsdlLocations(true);
		
		return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
	}
	
}
