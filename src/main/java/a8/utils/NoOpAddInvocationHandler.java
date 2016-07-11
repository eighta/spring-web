package a8.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class NoOpAddInvocationHandler implements InvocationHandler {
	  private final List proxied;
	  public NoOpAddInvocationHandler(List proxied) {
	    this.proxied = proxied;
	  }
	  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
	    if (method.getName().startsWith("add")) {
	      return false;
	    }
	    return method.invoke(proxied, args);
	  }

	  public static void main(String[] args) {
		
		  List<String> list = new ArrayList<>();
		  list.add("apples");
		  list.add("horses");
		  list.add("audis");
		  System.out.println(list);
		  
		  list.add("languages");
		  System.out.println(list);
		  
		  List proxy = (List) Proxy.newProxyInstance(
				  NoOpAddInvocationHandler.class.getClassLoader(),	//the class loader
				  new Class[] { List.class },							//an array of interfaces that will be implemented by the proxy
				  new NoOpAddInvocationHandler(list));					//the power behind the throne in the form of the invocation handler
		  
	
		  proxy.add("titles");
		  System.out.println(proxy);
	  }
	  


}



