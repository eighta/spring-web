package web.view_resolvers;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import web.views.pdf.CustomPdfView;
import web.views.pdf.PersonsPdfView;

public class PdfViewResolver implements ViewResolver{

	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		
		//OJO: EL VIEW RESOLVER debe escoger la vista segun,
		//un criterio especifico, lo obvio es por el nombre de vista
		
		switch(viewName){
			case "view_tech/list":
				return new PersonsPdfView();
			default:
				return new CustomPdfView(); 
		}
		
	}

}
