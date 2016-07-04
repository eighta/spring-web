package a8.core;


//ESTA CLASE en realidad debe ser una interface
//ya que ofrece una serie de servicios
//XXX exponerlo como spring bean
public class Life {

	public String giveMeDauthersName(){
		return "SOPHIE OCHOA"; //XXX leerlo del proeprties
	}
	
	
	
	
	//BEING: SINGLETON
	private static Life INSTANCE = null;
	private Life(){}
    private synchronized static void createInstance() {
        if (INSTANCE == null) { 
            INSTANCE = new Life();
        }
    }
    public static Life getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }
    //END: SINGLETON
	
}
