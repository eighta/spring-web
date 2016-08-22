package web.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import a8.data.Animal;
import a8.data.Hospital;
import a8.data.Person;
import a8.validators.AnimalValidator;

@Controller
@RequestMapping("/tasks/forms")
public class FormController {

	
	//BEGIN-FORM-VALIDATIONS
	@Autowired
	private AnimalValidator animalValidator;
	
	@InitBinder //XXX TODO, falta indagar todo el potencial que se puede hacer con el
	private void initTheBinder(WebDataBinder binder){
		binder.setValidator(animalValidator);
	}
	
	@RequestMapping(path="/b", method=RequestMethod.POST)
	public String createAnimal(
			@ModelAttribute @Valid Animal animal,
			BindingResult result
			){
		
		
		if(result.hasErrors()){
			return "forms/form_validation";
		}
		
		return "redirect:999";
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.GET)
	public String animalSuccess(){
		return "forms/success"; //EL JSP DICE PERSON
	}
	
	@RequestMapping("/b")
	public String goToFormValidation(Model model){
		model.addAttribute(new Animal());
		return "forms/form_validation";
	}
	//END-FORM-VALIDATIONS
	
	@RequestMapping(path="/persons/{id}", method=RequestMethod.GET)
	public String guardarPersonaGet(){
System.out.println("GET");
		return "forms/success";
	}
	
	@RequestMapping(path="/persons/{id}", method=RequestMethod.POST)
	public String guardarPersona(){
System.out.println("POST");
		return "redirect:999";
	}
	
	@RequestMapping("/a")
	public String goToForm(
			Model model
			){
		
		Person person = new Person("","","1982-09-29");
		model.addAttribute(person);
		
		return "forms/the_form";
	}
	
	@RequestMapping
	public String index(){
		return "forms/index";
	}
	
	@ModelAttribute
	//Este metodo que se encuentra anotado con @ModelAttribute, 
	//siempre es llamado, al momento en el que este mismo Controlador,
	//recibe una peticion
	public List<Hospital> cargarListaDeHospitales(){
		List<Hospital> listaDeHospitales = new ArrayList<>();
		listaDeHospitales.add(new Hospital("1","Cardio Infantil") );
		listaDeHospitales.add(new Hospital("2","Madre Bernarda") );
		return listaDeHospitales;
	}
}
