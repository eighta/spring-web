package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/tasks/sec")
public class SecurityController {

	@RequestMapping(method=RequestMethod.GET, path="/auth")
	public String goToAuth(){
		return "sec/auth";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/insured")
	public String goToResourceInsured(){
		return "sec/resource_insured";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "sec/index";
	}
	
}
