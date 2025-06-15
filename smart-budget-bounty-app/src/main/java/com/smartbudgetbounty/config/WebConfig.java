package com.smartbudgetbounty.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);

	public WebConfig() {
		logger.info("Web config initialized");
	}
	
	// Add MVC Config (Formatters, interceptors etc. if any)
	//...
}
