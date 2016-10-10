package web.conf;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.binding.convert.ConversionService;
import org.springframework.binding.convert.service.DefaultConversionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.ViewResolverComposite;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.config.FlowBuilderServicesBuilder;
import org.springframework.webflow.config.FlowDefinitionRegistryBuilder;
import org.springframework.webflow.config.FlowExecutorBuilder;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.ViewFactoryCreator;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.builder.MvcViewFactoryCreator;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;

import a8.data.Person;
import a8.data.Simple1;
import a8.services.InterviewFactory;
import a8.utils.CommonsUtils;
import web.converters.binding.UserToStringConverter;
import web.listeners.WebFlowListener;

@Configuration
public class WebFlowJavaConfig 
extends AbstractFlowConfiguration 
{

	//XXX OJO: Recordando, el nombre del metodo, es el nombre del Bean
	@Bean
	public WebFlowListener theFlowListener(){
		return new WebFlowListener(); 
	}
	
	@Bean
	public Simple1 simple1Bean(){
		return new Simple1();
	}
	
	@Bean
	public Person singletonPerson(){
		
		Person person = new Person();
		person.setFirstName("Alex");
		person.setLastName("Poe");
		
		return person;
	}
	
	@Bean
	public InterviewFactory interviewFactory(){
		return new InterviewFactory();
	}
	
	
/*
███████╗██╗      ██████╗ ██╗    ██╗███╗   ███╗ █████╗ ██████╗ ██████╗ ██╗███╗   ██╗ ██████╗ 
██╔════╝██║     ██╔═══██╗██║    ██║████╗ ████║██╔══██╗██╔══██╗██╔══██╗██║████╗  ██║██╔════╝ 
█████╗  ██║     ██║   ██║██║ █╗ ██║██╔████╔██║███████║██████╔╝██████╔╝██║██╔██╗ ██║██║  ███╗
██╔══╝  ██║     ██║   ██║██║███╗██║██║╚██╔╝██║██╔══██║██╔═══╝ ██╔═══╝ ██║██║╚██╗██║██║   ██║
██║     ███████╗╚██████╔╝╚███╔███╔╝██║ ╚═╝ ██║██║  ██║██║     ██║     ██║██║ ╚████║╚██████╔╝
╚═╝     ╚══════╝ ╚═════╝  ╚══╝╚══╝ ╚═╝     ╚═╝╚═╝  ╚═╝╚═╝     ╚═╝     ╚═╝╚═╝  ╚═══╝ ╚═════╝ 
*/
	@Bean
	public FlowHandlerMapping flowHandlerMapping(FlowDefinitionRegistry flowDefinitionRegistry) {
		FlowHandlerMapping handlerMapping = new FlowHandlerMapping();
		handlerMapping.setOrder(-1);
		handlerMapping.setFlowRegistry(flowDefinitionRegistry);
		return handlerMapping;
	}
/*	
███████╗██╗      ██████╗ ██╗    ██╗ █████╗ ██████╗  █████╗ ██████╗ ████████╗███████╗██████╗ 
██╔════╝██║     ██╔═══██╗██║    ██║██╔══██╗██╔══██╗██╔══██╗██╔══██╗╚══██╔══╝██╔════╝██╔══██╗
█████╗  ██║     ██║   ██║██║ █╗ ██║███████║██║  ██║███████║██████╔╝   ██║   █████╗  ██████╔╝
██╔══╝  ██║     ██║   ██║██║███╗██║██╔══██║██║  ██║██╔══██║██╔═══╝    ██║   ██╔══╝  ██╔══██╗
██║     ███████╗╚██████╔╝╚███╔███╔╝██║  ██║██████╔╝██║  ██║██║        ██║   ███████╗██║  ██║
╚═╝     ╚══════╝ ╚═════╝  ╚══╝╚══╝ ╚═╝  ╚═╝╚═════╝ ╚═╝  ╚═╝╚═╝        ╚═╝   ╚══════╝╚═╝  ╚═╝	
*/
	@Bean
	public FlowHandlerAdapter flowHandlerAdapter(FlowExecutor flowExecutor) {
		FlowHandlerAdapter handlerAdapter = new FlowHandlerAdapter();
		handlerAdapter.setFlowExecutor(flowExecutor);
		handlerAdapter.setSaveOutputToFlashScopeOnRedirect(true);
		return handlerAdapter;
	}
	
	//=========================
	//OTHERs WebFlow Components
	//=========================
	
//	@Bean
//	public ViewResolver tilesViewResolver4WebFlow (){
//		TilesViewResolver tilesViewResolver4WebFlow
//			= new TilesViewResolver();
//		return tilesViewResolver4WebFlow;
//	}
	
	@Bean
	public ViewFactoryCreator mvcViewFactoryCreator(List<ViewResolver> viewResolvers) {
		
		System.out.println("WEBFLOW.mvcViewFactoryCreator");
		CommonsUtils commonsUtils = CommonsUtils.getInstance();
		
		ViewResolverComposite viewResolverComposite 
			= (ViewResolverComposite) viewResolvers.get(0);
		
		
		ContentNegotiatingViewResolver contentNegotiatingViewResolver
			= (ContentNegotiatingViewResolver) viewResolverComposite.getViewResolvers().get(0);
		
		//IMPRIMIR
		commonsUtils.printList(contentNegotiatingViewResolver.getViewResolvers());
/*WEBFLOW.mvcViewFactoryCreator
| web.view_resolvers.XlsViewResolver@430af406                                    
| web.view_resolvers.PdfViewResolver@2110f0da                                    
| web.view_resolvers.JsonViewResolver@6152acfb                                   
| org.springframework.web.servlet.view.tiles3.TilesViewResolver@11ee49d5         
| org.springframework.web.servlet.view.BeanNameViewResolver@550252a              
| org.springframework.web.servlet.view.velocity.VelocityViewResolver@23e0937a    
| org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver@5721d689
| org.springframework.web.servlet.view.InternalResourceViewResolver@34e28d59 
*/
		MvcViewFactoryCreator factoryCreator = new MvcViewFactoryCreator();
		factoryCreator.setViewResolvers(viewResolvers);
		
		//factoryCreator.setViewResolvers(Arrays.<ViewResolver>asList(this.mvcConfig.tilesViewResolver()));
		//factoryCreator.setUseSpringBeanBinding(true);
		return factoryCreator;
	}
	
/*
███████╗██╗      ██████╗ ██╗    ██╗██████╗ ██╗   ██╗██╗██╗     ██████╗ ███████╗██████╗ 
██╔════╝██║     ██╔═══██╗██║    ██║██╔══██╗██║   ██║██║██║     ██╔══██╗██╔════╝██╔══██╗
█████╗  ██║     ██║   ██║██║ █╗ ██║██████╔╝██║   ██║██║██║     ██║  ██║█████╗  ██████╔╝  
██╔══╝  ██║     ██║   ██║██║███╗██║██╔══██╗██║   ██║██║██║     ██║  ██║██╔══╝  ██╔══██╗  
██║     ███████╗╚██████╔╝╚███╔███╔╝██████╔╝╚██████╔╝██║███████╗██████╔╝███████╗██║  ██║
╚═╝     ╚══════╝ ╚═════╝  ╚══╝╚══╝ ╚═════╝  ╚═════╝ ╚═╝╚══════╝╚═════╝ ╚══════╝╚═╝  ╚═╝
███████╗███████╗██████╗ ██╗   ██╗██╗ ██████╗███████╗
██╔════╝██╔════╝██╔══██╗██║   ██║██║██╔════╝██╔════╝
███████╗█████╗  ██████╔╝██║   ██║██║██║     █████╗  
╚════██║██╔══╝  ██╔══██╗╚██╗ ██╔╝██║██║     ██╔══╝  
███████║███████╗██║  ██║ ╚████╔╝ ██║╚██████╗███████╗
╚══════╝╚══════╝╚═╝  ╚═╝  ╚═══╝  ╚═╝ ╚═════╝╚══════╝*/
	@Bean
	public FlowBuilderServices flowBuilderServices(
			ViewFactoryCreator viewFactoryCreator, 
			@Qualifier("mvcValidator")
			Validator validator
			
			) {
		
		FlowBuilderServicesBuilder flowBuilderServicesBuilder = super.getFlowBuilderServicesBuilder();
		flowBuilderServicesBuilder.setViewFactoryCreator(viewFactoryCreator);
		flowBuilderServicesBuilder.setValidator(validator);
		flowBuilderServicesBuilder.setConversionService(conversionService());
		flowBuilderServicesBuilder.setDevelopmentMode(true);
		
		return flowBuilderServicesBuilder.build();
	}
	
	
/*
███████╗██╗      ██████╗ ██╗    ██╗██████╗ ███████╗ ██████╗ ██╗███████╗████████╗██████╗ ██╗   ██╗
██╔════╝██║     ██╔═══██╗██║    ██║██╔══██╗██╔════╝██╔════╝ ██║██╔════╝╚══██╔══╝██╔══██╗╚██╗ ██╔╝
█████╗  ██║     ██║   ██║██║ █╗ ██║██████╔╝█████╗  ██║  ███╗██║███████╗   ██║   ██████╔╝ ╚████╔╝ 
██╔══╝  ██║     ██║   ██║██║███╗██║██╔══██╗██╔══╝  ██║   ██║██║╚════██║   ██║   ██╔══██╗  ╚██╔╝  
██║     ███████╗╚██████╔╝╚███╔███╔╝██║  ██║███████╗╚██████╔╝██║███████║   ██║   ██║  ██║   ██║   
╚═╝     ╚══════╝ ╚═════╝  ╚══╝╚══╝ ╚═╝  ╚═╝╚══════╝ ╚═════╝ ╚═╝╚══════╝   ╚═╝   ╚═╝  ╚═╝   ╚═╝   
*/
	@Bean
	public FlowDefinitionRegistry flowRegistry(FlowBuilderServices flowBuilderServices) {
		
		FlowDefinitionRegistryBuilder flowDefinitionRegistryBuilder = super.getFlowDefinitionRegistryBuilder(flowBuilderServices);
		// ONE AT A TIME 
		//flowDefinitionRegistryBuilder.addFlowLocation("/WEB-INF/flights/checkin/checkin.xml");
		flowDefinitionRegistryBuilder.setBasePath("/WEB-INF/flows");
		flowDefinitionRegistryBuilder.addFlowLocationPattern("/**/*-flow.xml");
		
		return flowDefinitionRegistryBuilder.build();
	}
/*
███████╗██╗      ██████╗ ██╗    ██╗███████╗██╗  ██╗███████╗ ██████╗██╗   ██╗████████╗ ██████╗ ██████╗ 
██╔════╝██║     ██╔═══██╗██║    ██║██╔════╝╚██╗██╔╝██╔════╝██╔════╝██║   ██║╚══██╔══╝██╔═══██╗██╔══██╗
█████╗  ██║     ██║   ██║██║ █╗ ██║█████╗   ╚███╔╝ █████╗  ██║     ██║   ██║   ██║   ██║   ██║██████╔╝
██╔══╝  ██║     ██║   ██║██║███╗██║██╔══╝   ██╔██╗ ██╔══╝  ██║     ██║   ██║   ██║   ██║   ██║██╔══██╗
██║     ███████╗╚██████╔╝╚███╔███╔╝███████╗██╔╝ ██╗███████╗╚██████╗╚██████╔╝   ██║   ╚██████╔╝██║  ██║
╚═╝     ╚══════╝ ╚═════╝  ╚══╝╚══╝ ╚══════╝╚═╝  ╚═╝╚══════╝ ╚═════╝ ╚═════╝    ╚═╝    ╚═════╝ ╚═╝  ╚═╝
*/
	@Bean
	public FlowExecutor flowExecutor(FlowDefinitionRegistry flowDefinitionRegistry) {
		
		FlowExecutorBuilder flowExecutorBuilder = super.getFlowExecutorBuilder(flowDefinitionRegistry);
		
		// apply the listener for all flow definitions
		//flowExecutorBuilder.addFlowExecutionListener(new AuditFlowExecutorListener(), "*")
		
		flowExecutorBuilder.setMaxFlowExecutions(5);
		flowExecutorBuilder.setMaxFlowExecutionSnapshots(30);
		
		return flowExecutorBuilder.build();
	}


	@Bean
	public ConversionService conversionService() {
		//return new DefaultConversionService(conversionServiceFactoryBean().getObject());
		
		org.springframework.core.convert.ConversionService mvcConSvc = getApplicationContext().getBean(
				org.springframework.core.convert.ConversionService.class);
		
//System.out.println("[-ConversionService-] " + mvcConSvc);
/*		
[-ConversionService-] ConversionService converters =
	@org.springframework.format.annotation.DateTimeFormat java.lang.Long -> java.lang.String: org.springframework.format.datetime.DateTimeFormatAnnotationFormatterFactory@6301a9f7,@org.springframework.format.annotation.NumberFormat java.lang.Long -> java.lang.String: org.springframework.format.number.NumberFormatAnnotationFormatterFactory@34a5767c
	@org.springframework.format.annotation.DateTimeFormat java.time.LocalDate -> java.lang.String: org.springframework.format.datetime.standard.Jsr310DateTimeFormatAnnotationFormatterFactory@2d5c3776,java.time.LocalDate -> java.lang.String : org.springframework.format.datetime.standard.TemporalAccessorPrinter@5ad1fb17
	@org.springframework.format.annotation.DateTimeFormat java.time.LocalDateTime -> java.lang.String: org.springframework.format.datetime.standard.Jsr310DateTimeFormatAnnotationFormatterFactory@2d5c3776,java.time.LocalDateTime -> java.lang.String : org.springframework.format.datetime.standard.TemporalAccessorPrinter@53001cd8
	@org.springframework.format.annotation.DateTimeFormat java.time.LocalTime -> java.lang.String: org.springframework.format.datetime.standard.Jsr310DateTimeFormatAnnotationFormatterFactory@2d5c3776,java.time.LocalTime -> java.lang.String : org.springframework.format.datetime.standard.TemporalAccessorPrinter@4a772ff8
	@org.springframework.format.annotation.DateTimeFormat java.time.OffsetDateTime -> java.lang.String: org.springframework.format.datetime.standard.Jsr310DateTimeFormatAnnotationFormatterFactory@2d5c3776,java.time.OffsetDateTime -> java.lang.String : org.springframework.format.datetime.standard.TemporalAccessorPrinter@44388595
	@org.springframework.format.annotation.DateTimeFormat java.time.OffsetTime -> java.lang.String: org.springframework.format.datetime.standard.Jsr310DateTimeFormatAnnotationFormatterFactory@2d5c3776,java.time.OffsetTime -> java.lang.String : org.springframework.format.datetime.standard.TemporalAccessorPrinter@68577d6d
	@org.springframework.format.annotation.DateTimeFormat java.time.ZonedDateTime -> java.lang.String: org.springframework.format.datetime.standard.Jsr310DateTimeFormatAnnotationFormatterFactory@2d5c3776,java.time.ZonedDateTime -> java.lang.String : org.springframework.format.datetime.standard.TemporalAccessorPrinter@7307a329
	@org.springframework.format.annotation.DateTimeFormat java.util.Calendar -> java.lang.String: org.springframework.format.datetime.DateTimeFormatAnnotationFormatterFactory@6301a9f7
	@org.springframework.format.annotation.DateTimeFormat java.util.Date -> java.lang.String: org.springframework.format.datetime.DateTimeFormatAnnotationFormatterFactory@6301a9f7
	@org.springframework.format.annotation.NumberFormat java.lang.Byte -> java.lang.String: org.springframework.format.number.NumberFormatAnnotationFormatterFactory@34a5767c
	@org.springframework.format.annotation.NumberFormat java.lang.Double -> java.lang.String: org.springframework.format.number.NumberFormatAnnotationFormatterFactory@34a5767c
	@org.springframework.format.annotation.NumberFormat java.lang.Float -> java.lang.String: org.springframework.format.number.NumberFormatAnnotationFormatterFactory@34a5767c
	@org.springframework.format.annotation.NumberFormat java.lang.Integer -> java.lang.String: org.springframework.format.number.NumberFormatAnnotationFormatterFactory@34a5767c
	@org.springframework.format.annotation.NumberFormat java.lang.Short -> java.lang.String: org.springframework.format.number.NumberFormatAnnotationFormatterFactory@34a5767c
	@org.springframework.format.annotation.NumberFormat java.math.BigDecimal -> java.lang.String: org.springframework.format.number.NumberFormatAnnotationFormatterFactory@34a5767c
	@org.springframework.format.annotation.NumberFormat java.math.BigInteger -> java.lang.String: org.springframework.format.number.NumberFormatAnnotationFormatterFactory@34a5767c
	java.lang.Boolean -> java.lang.String : org.springframework.core.convert.support.ObjectToStringConverter@4c051efd
	java.lang.Character -> java.lang.Number : org.springframework.core.convert.support.CharacterToNumberFactory@26be7c78
	java.lang.Character -> java.lang.String : org.springframework.core.convert.support.ObjectToStringConverter@23699d72
	java.lang.Enum -> java.lang.Integer : org.springframework.core.convert.support.EnumToIntegerConverter@dfa2231
	java.lang.Enum -> java.lang.String : org.springframework.core.convert.support.EnumToStringConverter@e4c00cc
	java.lang.Integer -> java.lang.Enum : org.springframework.core.convert.support.IntegerToEnumConverterFactory@69ec2b07
	java.lang.Long -> java.time.Instant : org.springframework.format.datetime.standard.DateTimeConverters$LongToInstantConverter@3483772e
	java.lang.Long -> java.util.Calendar : org.springframework.format.datetime.DateFormatterRegistrar$LongToCalendarConverter@73938b4c,java.lang.Long -> java.util.Calendar : org.springframework.format.datetime.DateFormatterRegistrar$LongToCalendarConverter@230c5259
	java.lang.Long -> java.util.Date : org.springframework.format.datetime.DateFormatterRegistrar$LongToDateConverter@1c940558,java.lang.Long -> java.util.Date : org.springframework.format.datetime.DateFormatterRegistrar$LongToDateConverter@6ac9a091
	java.lang.Number -> java.lang.Character : org.springframework.core.convert.support.NumberToCharacterConverter@6f8f0b26
	java.lang.Number -> java.lang.Number : org.springframework.core.convert.support.NumberToNumberConverterFactory@709e0a72
	java.lang.Number -> java.lang.String : org.springframework.core.convert.support.ObjectToStringConverter@26adfabd
	java.lang.String -> @org.springframework.format.annotation.DateTimeFormat java.lang.Long: org.springframework.format.datetime.DateTimeFormatAnnotationFormatterFactory@6301a9f7,java.lang.String -> @org.springframework.format.annotation.NumberFormat java.lang.Long: org.springframework.format.number.NumberFormatAnnotationFormatterFactory@34a5767c
	java.lang.String -> @org.springframework.format.annotation.DateTimeFormat java.time.LocalDate: org.springframework.format.datetime.standard.Jsr310DateTimeFormatAnnotationFormatterFactory@2d5c3776,java.lang.String -> java.time.LocalDate: org.springframework.format.datetime.standard.TemporalAccessorParser@7544dd64
	java.lang.String -> @org.springframework.format.annotation.DateTimeFormat java.time.LocalDateTime: org.springframework.format.datetime.standard.Jsr310DateTimeFormatAnnotationFormatterFactory@2d5c3776,java.lang.String -> java.time.LocalDateTime: org.springframework.format.datetime.standard.TemporalAccessorParser@fc5421
	java.lang.String -> @org.springframework.format.annotation.DateTimeFormat java.time.LocalTime: org.springframework.format.datetime.standard.Jsr310DateTimeFormatAnnotationFormatterFactory@2d5c3776,java.lang.String -> java.time.LocalTime: org.springframework.format.datetime.standard.TemporalAccessorParser@65c4647
	java.lang.String -> @org.springframework.format.annotation.DateTimeFormat java.time.OffsetDateTime: org.springframework.format.datetime.standard.Jsr310DateTimeFormatAnnotationFormatterFactory@2d5c3776,java.lang.String -> java.time.OffsetDateTime: org.springframework.format.datetime.standard.TemporalAccessorParser@26079c6f
	java.lang.String -> @org.springframework.format.annotation.DateTimeFormat java.time.OffsetTime: org.springframework.format.datetime.standard.Jsr310DateTimeFormatAnnotationFormatterFactory@2d5c3776,java.lang.String -> java.time.OffsetTime: org.springframework.format.datetime.standard.TemporalAccessorParser@12cbbb2a
	java.lang.String -> @org.springframework.format.annotation.DateTimeFormat java.time.ZonedDateTime: org.springframework.format.datetime.standard.Jsr310DateTimeFormatAnnotationFormatterFactory@2d5c3776,java.lang.String -> java.time.ZonedDateTime: org.springframework.format.datetime.standard.TemporalAccessorParser@170dbd70
	java.lang.String -> @org.springframework.format.annotation.DateTimeFormat java.util.Calendar: org.springframework.format.datetime.DateTimeFormatAnnotationFormatterFactory@6301a9f7
	java.lang.String -> @org.springframework.format.annotation.DateTimeFormat java.util.Date: org.springframework.format.datetime.DateTimeFormatAnnotationFormatterFactory@6301a9f7
	java.lang.String -> @org.springframework.format.annotation.NumberFormat java.lang.Byte: org.springframework.format.number.NumberFormatAnnotationFormatterFactory@34a5767c
	java.lang.String -> @org.springframework.format.annotation.NumberFormat java.lang.Double: org.springframework.format.number.NumberFormatAnnotationFormatterFactory@34a5767c
	java.lang.String -> @org.springframework.format.annotation.NumberFormat java.lang.Float: org.springframework.format.number.NumberFormatAnnotationFormatterFactory@34a5767c
	java.lang.String -> @org.springframework.format.annotation.NumberFormat java.lang.Integer: org.springframework.format.number.NumberFormatAnnotationFormatterFactory@34a5767c
	java.lang.String -> @org.springframework.format.annotation.NumberFormat java.lang.Short: org.springframework.format.number.NumberFormatAnnotationFormatterFactory@34a5767c
	java.lang.String -> @org.springframework.format.annotation.NumberFormat java.math.BigDecimal: org.springframework.format.number.NumberFormatAnnotationFormatterFactory@34a5767c
	java.lang.String -> @org.springframework.format.annotation.NumberFormat java.math.BigInteger: org.springframework.format.number.NumberFormatAnnotationFormatterFactory@34a5767c
	java.lang.String -> java.lang.Boolean : org.springframework.core.convert.support.StringToBooleanConverter@236895ef
	java.lang.String -> java.lang.Character : org.springframework.core.convert.support.StringToCharacterConverter@23a4deee
	java.lang.String -> java.lang.Enum : org.springframework.core.convert.support.StringToEnumConverterFactory@5b44ea60
	java.lang.String -> java.lang.Number : org.springframework.core.convert.support.StringToNumberConverterFactory@19aaa011
	java.lang.String -> java.nio.charset.Charset : org.springframework.core.convert.support.StringToCharsetConverter@17094e78
	java.lang.String -> java.time.Duration: org.springframework.format.datetime.standard.DurationFormatter@5b4e2a77
	java.lang.String -> java.time.Instant: org.springframework.format.datetime.standard.InstantFormatter@12df90cb
	java.lang.String -> java.time.MonthDay: org.springframework.format.datetime.standard.MonthDayFormatter@7aa45b4e
	java.lang.String -> java.time.Period: org.springframework.format.datetime.standard.PeriodFormatter@8a1821b
	java.lang.String -> java.time.YearMonth: org.springframework.format.datetime.standard.YearMonthFormatter@1493d8df
	java.lang.String -> java.util.Currency : org.springframework.core.convert.support.StringToCurrencyConverter@34200b9f
	java.lang.String -> java.util.Locale : org.springframework.core.convert.support.StringToLocaleConverter@7191ce04
	java.lang.String -> java.util.Properties : org.springframework.core.convert.support.StringToPropertiesConverter@59d6b2d3
	java.lang.String -> java.util.TimeZone : org.springframework.core.convert.support.StringToTimeZoneConverter@16504820
	java.lang.String -> java.util.UUID : org.springframework.core.convert.support.StringToUUIDConverter@567361dd
	java.nio.charset.Charset -> java.lang.String : org.springframework.core.convert.support.ObjectToStringConverter@4bbe1d02
	java.time.Duration -> java.lang.String : org.springframework.format.datetime.standard.DurationFormatter@5b4e2a77
	java.time.Instant -> java.lang.Long : org.springframework.format.datetime.standard.DateTimeConverters$InstantToLongConverter@698ad0c4
	java.time.Instant -> java.lang.String : org.springframework.format.datetime.standard.InstantFormatter@12df90cb
	java.time.LocalDateTime -> java.time.LocalDate : org.springframework.format.datetime.standard.DateTimeConverters$LocalDateTimeToLocalDateConverter@1f0e2da5
	java.time.LocalDateTime -> java.time.LocalTime : org.springframework.format.datetime.standard.DateTimeConverters$LocalDateTimeToLocalTimeConverter@3864dbfd
	java.time.MonthDay -> java.lang.String : org.springframework.format.datetime.standard.MonthDayFormatter@7aa45b4e
	java.time.OffsetDateTime -> java.time.Instant : org.springframework.format.datetime.standard.DateTimeConverters$OffsetDateTimeToInstantConverter@2c3c0d6c
	java.time.OffsetDateTime -> java.time.LocalDate : org.springframework.format.datetime.standard.DateTimeConverters$OffsetDateTimeToLocalDateConverter@5f227331
	java.time.OffsetDateTime -> java.time.LocalDateTime : org.springframework.format.datetime.standard.DateTimeConverters$OffsetDateTimeToLocalDateTimeConverter@6d0b1013
	java.time.OffsetDateTime -> java.time.LocalTime : org.springframework.format.datetime.standard.DateTimeConverters$OffsetDateTimeToLocalTimeConverter@236eac3f
	java.time.OffsetDateTime -> java.time.ZonedDateTime : org.springframework.format.datetime.standard.DateTimeConverters$OffsetDateTimeToZonedDateTimeConverter@60cb51bf
	java.time.Period -> java.lang.String : org.springframework.format.datetime.standard.PeriodFormatter@8a1821b
	java.time.YearMonth -> java.lang.String : org.springframework.format.datetime.standard.YearMonthFormatter@1493d8df
	java.time.ZoneId -> java.util.TimeZone : org.springframework.core.convert.support.ZoneIdToTimeZoneConverter@4a7eb538
	java.time.ZonedDateTime -> java.time.Instant : org.springframework.format.datetime.standard.DateTimeConverters$ZonedDateTimeToInstantConverter@2645610c
	java.time.ZonedDateTime -> java.time.LocalDate : org.springframework.format.datetime.standard.DateTimeConverters$ZonedDateTimeToLocalDateConverter@57c2fe56
	java.time.ZonedDateTime -> java.time.LocalDateTime : org.springframework.format.datetime.standard.DateTimeConverters$ZonedDateTimeToLocalDateTimeConverter@7890940e
	java.time.ZonedDateTime -> java.time.LocalTime : org.springframework.format.datetime.standard.DateTimeConverters$ZonedDateTimeToLocalTimeConverter@161df06d
	java.time.ZonedDateTime -> java.time.OffsetDateTime : org.springframework.format.datetime.standard.DateTimeConverters$ZonedDateTimeToOffsetDateTimeConverter@663503d2
	java.time.ZonedDateTime -> java.util.Calendar : org.springframework.core.convert.support.ZonedDateTimeToCalendarConverter@5eefec51
	java.util.Calendar -> java.lang.Long : org.springframework.format.datetime.DateFormatterRegistrar$CalendarToLongConverter@7eacfdf1,java.util.Calendar -> java.lang.Long : org.springframework.format.datetime.DateFormatterRegistrar$CalendarToLongConverter@17c8f0ef
	java.util.Calendar -> java.time.Instant : org.springframework.format.datetime.standard.DateTimeConverters$CalendarToInstantConverter@7ca17b79
	java.util.Calendar -> java.time.LocalDate : org.springframework.format.datetime.standard.DateTimeConverters$CalendarToLocalDateConverter@7527a9ba
	java.util.Calendar -> java.time.LocalDateTime : org.springframework.format.datetime.standard.DateTimeConverters$CalendarToLocalDateTimeConverter@6995a0fb
	java.util.Calendar -> java.time.LocalTime : org.springframework.format.datetime.standard.DateTimeConverters$CalendarToLocalTimeConverter@393c9d1f
	java.util.Calendar -> java.time.OffsetDateTime : org.springframework.format.datetime.standard.DateTimeConverters$CalendarToOffsetDateTimeConverter@4fdb5f57
	java.util.Calendar -> java.time.ZonedDateTime : org.springframework.format.datetime.standard.DateTimeConverters$CalendarToZonedDateTimeConverter@7a654380
	java.util.Calendar -> java.util.Date : org.springframework.format.datetime.DateFormatterRegistrar$CalendarToDateConverter@2dd4c07e,java.util.Calendar -> java.util.Date : org.springframework.format.datetime.DateFormatterRegistrar$CalendarToDateConverter@7b41bd56
	java.util.Currency -> java.lang.String : org.springframework.core.convert.support.ObjectToStringConverter@47156ab
	java.util.Date -> java.lang.Long : org.springframework.format.datetime.DateFormatterRegistrar$DateToLongConverter@4e66e79b,java.util.Date -> java.lang.Long : org.springframework.format.datetime.DateFormatterRegistrar$DateToLongConverter@25de73a9
	java.util.Date -> java.util.Calendar : org.springframework.format.datetime.DateFormatterRegistrar$DateToCalendarConverter@3ad7f7f8,java.util.Date -> java.util.Calendar : org.springframework.format.datetime.DateFormatterRegistrar$DateToCalendarConverter@54f7af44
	java.util.Locale -> java.lang.String : org.springframework.core.convert.support.ObjectToStringConverter@1fb5fa9a
	java.util.Properties -> java.lang.String : org.springframework.core.convert.support.PropertiesToStringConverter@62bbe2e6
	java.util.UUID -> java.lang.String : org.springframework.core.convert.support.ObjectToStringConverter@58b9b1b6
	org.springframework.core.convert.support.ArrayToArrayConverter@44345d9a
	org.springframework.core.convert.support.ArrayToCollectionConverter@3415c058
	org.springframework.core.convert.support.ArrayToObjectConverter@7ba39ec7
	org.springframework.core.convert.support.ArrayToStringConverter@67f77f84
	org.springframework.core.convert.support.ByteBufferConverter@4639e71a
	org.springframework.core.convert.support.ByteBufferConverter@4639e71a
	org.springframework.core.convert.support.ByteBufferConverter@4639e71a
	org.springframework.core.convert.support.ByteBufferConverter@4639e71a
	org.springframework.core.convert.support.CollectionToArrayConverter@904b8ee
	org.springframework.core.convert.support.CollectionToCollectionConverter@511570ab
	org.springframework.core.convert.support.CollectionToObjectConverter@8a1673
	org.springframework.core.convert.support.CollectionToStringConverter@2f0a5827
	org.springframework.core.convert.support.FallbackObjectToStringConverter@7c0a2804
	org.springframework.core.convert.support.IdToEntityConverter@57e7d1a1,org.springframework.core.convert.support.ObjectToObjectConverter@43e28235
	org.springframework.core.convert.support.MapToMapConverter@4b987dd1
	org.springframework.core.convert.support.ObjectToArrayConverter@735d0297
	org.springframework.core.convert.support.ObjectToCollectionConverter@1b58e54f
	org.springframework.core.convert.support.ObjectToOptionalConverter@5da6ceb0
	org.springframework.core.convert.support.StreamConverter@66ee5d63
	org.springframework.core.convert.support.StreamConverter@66ee5d63
	org.springframework.core.convert.support.StreamConverter@66ee5d63
	org.springframework.core.convert.support.StreamConverter@66ee5d63
	org.springframework.core.convert.support.StringToArrayConverter@149366d
	org.springframework.core.convert.support.StringToCollectionConverter@6738f9f5	
		
*/
		DefaultConversionService defaultConversionService = 
			new DefaultConversionService(mvcConSvc);
		
		//CUSTOM BINDING CONVERTER
		defaultConversionService.addConverter(new UserToStringConverter());


		return defaultConversionService;
	}

//	@Bean
//	public FormattingConversionServiceFactoryBean conversionServiceFactoryBean() {
//		FormattingConversionServiceFactoryBean fcs = new FormattingConversionServiceFactoryBean();
//		Set<Formatter> fmts = new HashSet<>();
//		// fmts.add(this.mvcConfig.dateFormatter());
//		// fmts.add(this.mvcConfig.hospitalFormatter());
//		fcs.setFormatters(fmts);
//		return fcs;
//	}

}
