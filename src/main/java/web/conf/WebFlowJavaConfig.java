package web.conf;

import java.util.List;

import org.springframework.binding.convert.ConversionService;
import org.springframework.binding.convert.service.DefaultConversionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
╚══════╝╚══════╝╚═╝  ╚═╝  ╚═══╝  ╚═╝ ╚═════╝╚══════╝
*/
	@Bean
	public FlowBuilderServices flowBuilderServices(ViewFactoryCreator viewFactoryCreator ) {
		
		FlowBuilderServicesBuilder flowBuilderServicesBuilder = super.getFlowBuilderServicesBuilder();
		flowBuilderServicesBuilder.setViewFactoryCreator(viewFactoryCreator);
		//flowBuilderServicesBuilder.setValidator(this.mvcConfig.validator())
		//flowBuilderServicesBuilder.setConversionService(conversionService())
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
		return new DefaultConversionService(mvcConSvc);
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
