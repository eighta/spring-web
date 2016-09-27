package a8.test.research;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.Ignore;
import org.junit.Test;

public class ReflectionTest {

	@Test
	public void accessToNonPublicField() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException{
		
		Class<?> clazz = Class.forName("a8.data.User");
		assertNotNull(clazz);
		
		Object object = clazz.newInstance();
		assertNotNull(object);
		
		Field  fieldPrivate = null;
		for(Field f: clazz.getDeclaredFields()){
//			System.out.println(f.getName());
			if(f.getName().equalsIgnoreCase("_secretNumber")){
				fieldPrivate = f;
				break;
			}
		}
		
		fieldPrivate.setAccessible(Boolean.TRUE);
		
		Integer _secretNumber = (Integer)fieldPrivate.get(object);
		assertThat(_secretNumber , is(equalTo(0)));
		
		fieldPrivate.set(object, 8);
		_secretNumber = (Integer)fieldPrivate.get(object);
		assertThat(_secretNumber , is(equalTo(8)));
		
		//System.out.println(fieldPrivate.getName());
		
	}
	
	@Test
	@Ignore
	public void accessToNonPublicMethod() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException{
		
		Class<?> clazz = Class.forName("a8.data.User");
		assertNotNull(clazz);
		
		Object object = clazz.newInstance();
		assertNotNull(object);
		
		Integer secretNumber = (Integer) MethodUtils.invokeExactMethod(object, "getSecretNumber");
		assertThat(secretNumber , is(equalTo(0)));
		
		//SE LANZA EL METODO QUE CALCULA EL NUMERO SECRETO
		Method calculateSecretNumberMethod = clazz.getDeclaredMethod("calculateSecretNumber");
		calculateSecretNumberMethod.setAccessible(Boolean.TRUE);
		calculateSecretNumberMethod.invoke(object);
		
		//SE VERIFICA NUEVAMENTE EL NUMERO SECRETO
		secretNumber = (Integer) MethodUtils.invokeExactMethod(object, "getSecretNumber");
		assertThat(secretNumber , is(not(equalTo(0))));
		
		
	}
}
