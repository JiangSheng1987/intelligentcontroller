package com.kdyzm.gentleman.intelligentcontroller.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

@Configuration
@ComponentScan(basePackages = { "com.kdyzm" })
@Import(value = { JerseyConfig.class})
public class Config {
	private static Logger logger = LoggerFactory.getLogger(Config.class);

	@Bean
	public static PropertyPlaceholderConfigurer getPropertyPlaceHolderConfigurer() {
		PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
		Resource resource = null;
		try {
			resource = new InputStreamResource(new FileInputStream(new File("config/init.properties")));
		} catch (FileNotFoundException e) {
			logger.error("failed to found properties file {}", "config/init.properties");
			System.exit(0);
		}
		configurer.setLocation(resource);
		return configurer;
	}
}
