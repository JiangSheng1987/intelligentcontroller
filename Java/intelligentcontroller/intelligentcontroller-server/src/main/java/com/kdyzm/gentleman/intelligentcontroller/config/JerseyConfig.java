package com.kdyzm.gentleman.intelligentcontroller.config;

import javax.annotation.PostConstruct;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.kdyzm.gentleman.intelligentcontroller.api.rest.DemoRest;
import com.kdyzm.gentleman.intelligentcontroller.api.rest.MusicController;
import com.kdyzm.gentleman.intelligentcontroller.api.rest.SystemController;

@Configuration
public class JerseyConfig extends ResourceConfig {
	private Logger logger = LoggerFactory.getLogger(JerseyConfig.class);

	@Autowired
	private MusicController musicController;

	@Autowired
	private SystemController systemController;
	
	public JerseyConfig() {

	}

	@PostConstruct
	public void setRegisterInfo() {
		register(LoggingFilter.class);
		register(musicController);
		register(systemController);
	}

	@Override
	public ResourceConfig register(Class<?> componentClass) {
		showResourceInfo(componentClass);
		return super.register(componentClass);
	}

	@Override
	public ResourceConfig register(Object component) {
		showResourceInfo(component.getClass());
		return super.register(component);
	}

	public void showResourceInfo(Class<?> componentClass) {
		logger.info("install resource ={}", componentClass.getCanonicalName());
	}
}
