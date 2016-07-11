package a8.utils;

import java.lang.reflect.Method;
import java.util.Map;

public class ComparableBehavior implements java.lang.reflect.InvocationHandler, Comparable{
	
	static{
//		System.out.println("STATIC LOADING...");
	}
	
	//private Object entrySet;
	
	private final Map//<Object,Object> 
						proxied; 
	
	public ComparableBehavior(Map	//<Object,Object> 
									proxied){
		
		
		
		

//System.out.println("CONSTRUCTOR ComparableBehavior proxied: " + proxied);
		this.proxied=proxied;
		
		//entrySet=this.proxied.entrySet();
	}
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//System.out.println("INVOKE METHOD: " + method.getName());			
		/*
		if (method.getName().startsWith("compareTo")) {

			
			
			Comparable c =	new Comparable(){

				public int compareTo(Object o) {
					// TODO Auto-generated method stub
					return 0;
				}
				
			}
			
			
			
		      return false;
		}
		
		return (o1.getValue()).compareTo( o2.getValue() );
		*/

System.out.print("[proxy]INVOKING (" +  method.getName() + ")...");
		Object invoke = method.invoke(proxied, args);		
System.out.println(" ...OK");

		return invoke; 
	}
	
	
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//COMPARACION NORMAL:
	//public int comparar(Object otro){
	//}
	
	public static void main(String[] args) {
		
		String s = "milton";
		System.out.println("s.hashCode ORIGINAL: " + s.hashCode() );
		
		CommonsUtils c = CommonsUtils.getInstance();
		
		int hash = (Integer) c.callMethod(s, "hashCode");
		  System.out.println("s.hashCode reflect: " + hash );
		
		
	}
	
}