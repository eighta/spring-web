package a8.test.utils;

import java.util.Properties;
import java.util.StringJoiner;

public class CommonsUtils {

	//BEGIN: SINGLETON
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
	
	// UTIL
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

}
