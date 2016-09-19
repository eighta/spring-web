package a8.exceptions;

public class EightaException extends RuntimeException{

	private static final long serialVersionUID = -1674874782871071399L;

	public EightaException(String message) {
		super(message);
	}
	
	public EightaException(Exception e) {
		super(e);
	}

}
