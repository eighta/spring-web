package web.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import web.utils.ServletContextUtil;

public class AuditInterceptor extends HandlerInterceptorAdapter{

	private static final Logger logger = LoggerFactory.getLogger(AuditInterceptor.class);
	
	@Override
	public boolean preHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler)
					throws Exception {

		logger.info("METHOD: preHandle");
		return true;
	}
	
	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
					throws Exception {
		
		logger.info("METHOD: postHandle");
	}
	
	@Override
	public void afterCompletion(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
					throws Exception {
		
		logger.info("METHOD: afterCompletion");
	}
	
}
