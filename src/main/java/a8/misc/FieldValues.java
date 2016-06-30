package a8.misc;

public class FieldValues {
	String[] FIELD1 = new String[]{"value1", "value2",System.getProperty("os.name")};
	
	public static final String OS_NAME;
	public static final String CONSTANTE;
	
	static{
		OS_NAME = System.getProperty("os.name");
		CONSTANTE = "1";
	}
}
