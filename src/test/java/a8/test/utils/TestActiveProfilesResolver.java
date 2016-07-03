package a8.test.utils;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfilesResolver;

public class TestActiveProfilesResolver implements ActiveProfilesResolver {

	private static String OS_NAME = System.getProperty("os.name");
	
	@Override
	public String[] resolve(Class<?> aClass) {

		String[] profiles = new String[1];
		
		if(!OS_NAME.equalsIgnoreCase("LINUX")){
			profiles[0] = "sophieHome";
			sophieHome= Boolean.TRUE;
		}
        return profiles;
	}
	
	private static boolean sophieHome = Boolean.FALSE;
	
	public static boolean isSophieHome() {
		return sophieHome;
	}
	
	@Autowired
	private Environment environment;
	
	@PostConstruct
	public void init(){
		//is Sophie Home ?
		List<String> activeProfiles = Arrays.asList(environment.getActiveProfiles());
		sophieHome = activeProfiles.contains("sophieHome");
	}

}
