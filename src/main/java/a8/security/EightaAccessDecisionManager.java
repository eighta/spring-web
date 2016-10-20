package a8.security;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;

public class EightaAccessDecisionManager implements AccessDecisionManager{

	//XXX TODO ANALIZAR COMO SE PUEDE HACER UNA EVALUACION (CRITERIO DE ACCESO) DIFERENTE AL DEFAULT
	
	@Override
	public void decide(Authentication auth, Object object, Collection<ConfigAttribute> collection)
			throws AccessDeniedException, InsufficientAuthenticationException {
System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>decide");
		
	}

	@Override
	public boolean supports(ConfigAttribute configAttribute) {
System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>supports");
		return false;
	}

	@Override
	public boolean supports(Class<?> clazz) {
System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>supports");
		return false;
	}

}
