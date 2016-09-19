package web.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import a8.data.Person;
import web.rest.exceptions.RestException;
import web.rest.exceptions.RestNoBodyException;

/*
 
 El atributo basePackages en la anotacion @ControllerAdvice, viene a
 significar que, solo aplicara sobre aquellos controladores
 que se encuentren en el paquete especificado
  
 */

@ControllerAdvice
(basePackages="web.rest.controllers")
public class RestControllerAdvice {

	@ResponseStatus(code=HttpStatus.NOT_IMPLEMENTED, reason="Excepcion traducida a Status Code: 404")
	@ExceptionHandler
	public void handleRestException(RestException e){
		System.out.println("Translating 'RestException' 2 HttpStatus.NOT_IMPLEMENTED");
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.PERMANENT_REDIRECT)
	@ExceptionHandler(RestNoBodyException.class)
	public Person handleRestNoBodyException(){
		System.out.println("Building a Body...");
		return new Person(987,"Ivan","Ivanov","2016-01-01");
	}
}
