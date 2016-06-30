package a8.beans;

public class SingletonBean {
	
	private static SingletonBean instance = new SingletonBean();
	
	//PRIVATE CONSTRUCTOR
	private SingletonBean(){}
	
	public static SingletonBean getInstance(){
		return instance;
	}

}
