package web.view_resolvers;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import web.views.OneExcelView;

public class XlsViewResolver implements ViewResolver{

	@Override
	public View resolveViewName(String viewName, Locale local) throws Exception {
		return new OneExcelView();
	}

}
