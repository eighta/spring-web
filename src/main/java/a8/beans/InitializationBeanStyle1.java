package a8.beans;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class InitializationBeanStyle1 {

	private String innerBeanName;
	
	@PostConstruct
	public void init(){
		this.innerBeanName = "InitializationBeanStyle1";
	}

	public String getInnerBeanName() {
		return innerBeanName;
	}

	public void setInnerBeanName(String innerBeanName) {
		this.innerBeanName = innerBeanName;
	}
	
	
}
