package web.controllers;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;

import a8.utils.CommonsUtils;

@Controller
@RequestMapping("/tasks/webflow")
public class WebFlowController {

	@RequestMapping(path = "/persons/cancelled", method = RequestMethod.GET)
	public String cancelPersonCreation() {
		return "webflow/cancelled";
	}
	
	@RequestMapping(path = "/persons/{id}", method = RequestMethod.GET)
	public String getPersonByFlow(@PathVariable Long id) {
		return "webflow/person";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "webflow/index";
	}

	@Autowired
	private WebApplicationContext webContext;
	
	@RequestMapping(path = "/ids")
	public String flowsIds(HttpServletRequest request) {
		
		FlowHandlerMapping flowHandlerMapping = webContext.getBean(FlowHandlerMapping.class);
		FlowDefinitionRegistry flowRegistry = flowHandlerMapping.getFlowRegistry();
		
		System.out.println("Registered Flow Ids are:");
		CommonsUtils.getInstance().printList(Arrays.asList(flowRegistry.getFlowDefinitionIds() ) );

		return "no_vista_mapeada";

	}

}
