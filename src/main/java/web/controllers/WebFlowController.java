package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/tasks/webflow")
public class WebFlowController {

	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "webflow/index";
	}
}
