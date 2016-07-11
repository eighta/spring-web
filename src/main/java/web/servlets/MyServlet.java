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
		
		//HandlerMapping
		ServletContext servletContext = request.getServletContext();
		Life life = (Life)servletContext.getAttribute("GLOBAL");
		Map<String, HandlerMapping> handlerMappings = life.getHandlerMappings();
		
		handlerMappings.entrySet();
		
		
		Set<String> keySet = handlerMappings.keySet();
		for(String  nameHandlerMapping: keySet){
			
			Object hm = handlerMappings.get(nameHandlerMapping);
			//HandlerMapping hm =handlerMappings.get(nameHandlerMapping);
			
			
			Integer order = (Integer)commonsUtils.callMethod(hm, "getOrder");
			
			System.out.print("["+ /*String.valueOf( ((Ordered) hm).getOrder()  )*/order+"]" );
			System.out.println("["+nameHandlerMapping+"]");
			/*
			logger.info("["+nameHandlerMapping+"]");
			logger.info(hm.toString());
			logger.info( String.valueOf( ((Ordered) hm).getOrder()  ) );
			*/
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
