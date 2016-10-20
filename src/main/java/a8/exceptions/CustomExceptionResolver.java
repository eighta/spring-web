package a8.exceptions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class CustomExceptionResolver extends SimpleMappingExceptionResolver{

	@Override
	protected ModelAndView doResolveException(
			HttpServletRequest req, HttpServletResponse res,
			Object handler, Exception ex) {
		
		if (ex instanceof AccessDeniedException)
			throw (AccessDeniedException) ex;
		
		return super.doResolveException(req, res, handler, ex);
	}
}
