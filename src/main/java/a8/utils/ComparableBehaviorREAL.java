package a8.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ComparableBehaviorREAL implements InvocationHandler{
	
	public final CommonsUtils commonsUtils = CommonsUtils.getInstance();

	private final Object proxied;
	
	public Object getProxied() {
		return proxied;
	}

	public ComparableBehaviorREAL(Object o) {
		this.proxied = o;
		
System.out.println("ConstructorREAL-- OLD["+proxied.hashCode()+ "], NEW: [" +this.hashCode()+"]");
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

System.out.print("[proxyREAL]INVOKING (" +  method.getName() + ")...");

		Object invokeResult = null;

		if(method.getName().equals("compareTo") ){
			
			System.out.println("argumentos:");
			System.out.println(args);
			
			Object value1 = args[0];
			System.out.println("clase del argumento 1: " + value1.getClass());

			

			if(value1 instanceof Proxy){
				//System.out.println("instancia de PROXY");
				Object proxied = commonsUtils.callMethod(value1, "getProxied");
				System.out.println("hash del proxied: " + proxied.hashCode());
				
				
			}else{
				System.out.println("NO instancia de PROXY");
			}
			
			
			
//			Object value2 = args[1];
			
//			System.out.println(value1 +"<<==>>"+value2);
			
			invokeResult = proxied.hashCode();
			
			
		}else{
			invokeResult = method.invoke(proxied, args);
		}

System.out.println(" ...OK");

		return invokeResult; 
		
	}

}
