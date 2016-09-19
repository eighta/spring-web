package web.rest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import a8.data.Person;

@Controller
@RequestMapping("/tasks/rest")
public class MyRestController {

	@RequestMapping
	public String index(Model model){
		model.addAttribute(new Person() );
		return "rest/index";
	}
}
