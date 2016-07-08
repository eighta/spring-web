package a8.test.plain;

import static org.junit.Assert.*;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

import a8.utils.CommonsUtils;

public class EntireAppTest {

	CommonsUtils commonsUtils = CommonsUtils.getInstance();
	
	String bundlesDirectory = "bundles";
	String resource = "greetings";
	String entirePath = bundlesDirectory+"/"+resource;
	
	@Test
	public void readResourceBundleColombia(){
		
		Locale locale = new Locale("es","CO");
		ResourceBundle resourceBundle = commonsUtils.getResourceBundle(entirePath, locale);
		
		String text = testResourceBundle(resourceBundle);
		assertEquals("Bienvenido", text);
	}
	
	@Test
	public void readResourceBundleAleman(){
		
		Locale locale = new Locale("de","DE");
		ResourceBundle resourceBundle = commonsUtils.getResourceBundle(entirePath, locale);
		
		String text = testResourceBundle(resourceBundle);
		assertEquals("Willkommen", text);
	}
	
	@Test
	public void readResourceBundleFrances(){
		
		Locale locale = new Locale("fr","FR");
		ResourceBundle resourceBundle = commonsUtils.getResourceBundle(entirePath, locale);
		
		String text = testResourceBundle(resourceBundle);
		assertEquals("Bienvenue", text);
	}
	
	private String testResourceBundle(ResourceBundle resourceBundle){
		assertNotNull(resourceBundle);
		
		String welcome = resourceBundle.getString("welcome");
		assertNotNull(welcome);
		
		return welcome;
	}
		
}

