package a8.evaluators;

import java.io.Serializable;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

public class MyPermissionEvaluator implements PermissionEvaluator{

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
System.out.println("MyPermissionEvaluator.hasPermission(1)");
		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
System.out.println("MyPermissionEvaluator.hasPermission(2)");
		return false;
	}

}
