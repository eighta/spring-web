package web.servlets;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import web.freemaker.FreeMarkerUtil;

@WebServlet("/freemarker")
public class FreeMarkerServlet extends HttpServlet{
	
	private static final long serialVersionUID = -2584814395029189648L;
	//private static final Logger logger = LoggerFactory.getLogger(FreeMarkerServlet.class);
	private final FreeMarkerUtil freeMarkerUtil = FreeMarkerUtil.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		Template template = freeMarkerUtil.getTemplate("single.ftl");
		
		//Dummy MODEL
		Map<String,String> model = freeMarkerUtil.getFixedModel();
		
		//RESPONSE
		Writer out = new OutputStreamWriter(response.getOutputStream());
		try {
			template.process(model, out);
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		
	}

}
