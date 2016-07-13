package web.conf;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.XmlViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.velocity.VelocityConfig;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import web.controllers.PlainController;
import web.templates.customs.CustomPdfView;
import web.templates.customs.OneExcelView;
import web.templates.customs.TwoExcelView;

//This is a JavaConfiguration File
@Configuration

//Annotating a configuration class with @EnableWebMvc has the result of importing the Spring MVC
//configuration implemented in the WebMvcConfigurationSupport class;
//it is equivalent to <mvc:annotation-driven/>
@EnableWebMvc

//<context:component-scan />.
@ComponentScan(basePackages={"web.beans","web.controllers"})
public class MvcJavaConfig 
	
	//implements WebMvcConfigurer {
//	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> arg0) {}
//	public void addCorsMappings(CorsRegistry arg0) {}
//	public void addFormatters(FormatterRegistry arg0) {}
//	public void addInterceptors(InterceptorRegistry arg0) {}
//	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> arg0) {}
//	public void configureAsyncSupport(AsyncSupportConfigurer arg0) {}
//	public void configureContentNegotiation(ContentNegotiationConfigurer arg0) {}
//	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> arg0) {}
//	public void configureMessageConverters(List<HttpMessageConverter<?>> arg0) {}
//	public void configurePathMatch(PathMatchConfigurer arg0) {}
//	public void configureViewResolvers(ViewResolverRegistry arg0) {}
//	public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> arg0) {}
//	public void extendMessageConverters(List<HttpMessageConverter<?>> arg0) {}
//	public MessageCodesResolver getMessageCodesResolver() {return ;}
//	public Validator getValidator() { return ;}

	extends WebMvcConfigurerAdapter { // <- better than WebMvcConfigurer (interface)
	
	// <=> <mvc:default-servlet-handler/>
		public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
			configurer.enable();
		}
		
	// <=> <mvc:resources />
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/images/**").addResourceLocations("/images/").setCachePeriod(31556926);
			registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);;
			registry.addResourceHandler("/libs/**").addResourceLocations("/libs//").setCachePeriod(31556926);;
			registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts//").setCachePeriod(31556926);;
		}
		
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addViewController("/").setViewName("bienvenido");
			registry.addViewController("/tasks").setViewName("tasks");
			registry.addViewController("/tasks/mvc_components").setViewName("tasks/mvc_components");
			
			//XXX TODO cual sera la forma de mapear TODAS LA URLs  a vistas?
			registry.addViewController("/tasks/handler_mapping").setViewName("tasks/handler_mapping");
			registry.addViewController("/tasks/view_resolver").setViewName("tasks/view_resolver");
		}
		
	//HandlerMapping
//Este Handler mapping es configurado automaticamente por @EnableWebMvc
//	@Bean(name="resourceHandlerMapping_BAD") 
//	RequestMappingHandlerMapping getRequestMappingHandlerMapping(){
//		return new RequestMappingHandlerMapping();
//	}

/*		
	@Bean
	ControllerClassNameHandlerMapping controllerClassNameHandlerMapping(){
		ControllerClassNameHandlerMapping ccnHM = new ControllerClassNameHandlerMapping();
		ccnHM.setCaseSensitive(true);
		return ccnHM;
	}
*/	
	
	//HandlerAdapter
		/*
	@Bean
	AnnotationMethodHandlerAdapter  annotationMethodHandlerAdapter(){
		return new AnnotationMethodHandlerAdapter();
	}
	*/
		
