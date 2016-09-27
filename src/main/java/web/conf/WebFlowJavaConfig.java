package web.conf;

import java.util.HashSet;
import java.util.Set;

import org.springframework.binding.convert.service.DefaultConversionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.builder.MvcViewFactoryCreator;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;

@Configuration
public class WebFlowJavaConfig extends AbstractFlowConfiguration {

	@Bean
	public FlowHandlerMapping flowHandlerMapping() {
		FlowHandlerMapping handlerMapping = new FlowHandlerMapping();
		handlerMapping.setOrder(-1);
		handlerMapping.setFlowRegistry(this.flowRegistry());
		return handlerMapping;
	}

	@Bean
	public FlowHandlerAdapter flowHandlerAdapter() {
		FlowHandlerAdapter handlerAdapter = new FlowHandlerAdapter();
		handlerAdapter.setFlowExecutor(this.flowExecutor());
		handlerAdapter.setSaveOutputToFlashScopeOnRedirect(true);
		return handlerAdapter;
	}

	@Bean
	public FlowExecutor flowExecutor() {
		return getFlowExecutorBuilder(this.flowRegistry())
				
				// apply the listener for all flow definitions
				// .addFlowExecutionListener(new AuditFlowExecutorListener(), "*")
				
				.setMaxFlowExecutions(5)
				.setMaxFlowExecutionSnapshots(30)
				.build();
	}

	@Bean
	public FlowDefinitionRegistry flowRegistry() {
		return getFlowDefinitionRegistryBuilder(flowBuilderServices()).setBasePath("/WEB-INF/flows")
				.addFlowLocationPattern("/**/*-flow.xml").build();
	}

	@Bean
	public FlowBuilderServices flowBuilderServices() {
		return getFlowBuilderServicesBuilder().setViewFactoryCreator(mvcViewFactoryCreator())
				// .setValidator(this.mvcConfig.validator())
				.setConversionService(conversionService())
				.setDevelopmentMode(true).build();
	}

	@Bean
	public MvcViewFactoryCreator mvcViewFactoryCreator() {
		MvcViewFactoryCreator factoryCreator = new MvcViewFactoryCreator();
		// factoryCreator.setViewResolvers(Arrays.<ViewResolver>asList(this.mvcConfig.tilesViewResolver()));
		factoryCreator.setUseSpringBeanBinding(true);
		return factoryCreator;
	}

	@Bean
	public DefaultConversionService conversionService() {
		return new DefaultConversionService(conversionServiceFactoryBean().getObject());
	}

	@Bean
	public FormattingConversionServiceFactoryBean conversionServiceFactoryBean() {
		FormattingConversionServiceFactoryBean fcs = new FormattingConversionServiceFactoryBean();
		Set<Formatter> fmts = new HashSet<>();
		// fmts.add(this.mvcConfig.dateFormatter());
		// fmts.add(this.mvcConfig.hospitalFormatter());
		fcs.setFormatters(fmts);
		return fcs;
	}

}
