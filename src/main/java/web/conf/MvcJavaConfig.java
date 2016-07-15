package web.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

import web.controllers.MyAbstractController;
import web.controllers.PlainController;
import web.view_resolvers.JsonViewResolver;
import web.view_resolvers.PdfViewResolver;
import web.view_resolvers.XlsViewResolver;
import web.views.CustomPdfView;
import web.views.OneExcelView;

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
//>	public void addInterceptors(InterceptorRegistry arg0) {}
//>	public void addResourceHandlers(ResourceHandlerRegistry registry) {}
//	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> arg0) {}
//>	public void addViewControllers(ViewControllerRegistry registry) {}
//	public void configureAsyncSupport(AsyncSupportConfigurer arg0) {}
//>	public void configureContentNegotiation(ContentNegotiationConfigurer arg0) {}
//>	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {}
//	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> arg0) {}
//	public void configureMessageConverters(List<HttpMessageConverter<?>> arg0) {}
//	public void configurePathMatch(PathMatchConfigurer arg0) {}
//>	public void configureViewResolvers(ViewResolverRegistry arg0) {}
//	public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> arg0) {}
//	public void extendMessageConverters(List<HttpMessageConverter<?>> arg0) {}
//	public MessageCodesResolver getMessageCodesResolver() {return ;}
//	public Validator getValidator() { return ;}

	extends WebMvcConfigurerAdapter { // <- better than WebMvcConfigurer (interface)
	
	// <=> <mvc:default-servlet-handler/>
		@Override
		public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
			configurer.enable();
		}
		
	// <=> <mvc:resources />
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/images/**").addResourceLocations("/images/").setCachePeriod(31556926);
			registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);;
			registry.addResourceHandler("/libs/**").addResourceLocations("/libs//").setCachePeriod(31556926);;
			registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts//").setCachePeriod(31556926);;
		}
		
		@Override
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
   .                                              
  @88>        oe       u+=~~~+u.                  
  %8P       .@88     z8F      `8N.     u.    u.   
   .    ==*88888    d88L       98E   x@88k u@88c. 
 .@88u     88888    98888bu.. .@*   ^"8888""8888" 
''888E`    88888    "88888888NNu.     8888  888R  
  888E     88888     "*8888888888i    8888  888R  
  888E     88888     .zf""*8888888L   8888  888R  
  888E     88888    d8F      ^%888E   8888  888R  
  888&     88888    88>        `88~  "*88*" 8888" 
  R888"    88888    '%N.       d*"     ""   'Y"   
   ""   '**%%%%%%**    ^"====="`                  
*/
	
		
	@Override
	// <=>  <mvc:interceptors>
	public void addInterceptors(InterceptorRegistry interceptorRegistry) {
		
		LocaleChangeInterceptor localeChangeInterceptor = 
				new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		interceptorRegistry.addInterceptor(localeChangeInterceptor);
	}
	
	@Bean
	public LocaleResolver localeResolver(){
		
		CookieLocaleResolver cookieLocaleResolver =
				new CookieLocaleResolver();
		return cookieLocaleResolver;
	}
		
	@Bean
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = 
				new ReloadableResourceBundleMessageSource();
		reloadableResourceBundleMessageSource.setBasename("classpath:/bundles/greetings");
		reloadableResourceBundleMessageSource.setCacheSeconds(5);
		return reloadableResourceBundleMessageSource;
	}
		
//	@Bean
//	public MessageSource messageSource(){
//		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
//		resourceBundleMessageSource.setBasename("bundles/greetings");
//		return resourceBundleMessageSource;
//	}
		
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
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer contentNegotiationConfigurer) {
		contentNegotiationConfigurer.useJaf(false).
				ignoreAcceptHeader(true).	//IGNORAR EL ACEPT HEADER
				favorPathExtension(false).	//NO IDENTIFICAR POR EXTENSION
				favorParameter(true).		//IDENTIFICAR POR PARAMETRO
				parameterName("formatType").//NOMBRE DEL PARAMETRO PARA IDENTIFICAR EL FORMATO SOLICITADO
				
				mediaType("xls", new MediaType("application","vnd.ms-excel")).
				mediaType("json", MediaType.APPLICATION_JSON).
				mediaType("pdf", MediaType.APPLICATION_PDF).
				defaultContentType(MediaType.TEXT_HTML);
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry viewResolverRegistry) {
		
		// <=><mvc:annotation-driven content-negotiation-manager="cnm"/>
		viewResolverRegistry.enableContentNegotiation();
		
		//xls
		XlsViewResolver xlsViewResolver = new XlsViewResolver(); 
		viewResolverRegistry.viewResolver(xlsViewResolver);
		
		//pdf
		PdfViewResolver pdfViewResolver = new PdfViewResolver();
		viewResolverRegistry.viewResolver(pdfViewResolver);
		
		//json
		JsonViewResolver jsonViewResolver = new JsonViewResolver();
		viewResolverRegistry.viewResolver(jsonViewResolver);
		
		//built-in
		viewResolverRegistry.tiles();
		viewResolverRegistry.beanName();
		viewResolverRegistry.velocity();
		viewResolverRegistry.freeMarker();
		viewResolverRegistry.jsp("/WEB-INF/views/", ".jsp");
		
		
	}
	//Tiles
	@Bean
	public TilesConfigurer tilesConfigurer(){
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions("/WEB-INF/views/tiles/tiles.xml");
		
		return tilesConfigurer;
	}
	
	//BeanName
	@Bean(name="bean/xls_poi")
	public View excelPoiView(){
		return new OneExcelView();
	}
	@Bean(name="bean/pdf_itext")
	public View pdfItextView(){
		return new CustomPdfView();
	}
	
	//Velocity
	@Bean
	public VelocityConfigurer velocityConfigurer(){
		VelocityConfigurer velocityConfigurer = new VelocityConfigurer();
		velocityConfigurer.setResourceLoaderPath("/WEB-INF/views/velocity");
		return velocityConfigurer;
	}
	
	//FreeMarker
	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer(){
		FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
		//FreeMarkerUtil freeMarkerUtil = FreeMarkerUtil.getInstance();
		//freeMarkerConfigurer.setConfiguration(freeMarkerUtil.getConfiguration());
		freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/views/freemarker");
		return freeMarkerConfigurer;
	}
		
