package a8.core;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.security.web.FilterChainProxy;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.RequestToViewNameTranslator;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.ViewResolverComposite;

import a8.utils.CommonsUtils;
import web.utils.ServletContextUtil;

//ESTA CLASE en realidad debe ser una interface
//ya que ofrece una serie de servicios
//XXX exponerlo como spring bean
public class Life {

	private CommonsUtils commonsUtils = CommonsUtils.getInstance();
	
	private Heart heart = Heart.getInstance(); 
	
	public List<String> getThemifyIcons(){
		return ThemifyIcons.getNames();
	}
	
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
		Map<String, HandlerExceptionResolver> handlerExceptionResolverMap = commonsUtils.getBeansOfType(frontendApplicationContext, HandlerExceptionResolver.class);
		
		//Composite
		HandlerExceptionResolverComposite herComposite = frontendApplicationContext.getBean(HandlerExceptionResolverComposite.class);
		List<HandlerExceptionResolver> innerExceptionResolvers = herComposite.getExceptionResolvers();
		
		for(HandlerExceptionResolver her:innerExceptionResolvers){
			handlerExceptionResolverMap.put("inComposite::"+her.getClass().getSimpleName(), her);
		}
		
		return handlerExceptionResolverMap;
	}
	
	public List<String> getAllBeans() {
		
		List<String> allBeans = new ArrayList<>();
		
		WebApplicationContext frontendApplicationContext = heart.getFrontendApplicationContext();
		String[] beanDefinitionNames = frontendApplicationContext.getBeanDefinitionNames();
		
		for(String beanName: beanDefinitionNames){
			
			Object bean = frontendApplicationContext.getBean(beanName);
			allBeans.add(bean.getClass()+"::"+beanName);
		}
		
		return allBeans;
	}
	
	
	public Map<String, ViewResolver> getViewResolvers(){
		
		WebApplicationContext frontendApplicationContext = heart.getFrontendApplicationContext();
		Map<String, ViewResolver> viewResolverMap = commonsUtils.getBeansOfType(frontendApplicationContext, ViewResolver.class);
		
		//Composite
		ViewResolverComposite vrComposite = frontendApplicationContext.getBean(ViewResolverComposite.class);
		List<ViewResolver> innerViewResolvers = vrComposite.getViewResolvers();
		
		for(ViewResolver vr:innerViewResolvers){
			viewResolverMap.put("inComposite::"+vr.getClass().getSimpleName(), vr);
			
			if(vr instanceof ContentNegotiatingViewResolver){
				ContentNegotiatingViewResolver cnvr = (ContentNegotiatingViewResolver) vr;
				List<ViewResolver> cnvrViewResolvers = cnvr.getViewResolvers();
				for(ViewResolver innerCnvr:cnvrViewResolvers){
					viewResolverMap.put("inNegotiation::"+innerCnvr.getClass().getSimpleName(), vr);
				}
			}
			
		}
		
		return viewResolverMap;
	}
	
	public Map<String, HandlerAdapter> getHandlerAdapters(){
		
		WebApplicationContext frontendApplicationContext = heart.getFrontendApplicationContext();
		return commonsUtils.getBeansOfType(frontendApplicationContext, HandlerAdapter.class);
	}
	
	public Map<String, Filter> getFilters(){
		
		ServletContextUtil.getInstance().getFilters(servletContext);
		System.out.println("OK-FILTER");
		
		WebApplicationContext frontendApplicationContext = heart.getFrontendApplicationContext();
		FilterChainProxy filterChainProxy = frontendApplicationContext.getBean(FilterChainProxy.class);
		System.out.println("filterChainProxy: " + filterChainProxy);
		
		return null;
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
		return "SOPHIE OCHOA"; //XXX leerlo del properties
	}
	
	//BEING: SINGLETON
	private ServletContext servletContext;
	private static Life INSTANCE = null;
	private Life(ServletContext servletContext){
		this.servletContext=servletContext;
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
