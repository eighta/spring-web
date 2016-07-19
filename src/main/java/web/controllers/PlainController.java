package web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

public class PlainController {
	
	@RequestMapping
	public String cualquierMethod(){
		return "no_view";
	}
}
