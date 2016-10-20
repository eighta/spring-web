package web.conf;

import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.ui.context.ThemeSource;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

import a8.business.PersonManager;
import a8.business.PersonManagerImpl;
import web.controllers.MyAbstractController;
import web.controllers.PlainController;
import web.converters.http.message.HtmlFormPersonMessageConverter;
import web.converters.http.message.JsonPersonMessageConverter;
import web.converters.http.message.PersonMessageConverter;
import web.converters.http.message.SeveralPersonMessageConverter;
import web.formatters.UserConverter;
import web.formatters.UserFormatter;
import web.interceptors.AuditInterceptor;
import web.view_resolvers.JsonViewResolver;
import web.view_resolvers.PdfViewResolver;
import web.view_resolvers.XlsViewResolver;
import web.views.pdf.CustomPdfView;
import web.views.xls.OneExcelView;

//This is a JavaConfiguration File
@Configuration

//Annotating a configuration class with @EnableWebMvc has the result of importing the Spring MVC
//configuration implemented in the WebMvcConfigurationSupport class;
//it is equivalent to <mvc:annotation-driven/>
@EnableWebMvc

//<task:annotation-driven/>
//The Spring Task namespace was introduced in Spring 3.0 to help
//configure TaskExecutor and TaskScheduler instances.
//<task:annotation-driven executor="prExecutor"/>
//<task:executor id="prExecutor" pool-size="100"/>

//like in the XML configuration, the class must implement 
//org.springframework.scheduling.annotation.AsyncConfigurer 
//and provide a concrete implementation for the getAsyncExecutor method.
@EnableAsync


//<context:component-scan />.
@ComponentScan(basePackages={"web.beans","web.controllers","web.rest.controllers","web.rest.interceptors"})
public class MvcJavaConfig 
	
	//implements WebMvcConfigurer {
