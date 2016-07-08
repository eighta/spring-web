package web.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebApplicationContextUtil {

	private static final Logger logger = LoggerFactory.getLogger(WebApplicationContextUtil.class);
	
	//BEING: SINGLETON
	private static WebApplicationContextUtil INSTANCE = null;
	private WebApplicationContextUtil(){}
    private synchronized static void createInstance() {
        if (INSTANCE == null) { 
            INSTANCE = new WebApplicationContextUtil();
        }
    }
    public static WebApplicationContextUtil getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }
    //END: SINGLETON
}
