package web.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebFlowListener {

	private static final Logger logger = LoggerFactory.getLogger(WebFlowListener.class);
	
	public void onStart(){
		logger.info("Starting WebFlow...");
	}
	public void onEnd(){
		logger.info("Ending WebFlow...");
	}
	public void onViewStateEntry(){
		logger.info("View-State Entring...");
	}
	public void onViewStateExit(){
		logger.info("View-State Exiting...");
	}
	public void onViewStateRender(){
		logger.info("View-State Rendering...");
	}
	public void onViewStateTransition(){
		logger.info("View-State Transition...");
	}
}
