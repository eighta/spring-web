package a8.data;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class GrammyAward implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(GrammyAward.class);
	
	private Person person;
	
	@Autowired
	public GrammyAward(Person person){
		logger.info("Instanciando... [with: " + person);
		this.person = person;
	}
	
}
