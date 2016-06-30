package a8.beans;

public class SimplePropertiesBean {
	
	private final String constructorProperty;
	private String methodProperty;
	
	public SimplePropertiesBean(String constructorProperty){
		this.constructorProperty = constructorProperty;
	}

	public String getMethodProperty() {
		return methodProperty;
	}

	public void setMethodProperty(String methodProperty) {
		this.methodProperty = methodProperty;
	}

	public String getConstructorProperty() {
		return constructorProperty;
	}

}
