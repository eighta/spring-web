package a8.core;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

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
	
	public Map<String,Object> getSessionAttributeNames(HttpSession session){
		Map<String,Object> sessionAttributeMap = new HashMap<>();

		if(session!=null){
			Enumeration<String> attributeNamesEnumeration = session.getAttributeNames();
			while(attributeNamesEnumeration.hasMoreElements()){
				String attributeName = attributeNamesEnumeration.nextElement();
				Object attributeValue = session.getAttribute(attributeName);
				sessionAttributeMap.put(attributeName, attributeValue);
			}
		}
		return sessionAttributeMap;
	}
	
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
		Map<String, HandlerMapping> beansOfType = commonsUtils.getBeansOfType(frontendApplicationContext, HandlerMapping.class);
		return beansOfType;

/*
 *XXX IMPLEMENTAR UN MAPA ORDERNABLE 
 * 		
 *
		System.out.println("-IMPRIMIENDO MAPA ORIGINAL");
commonsUtils.printHashOfMapValues(beansOfType,Comparable.class);

System.out.println("-CONVIRTIENDO MAPA A WRAP...");
		Map<String,Comparable> mapComparable= mapComparable = commonsUtils.wrapComparableInterfaceToMapValues(beansOfType);
		System.out.println("-IMPRIMIENDO MAPA WRAPEADO");
commonsUtils.printHashOfMapValues(mapComparable,Comparable.class);

/*
System.out.println("despues de aplicar WRAP Comparable");		
Set<Entry<String, Comparable>> entrySet = mapComparable.entrySet();		
for(Entry<String, Comparable> entry: entrySet){
String key = entry.getKey() ;
System.out.println("->" + key + "--");

Object c = mapComparable.get(key);
if(c instanceof Comparable){
	System.out.print("SI ES INSTANCIA DE COMPARABLE");
}else{
	System.out.print("SI ES INSTANCIA DE COMPARABLE");
}
System.out.print("---"+commonsUtils.callMethod(c, "hashCode"));

}		
		
		//Map<String, DeleteMe> map = new HashMap<>();
		commonsUtils.sortByValue(mapComparable);
*/		
		/*
		Map sortedMap = commonsUtils.sortByValue(mapComparable);
		
		return sortedMap;
		*/
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
