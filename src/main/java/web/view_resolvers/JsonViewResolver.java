package web.view_resolvers;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class JsonViewResolver implements ViewResolver{

	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		
//		//bypass
//		if(!viewName.endsWith(".json")){
//			return null;
//		}
		
		MappingJackson2JsonView view = new MappingJackson2JsonView();
		//make JSON output readable using proper indentation
		view.setPrettyPrint(true);
		return view;
	}

}
