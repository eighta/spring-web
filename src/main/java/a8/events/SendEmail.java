package a8.events;

import org.springframework.context.ApplicationEvent;

//T extends SendMailTypes
//public class SendEmail<Bouldering,Sport,Traditional> extends ApplicationEvent{
public class SendEmail<T> extends ApplicationEvent{

	private T typeParameterClass;
	
	private static final long serialVersionUID = 3391804000764121359L;
	private String to;

	public SendEmail(String to, T typeParameterClass) {
		super(typeParameterClass);
		this.setTo(to);
		 this.typeParameterClass = typeParameterClass;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
	
	public T getTheType(){ return typeParameterClass;}

}
