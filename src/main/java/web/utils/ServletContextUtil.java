package web.utils;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import web.conf.MvcJavaConfig;
import web.conf.WebFlowJavaConfig;

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
		
		logger.info("[SERVLETS NAMES]");
		for(String servletName:servletRegistrations.keySet()){
			logger.info(servletName);
		}
		logger.info("[END SERVLETS NAMES]");
	}

	
	public void registrarListener(ServletContext servletContext) {
		
		servletContext.setInitParameter("contextConfigLocation", "/WEB-INF/spring/backend-config.xml");
		//by default the param value is: /WEB-INF/applicationContext.xml
		
		org.springframework.web.context.ContextLoaderListener backendContextLoaderListener 
			= new org.springframework.web.context.ContextLoaderListener();
		
		servletContext.addListener(backendContextLoaderListener);
	}
	
	public void registrarFrontControllerServlet(ServletContext servletContext){
		
		//registrando un servlet (frontController)
		String servletName = "frontController";
		
		
//		WAY NRO 1
//		DispatcherServlet frontControllerDispatcherServlet = new DispatcherServlet();
//		ServletRegistration.Dynamic registration =servletContext.addServlet(frontController, frontControllerDispatcherServlet);
//		registration.setLoadOnStartup(1);
//		registration.addMapping("/s/*");
//		registration.setInitParameter("contextConfigLocation","/WEB-INF/spring/mvc-config.xml");
		
//		WAY NRO 2
//using XML		
//		XmlWebApplicationContext xmlWebApplicationContext = new XmlWebApplicationContext();
//		xmlWebApplicationContext.setConfigLocation("/WEB-INF/spring/mvc-config.xml");
		
//		DispatcherServlet frontControllerDispatcherServlet = new DispatcherServlet(xmlWebApplicationContext);

//or		
//using Java
		AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
		annotationConfigWebApplicationContext.register(MvcJavaConfig.class, WebFlowJavaConfig.class);
		
		DispatcherServlet frontControllerDispatcherServlet = new DispatcherServlet(annotationConfigWebApplicationContext);
		
//commons for both (WAY NRO 2)		
		ServletRegistration.Dynamic registration = servletContext.addServlet(servletName, frontControllerDispatcherServlet);
		registration.setLoadOnStartup(1);
		registration.addMapping("/s/*");
		
	}
	
}
