package a8.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class MyAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
System.out.println("additionalAuthenticationChecks(...)");
		
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		
		String password = "admin";
		
		Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
		
		User usuario =  new User(username,password, authorities);
		return usuario;
	}

}