//	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> arg0) {}
//	public void addCorsMappings(CorsRegistry arg0) {}
//>	public void addFormatters(FormatterRegistry arg0) {}
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
//>	public MessageCodesResolver getMessageCodesResolver() {return ;}
//	public Validator getValidator() { return ;}

	extends WebMvcConfigurerAdapter  // <- better than WebMvcConfigurer (interface)
	implements AsyncConfigurer // <<< this is for use @EnableAsync, @Async
	{
		
	// <=> <mvc:annotation-driven conversion-service="conversionService"/>
//XXX NOT WORKING WITH WEB-FLOW :(	
		@Override
		public void addFormatters(FormatterRegistry registry) {
			//XXX Viene a representar "conversionService"
			//registry.addConverter(this.formattingConversionServiceFactoryBean().getObject());
			registry.addConverter(new UserConverter()); //<<< XXX ESTE CONVERTER NO SIRVE PARA WEB-FLOW
		}
		
//		@Bean
//	    public FormattingConversionService mvcConversionService() {
//	        FormattingConversionService conversionService = new FormattingConversionService();
//	        conversionService.addConverter(new UserConverter());
//	        addFormatters(conversionService);
//	        return conversionService;
//	    }
	
		@Bean(name="typeConversionService")
		public FormattingConversionServiceFactoryBean formattingConversionServiceFactoryBean(){
			FormattingConversionServiceFactoryBean formattingConversionServiceFactoryBean =
					new FormattingConversionServiceFactoryBean();
			
			Set<Formatter<?>> formatterSet = new HashSet<>();
			formatterSet.add(new UserFormatter());
			
			formattingConversionServiceFactoryBean.setFormatters(formatterSet);
			return formattingConversionServiceFactoryBean;
		}
		
	
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
		
	// <=> <mvc:view-controller path="/" view-name="welcome"/>		
		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addViewController("/").setViewName("bienvenido");
			registry.addViewController("tasks").setViewName("tasks");
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
███╗   ███╗███████╗███████╗███████╗ █████╗  ██████╗ ███████╗  ██████╗ ██████╗  DDDD   ███████╗███████╗
████╗ ████║██╔════╝██╔════╝██╔════╝██╔══██╗██╔════╝ ██╔════╝ ██╔════╝██╔═══██╗ D   D  ██╔════╝██╔════╝
██╔████╔██║█████╗  ███████╗███████╗███████║██║  ███╗█████╗   ██║     ██║   ██║ D    D █████╗  ███████╗
██║╚██╔╝██║██╔══╝  ╚════██║╚════██║██╔══██║██║   ██║██╔══╝   ██║     ██║   ██║ D   D  ██╔══╝  ╚════██║
██║ ╚═╝ ██║███████╗███████║███████║██║  ██║╚██████╔╝███████╗ ╚██████╗╚██████╔╝ DDDD   ███████╗███████║
╚═╝     ╚═╝╚══════╝╚══════╝╚══════╝╚═╝  ╚═╝ ╚═════╝ ╚══════╝  ╚═════╝ ╚═════╝         ╚══════╝╚══════╝

 ██▀███  ▓█████   ██████  ▒█████   ██▓  ██▒   █▓▓█████  ██▀███    ██████ 
▓██ ▒ ██▒▓█   ▀ ▒██    ▒ ▒██▒  ██▒▓██▒ ▓██░   █▒▓█   ▀ ▓██ ▒ ██▒▒██    ▒ 
▓██ ░▄█ ▒▒███   ░ ▓██▄   ▒██░  ██▒▒██░  ▓██  █▒░▒███   ▓██ ░▄█ ▒░ ▓██▄   
▒██▀▀█▄  ▒▓█  ▄   ▒   ██▒▒██   ██░▒██░   ▒██ █░░▒▓█  ▄ ▒██▀▀█▄    ▒   ██▒
░██▓ ▒██▒░▒████▒▒██████▒▒░ ████▓▒░░██████▒▒▀█░  ░▒████▒░██▓ ▒██▒▒██████▒▒
░ ▒▓ ░▒▓░░░ ▒░ ░▒ ▒▓▒ ▒ ░░ ▒░▒░▒░ ░ ▒░▓  ░░ ▐░  ░░ ▒░ ░░ ▒▓ ░▒▓░▒ ▒▓▒ ▒ ░
  ░▒ ░ ▒░ ░ ░  ░░ ░▒  ░ ░  ░ ▒ ▒░ ░ ░ ▒  ░░ ░░   ░ ░  ░  ░▒ ░ ▒░░ ░▒  ░ ░
  ░░   ░    ░   ░  ░  ░  ░ ░ ░ ▒    ░ ░     ░░     ░     ░░   ░ ░  ░  ░  
   ░        ░  ░      ░      ░ ░      ░  ░   ░     ░  ░   ░           ░  
UTILIZADO EN WEBFLOW
*/
//	@Override
//	public MessageCodesResolver getMessageCodesResolver() {
//		
//		DefaultMessageCodesResolver resolver = new DefaultMessageCodesResolver();
//		//resolver.setMessageCodeFormatter(this.getMessageCodeFormatter());
//		return resolver;
//	}
	
//	@Bean 
//	public MessageCodeFormatter getMessageCodeFormatter(){
//		
//		return null;
//	}

/*
███████╗███████╗███████╗██═╗  ██╗
██║  ██║╚═══██╔╝██║  ██║ ██║ ██╔╝
███████║    ██║ ███████║  ████ ║
██╔══██║██  ██║ ██╔══██║ ██╔═██╚╗
██║  ██║╚═███╔╝ ██║  ██║██╔╝  ██║
╚═╝  ╚═╝  ╚══╝  ╚═╝  ╚═╝╚═╝   ╚═╝
*/
		
	@Override //Default Async Executor
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(100);
		executor.initialize();
		return executor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		// TODO Auto-generated method stub
		System.out.println("METHOD: getAsyncUncaughtExceptionHandler()");
		return null;
	}
	
	//Custom Async Executor
	@Bean(name="otherExecutor", destroyMethod = "shutdown",
			initMethod = "initialize")
	public ThreadPoolTaskExecutor getExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(100);
		return executor;
	}

// o SIMPLEMENTE		
//		@Bean(name = "threadPoolTaskExecutor")
//	    public Executor threadPoolTaskExecutor() {
//	        return new ThreadPoolTaskExecutor();
//	    }
	
