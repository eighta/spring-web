package web.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import a8.exceptions.DummyDataAccessException;

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
	
	@ExceptionHandler//(DummyDataAccessException.class) in arg can too
	public String exceptionHandlerMethod(DummyDataAccessException ddae){
		return "errors/entire_webapp_error";
	}
	
	
	
}
