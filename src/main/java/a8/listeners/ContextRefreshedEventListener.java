package a8.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import a8.utils.CommonsUtils;

public class ContextRefreshedEventListener 
	implements ApplicationListener<ContextRefreshedEvent>{

	private static final Logger logger = LoggerFactory.getLogger(ContextRefreshedEventListener.class);
	private CommonsUtils commonsUtils = CommonsUtils.getInstance();
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		
		ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
		commonsUtils.printAllNameBeans(applicationContext);
		

String text ="\n"+
" _______  _______  __    _  _______  _______  __   __  _______  ______    _______  _______  ______    _______  _______  __   __  _______  ______  \n"+
"|       ||       ||  |  | ||       ||       ||  |_|  ||       ||    _ |  |       ||       ||    _ |  |       ||       ||  | |  ||       ||      | \n"+
"|       ||   _   ||   |_| ||_     _||    ___||       ||_     _||   | ||  |    ___||    ___||   | ||  |    ___||  _____||  |_|  ||    ___||  _    |\n"+
"|       ||  | |  ||       |  |   |  |   |___ |       |  |   |  |   |_||_ |   |___ |   |___ |   |_||_ |   |___ | |_____ |       ||   |___ | | |   |\n"+
"|      _||  |_|  ||  _    |  |   |  |    ___| |     |   |   |  |    __  ||    ___||    ___||    __  ||    ___||_____  ||       ||    ___|| |_|   |\n"+
"|     |_ |       || | |   |  |   |  |   |___ |   _   |  |   |  |   |  | ||   |___ |   |    |   |  | ||   |___  _____| ||   _   ||   |___ |       |\n"+
"|_______||_______||_|  |__|  |___|  |_______||__| |__|  |___|  |___|  |_||_______||___|    |___|  |_||_______||_______||__| |__||_______||______| \n"+
applicationContext;

		logger.info(text);
	
	}

}
