package web.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import web.utils.ServletContextUtil;

@WebListener
public class MyServletContextListener implements ServletContextListener{

	private static final Logger logger = LoggerFactory.getLogger(MyServletContextListener.class);
	private static final ServletContextUtil servletContextUtil = ServletContextUtil.getInstance();
	
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		logger.info("contextDestroyed(...)");
		
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		logger.info("contextInitialized(...)");
		servletContextUtil.printAllServletNames(servletContextEvent.getServletContext());
		
		
		
	}

}
