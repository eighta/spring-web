package web.freemaker;

import java.io.File;
import java.io.IOException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class FreeMarkerUtil {

	private Configuration cfg;
	
	public Template getTemplate(String template) {

		try {
			return cfg.getTemplate(template);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void createFreeMarkerConfiguration() throws IOException{
		// Create your Configuration instance, and specify if up to what FreeMarker
		// version (here 2.3.25) do you want to apply the fixes that are not 100%
		// backward-compatible. See the Configuration JavaDoc for details.
		cfg = new Configuration(Configuration.VERSION_2_3_23);

		// Specify the source where the template files come from. Here I set a
		// plain directory for it, but non-file-system sources are possible too:
		cfg.setDirectoryForTemplateLoading(new File("/p_wrk1/2016/workspace/eclipseNeon/git/spring-web/src/main/webapp/WEB-INF/views/freemarker"));

		// Set the preferred charset template files are stored in. UTF-8 is
		// a good choice in most applications:
		cfg.setDefaultEncoding("UTF-8");

		// Sets how errors will appear.
		// During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

		// Don't log exceptions inside FreeMarker that it will thrown at you anyway:
		cfg.setLogTemplateExceptions(false);
	}
	
	// BEGIN: SINGLETON
	private static FreeMarkerUtil INSTANCE = null;
	private FreeMarkerUtil(){
		try {
			this.createFreeMarkerConfiguration();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new FreeMarkerUtil();
		}
	}
	public static FreeMarkerUtil getInstance() {
		if (INSTANCE == null)
			createInstance();
		return INSTANCE;
	}
	// END: SINGLETON
}
