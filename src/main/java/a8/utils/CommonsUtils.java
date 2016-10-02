package a8.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/*
 * XXX TODO XXX TODO
 * TIRE LOS GUANTES: NO PUDE HACER UNA ORDENACION DE UN MAPA CON REFLECTION, ES COMPLICADO
 */


public class CommonsUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonsUtils.class);
	
	public <T> T getPrivateAttribute(Object object, String attName,Class<T> requiredType){
		
		try{
			Class<?> theClass = object.getClass();
			Field field = theClass.getDeclaredField(attName);
			field.setAccessible(true); //<<<< AQUI ESTA LA CLAVE
			Object value = field.get(object);
			return (T) value;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public void printList(List<?> list){
		
		ConsoleStringTable consoleList= new ConsoleStringTable();
		int currentRow = 0;
		for(Object item:list){
			consoleList.addString(currentRow++, 0, item.toString());
		}
		
		System.out.println(consoleList.toString());
		
	}

	public void printMap(Map<?,?> map){
		
		ConsoleStringTable table= new ConsoleStringTable();
		Set<?> keySet = map.keySet();
		
		int currentRow = -1;
		for(Object key: keySet){ //NOT SUITABLE POR INDEX
		//for(int i=0; i<allRows ;i++){
			currentRow++;
			Object value = map.get(key);
			
			table.addString(currentRow, 0, key.toString());
			table.addString(currentRow, 1, value.toString());
		}
		System.out.println(table.toString());
		
		
	}
	
	public Object callMethod(Object target, String methodName){
		
		try {
			Object invokeResult = null;
			
			Class<? extends Object> classTarget = target.getClass();
			Object classTargetObject = (Object) classTarget;
			
			
			if(classTargetObject instanceof Proxy){ 
				Object proxied = callMethod(classTargetObject, "getProxied");
				invokeResult = callMethod(proxied,methodName);
			}else{
				Method methodTarget = classTarget.getMethod(methodName);
				invokeResult = methodTarget.invoke(target);
				
			}
			
			return invokeResult;
		} catch (Exception e) {
		  e.printStackTrace();
		}
		return null;
	}
	
	public <K, V extends Comparable<? super V>> Map<K, V> 
  		sortByValue( Map<K, V> map ) {
	
		/*
		List<V> listValues = new ArrayList<V>(map.values());

System.out.println("ORDERANANDO lista de hashs:");
for(Object vObject :listValues){
	Object callMethod = callMethod(vObject, "hashCode");
	System.out.println("-"+callMethod);
}
	*/	
//			Collection<V> values = map.values();
//System.out.println("VALUES->OK");			
//Collections.sort(listValues);
//System.out.println("ORDERNADO OK->OK");


		
		 	//SE CREA UNA LISTA-LINKEADA con las entredas del mapa
		 	List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>( map.entrySet() );
  
		 	//SE MANDA A ORDER utilizando un Comparator
		 	Collections.sort( 
		 			
		 			list, new Comparator<Map.Entry<K, V>>()	{
		 						public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 ){
System.out.println("CUSTOM comparator HERE... ");
		 								
		 								//Object o = o1.getValue();
		 								//System.out.println(o);
		 								
		 							
//		 							org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping 
//		 							cannot be cast to java.lang.Comparable
		 								
		 								//System.out.println("KEY: " + o1.getKey() + "--hash: " + callMethod(o1, "hashCode"));
		 								
		 								//http://stackoverflow.com/questions/12037489/cant-access-to-java-util-hashmapentry-with-modifiers-public-final
		 								//V value1 = o1.getValue();
		 								//V value2 = o2.getValue();
		 								
		 								
		 								
		 								
		 								V value1 = o1.getValue();
		 								V value2 = o2.getValue();
		 							
		 								return value1.compareTo(value2);
		 						}
		 					} 
		 			);

		 	//SE CREA UN LINKED-HASH-MAP con los resultados de la lista
		 	Map<K, V> result = new LinkedHashMap<K, V>();
		 	for (Map.Entry<K, V> entry : list){
		 		result.put( entry.getKey(), entry.getValue() );
		 	}
  
		 	return result;
		 	
	 }
	
	public <T> Map<String, T> getBeansOfType(ApplicationContext webApplicationContext, Class<T> type){
		return webApplicationContext.getBeansOfType(type);
	}
	
	public void printEnumeration(Enumeration<String> attributeNames) {

		logger.info("===Enumeration BEGIN===");
		
		while(attributeNames.hasMoreElements()){
			String item = attributeNames.nextElement(); 
			logger.info(item);
		}
		logger.info("===Enumeration END===");
	} 
	
	public ResourceBundle getResourceBundle(String resource, Locale locale){
		//https://docs.oracle.com/javase/7/docs/api/java/util/ResourceBundle.html
		//Resource bundles contain key/value pairs. The keys uniquely identify a locale-specific object in the bundle
		return ResourceBundle.getBundle(resource, locale);
	}
	
    public void printAllNameBeans(ApplicationContext applicationContext){
    	
    	logger.info("===BEANS NAMES===");
		for(String beanName:applicationContext.getBeanDefinitionNames()){
			logger.info(beanName);
		}
		logger.info("===END BEANS NAMES===");
    }
    
	public String stringJoinUsingCharDelimiter(CharSequence delimiter, String... strings) {

		StringJoiner sjr = new StringJoiner(delimiter);
		for (String str : strings) {
			sjr.add(str);
		}

		return sjr.toString();
	}

	@SuppressWarnings("static-access")
	public Properties getProperties(String path) {

		try {
			
			Properties _properties = new Properties();
//			_properties.load(java.lang.Class.class.getClassLoader().getResourceAsStream(path));
			_properties.load(ClassLoader.getSystemClassLoader().getSystemResourceAsStream(path));
			return _properties;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	 //Dynamic Proxies
		public Map<String, Comparable> wrapComparableInterface(
				Map//<String, ? extends Object> 
					map) {
			
			Object newProxyInstance = Proxy.newProxyInstance(
					Object.class.getClassLoader(),	//the class loader
					new Class[] { Map.class },							//an array of interfaces that will be implemented by the proxy
					new ComparableBehavior(map));					//the power behind the throne in the form of the invocation handler
			Map m = (Map) newProxyInstance;
			
			return m;
		}

		public void printHashOfMapValues(Map<String, ? extends Object> beansOfType, Class<?> class1) {
			Collection<? extends Object> values = beansOfType.values();
			for(Object o:values){
				Boolean instanceOf = o instanceof Comparable;
				System.out.println(o.hashCode()+">>>"+o.getClass()+">>>"+instanceOf);
			}
		}

		public Map<String, Comparable> wrapComparableInterfaceToMapValues(Map<String, ? extends Object> map) {
			
			Map<String, Comparable> mapaComparable = new TreeMap<>();
			
			Set<String> keySet = map.keySet();
			
			//Collection<? extends Object> values = beansOfType.values();
			//for(Object v:values){
			for(String key:keySet){
				Object value = map.get(key);
			
				Object newProxyInstance = Proxy.newProxyInstance(
						Object.class.getClassLoader(),		//the class loader
						new Class[] { Comparable.class }, 	//an array of interfaces that will be implemented by the proxy
						new ComparableBehaviorREAL(value));		//the power behind the throne in the form of the invocation handler
				
				Comparable c = (Comparable) newProxyInstance;
				mapaComparable.put(key, c);
			}
			
			return mapaComparable;
		}
	
	//BEING: SINGLETON
	private static CommonsUtils INSTANCE = null;
	private CommonsUtils(){}
    private synchronized static void createInstance() {
        if (INSTANCE == null) { 
            INSTANCE = new CommonsUtils();
        }
    }
    public static CommonsUtils getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }
    //END: SINGLETON
}

