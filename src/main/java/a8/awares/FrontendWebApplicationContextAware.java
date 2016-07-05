package a8.awares;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;

import a8.core.Heart;

public class FrontendWebApplicationContextAware implements ApplicationContextAware{

	private static final Logger logger = LoggerFactory.getLogger(FrontendWebApplicationContextAware.class);
	private Heart heart = Heart.getInstance();
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		
		heart.setFrontendApplicationContext((WebApplicationContext)applicationContext);
		
		//+--- banner3-D
				String text ="\n"+

		":::'###::::'##:::::'##::::'###::::'########::'########:\n"+
		"::'## ##::: ##:'##: ##:::'## ##::: ##.... ##: ##.....::\n"+
		":'##:. ##:: ##: ##: ##::'##:. ##:: ##:::: ##: ##:::::::\n"+
		"'##:::. ##: ##: ##: ##:'##:::. ##: ########:: ######:::\n"+
		" #########: ##: ##: ##: #########: ##.. ##::: ##...::::\n"+
		" ##.... ##: ##: ##: ##: ##.... ##: ##::. ##:: ##:::::::\n"+
		" ##:::: ##:. ###. ###:: ##:::: ##: ##:::. ##: ########:\n"+
		"..:::::..:::...::...:::..:::::..::..:::::..::........::\n"+
		"WebApplicationContext: "+ applicationContext + "\n" + 
		":::::::::::::::::::::::::::::::::::::::::::::::::::::::";
				logger.info(text);
	} 
}