/*
██╗  ██╗████████╗████████╗██████╗  ███╗   ███╗███████╗███████╗███████╗ █████╗  ██████╗ ███████╗
██║  ██║╚══██╔══╝╚══██╔══╝██╔══██╗ ████╗ ████║██╔════╝██╔════╝██╔════╝██╔══██╗██╔════╝ ██╔════╝
███████║   ██║      ██║   ██████╔╝ ██╔████╔██║█████╗  ███████╗███████╗███████║██║  ███╗█████╗
██╔══██║   ██║      ██║   ██╔═══╝  ██║╚██╔╝██║██╔══╝  ╚════██║╚════██║██╔══██║██║   ██║██╔══╝ 
██║  ██║   ██║      ██║   ██║      ██║ ╚═╝ ██║███████╗███████║███████║██║  ██║╚██████╔╝███████╗
╚═╝  ╚═╝   ╚═╝      ╚═╝   ╚═╝      ╚═╝     ╚═╝╚══════╝╚══════╝╚══════╝╚═╝  ╚═╝ ╚═════╝ ╚══════╝
 ██████╗ ██████╗ ███╗   ██╗██╗   ██╗███████╗██████╗ ████████╗███████╗██████╗ ███████╗
██╔════╝██╔═══██╗████╗  ██║██║   ██║██╔════╝██╔══██╗╚══██╔══╝██╔════╝██╔══██╗██╔════╝
██║     ██║   ██║██╔██╗ ██║██║   ██║█████╗  ██████╔╝   ██║   █████╗  ██████╔╝███████╗
██║     ██║   ██║██║╚██╗██║╚██╗ ██╔╝██╔══╝  ██╔══██╗   ██║   ██╔══╝  ██╔══██╗╚════██║
╚██████╗╚██████╔╝██║ ╚████║ ╚████╔╝ ███████╗██║  ██║   ██║   ███████╗██║  ██║███████║
 ╚═════╝ ╚═════╝ ╚═╝  ╚═══╝  ╚═══╝  ╚══════╝╚═╝  ╚═╝   ╚═╝   ╚══════╝╚═╝  ╚═╝╚══════╝
*/		
		@Override
		public void configureMessageConverters(List<HttpMessageConverter<?>> httpMessageConverterList) {
			httpMessageConverterList.add(new JsonPersonMessageConverter() );
			httpMessageConverterList.add(new PersonMessageConverter() );
			httpMessageConverterList.add(new SeveralPersonMessageConverter() );
			httpMessageConverterList.add(new HtmlFormPersonMessageConverter() );
		}
		
/*
 █████ ▒██   ██▒ ▄████▄  ▓█████  ██▓███  ▄▄▄█████▓ ██▓ ▒█████   ███▄    █   ██████ 
▓█   ▀ ▒▒ █ █ ▒░▒██▀ ▀█  ▓█   ▀ ▓██░  ██▒▓  ██▒ ▓▒▓██▒▒██▒  ██▒ ██ ▀█   █ ▒██    ▒ 
▒███   ░░  █   ░▒▓█    ▄ ▒███   ▓██░ ██▓▒▒ ▓██░ ▒░▒██▒▒██░  ██▒▓██  ▀█ ██▒░ ▓██▄   
▒▓█  ▄  ░ █ █ ▒ ▒▓▓▄ ▄██▒▒▓█  ▄ ▒██▄█▓▒ ▒░ ▓██▓ ░ ░██░▒██   ██░▓██▒  ▐▌██▒  ▒   ██▒
░▒████▒▒██▒ ▒██▒▒ ▓███▀ ░░▒████▒▒██▒ ░  ░  ▒██▒ ░ ░██░░ ████▓▒░▒██░   ▓██░▒██████▒▒
░░ ▒░ ░▒▒ ░ ░▓ ░░ ░▒ ▒  ░░░ ▒░ ░▒▓▒░ ░  ░  ▒ ░░   ░▓  ░ ▒░▒░▒░ ░ ▒░   ▒ ▒ ▒ ▒▓▒ ▒ ░
 ░ ░  ░░░   ░▒ ░  ░  ▒    ░ ░  ░░▒ ░         ░     ▒ ░  ░ ▒ ▒░ ░ ░░   ░ ▒░░ ░▒  ░ ░
   ░    ░    ░  ░           ░   ░░         ░       ▒ ░░ ░ ░ ▒     ░   ░ ░ ░  ░  ░  
   ░  ░ ░    ░  ░ ░         ░  ░                   ░      ░ ░           ░       ░  
	*/	
		
		@Bean
		public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
			SimpleMappingExceptionResolver simpleMappingExceptionResolver =
					new SimpleMappingExceptionResolver();
//			
//			
			Properties mappings = new Properties();
			//EL VALUE significa nombre de vista logica
