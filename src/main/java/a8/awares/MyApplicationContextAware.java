package a8.awares;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import a8.core.Heart;


public class MyApplicationContextAware implements ApplicationContextAware{

	private static final Logger logger = LoggerFactory.getLogger(MyApplicationContextAware.class);
	private Heart heart = Heart.getInstance(); 
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		
		heart.setBackendApplicationContext(applicationContext);
		
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
"ApplicationContext: "+ applicationContext + "\n" + 
":::::::::::::::::::::::::::::::::::::::::::::::::::::::";
		logger.info(text);
	}

}
