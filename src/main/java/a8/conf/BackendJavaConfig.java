package a8.conf;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;

import a8.beans.SimpleBean;
import a8.data.Person;

@Configuration
@PropertySource(value = "classpath:core/backend.properties")
public class BackendJavaConfig {

	@Bean
	public ResourceBundleMessageSource resourceBundle(){
		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
		resourceBundleMessageSource.setBasename("bundles/errors/codes");
		String message = resourceBundleMessageSource.getMessage("typeMismatch", null, Locale.getDefault());
		System.out.println("Nojoda: " + message);
		
		return resourceBundleMessageSource;
	}
	
	@Bean
	public SimpleBean getWhatIsMyBeanName(){
		return  new SimpleBean("unknown");
	}
	
	@Bean(name="daughterName")
	public SimpleBean getSimpleBeanSpELProperties(
			@Value("${daughter.name}")String daughterName){
		
		return new SimpleBean(daughterName);
	}
	
	@Bean
	public SimpleBean verySimpleBean(){
		return new SimpleBean("ONE");
	}
	
	//profiles
	@Bean
	@Profile("dev_1")
	public SimpleBean simpleBeanProfileOne(){
		return new SimpleBean("simpleBean in profile dev_1");
	}
	
	@Bean
	@Profile("server_2")
	public SimpleBean simpleBeanProfileTwo(){
		return new SimpleBean("simpleBean in profile server_2");
	}
}
