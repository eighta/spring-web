package a8.core;

import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.RequestToViewNameTranslator;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.ViewResolver;

//ESTA CLASE en realidad debe ser una interface
//ya que ofrece una serie de servicios
//XXX exponerlo como spring bean
public class Life {

	private Heart heart = Heart.getInstance(); 
	
	public Map<String, FlashMapManager> getFlashMapManagers(){
		
		WebApplicationContext frontendApplicationContext = heart.getFrontendApplicationContext();
		Map<String, FlashMapManager> flashMapManagerMap = frontendApplicationContext.getBeansOfType(FlashMapManager.class);
		return flashMapManagerMap;
	}
	
	public Map<String, RequestToViewNameTranslator> getRequestToViewNameTranslators(){
		
		WebApplicationContext frontendApplicationContext = heart.getFrontendApplicationContext();
		Map<String, RequestToViewNameTranslator> requestToViewNameTranslatorMap = frontendApplicationContext.getBeansOfType(RequestToViewNameTranslator.class);
		return requestToViewNameTranslatorMap;
	}
	
	public Map<String, ThemeResolver> getThemeResolvers(){
		
		WebApplicationContext frontendApplicationContext = heart.getFrontendApplicationContext();
		Map<String, ThemeResolver> themeResolverMap = frontendApplicationContext.getBeansOfType(ThemeResolver.class);
		return themeResolverMap;
	}
	
	public Map<String, LocaleResolver> getLocaleResolvers(){
		
		WebApplicationContext frontendApplicationContext = heart.getFrontendApplicationContext();
		Map<String, LocaleResolver> localeResolverMap = frontendApplicationContext.getBeansOfType(LocaleResolver.class);
		return localeResolverMap;
	}
	
	public Map<String, HandlerExceptionResolver> getHandlerExceptionResolvers(){
		
		WebApplicationContext frontendApplicationContext = heart.getFrontendApplicationContext();
		Map<String, HandlerExceptionResolver> handlerExceptionResolverMap = frontendApplicationContext.getBeansOfType(HandlerExceptionResolver.class);
		return handlerExceptionResolverMap;
	}
	
	public Map<String, ViewResolver> getViewResolvers(){
		
		WebApplicationContext frontendApplicationContext = heart.getFrontendApplicationContext();
		Map<String, ViewResolver> viewResolverMap = frontendApplicationContext.getBeansOfType(ViewResolver.class);
		return viewResolverMap;
	}
	
	public Map<String, HandlerAdapter> getHandlerAdapters(){
		
		WebApplicationContext frontendApplicationContext = heart.getFrontendApplicationContext();
		Map<String, HandlerAdapter> handlerAdapterMap = frontendApplicationContext.getBeansOfType(HandlerAdapter.class);
		return handlerAdapterMap;
	}
	
	public Map<String, HandlerMapping> getHandlerMappings(){
		
		WebApplicationContext frontendApplicationContext = heart.getFrontendApplicationContext();
		Map<String, HandlerMapping> handlerMappingMap = frontendApplicationContext.getBeansOfType(HandlerMapping.class);
		return handlerMappingMap;
	}
	
	public String giveMeDauthersName(){
		return "SOPHIE OCHOA"; //XXX leerlo del proeprties
	}
	
	//BEING: SINGLETON
	private static Life INSTANCE = null;
	private Life(ServletContext servletContext){
//XXX		this.servletContext=servletContext;
	}
    private synchronized static void createInstance(ServletContext servletContext) {
        if (INSTANCE == null) { 
            INSTANCE = new Life(servletContext);
        }
    }
    public static Life getInstance(ServletContext servletContext) {
        if (INSTANCE == null) createInstance(servletContext);
        return INSTANCE;
    }
    //END: SINGLETON
	
}
