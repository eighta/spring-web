package web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import a8.exceptions.DummyDataAccessException;
import a8.exceptions.ModelException;

@ControllerAdvice
public class EntireWebAppControllerAdvice {

	/*
	Any class annotated with @ControllerAdvice becomes a controller-advice 
	and three types of method are supported:

	-Exception handling methods annotated with @ExceptionHandler.
	
	-Model enhancement methods (for adding additional data to the model) 
	annotated with @ModelAttribute. 
	
	Note that these attributes are not available to the exception handling views.
	
	-Binder initialization methods (used for configuring form-handling) annotated with
	@InitBinder.
	
	A class annotated with @ControllerAdvice allows you to use the same exception 
	handling techniques across the whole application, not just a single controller. 
	
	Three types of methods are supported inside a class annotated with 
		@ControllerAdvice:

	-Methods annotated with @ExceptionHandler that are used to handle exceptions
	-Methods annotated with @ModelAttribute that are used to add data to the model
	-Methods annotated with @InitBinder that are used for configuring form-handling
	
	*/
	
	
	
	@ExceptionHandler//(DummyDataAccessException.class) in args can too
	public String exceptionHandlerMethod(DummyDataAccessException ddae){
		return "errors/entire_webapp_error";
	}
	
	@ExceptionHandler(ModelException.class)
	public ModelAndView defaultErrorHandler(
			HttpServletRequest req, Exception e) throws Exception{
	
		if (AnnotationUtils.findAnnotation(e.getClass(),ResponseStatus.class) != null){
			// we test to see if the exception is annotated with @ResponseStatus
			// if it is, we will re-throw it and let Spring handle it.
			throw e;
		}
		
		ModelAndView mav = new ModelAndView();
		//set exception details to be displayed in the page
		mav.addObject("exception", e);
		//set request URL to be displayed in the page, so the request causing
		//the problem can be identified
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("errors/entire_webapp_error");
		return mav;
	}
	
	
	
}
