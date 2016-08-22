package web.conf;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.CharacterEncodingFilter;

import web.utils.ServletContextUtil;

public class MyWebApplicationInitializer

//[[[Servlet 3.0]]]
//http://piotrnowicki.com/2011/03/using-servlets-3-0-servletcontainerinitializer/
//ESTO SE DEBE HACER EN UN JAR APARTE Y COLOCARLO EN:  WEB-INF/lib directory
//	implements javax.servlet.ServletContainerInitializer {
//	public void onStartup(Set<Class<?>> arg0, ServletContext arg1) throws ServletException {}

//[[[the Spring way's]]]
	
	//extends org.springframework.web.servlet.support.AbstractDispatcherServletInitializer{	<- returning a WebApplicationContext
	//	protected WebApplicationContext createRootApplicationContext() {return null;}
	//	protected WebApplicationContext createServletApplicationContext() {return null;}
	//	protected String[] getServletMappings() {return null;}

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
		
		//+--- nancyj-underlined
		String text ="\n"+

".d88888b                     oo                            dP          oo   dP   oo          dP oo                            \n"+
"88.    '                                                   88               88               88                               \n"+
"`Y88888b.  88d888b. 88d888b. dP 88d888b. .d8888b.          88 88d888b. dP d8888P dP .d8888b. 88 dP d888888b .d8888b. 88d888b. \n"+
"      `8b  88'  `88 88'  `88 88 88'  `88 88'  `88 88888888 88 88'  `88 88   88   88 88'  `88 88 88    .d8P' 88ooood8 88'  `88 \n"+
"d8'   .8P  88.  .88 88       88 88    88 88.  .88          88 88    88 88   88   88 88.  .88 88 88  .Y8P    88.  ... 88       \n"+
" Y88888P   88Y888P' dP       dP dP    dP `8888P88          dP dP    dP dP   dP   dP `88888P8 dP dP d888888P `88888P' dP       \n"+
"oooooooooo~88~oooooooooooooooooooooooooooo~~~~.88~oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo\n"+
"           dP                             d8888P                                                                              ";
//http://www.askapache.com/online-tools/figlet-ascii/
		
		logger.info(text);

		//backend
		servletContextUtil.registrarListener(servletContext);
		//lets go with annottation -> web.listeners.MyServletContextListener (this way gives ERROR)
		
		//frontend
		servletContextUtil.registrarFrontControllerServlet(servletContext);
		
		//Filter encoding 4 ReloadableResourceBundleMessageSource is used to apply character encoding to requests
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		servletContext.addFilter("characterEncodingFilter", characterEncodingFilter);
	}
	
}
