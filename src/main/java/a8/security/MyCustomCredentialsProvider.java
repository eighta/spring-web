package a8.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyCustomCredentialsProvider implements UserDetailsService{

	@SuppressWarnings("unused")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
System.out.println("loadUserByUsername: " + username);
		
		//IF NOT FOUND
		if(false){
			throw new UsernameNotFoundException("Username no identificado: " + username);
		}
		
		//SE CONSULTA USUARIO (junto con su: username, password, authorities)
		User usuario = null;
		if(username.equalsIgnoreCase("user")){
			
			String password = "user";
			
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			
			usuario =  new User(username,password, authorities);
		
		}else if(username.equalsIgnoreCase("secure")){
			
			String password = "secure";
			
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_SECURE"));
			
			usuario =  new User(username,password, authorities);
		}else{
			

			//md5 - encode with the string: "MySalt"
			//String password = "5a693853b2958ecb256db46b808ac488";
			String password = "admin";
			
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_CHISMOSO"));
			
			usuario =  new User(username,password, authorities);
			
		}
		
		return usuario;
	}

}
