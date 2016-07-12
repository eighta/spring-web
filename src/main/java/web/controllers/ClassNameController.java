package web.controllers;

//@Controller
public class ClassNameController {

//	@RequestMapping
	public String methodName(){
		System.out.println("methodName");
		return "simple/viewFromClassNameController";
	}
}
