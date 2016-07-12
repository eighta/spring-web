package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/annotated_controller")
public class AnnotatedController {

	@RequestMapping(method=RequestMethod.GET)
	public String goToView(){
		return "simple/viewFromAnnotatedController";
	}
}
