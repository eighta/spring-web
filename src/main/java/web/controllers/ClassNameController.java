package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
public class ClassNameController {

//	@RequestMapping
	public String methodName(){
		System.out.println("methodName");
		return "viewFromClassNameController";
	}
}
