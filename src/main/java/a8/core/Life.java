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

import a8.utils.CommonsUtils;

//ESTA CLASE en realidad debe ser una interface
//ya que ofrece una serie de servicios
//XXX exponerlo como spring bean
public class Life {

	private CommonsUtils commonsUtils = CommonsUtils.getInstance();
	
	private Heart heart = Heart.getInstance(); 
	
	public Map<String, FlashMapManager> getFlashMapManagers(){
		
		WebApplicationContext frontendApplicationContext = heart.getFrontendApplicationContext();
		return commonsUtils.getBeansOfType(frontendApplicationContext, FlashMapManager.class);
	}
	
	public Map<String, RequestToViewNameTranslator> getRequestToViewNameTranslators(){
		
		WebApplicationContext frontendApplicationContext = heart.getFrontendApplicationContext();
		return commonsUtils.getBeansOfType(frontendApplicationContext, RequestToViewNameTranslator.class);
	}
	
	public Map<String, ThemeResolver> getThemeResolvers(){
		
		WebApplicationContext frontendApplicationContext = heart.getFrontendApplicationContext();
		return commonsUtils.getBeansOfType(frontendApplicationContext, ThemeResolver.class);
	}
	
	public Map<String, LocaleResolver> getLocaleResolvers(){
		
		WebApplicationContext frontendApplicationContext = heart.getFrontendApplicationContext();
		return commonsUtils.getBeansOfType(frontendApplicationContext, LocaleResolver.class);
	}
	
	public Map<String, HandlerExceptionResolver> getHandlerExceptionResolvers(){
		
		WebApplicationContext frontendApplicationContext = heart.getFrontendApplicationContext();
		return commonsUtils.getBeansOfType(frontendApplicationContext, HandlerExceptionResolver.class);
	}
	
	public Map<String, ViewResolver> getViewResolvers(){
		
		WebApplicationContext frontendApplicationContext = heart.getFrontendApplicationContext();
		return commonsUtils.getBeansOfType(frontendApplicationContext, ViewResolver.class);
	}
	
	public Map<String, HandlerAdapter> getHandlerAdapters(){
		
		WebApplicationContext frontendApplicationContext = heart.getFrontendApplicationContext();
		return commonsUtils.getBeansOfType(frontendApplicationContext, HandlerAdapter.class);
	}
	
	public Map<String, HandlerMapping> getHandlerMappings(){
		
		WebApplicationContext frontendApplicationContext = heart.getFrontendApplicationContext();
		return commonsUtils.getBeansOfType(frontendApplicationContext, HandlerMapping.class);
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