/* 
 ██▒   █▓ ██▓▓█████  █     █░    ██▀███  ▓█████   ██████  ▒█████   ██▓  ██▒   █▓▓█████  ██▀███    ██████ 
▓██░   █▒▓██▒▓█   ▀ ▓█░ █ ░█░   ▓██ ▒ ██▒▓█   ▀ ▒██    ▒ ▒██▒  ██▒▓██▒ ▓██░   █▒▓█   ▀ ▓██ ▒ ██▒▒██    ▒ 
 ▓██  █▒░▒██▒▒███   ▒█░ █ ░█    ▓██ ░▄█ ▒▒███   ░ ▓██▄   ▒██░  ██▒▒██░  ▓██  █▒░▒███   ▓██ ░▄█ ▒░ ▓██▄   
  ▒██ █░░░██░▒▓█  ▄ ░█░ █ ░█    ▒██▀▀█▄  ▒▓█  ▄   ▒   ██▒▒██   ██░▒██░   ▒██ █░░▒▓█  ▄ ▒██▀▀█▄    ▒   ██▒
   ▒▀█░  ░██░░▒████▒░░██▒██▓    ░██▓ ▒██▒░▒████▒▒██████▒▒░ ████▓▒░░██████▒▒▀█░  ░▒████▒░██▓ ▒██▒▒██████▒▒
   ░ ▐░  ░▓  ░░ ▒░ ░░ ▓░▒ ▒     ░ ▒▓ ░▒▓░░░ ▒░ ░▒ ▒▓▒ ▒ ░░ ▒░▒░▒░ ░ ▒░▓  ░░ ▐░  ░░ ▒░ ░░ ▒▓ ░▒▓░▒ ▒▓▒ ▒ ░
   ░ ░░   ▒ ░ ░ ░  ░  ▒ ░ ░       ░▒ ░ ▒░ ░ ░  ░░ ░▒  ░ ░  ░ ▒ ▒░ ░ ░ ▒  ░░ ░░   ░ ░  ░  ░▒ ░ ▒░░ ░▒  ░ ░
     ░░   ▒ ░   ░     ░   ░       ░░   ░    ░   ░  ░  ░  ░ ░ ░ ▒    ░ ░     ░░     ░     ░░   ░ ░  ░  ░  
      ░   ░     ░  ░    ░          ░        ░  ░      ░      ░ ░      ░  ░   ░     ░  ░   ░           ░  
     ░                                                                      ░                            
*/
	
	//BeanNameViewResolver
	//returns a view based on the name of a bean
	@Bean(name="bean/json_jackson2")
	public View pdfJackson2View(){
		//Esta clase esta disenada para tomar el Modelo
		//y transformarlo a un objeto JSON
		//no se necesita extender la clase
		return new MappingJackson2JsonView();
	}
		
	@Bean(name="bean/pdf_itext")
	public View pdfItextView(){
		return new CustomPdfView();
	}
		
	@Bean(name="bean/xls_jexcelapi")
	public View excelJExcelApiView(){
		return new TwoExcelView();
	}
		
	@Bean(name="bean/xls_poi")
	public View excelPoiView(){
		return new OneExcelView();
	}
		
	@Bean
	public ViewResolver beanNameViewResolver(){
		BeanNameViewResolver beanNameViewResolver = new BeanNameViewResolver();
		beanNameViewResolver.setOrder(3);
		return beanNameViewResolver;
	}
	//XML
	@Autowired
	ServletContext servletContext;
//	
	@Bean
	public ViewResolver xmlViewResolver(){
		
		//Este ViewResolver lo que pretende es que:
		//en un archivo XML, esten definidos las vistas (beans) tipo View
		
		//by default the files is in and is: [/WEB-INF/views.xml]
		Resource location = new ServletContextResource(servletContext,"/WEB-INF/views/xml/views.xml");
		
		XmlViewResolver xmlViewResolver = new XmlViewResolver();
		xmlViewResolver.setLocation(location);
		xmlViewResolver.setOrder(4);
		
		return xmlViewResolver;
	}
		
	//Velocity
	@Bean
	public VelocityConfig velocityConfig(){
		VelocityConfigurer velocityConfigurer = new VelocityConfigurer();
		velocityConfigurer.setResourceLoaderPath("/WEB-INF/views/velocity");
		return velocityConfigurer;
	}
		
	@Bean
	public ViewResolver velocityViewResolver(){
		VelocityViewResolver velocityViewResolver = new VelocityViewResolver();
		velocityViewResolver.setOrder(2);
		return velocityViewResolver;
	}
		
	//FreeMarker
	@Bean
	public FreeMarkerConfig freeMarkerConfig(){
		FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
		//FreeMarkerUtil freeMarkerUtil = FreeMarkerUtil.getInstance();
		//freeMarkerConfigurer.setConfiguration(freeMarkerUtil.getConfiguration());
		freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/views/freemarker");
		return freeMarkerConfigurer;
	}
	@Bean
	public ViewResolver freeMarkerViewResolver(){
		FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
		freeMarkerViewResolver.setOrder(1);
		return freeMarkerViewResolver;
	}
	
	//ResourceBundle
	//http://www.mkyong.com/spring-mvc/spring-mvc-resourcebundleviewresolver-example/
