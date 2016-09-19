package a8.test.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.junit.Test;

import a8.data.Person;

public class PropertiesUtilsTest {

	@Test
	public void testProperties() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
		
		Properties props = new Properties();
		props.put("firstName", "Milton");
		
		Person p = new Person();
		propertyUtilsBean.copyProperties(p, props);
		
		System.out.println(p.getFirstName());
	}
}
