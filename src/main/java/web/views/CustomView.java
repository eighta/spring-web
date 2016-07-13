package web.views;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;

public class CustomView implements View{

	@Override
	public String getContentType() {
		return "application/custom";
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//XXX CREAR UN CUSTOM VIEW, UNA IMAGEN, GENERADA, YO QUE SE
	}

}
