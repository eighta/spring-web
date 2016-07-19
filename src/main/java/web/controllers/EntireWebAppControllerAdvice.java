package web.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import a8.exceptions.DummyDataAccessException;
import a8.exceptions.EightaException;

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
	*/
	
	@ExceptionHandler//(DummyDataAccessException.class) in arg can too
	public String exceptionHandlerMethod(DummyDataAccessException ddae){
		return "errors/entire_webapp_error";
	} 
	
}
