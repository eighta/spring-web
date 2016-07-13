package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

import web.templates.freemaker.FreeMarkerUtil;
import web.views.MyInternalResourceView;

@Controller
@RequestMapping("/view")
public class ViewController {
	
	private final FreeMarkerUtil freeMarkerUtil = FreeMarkerUtil.getInstance();
	
	@RequestMapping(method=RequestMethod.GET, path="/g")
	public String goToCustomViewXlsJExcelApi(Model model){
		return "bean/xls_jexcelapi";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/f")
	public String goToCustomViewXlsPoi(Model model){
		return "bean/xls_poi";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/e")
	public String goToViewUsingVelocity(Model model){
		model.addAttribute("name", "EIGHTA");
		model.addAttribute("project", "SPRING-WEB");
		return "template.vm";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/d")
	public String goToViewUsingFreeMarker(Model model){
		model.addAllAttributes(freeMarkerUtil.getFixedModel());
		return "single.ftl";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/c")
	public ModelAndView goToViewUsingModelAndView(){
		
		ModelAndView modelAndView = new ModelAndView();
		//modelAndView.setViewName("simple/viewFromAnnotatedController"); //OK
		modelAndView.setView(goToViewUsingInternalResourceView());
		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/b")
	public View goToViewUsingMyInternalResourceView(){
		
		MyInternalResourceView myView = new MyInternalResourceView();
		return myView;
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/a")
	public View goToViewUsingInternalResourceView(){
		
		InternalResourceView view = new InternalResourceView("/WEB-INF/views/simple/viewFromAnnotatedController.jsp");
		return view;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String goToViewString(){
		return "simple/viewFromAnnotatedController";
	}
	
}
