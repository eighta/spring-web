package a8.test.java;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.RequestToViewNameTranslator;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.ViewResolver;

import a8.conf.BackendJavaConfig;
import a8.core.Heart;
import a8.core.Life;
import a8.utils.CommonsUtils;
import web.conf.MvcJavaConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration //XXX COMO ESPECIFICAR QUIEN ES EL FRONT Y QUIEN ES EL ROOT?
@ContextConfiguration(classes={MvcJavaConfig.class,BackendJavaConfig.class})
public class FrontendJavaConfigTest {

	CommonsUtils commonsUtils = CommonsUtils.getInstance();
	
	@Autowired
	public WebApplicationContext webApplicationContext;
	
	@Test
	public void accessToMvcComponents() throws Exception{
		
		MockMvc mockMvc = webAppContextSetup(webApplicationContext).build();
		
		mockMvc.perform( get("/tasks/mvc_components"))
		.andExpect(status().isOk());
		//.andExpect(content().contentType(MediaType.TEXT_HTML));
	}
	
	@Test
	public void printServletApplicationAttributeNames(){
		ServletContext servletContext = webApplicationContext.getServletContext();
		Enumeration<String> attributeNames = servletContext.getAttributeNames();
		commonsUtils.printEnumeration(attributeNames);
	}
	
	@Test
	public void getMvcComponents(){
		
		Heart heart = Heart.getInstance();
		heart.setFrontendApplicationContext(webApplicationContext);
		
		ServletContext servletContext = webApplicationContext.getServletContext();
		Life life = Life.getInstance(servletContext);
		
		//HandlerMapping's
		Map<String, HandlerMapping> handlerMappings = life.getHandlerMappings();
		assertEquals(5,handlerMappings.size());
		assertNotNull(handlerMappings.get("beanNameHandlerMapping"));
		assertNotNull(handlerMappings.get("resourceHandlerMapping"));
		assertNotNull(handlerMappings.get("defaultServletHandlerMapping"));
		assertNotNull(handlerMappings.get("viewControllerHandlerMapping"));
		assertNotNull(handlerMappings.get("requestMappingHandlerMapping"));
		
		//HandlerAdapter's
		Map<String, HandlerAdapter> handlerAdapters = life.getHandlerAdapters();
		assertEquals(3,handlerAdapters.size());
		assertNotNull(handlerAdapters.get("simpleControllerHandlerAdapter"));
		assertNotNull(handlerAdapters.get("requestMappingHandlerAdapter"));
		assertNotNull(handlerAdapters.get("httpRequestHandlerAdapter"));
		
		//ViewResolver's
		Map<String, ViewResolver> viewResolvers = life.getViewResolvers();
		assertEquals(2,viewResolvers.size());
		assertNotNull(viewResolvers.get("mvcViewResolver"));
		assertNotNull(viewResolvers.get("getViewResolver"));
		
		//HandlerExceptionResolver's
		Map<String, HandlerExceptionResolver> handlerExceptionResolvers = life.getHandlerExceptionResolvers();
		assertEquals(1,handlerExceptionResolvers.size());
		assertNotNull(handlerExceptionResolvers.get("handlerExceptionResolver"));
		
		//LocaleResolver's
		Map<String, LocaleResolver> localeResolvers = life.getLocaleResolvers();
		assertEquals(0,localeResolvers.size());
		
		//ThemeResolver's
		Map<String, ThemeResolver> themeResolvers = life.getThemeResolvers();
		assertEquals(0,themeResolvers.size());
		
		//RequestToViewNameTranslator's
		Map<String, RequestToViewNameTranslator> requestToViewNameTranslators = life.getRequestToViewNameTranslators();
		assertEquals(0,requestToViewNameTranslators.size());
		
		//FlashMapManager's
		Map<String, FlashMapManager> flashManagers = life.getFlashMapManagers();
		assertEquals(0,flashManagers.size());
		
	}
	
	
}