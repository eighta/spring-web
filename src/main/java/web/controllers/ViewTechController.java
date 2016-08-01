package web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import a8.business.PersonManager;

@Controller
@RequestMapping("/tasks/view_tech")
public class ViewTechController {

	@Autowired
	private PersonManager personManager;
	
	@RequestMapping("/b")
    public String tiles(Model model,HttpServletRequest rq) {
        
        return "view_tech/tiles";
    }
	
	@RequestMapping("/a")
    public String list(Model model,HttpServletRequest rq) {
        model.addAttribute("persons", personManager.findAll() );
        return "view_tech/list";
    }
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "view_tech/index";
	}
}
