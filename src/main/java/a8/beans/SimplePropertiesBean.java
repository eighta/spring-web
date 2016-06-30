package a8.beans;

public class SimplePropertiesBean {
	
	private final String constructorProperty;
	private String methodProperty;
	private SimpleBean referenceProperty;
	
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

	public SimpleBean getReferenceProperty() {
		return referenceProperty;
	}

	public void setReferenceProperty(SimpleBean referenceProperty) {
		this.referenceProperty = referenceProperty;
	}

}
