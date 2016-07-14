package web.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/tasks/i18n")
public class InternationalizationController {
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(method=RequestMethod.GET, path="/d")
	public String byModelD(){
		return "tasks/i18n_jstl_locale";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/c")
	public String byModelC(){
		return "tasks/i18n_jstl";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/b")
	public String byModelB(Model model){
		
		String saludo = messageSource.getMessage("welcome", null, Locale.GERMAN);
		model.addAttribute("modelAttribute", saludo);
		return "tasks/i18n_model";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/a")
	public String byModelA(Model model){
		
		Locale locale = Locale.getDefault();
		String saludo = messageSource.getMessage("welcome", null, locale);
		model.addAttribute("modelAttribute", saludo);
		return "tasks/i18n_model";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "tasks/i18n";
	}
}
