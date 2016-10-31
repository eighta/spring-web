package web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.event.AuthorizationFailureEvent;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import a8.core.JustBusiness;
import a8.data.Person;

@Controller
@RequestMapping("/tasks/sec")
public class SecurityController {
	
	@Autowired
	private JustBusiness justBusiness;
	
	@RequestMapping(method=RequestMethod.GET, path="/method_5")
	public String callingJsr250Method(Model model){
		Person mySelf = justBusiness.getPersonWithinRolesAllowed();
		model.addAttribute(mySelf);
		return "sec/method";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/method_4")
	public String callingSecuredMethod(Model model){
		Person mySelf = justBusiness.getPersonSecured();
		model.addAttribute(mySelf);
		return "sec/method";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/method_3")
	public String callingPreAuthorizeMethod(Model model){
		Person mySelf = justBusiness.getPersonPreAuthorize();
		model.addAttribute(mySelf);
		return "sec/method";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/method_2")
	public String callingMethodSecure2(Model model){
		Person mySelf = justBusiness.getMySelf();
		model.addAttribute(mySelf);
		return "sec/method";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/method_1")
	public String callingMethodSecure1(Model model){
		
		Person myDaughter = justBusiness.getMyDaughter();
		model.addAttribute(myDaughter);
		return "sec/method";
	}
	

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
