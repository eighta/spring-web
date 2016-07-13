package web.servlets;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;

import a8.core.Life;
import a8.utils.CommonsUtils;

@WebServlet(name="customServletName",value="/servlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(MyServlet.class);
	public final CommonsUtils commonsUtils = CommonsUtils.getInstance();  
	
	public MyServlet(){
		logger.info("Constructor(...)");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		logger.info("doGet(...)");
		
		ServletContext servletContext = request.getServletContext();
		Life life = (Life)servletContext.getAttribute("GLOBAL");
		Map<String, HandlerMapping> handlerMappings = life.getHandlerMappings();
		Map<String, ViewResolver> viewResolvers = life.getViewResolvers();
		
		//prints
		printMvcComponents("HandlerMapping",handlerMappings);
		printMvcComponents("ViewResolver",viewResolvers);
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	private void printMvcComponents(String title, Map<String, ? extends Object> map){
		
		System.out.println("=="+title+"===");
		
		Set<String> keySet = map.keySet();
		for(String  key: keySet){
			
			Object value = map.get(key);
			Integer order = (Integer)commonsUtils.callMethod(value, "getOrder");
			
			System.out.print("["+ order+"]" );
			System.out.print("["+key+"]");
			System.out.println("["+value.getClass()+"]");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
