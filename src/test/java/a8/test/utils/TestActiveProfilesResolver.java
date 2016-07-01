package a8.test.utils;

import org.springframework.test.context.ActiveProfilesResolver;

public class TestActiveProfilesResolver implements ActiveProfilesResolver {

	@Override
	public String[] resolve(Class<?> aClass) {

		String[] profiles = new String[1];
		
		String osName = System.getProperty("os.name");
		if(!osName.equalsIgnoreCase("LINUX")){
			profiles[0] = "sophieHome";
		}
        return profiles;
	}

}
