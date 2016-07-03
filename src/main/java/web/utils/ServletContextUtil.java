package web.utils;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServletContextUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ServletContextUtil.class);

	// BEGIN: SINGLETON
	private static ServletContextUtil INSTANCE = null;
	private ServletContextUtil(){}
	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ServletContextUtil();
		}
	}
	public static ServletContextUtil getInstance() {
		if (INSTANCE == null)
			createInstance();
		return INSTANCE;
	}
	// END: SINGLETON
	
	public void printAllServletNames(ServletContext servletContext) {
		
		//Deprecated -> Enumeration<String> servletNames = servletContext.getServletNames();
		Map<String, ? extends ServletRegistration> servletRegistrations = servletContext.getServletRegistrations();
		
		logger.info("===SERVLETS NAMES===");
		for(String servletName:servletRegistrations.keySet()){
			logger.info(servletName);
		}
		logger.info("===END SERVLETS NAMES===");
		
	}

}
