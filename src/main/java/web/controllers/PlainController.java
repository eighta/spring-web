package web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

public class PlainController {

	public PlainController(){
		System.out.println("instanciando: PlainController");
	}
	
	
	@RequestMapping
	public String cualquierMethod(){
		return "no_view";
	}
}