/*	
 
	//ContentNegotiatingViewResolver
		
	@Bean
	public ContentNegotiationManager contentNegotiationManager(){
		
		ContentNegotiationManagerFactoryBean contentNegotiationManagerFactoryBean 
			= new ContentNegotiationManagerFactoryBean();
		
		Properties mediaTypes = new Properties();
		mediaTypes.setProperty("html", MediaType.TEXT_HTML_VALUE);
		mediaTypes.setProperty("json", MediaType.APPLICATION_JSON_VALUE);
		mediaTypes.setProperty("pdf", MediaType.APPLICATION_PDF_VALUE);
		mediaTypes.setProperty("xls", "application/vnd.ms-excel");
		
		contentNegotiationManagerFactoryBean.setMediaTypes(mediaTypes);
		contentNegotiationManagerFactoryBean.setDefaultContentType(MediaType.TEXT_HTML);
		contentNegotiationManagerFactoryBean.setIgnoreAcceptHeader(Boolean.TRUE);
		contentNegotiationManagerFactoryBean.setFavorParameter(Boolean.FALSE);
		contentNegotiationManagerFactoryBean.setFavorPathExtension(Boolean.TRUE);
		
		return contentNegotiationManagerFactoryBean.getObject();
	}

	@Bean
	public ViewResolver contentNegotiatingViewResolver(
//			@Qualifier("internalResourceViewResolver")
//			ViewResolver internalResourceViewResolver
			
			@Qualifier("contentNegotiationManager")
			ContentNegotiationManager contentNegotiationManager
			){
		
		
//		List<ViewResolver> viewResolverList = new ArrayList<>();
//		viewResolverList.add(internalResourceViewResolver);
		
		
		ContentNegotiatingViewResolver contentNegotiatingViewResolver 
			= new ContentNegotiatingViewResolver();
		contentNegotiatingViewResolver.setOrder(-1);
		
		contentNegotiatingViewResolver.setContentNegotiationManager(contentNegotiationManager);
		return contentNegotiatingViewResolver;
	}
	
	//
		
	//BeanNameViewResolver
	//returns a view based on the name of a bean
	@Bean(name="bean/rss")
	public View rssView(){
		return new CustomRssView();
	}
	
	@Bean(name="bean/xml_jackson2")
	public View xmlJackson2View(){
		//Esta clase esta disenada para tomar el Modelo
		//y transformarlo a un XML
		//no se necesita extender la clase
		return new MappingJackson2XmlView();
	}
		
	@Bean(name="bean/json_jackson2")
	public View jsonJackson2View(){
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

	@Bean
	public ViewResolver xmlViewResolver(){

		//XmlViewResolver is used to resolve "view name" based on view beans in the XML file. 
		//By default, XmlViewResolver will loads the view beans from /WEB-INF/views.xml
		
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
	
//	//ResourceBundle
//	//http://www.mkyong.com/spring-mvc/spring-mvc-resourcebundleviewresolver-example/
//	
//	@Bean
//	public ViewResolver resourceBundleViewResolver(){
//		ResourceBundleViewResolver resourceBundleViewResolver = new ResourceBundleViewResolver();
//		resourceBundleViewResolver.setOrder(4);
//		return resourceBundleViewResolver;
//		
//	}
	
	
	//DEFAULT
	@Bean
	public ViewResolver internalResourceViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp" );
		resolver.setOrder(999999999);
		return resolver;
	}
*/	
	
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
	
	@Bean(name="/slash_abstract_controller")
	public MyAbstractController thisIsAbstractController(){
		return new MyAbstractController();
	}
	
	
}
