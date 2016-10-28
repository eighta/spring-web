package web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/tasks/sec")
public class SecurityController {

	@RequestMapping(method=RequestMethod.GET, path="/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
		
		return "sec/out";
	}
	
	//OBLIGADO MAPEAR LA URL QUE SE DEFINE EN <form-login login-page="/s/tasks/sec/auth"/>
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
