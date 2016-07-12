package web.templates.freemaker;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class FreeMakerTest {

	public static void main(String[] args) throws IOException, TemplateException {
		
		// Create your Configuration instance, and specify if up to what FreeMarker
		// version (here 2.3.25) do you want to apply the fixes that are not 100%
		// backward-compatible. See the Configuration JavaDoc for details.
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);

		// Specify the source where the template files come from. Here I set a
		// plain directory for it, but non-file-system sources are possible too:
		cfg.setDirectoryForTemplateLoading(new File("/p_wrk1/2016/workspace/eclipseNeon/git/spring-web/src/main/webapp/WEB-INF/views/freemaker"));

		// Set the preferred charset template files are stored in. UTF-8 is
		// a good choice in most applications:
		cfg.setDefaultEncoding("UTF-8");

		// Sets how errors will appear.
		// During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

		// Don't log exceptions inside FreeMarker that it will thrown at you anyway:
		cfg.setLogTemplateExceptions(false);
		
System.out.println("CONF... OK");

		Template temp = cfg.getTemplate("single.txt");
		
System.out.println("TEMPLATE... OK");		

		Map<String,String> model = new HashMap<>();
		model.put("user", "EIGHTA");
		model.put("url","/LA_URL");
		model.put("name", "Stopper");
		
System.out.println("MODEL... OK");

		Writer out = new OutputStreamWriter(System.out);
		temp.process(model, out);
		
		out.close();

System.out.println("\n...END");		
	}
	
}
