package a8.services;

import org.springframework.stereotype.Service;
import org.springframework.webflow.execution.Event;

import a8.data.Animal;
import a8.exceptions.EightaException;

@Service
public class AnimalEvalService {

	public String usingSetReturnString(){
		System.out.println("METHOD usingSetReturnString(...)");
		return "ojoPelao";
	}
	
	public void usingSet(){
		System.out.println("METHOD usingSet(...)");
	}
	
	public Event methodReturnEvent(){
		System.out.println("METHOD methodReturnEvent(...)");
		Event event = new Event(new Animal(), "_eventId");
		return event;
	}
	
	public Animal methodThrowingException(){
		System.out.println("METHOD methodThrowingException(...)");
		throw new EightaException("PAILAS");
	}
	
	public Animal methodReturnAnimal(){
		System.out.println("METHOD methodReturnAnimal(...)");
		return new Animal();
	}
	
	public Object methodReturnNull(){
		System.out.println("METHOD methodReturnNull(...)");
		return null;
	}
	
	public String methodReturnString(){
		System.out.println("METHOD methodReturnString(...)");
		return "theString";
	}
	
	public void methodReturnVoid(){
		System.out.println("METHOD methodReturnVoid(...)");
	}
	
	public boolean isPureBreed(Animal animal){
		return animal.getBreed().equalsIgnoreCase("pure");
	}
	
	public boolean haveName(Animal animal){
		return animal.getName() != null;
	}
}
