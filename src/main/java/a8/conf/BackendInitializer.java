package a8.conf;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class BackendInitializer implements ApplicationContextInitializer<GenericApplicationContext>{

	@Override
	public void initialize(GenericApplicationContext genericApplicationContext) {
		
		//org.springframework.beans.factory.xml.XmlBeanDefinitionReader
		//org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition
		
		//XXX INVESTIGAR LA MENRA DE COMO CARGAR EL CONTEXTO DESDE UNA JAVA CONFIG
		//AnnotatedGenericBeanDefinition annotatedGenericBeanDefinition= new AnnotatedGenericBeanDefinition(BackendJavaConfig.class); 
		//annotatedGenericBeanDefinition.
		
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(genericApplicationContext);
		reader.loadBeanDefinitions(new ClassPathResource("/conf/backend-config.xml", Object.class.getClass()));
	}

}
