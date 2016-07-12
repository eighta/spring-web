package web.views;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.InternalResourceView;

public class MyInternalResourceView extends InternalResourceView{

	@Override
	 protected void renderMergedOutputModel(
			 Map<String, Object> model, 
			 HttpServletRequest request, HttpServletResponse response) throws Exception {

		String view = "/WEB-INF/views/simple/viewFromAnnotatedController.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(view);
		requestDispatcher.forward(request, response);
	 }
}
