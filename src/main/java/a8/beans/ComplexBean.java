package a8.beans;

public class ComplexBean {

	private SimpleBean simpleBean;
	
	//NO-ARGS CONSTRUCTOR
	public ComplexBean() {}

	public SimpleBean getSimpleBean() {
		return simpleBean;
	}

	public void setSimpleBean(SimpleBean simpleBean) {
		this.simpleBean = simpleBean;
	}
}
