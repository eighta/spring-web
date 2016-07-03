package a8.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

import a8.events.SendEmail;
import a8.events.types.Bouldering;
import a8.events.types.Sport;
import a8.events.types.Traditional;

public class EmailListener {

	private static final Logger logger = LoggerFactory.getLogger(EmailListener.class);
	
	//OJO: con estos tres listeners, que aunque parece que maneja tipos distintos
	//en realidad. solo maneja hasta el primer level de tipado, es decir
	//los tres metodos seran disparados para manejar todos los SendEmail<?> 
	
	@EventListener
	public void handleCorreosDeBouldering(SendEmail<Bouldering> correoBoulder){
		logger.info("<<<<<<leyendo correo Bouldering (@EventListener): " + correoBoulder.getTo() + " - " + correoBoulder);
		
		//if(correoBoulder instanceof SendEmail<Bouldering>){ <- NOT WORK
		//http://stackoverflow.com/questions/7335018/cannot-perform-instanceof-check-against-parameterized-type-arraylistfoo
		
		//Bouldering theType = <- CREE UNO QUE ES DE Bouldering 
		//porque es typo parametrizado que espera en argumento del metodo 
		Object theType = correoBoulder.getTheType();
		//pero en realidad The JVM has no parameterized type information
		if(!(theType instanceof Bouldering)){
			logger.error("Toma tu error de Casting... (y yo esperando un tipo Bouldering, cuando es un: " + theType);
		}else{
			logger.info("boulder level: " + correoBoulder.getTheType().getLevel() );
		}
		
	}
	
	@EventListener
	public void handleCorreosDeSport(SendEmail<Sport> correoDeSport){
		logger.info("<<<<<<leyendo correo Sport (@EventListener): " + correoDeSport.getTo() + " - " + correoDeSport);
	}
	
	@EventListener
	public void handleCorreosDeTraditional(SendEmail<Traditional> correoDeTraditional){
		logger.info("<<<<<<leyendo correo Traditional (@EventListener): " + correoDeTraditional.getTo() + " - " + correoDeTraditional);
	}
	
	
}
