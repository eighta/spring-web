package a8.exceptions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.webflow.execution.FlowExecutionException;

public class CustomExceptionResolver extends SimpleMappingExceptionResolver{

	@Override
	protected ModelAndView doResolveException(
			HttpServletRequest req, HttpServletResponse res,
			Object handler, Exception ex) {
		
		if (ex instanceof FlowExecutionException){
			FlowExecutionException flowEx = (FlowExecutionException) ex;
			
			Throwable cause = flowEx.getCause();
			if (cause instanceof AccessDeniedException)
				//throw (AccessDeniedException) cause;
				//ex = (AccessDeniedException)cause;
				return super.doResolveException(req, res, handler, (AccessDeniedException)cause);
				//return super.doResolveException(req, res, handler, new EightaException("NEGADO!"));
				//throw new EightaException("NEGADO!");
		}
		
		return super.doResolveException(req, res, handler, ex);
	}
}
