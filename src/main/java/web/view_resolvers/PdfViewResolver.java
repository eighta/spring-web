package web.view_resolvers;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import web.views.CustomPdfView;

public class PdfViewResolver implements ViewResolver{

	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		return new CustomPdfView();
	}

}