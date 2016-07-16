package a8.misc;

import java.lang.reflect.Field;

import a8.utils.CommonsUtils;

public class AccesingPrivateAttributes {

	public static void main(String[] args) {
		
		CommonsUtils commonsUtils = CommonsUtils.getInstance(); 
		
		ClasePrivada privada = new ClasePrivada();
		Boolean privateAttribute = commonsUtils.getPrivateAttribute(privada, "atributoPrivado", Boolean.class);
		System.out.println(privateAttribute);
	}
	
	public static void main0(String[] args) {
	
		ClasePrivada book = new ClasePrivada();
		
		try {
		    Class<?> c = book.getClass();

		    Field chap = c.getDeclaredField("atributoPrivado");
		    chap.setAccessible(true);
	  	    //chap.setBoolean(book, true);
	  	    Object boolean1 = chap.get(book);
	  	    System.out.println(boolean1);
/*	  	    
		    out.format(fmt, "after", "chapters", chap.getLong(book));

		    Field chars = c.getDeclaredField("characters");
		    out.format(fmt, "before", "characters",
			       Arrays.asList(book.characters));
		    String[] newChars = { "Queen", "King" };
		    chars.set(book, newChars);
		    out.format(fmt, "after", "characters",
			       Arrays.asList(book.characters));

		    Field t = c.getDeclaredField("twin");
		    out.format(fmt, "before", "twin", book.twin);
		    t.set(book, Tweedle.DUM);
		    out.format(fmt, "after", "twin", t.get(book));
*/
	        // production code should handle these exceptions more gracefully
		} catch (NoSuchFieldException x) {
		    x.printStackTrace();
		} catch (IllegalAccessException x) {
		    x.printStackTrace();
		}
		
	}
	

}

class ClasePrivada{
	
	public boolean atributoPublico;
	private boolean atributoPrivado = true;
	
}
