package a8.utils;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.StringJoiner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

public class CommonsUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonsUtils.class);
	
	public <T> Map<String, T> getBeansOfType(ApplicationContext webApplicationContext, Class<T> type){
		return webApplicationContext.getBeansOfType(type);
	}
	
	public void printEnumeration(Enumeration<String> attributeNames) {

		logger.info("===Enumeration BEGIN===");
		
		while(attributeNames.hasMoreElements()){
			String item = attributeNames.nextElement(); 
			logger.info(item);
		}
		logger.info("===Enumeration END===");
	} 
	
	public ResourceBundle getResourceBundle(String resource, Locale locale){
		//https://docs.oracle.com/javase/7/docs/api/java/util/ResourceBundle.html
		//Resource bundles contain key/value pairs. The keys uniquely identify a locale-specific object in the bundle
		return ResourceBundle.getBundle(resource, locale);
	}
	
    public void printAllNameBeans(ApplicationContext applicationContext){
    	
    	logger.info("===BEANS NAMES===");
		for(String beanName:applicationContext.getBeanDefinitionNames()){
			logger.info(beanName);
		}
		logger.info("===END BEANS NAMES===");
    }
    
	public String stringJoinUsingCharDelimiter(CharSequence delimiter, String... strings) {

		StringJoiner sjr = new StringJoiner(delimiter);
		for (String str : strings) {
			sjr.add(str);
		}

		return sjr.toString();
	}

	@SuppressWarnings("static-access")
	public Properties getProperties(String path) {

		try {
			
			Properties _properties = new Properties();
//			_properties.load(java.lang.Class.class.getClassLoader().getResourceAsStream(path));
			_properties.load(ClassLoader.getSystemClassLoader().getSystemResourceAsStream(path));
			return _properties;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	//BEING: SINGLETON
	private static CommonsUtils INSTANCE = null;
	private CommonsUtils(){}
    private synchronized static void createInstance() {
        if (INSTANCE == null) { 
            INSTANCE = new CommonsUtils();
        }
    }
    public static CommonsUtils getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }
    //END: SINGLETON
}
