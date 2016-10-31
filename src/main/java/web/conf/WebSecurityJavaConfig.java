package web.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import a8.security.MyCustomCredentialsProvider;

@Configuration //<<< Solo es necesario, si esta clase es identificada a travez del Component-Scan o usar @EnableGlobalMethodSecurity 
@EnableWebSecurity

//Enables Spring Security global method security similar to the <global-method-security> xml support.
//<global-method-security secured-annotations="enabled" pre-post-annotations="enabled" jsr250-annotations="enabled">
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled= true, jsr250Enabled= true)

public class WebSecurityJavaConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/resources/**","/images/**","/styles/**").permitAll()
			.antMatchers("/s/tasks/sec/insured").authenticated()
			
			.and()
			.formLogin()
				//.usernameParameter("username")	//customizable
				//.passwordParameter("password") //customizable
				.loginProcessingUrl("/login") // customizable
				.loginPage("/s/tasks/sec/auth")
				.permitAll()
		
			.and()
			.csrf().disable();
	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		//AuthenticationProvider
		//MyAuthenticationProvider myAuthenticationProvider = new MyAuthenticationProvider();
		//auth.authenticationProvider(myAuthenticationProvider);
		
		auth.userDetailsService(new MyCustomCredentialsProvider() );
		
		//Securing Methods
		//auth.po
		
		//ONLY 4 DEV MODE 
//		try {
//			auth.inMemoryAuthentication()
//			.withUser("john").password("doe").roles("USER").and()
//			.withUser("jane").password("doe").roles("USER,ADMIN").and()
//			.withUser("admin").password("admin").roles("ADMIN");
//			} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	/*
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	            .authorizeRequests()
	                .antMatchers("/", "/home").permitAll()
	                .anyRequest().authenticated()
	                .and()
	            .formLogin()
	                .loginPage("/login")
	                .permitAll()
	                .and()
	            .logout()
	                .permitAll();
	    }

	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth
	            .inMemoryAuthentication()
	                .withUser("user").password("password").roles("USER");
	    }
	*/
}
