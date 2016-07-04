package com.book.init;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

/**
 * Created by iuliana.cosmina on 3/14/15.
 */
public class WebInitializer extends AbstractDispatcherServletInitializer {

	@Override
	protected WebApplicationContext createServletApplicationContext() {
		
		XmlWebApplicationContext context = new XmlWebApplicationContext();
		context.setConfigLocations("/WEB-INF/spring/mvc-config.xml");
		return context;
	}

	@Override
	protected String[] getServletMappings() {
		return new String [] {"/*"};
	}

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		return null;
	}

/*FIXED-TOD 14. Implement this class in order to provide a working web application configuration for a Servlet 3.0+ environment
     * and using the mvc-config.xml file for application configuration */
}