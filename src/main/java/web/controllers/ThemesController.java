package web.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import a8.utils.CommonsUtils;

@Controller
@RequestMapping("/tasks/themes")
public class ThemesController {

	@Autowired
	ApplicationContext applicationContext;
	
	private final CommonsUtils commonsUtils = CommonsUtils.getInstance();
	
	@RequestMapping(method=RequestMethod.GET, path="/a")
	public String toA(){
		
		Map<String, ResourceBundleThemeSource> beansOfType = commonsUtils.getBeansOfType(applicationContext, ResourceBundleThemeSource.class);
		commonsUtils.printMap(beansOfType);
		System.out.println(beansOfType.size());
		
		Map<String, ResourceBundleMessageSource> beansOfTypeResourceBundleMessageSource = commonsUtils.getBeansOfType(applicationContext, ResourceBundleMessageSource.class);
		commonsUtils.printMap(beansOfTypeResourceBundleMessageSource);
		System.out.println(beansOfTypeResourceBundleMessageSource.size());
		
		return "themes/change";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "themes/index";
	}
}
