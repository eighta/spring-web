package a8.beans;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class InitializationBeanStyle3 implements InitializingBean{

	private String innerBeanName;
	
	public String getInnerBeanName() {
		return innerBeanName;
	}

	public void setInnerBeanName(String innerBeanName) {
		this.innerBeanName = innerBeanName;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.innerBeanName = "InitializationBeanStyle3";
		
	}
}
