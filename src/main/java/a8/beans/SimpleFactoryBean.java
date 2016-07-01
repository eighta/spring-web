package a8.beans;

import org.springframework.beans.factory.FactoryBean;

public class SimpleFactoryBean implements FactoryBean<SimpleBean>{

	@Override
	public SimpleBean getObject() throws Exception {

		SimpleBean simpleBean = new SimpleBean("simpleFactoryBean");
		return simpleBean;
	}

	@Override
	public Class<?> getObjectType() {
		return null;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
