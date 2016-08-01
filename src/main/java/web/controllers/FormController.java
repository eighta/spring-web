package web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import a8.data.Hospital;
import a8.data.Person;

@Controller
@RequestMapping("/tasks/forms")
public class FormController {

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
	public String goToForm(Model model){
		
		List<Hospital> listaDeHospitales = new ArrayList<>();
		listaDeHospitales.add(new Hospital("1","Cardio Infantil") );
		listaDeHospitales.add(new Hospital("2","Madre Bernarda") );
		model.addAttribute(listaDeHospitales);
		
		Person person = new Person("","","1982-09-29");
		model.addAttribute(person);
		
		return "forms/the_form";
	}
	
	@RequestMapping
	public String index(){
		return "forms/index";
	}
}
