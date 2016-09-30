package web.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.config.FlowBuilderServicesBuilder;
import org.springframework.webflow.config.FlowDefinitionRegistryBuilder;
import org.springframework.webflow.config.FlowExecutorBuilder;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.builder.MvcViewFactoryCreator;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;

@Configuration
public class WebFlowJavaConfig 
extends AbstractFlowConfiguration 
{

	//==================
	//FlowHandlerMapping
	//==================
	@Bean
	public FlowHandlerMapping flowHandlerMapping() {
		FlowHandlerMapping handlerMapping = new FlowHandlerMapping();
		handlerMapping.setOrder(-1);
		handlerMapping.setFlowRegistry(this.flowRegistry());
		return handlerMapping;
	}
	
	//==================
	//FlowHandlerAdapter
	//==================
	@Bean
	public FlowHandlerAdapter flowHandlerAdapter() {
		FlowHandlerAdapter handlerAdapter = new FlowHandlerAdapter();
		handlerAdapter.setFlowExecutor(this.flowExecutor());
		handlerAdapter.setSaveOutputToFlashScopeOnRedirect(true);
		return handlerAdapter;
	}
	
	//=========================
	//OTHERs WebFlow Components
	//=========================
	
/*	@Bean
	public MvcViewFactoryCreator mvcViewFactoryCreator() {
		MvcViewFactoryCreator factoryCreator = new MvcViewFactoryCreator();
		//factoryCreator.setViewResolvers(Arrays.<ViewResolver>asList(this.mvcConfig.tilesViewResolver()));
		//factoryCreator.setUseSpringBeanBinding(true);
		return factoryCreator;
	}
*/	
	
	@Bean
	public FlowBuilderServices flowBuilderServices() {
		
		FlowBuilderServicesBuilder flowBuilderServicesBuilder = super.getFlowBuilderServicesBuilder();
		//flowBuilderServicesBuilder.setViewFactoryCreator(this.mvcViewFactoryCreator());
		//flowBuilderServicesBuilder.setValidator(this.mvcConfig.validator())
		//flowBuilderServicesBuilder.setConversionService(conversionService())
		//flowBuilderServicesBuilder.setDevelopmentMode(true)
		
		return flowBuilderServicesBuilder.build();
	}
	
	@Bean
	public FlowDefinitionRegistry flowRegistry() {
		
		FlowDefinitionRegistryBuilder flowDefinitionRegistryBuilder = super.getFlowDefinitionRegistryBuilder(this.flowBuilderServices());
		flowDefinitionRegistryBuilder.setBasePath("/WEB-INF/flows");
		flowDefinitionRegistryBuilder.addFlowLocationPattern("/**/*-flow.xml");
		
		return flowDefinitionRegistryBuilder.build();
	}
	
	@Bean
	public FlowExecutor flowExecutor() {
		
		FlowExecutorBuilder flowExecutorBuilder = super.getFlowExecutorBuilder(this.flowRegistry());
		
		// apply the listener for all flow definitions
		//flowExecutorBuilder.addFlowExecutionListener(new AuditFlowExecutorListener(), "*")
		
		flowExecutorBuilder.setMaxFlowExecutions(5);
		flowExecutorBuilder.setMaxFlowExecutionSnapshots(30);
		
		return flowExecutorBuilder.build();
	}

//
//	@Bean
//	public DefaultConversionService conversionService() {
//		return new DefaultConversionService(conversionServiceFactoryBean().getObject());
//	}
//
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
