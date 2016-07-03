package a8.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

import a8.events.CloseTheDoor;
import a8.events.OpenWindow;

//@Component -> bether in XML by bean tag
public class HardHouseListener {
	
	private static final Logger logger = LoggerFactory.getLogger(HardHouseListener.class);
	
	@EventListener
	public void handleOpenWindow(OpenWindow abrirVentana){
		logger.info("abriendo la ventana... (@EventListener) " + abrirVentana);
	}
	
	@EventListener
	public void handleCloseTheDoor(CloseTheDoor cerrarPuerta){
		logger.info("cerrando la puerta... (@EventListener) " + cerrarPuerta);
	}
	
}
