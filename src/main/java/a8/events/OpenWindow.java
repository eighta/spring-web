package a8.events;

import org.springframework.context.ApplicationEvent;

public class OpenWindow extends ApplicationEvent{

	private static final long serialVersionUID = -1912611451808373932L;

	public OpenWindow() {
		super(new Object());
	}

}
