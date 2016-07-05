package a8.core;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

public class Heart {
	
	private ApplicationContext backendApplicationContext;
	private WebApplicationContext frontendApplicationContext;

	public WebApplicationContext getFrontendApplicationContext() {
		return frontendApplicationContext;
	}
	public void setFrontendApplicationContext(WebApplicationContext frontendApplicationContext) {
		this.frontendApplicationContext = frontendApplicationContext;
	}
	public ApplicationContext getBackendApplicationContext() {
		return backendApplicationContext;
	}
	public void setBackendApplicationContext(ApplicationContext backendApplicationContext) {
		this.backendApplicationContext = backendApplicationContext;
	}
	
	//BEING: SINGLETON
	private static Heart INSTANCE = null;
	private Heart(){}
    private synchronized static void createInstance() {
        if (INSTANCE == null) { 
            INSTANCE = new Heart();
        }
    }
    public static Heart getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }
    //END: SINGLETON
}
