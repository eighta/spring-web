package a8.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import a8.beans.SimpleBean;

@Configuration
@PropertySource(value = "classpath:core/backend.properties")
public class BackendJavaConfig {

	@Bean(name="daughterName")
	public SimpleBean getSimpleBeanSpELProperties(
			@Value("${daughter.name}")String daughterName){
		
		return new SimpleBean(daughterName);
	} 
}
