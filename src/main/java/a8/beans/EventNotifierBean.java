package a8.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import a8.events.CloseTheDoor;
import a8.events.OpenWindow;
import a8.events.SendEmail;
import a8.events.types.Bouldering;
import a8.events.types.Sport;
import a8.events.types.Traditional;

@Component
public class EventNotifierBean {

	private static final Logger logger = LoggerFactory.getLogger(EventNotifierBean.class);
	private final ApplicationEventPublisher publisher;

	@Autowired//or implementing the interface ApplicationEventPublisherAware 
	public EventNotifierBean(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	public void openTheWindow() {
		OpenWindow content = new OpenWindow();
		logger.info("[EMITING EVENT] ABRIENDO LA VENTANA... " + content);
		this.publisher.publishEvent(content);
	}

	public void closeTheDoor() {
		CloseTheDoor content = new CloseTheDoor();
		logger.info("[EMITING EVENT] CERRANDO LA PUERTA..." + content);
		this.publisher.publishEvent(content);
	}

	public void sendEmail2Bouldering() {
		Bouldering content = new Bouldering(1937);
		SendEmail<Bouldering> email = new SendEmail<Bouldering>("bouldering", content);
		logger.info("[EMITING EVENT] >>>enviando correo a los del Bouldering... " + content);
		this.publisher.publishEvent(email);
	}

	public void sendEmail2Sport() {
		Sport content = new Sport();
		logger.info("[EMITING EVENT] >>>enviando correo a los de la Deportiva... " + content);
		this.publisher.publishEvent(new SendEmail<Sport>("sport", content));
	}

	public void sendEmail2Traditional() {
		Traditional content = new Traditional();
		logger.info("[EMITING EVENT] >>>enviando correo a los de Tradicional... " + content);
		this.publisher.publishEvent(new SendEmail<Traditional>("traditional", content ));
	}

}
