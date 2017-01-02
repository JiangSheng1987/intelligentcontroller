package com.kdyzm.gentleman.intelligentcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

import com.kdyzm.gentleman.intelligentcontroller.config.Config;

/**
 *
 */
@EnableAutoConfiguration
@Import(value = { Config.class })
public class Driver {
	private static Logger logger = LoggerFactory.getLogger(Driver.class);

	static {
		System.setProperty("java.awt.headless", "false");
	}

	public static void main(String[] args) {
		SpringApplication.run(Driver.class);
		logger.info("system init successful !");
	}

}
