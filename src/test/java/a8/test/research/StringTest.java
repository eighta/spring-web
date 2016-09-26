package a8.test.research;

import org.junit.Test;

public class StringTest {

	@Test
	public void probar(){
		
		String text = "hola %s, me gustaria saber tu %s";
		String formated = String.format(text, "Mundo", "Edad");
		System.out.println(formated);
	}
}
