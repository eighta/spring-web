package web.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import a8.core.Life;
import web.utils.ServletContextUtil;

@WebListener
public class MyServletContextListener 
	implements ServletContextListener{ //OJO: SOLO PUEDE EXISTIR UN SOLO WEBLISTENER DE ESTA MISMA FORMA

	private static final Logger logger = LoggerFactory.getLogger(MyServletContextListener.class);
	private static final ServletContextUtil servletContextUtil = ServletContextUtil.getInstance();

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		
		//Spring Backend Configuration
		//ERROR: 
		//java.lang.IllegalArgumentException: Una vez que el primer ServletContextListener ha sido llamado, no se pueden añadir más ServletContextListeners.
		//ServletContext servletContext = servletContextEvent.getServletContext();
		//servletContextUtil.registrarListener4backendConfiguration(servletContext);
		
		ServletContext servletContext = servletContextEvent.getServletContext();
		servletContext.setAttribute("GLOBAL", Life.getInstance(servletContext));
		//+--- nancyj
		String text ="\n"+
"dP   dP   dP          dP        a88888b.                     dP                       dP   dP          oo   dP   oo          dP oo                         dP \n"+
"88   88   88          88       d8'   `88                     88                       88   88               88               88                            88 \n"+
"88  .8P  .8P .d8888b. 88d888b. 88        .d8888b. 88d888b. d8888P .d8888b. dP.  .dP d8888P 88 88d888b. dP d8888P dP .d8888b. 88 dP d888888b .d8888b. .d888b88 \n"+
"88  d8'  d8' 88ooood8 88'  `88 88        88'  `88 88'  `88   88   88ooood8  `8bd8'    88   88 88'  `88 88   88   88 88'  `88 88 88    .d8P' 88ooood8 88'  `88 \n"+
"88.d8P8.d8P  88.  ... 88.  .88 Y8.   .88 88.  .88 88    88   88   88.  ...  .d88b.    88   88 88    88 88   88   88 88.  .88 88 88  .Y8P    88.  ... 88.  .88 \n"+
"8888' Y88'   `88888P' 88Y8888'  Y88888P' `88888P' dP    dP   dP   `88888P' dP'  `dP   dP   dP dP    dP dP   dP   dP `88888P8 dP dP d888888P `88888P' `88888P8 ";
//http://www.askapache.com/online-tools/figlet-ascii/
		
		logger.info(text);
		servletContextUtil.printAllServletNames(servletContext);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		logger.info("contextDestroyed(...)");
		
		//+--- fender
		String text ="\n"+

".|'''',                   ||                    ||    '||'''|.                ||                                      ||` \n"+
"||                        ||                    ||     ||   ||                ||                                      ||  \n"+
"||      .|''|, `||''|,  ''||''  .|''|, \\  // ''||''   ||   || .|''|, ('''' ''||''  '||''| .|''|, '||  ||` .|''|, .|''||  \n"+
"||      ||  ||  ||  ||    ||    ||..||   ><     ||     ||   || ||..||  `'')   ||     ||    ||  ||  `|..||  ||..|| ||  ||  \n"+
"`|....' `|..|' .||  ||.   `|..' `|...  //  \\   `|..' .||...|' `|...  `...'   `|..' .||.   `|..|'      ||  `|...  `|..||. \n"+
"                                                                                                    ,  |'                 \n"+
"                                                                                                     ''                   \n";
		logger.info(text);
		
	}

}
