package web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.InternalResourceView;

public class MyAbstractController extends AbstractController{

	@Override
	protected ModelAndView handleRequestInternal(
				HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		InternalResourceView view = new InternalResourceView("/WEB-INF/views/simple/viewFromAbstractController.jsp");
		modelAndView.setView(view);
		return modelAndView;
		
	}

}
