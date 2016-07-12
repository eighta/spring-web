package web.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import web.controllers.PlainController;

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
	@Bean
	InternalResourceViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp" );
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