//			mappings.put("DataAccessException", "errors/databaseError"); 
			mappings.put("EightaException", "errors/business_error");
			simpleMappingExceptionResolver.setExceptionMappings(mappings);
			
			simpleMappingExceptionResolver.setDefaultStatusCode(405); //DEFAULT IS 200
			//simpleMappingExceptionResolver.setDefaultStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			
			//VISTA QUE AGARRA CUALQUIER EXCEPTION LANZADA Y NO ESTEN MAPEADAS
			//OJO: 
			//-Request no mapeados NO los atrapa (404)
			//-Si en el HttpServletResponse se coloca un status code 404 por ejemplo, tampoco lo mapea
			//-Es posible acceder a la exception desde la pagina setDefaultErrorView(...)
			//-ni las mappings, ni en las @ExceptionHandler se puede acceder a la exception lanzada
			simpleMappingExceptionResolver.setDefaultErrorView("errors/any_error");
			
			return simpleMappingExceptionResolver;
		}
		
/*
TTTTTTTTTTTTTTTTTTTTTTThhhhhhh                                                                                              
T:::::::::::::::::::::Th:::::h                                                                                              
T:::::::::::::::::::::Th:::::h                                                                                              
T:::::TT:::::::TT:::::Th:::::h                                                                                              
TTTTTT  T:::::T  TTTTTT h::::h hhhhh           eeeeeeeeeeee       mmmmmmm    mmmmmmm       eeeeeeeeeeee        ssssssssss   
        T:::::T         h::::hh:::::hhh      ee::::::::::::ee   mm:::::::m  m:::::::mm   ee::::::::::::ee    ss::::::::::s  
        T:::::T         h::::::::::::::hh   e::::::eeeee:::::eem::::::::::mm::::::::::m e::::::eeeee:::::eess:::::::::::::s 
        T:::::T         h:::::::hhh::::::h e::::::e     e:::::em::::::::::::::::::::::me::::::e     e:::::es::::::ssss:::::s
        T:::::T         h::::::h   h::::::he:::::::eeeee::::::em:::::mmm::::::mmm:::::me:::::::eeeee::::::e s:::::s  ssssss 
        T:::::T         h:::::h     h:::::he:::::::::::::::::e m::::m   m::::m   m::::me:::::::::::::::::e    s::::::s      
        T:::::T         h:::::h     h:::::he::::::eeeeeeeeeee  m::::m   m::::m   m::::me::::::eeeeeeeeeee        s::::::s   
        T:::::T         h:::::h     h:::::he:::::::e           m::::m   m::::m   m::::me:::::::e           ssssss   s:::::s 
      TT:::::::TT       h:::::h     h:::::he::::::::e          m::::m   m::::m   m::::me::::::::e          s:::::ssss::::::s
      T:::::::::T       h:::::h     h:::::h e::::::::eeeeeeee  m::::m   m::::m   m::::m e::::::::eeeeeeee  s::::::::::::::s 
      T:::::::::T       h:::::h     h:::::h  ee:::::::::::::e  m::::m   m::::m   m::::m  ee:::::::::::::e   s:::::::::::ss  
      TTTTTTTTTTT       hhhhhhh     hhhhhhh    eeeeeeeeeeeeee  mmmmmm   mmmmmm   mmmmmm    eeeeeeeeeeeeee    sssssssssss    
*/		
		@Bean
		public ThemeResolver themeResolver (){
			CookieThemeResolver cookieThemeResolver = 
					new CookieThemeResolver();
			cookieThemeResolver.setDefaultThemeName("base");
			cookieThemeResolver.setCookieName("myWebApp.THEME"); //BY DEFAULT IS: org.springframework.web.servlet.theme.cookieThemeResolver.THEME
			return cookieThemeResolver;
		}
		
		@Bean
		public ThemeSource themeSource(){
			ResourceBundleThemeSource resourceBundleThemeSource = 
					new ResourceBundleThemeSource();
			//XXX NO LOGRE QUE EL ARCHIVO SE PUEDA ACCEDER EN UNA CARPETA DEL CLASS-PATH
			//COMO LO HACE 
			//INTENTARLO EN ESTA CLASE (here.ClassPathThemeSource)

			//la idea era tener en los archivos del theme en una carpeta de los
			//resources llamada themes (i.e. classpath:/themes),
			//como mas abajo la uso en 
			//reloadableResourceBundleMessageSource.setBasename("classpath:/bundles/greetings");
			resourceBundleThemeSource.setBasenamePrefix("themes-");
			return resourceBundleThemeSource;
		}
		
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

		//REST-CUSTOM
		//interceptorRegistry.addInterceptor(new RestAuditInterceptor());
		//interceptorRegistry.addInterceptor(new RestJsonInterceptor());
		//REST INTERCEPTORS se registran con @ControllerAdvice
		
		//MY-CUSTOM
		interceptorRegistry.addInterceptor(new AuditInterceptor());
		
		//THEMES
		ThemeChangeInterceptor themeChangeInterceptor  =
				new ThemeChangeInterceptor();
		themeChangeInterceptor.setParamName("tematica"); //DEFAULT: theme 
		interceptorRegistry.addInterceptor(themeChangeInterceptor);
		
		//LOCALE
		LocaleChangeInterceptor localeChangeInterceptor = 
				new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");	//DEFAULT: locale 
		interceptorRegistry.addInterceptor(localeChangeInterceptor);
		
	}

	@Bean
	public LocaleResolver localeResolver(){
		
		//SessionLocaleResolver
		//=====================
//		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		//XXX NO SE COMO CAMBIAR EL NOMBRE DEL ATTRIBUTO QUE QUEDA EN SESSION: //DEFAULT: org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE
//		return sessionLocaleResolver;
		
		//CookieLocaleResolver
		//====================
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setCookieName("myWebApp.LOCALE"); 	//DEFAULT: springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE
		//cookieLocaleResolver.setCookieMaxAge(-1);
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
				ignoreAcceptHeader(true).	//IGNORAR EL ACCEPT HEADER
				favorPathExtension(true).	//IDENTIFICAR POR EXTENSION
				favorParameter(false).		//IDENTIFICAR POR PARAMETRO
				parameterName("formatType").//NOMBRE DEL PARAMETRO PARA IDENTIFICAR EL FORMATO SOLICITADO
				
				mediaType("xls", new MediaType("application","vnd.ms-excel")).
				mediaType("json", MediaType.APPLICATION_JSON).
				mediaType("pdf", MediaType.APPLICATION_PDF).
				defaultContentType(MediaType.TEXT_HTML);
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry viewResolverRegistry) {
		
		//En el oden en que se van adicionando, asi mismo son evaluados
		
		// <=><mvc:annotation-driven content-negotiation-manager="cnm"/>
		viewResolverRegistry.enableContentNegotiation();
		
		//4 Web Flow
//		JstlViewsWebFlowResolver internalResourceViewResolverWebFlow =
//				new JstlViewsWebFlowResolver();
//		internalResourceViewResolverWebFlow.setPrefix("/WEB-INF/flows/");
//		internalResourceViewResolverWebFlow.setSuffix(".jsp");
//		viewResolverRegistry.viewResolver(internalResourceViewResolverWebFlow);
		
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
		//viewResolverRegistry.jsp("/WEB-INF/views/", ".jsp");
		
		//explicitamente:
		InternalResourceViewResolver internalResourceViewResolver =
				new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/views/");
		internalResourceViewResolver.setSuffix(".jsp");
		
		internalResourceViewResolver.setRequestContextAttribute("requestContext");
		viewResolverRegistry.viewResolver(internalResourceViewResolver);
		
		
	}
	//Tiles
	@Bean
	public TilesConfigurer tilesConfigurer(){
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(
				"/WEB-INF/views/tiles/tiles.xml",
				
				//4 WebFlow
				"/WEB-INF/flows/persons/newPerson/tiles.xml",
				"/WEB-INF/flows/dogs/newDog/tiles.xml",
				"/WEB-INF/flows/advanced/start/tiles.xml",
				"/WEB-INF/flows/advanced/mySubflow/tiles.xml",
				"/WEB-INF/flows/advanced/eightaCustomFlow/tiles.xml",
				"/WEB-INF/flows/advanced/abstractFlow/tiles.xml"
				
				);
		
		
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
	
	//BUSINESS
	@Bean
	public PersonManager getPersonManager(){
		return new PersonManagerImpl();
	}
	
//HandlerMappings
	
	@Bean
	public HandlerMapping myCustomsFlowsHandlerMappings(){
		
		SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
		simpleUrlHandlerMapping.setOrder(123);
		
		Properties mappings = new Properties();
		mappings.put("/eighta.flow", "_myCustomFlowHandler");
		simpleUrlHandlerMapping.setMappings(mappings);
		
		return simpleUrlHandlerMapping;
	}
		
	
}
