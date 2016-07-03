package a8.events;

import org.springframework.context.ApplicationEvent;

public class CloseTheDoor extends ApplicationEvent{

	private static final long serialVersionUID = 985490829528950151L;

	public CloseTheDoor() {
		super(new Object());
	}

}
