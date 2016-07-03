package a8.test.utils;

import java.util.Properties;
import java.util.StringJoiner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

public class CommonsUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonsUtils.class);

	//BEGIN: SINGLETON
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
	
	// UTIL
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

}