/*	
	@Bean
	public ViewResolver resourceBundleViewResolver(){
		ResourceBundleViewResolver resourceBundleViewResolver = new ResourceBundleViewResolver();
		resourceBundleViewResolver.setOrder(4);
		return resourceBundleViewResolver;
		
	}
*/	
	
	//DEFAULT
	@Bean
	public ViewResolver internalResourceViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp" );
		resolver.setOrder(999999999);
		return resolver;
	}
	
/* 
	 ▄████▄   ▒█████   ███▄    █ ▄▄▄█████▓ ██▀███   ▒█████   ██▓     ██▓    ▓█████  ██▀███    ██████ 
	▒██▀ ▀█  ▒██▒  ██▒ ██ ▀█   █ ▓  ██▒ ▓▒▓██ ▒ ██▒▒██▒  ██▒▓██▒    ▓██▒    ▓█   ▀ ▓██ ▒ ██▒▒██    ▒ 
	▒▓█    ▄ ▒██░  ██▒▓██  ▀█ ██▒▒ ▓██░ ▒░▓██ ░▄█ ▒▒██░  ██▒▒██░    ▒██░    ▒███   ▓██ ░▄█ ▒░ ▓██▄   
	▒▓▓▄ ▄██▒▒██   ██░▓██▒  ▐▌██▒░ ▓██▓ ░ ▒██▀▀█▄  ▒██   ██░▒██░    ▒██░    ▒▓█  ▄ ▒██▀▀█▄    ▒   ██▒
	▒ ▓███▀ ░░ ████▓▒░▒██░   ▓██░  ▒██▒ ░ ░██▓ ▒██▒░ ████▓▒░░██████▒░██████▒░▒████▒░██▓ ▒██▒▒██████▒▒
	░ ░▒ ▒  ░░ ▒░▒░▒░ ░ ▒░   ▒ ▒   ▒ ░░   ░ ▒▓ ░▒▓░░ ▒░▒░▒░ ░ ▒░▓  ░░ ▒░▓  ░░░ ▒░ ░░ ▒▓ ░▒▓░▒ ▒▓▒ ▒ ░
	  ░  ▒     ░ ▒ ▒░ ░ ░░   ░ ▒░    ░      ░▒ ░ ▒░  ░ ▒ ▒░ ░ ░ ▒  ░░ ░ ▒  ░ ░ ░  ░  ░▒ ░ ▒░░ ░▒  ░ ░
	░        ░ ░ ░ ▒     ░   ░ ░   ░        ░░   ░ ░ ░ ░ ▒    ░ ░     ░ ░      ░     ░░   ░ ░  ░  ░  
	░ ░          ░ ░           ░             ░         ░ ░      ░  ░    ░  ░   ░  ░   ░           ░  
	░                                                                                                
*/
	
	//FAKE-INDEX
	@Bean(name="/")
	public PlainController fakeIndex(){
		return new PlainController();
	}
	
	@Bean(name="/slash_plain_controller")
	public PlainController thisIsPlainController(){
		return new PlainController();
	}
	
	
}
