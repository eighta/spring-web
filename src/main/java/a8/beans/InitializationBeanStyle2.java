package a8.beans;

public class InitializationBeanStyle2 {

	private String innerBeanName;
	
	//@Bean’s initMethod attribute
	//init-method attribute on a <bean/> XML definition
	public void plainInitMethod(){
		this.innerBeanName = "InitializationBeanStyle2";
	}
	
	public String getInnerBeanName() {
		return innerBeanName;
	}

	public void setInnerBeanName(String innerBeanName) {
		this.innerBeanName = innerBeanName;
	}
}
