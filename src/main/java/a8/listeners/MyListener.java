package a8.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import web.utils.CommonsUtils;

@Component
public class MyListener {
	
	private static final Logger logger = LoggerFactory.getLogger(MyListener.class);
	private CommonsUtils commonsUtils = CommonsUtils.getInstance();
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@EventListener
	public void handleContextStarted(ContextStartedEvent event) {
		logger.info("handleContextRefresh(...)");
	}
	
	@EventListener
	public void handleContextRefresh(ContextRefreshedEvent event) {
		logger.info("handleContextRefresh(...)");
	}
	
	@EventListener
	public void handleContextStopped(org.springframework.context.event.ContextStoppedEvent event) {
		logger.info("handleContextStopped(...)");
	}
	
	@EventListener
	public void handleContextClosed(org.springframework.context.event.ContextClosedEvent event) {
		logger.info("handleContextClosed(...)");
		commonsUtils.printAllNameBeans(applicationContext);
	}
}
