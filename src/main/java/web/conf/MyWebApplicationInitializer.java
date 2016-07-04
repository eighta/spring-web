package web.conf;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import web.utils.ServletContextUtil;

public class MyWebApplicationInitializer

//[[[Servlet 3.0]]]
//http://piotrnowicki.com/2011/03/using-servlets-3-0-servletcontainerinitializer/
//ESTO SE DEBE HACER EN UN JAR APARTE Y COLOCARLO EN:  WEB-INF/lib directory
//	implements javax.servlet.ServletContainerInitializer {
//	public void onStartup(Set<Class<?>> arg0, ServletContext arg1) throws ServletException {}

//[[[the Spring way's]]]
	
	//extends org.springframework.web.servlet.support.AbstractDispatcherServletInitializer{	<- returning a WebApplicationContext 
	//	protected WebApplicationContext createServletApplicationContext() {return null;}
	//	protected String[] getServletMappings() {return null;}
	//	protected WebApplicationContext createRootApplicationContext() {return null;}
//or
	//extends org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer{ <- returning a JavaClasses for Conf! (implicit Context)
	//	protected Class<?>[] getRootConfigClasses() {return null;}
	//	protected Class<?>[] getServletConfigClasses() {return null;}
	//	protected String[] getServletMappings() {return null;}

//or 
	implements org.springframework.web.WebApplicationInitializer{

	private static final ServletContextUtil servletContextUtil = ServletContextUtil.getInstance();
	private static final Logger logger = LoggerFactory.getLogger(MyWebApplicationInitializer.class);
	
	public void onStartup(ServletContext servletContext) throws ServletException {
		logger.info("spring - onStartup(...)");

		//backend
		servletContextUtil.registrarListener(servletContext);
		//lets go with annottation -> web.listeners.MyServletContextListener (this way gives ERROR)
		
		//frontend
		servletContextUtil.registrarFrontControllerServlet(servletContext);
	}
}
