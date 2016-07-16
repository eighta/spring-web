package web.controllers;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import a8.utils.CommonsUtils;

@Controller
@RequestMapping("/tasks/i18n")
public class InternationalizationController {
	
	public static final CommonsUtils commonsUtils = CommonsUtils.getInstance();
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(method=RequestMethod.GET, path="/g")
	public String byAcceptHeaderLocaleResolver(
			Model model,
			@RequestHeader Map<String,String> headers){
		
		commonsUtils.printMap(headers);
		
		model.addAttribute("headers", headers);
		return "i18n/i18n_headers_locale";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/simple")
	public String byI18nSimple(){
		return "i18n/i18n_simple";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/f")
	public String bySessionLocaleResolver(){
		
		ServletRequestAttributes currentRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = currentRequestAttributes.getRequest();
		HttpSession session = request.getSession();
		Enumeration<String> attributeNames = session.getAttributeNames();
System.out.println(">>>attributeNames SESSION");		
		while(attributeNames.hasMoreElements() ){
			System.out.println(attributeNames.nextElement());
		}
System.out.println("<<< attributeNames SESSION");
		return "i18n/i18n_session_locale";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/e")
	public String byCookieLocaleResolver(){
		return "i18n/i18n_cookie_locale";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/d")
	public String byJstlD(){
		return "i18n/i18n_jstl_locale";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/c")
	public String byJstlC(){
		return "i18n/i18n_jstl";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/b")
	public String byModelB(Model model){
		
		String saludo = messageSource.getMessage("welcome", null, Locale.GERMAN);
		model.addAttribute("modelAttribute", saludo);
		return "i18n/i18n_model";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/a")
	public String byModelA(Model model){
		
		Locale locale = Locale.getDefault();
		String saludo = messageSource.getMessage("welcome", null, locale);
		model.addAttribute("modelAttribute", saludo);
		return "i18n/i18n_model";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "i18n/i18n";
	}
}
