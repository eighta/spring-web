package web.controllers;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import a8.business.PersonManager;
import a8.data.Person;
import a8.data.SomeValue;
import web.interceptors.AuditInterceptor;


//The @SessionAttributes is used on a controller class to designate which model attributes 
//should be stored in the session
@SessionAttributes("person")

@Controller
@RequestMapping("/tasks/ctrls")
public class TheController {

	private static final Logger logger = LoggerFactory.getLogger(TheController.class);
	
	private PersonManager personManager;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("persons", personManager.findAll());
		return "persons/list";
	}
	
	
	@Autowired
	public TheController(PersonManager personManager){
		this.personManager=personManager;
	}
	
	
	@RequestMapping("/s")
	public String getFlashAttribute(
			//@ModelAttribute("anotherPerson")		AL ACTIVAR ESTO, SE QUEDAN EN LA SESSION
			//Person anotherPerson, Model model
			){
		
		//model.addAttribute("theAnotherPerson", anotherPerson);
		
		return "controllers/q";
	}
	
	@RequestMapping("/r")
//	using flash attributes, which are saved in an object model implementing the 
//	RedirectAttributes interface. Flash attributes provide a way for one request to
//	store attributes intended for use in another.	
	public String flashAttributes(
			final RedirectAttributes redirectAttributes){
		
		redirectAttributes.addFlashAttribute("anotherPerson", new Person() );
		return "redirect:s";
	}
	
	@RequestMapping("/q")
	public String getSessionAttribute(){
		return "controllers/q";
	}
	
	@RequestMapping("/p")
	public String setSessionAttirubte(Model model){
		model.addAttribute(new Person() );
		return "controllers/simple";
	}
	
	
	/*
	OJO: DE ESTA FORMA, ESTE METODO SIEMPRE ES EJECUTADO
	
	@ModelAttribute
	public Person getPerson(@PathVariable Long id){
		System.out.println("CUANDO EJECTUADO?");
		return new Person();
	}
	*/
	
	@RequestMapping("/o")
	public String getRedirect(Model model){
		
		System.out.println(model.containsAttribute("RequestAttribute") );
		
		model.addAttribute("method","o");
		return "controllers/simple";
	}
	
	@RequestMapping("/n")
	public String redirecting(Model model){
		model.addAttribute("RequestAttribute","SI");
		
		//EL REDIRECT OCACIONARA QUE COLOQUE LOS PARAMTROS EN LA URL DEL REQUEST
		return "redirect:o";
	}
	
	@RequestMapping("/m")
	@ModelAttribute("someValue")
	public Character returningNull(Model model){
		return null;
	}
	
//	When the handler method returns an object, the DispatcherServlet has to infer the view to render the
//	model. The logical view name is extracted from the mapping URL by stripping the first "/" and the extension if
//	present
	
//	The logical view name can be extracted from the request URI by removing the leading slash and
//	extension. This is done by the RequestToViewNameTranslator
	
	@RequestMapping("/l")
	@ModelAttribute("someValue")
	public Character returningObjectModelAttribute(){
		return new Character('Ñ');
	}
	
//	When only one object needs to be added to the model, the object can simply be returned by the method
//	and it is automatically added to the model. This obviously does not work with objects of type String,
//	because Spring assumes the returned String value is a logical view name.

//POR DEFECTO, la vista a seleccionar es la del request URL, i.e.:
//la URL: http://localhost:8080/spring-web/s/tasks/ctrls/k
//es mapeada por: @RequestMapping("/tasks/ctrls/k")
//y la vista a resolver es: tasks/ctrls/k (VIEW RESOLVER-> [/WEB-INF/views/tasks/ctrls/k.jsp])	
	
	
	@RequestMapping("/k")
	public SomeValue returningObject(){
		SomeValue someValue = new SomeValue("EIGHT-A");
		return someValue;
	}
	
	@RequestMapping("/j/{id}")
	public String usingSpringTagLib(Model model,
			@PathVariable Long id){
		model.addAttribute("method","j");
		model.addAttribute("someValue", id);
		return "controllers/simple";
	}
	
	@RequestMapping("/i")
	public String usingNumberFormat(Model model,
		@RequestParam
		@NumberFormat(style = NumberFormat.Style.CURRENCY) 
		Double amount){
		
		model.addAttribute("method","i");
		model.addAttribute("someValue", amount);
		return "controllers/simple";
	}
	
	@RequestMapping("/h/{id}")
	public String noName(Model model,
			//@PathVariable SE VUELVE OPCIONAL, PERO NO ES RECOMENDABLE
			Long id,
			@RequestParam String foo,
			@RequestParam(required=false, defaultValue="_default") String unknown){
		
		model.addAttribute("method","h");
		model.addAttribute(model);
		model.addAttribute(id);
		model.addAttribute(foo);
		model.addAttribute("unknown",unknown);
		
		return "controllers/flex_signa";
	}
	
	@RequestMapping("/g")
	public String flexibleSignature(
			Model model, HttpServletRequest request,
			HttpSession session, Principal principal,
			Locale locale, HttpServletResponse response
			){
		
/* PARA DETERMINAR QUE NOMBRE SE LE ASIGNAN AL MODELO CON EL METODO:
 	addAttribute(...) y solo se pasa el Object
		System.out.println(Conventions.getVariableName(model));
		System.out.println(Conventions.getVariableName(request));
		System.out.println(Conventions.getVariableName(session));
		System.out.println(principal);
		System.out.println(Conventions.getVariableName(locale));
		System.out.println(Conventions.getVariableName(response));
*/		
		model.addAttribute("method","g");
		model.addAttribute(model);
		model.addAttribute(request);
		model.addAttribute(session);
//		model.addAttribute(principal);
		model.addAttribute(locale);
		model.addAttribute(response);
		
		return "controllers/flex_signa";
	}
	
	//@RequestMapping(value="/f/{id}?dateOfBirth=1983-08-18")
	@RequestMapping(value="/f/{id}")
	public String byPathVariableAndDate(
				
				@RequestParam("dateOfBirth") 
				@DateTimeFormat(pattern = "yyyy-MM-dd")
				Date date,
				@PathVariable Long id, Model model){
		
		model.addAttribute("method","f");
		
		DateFormat dateFormat = DateFormat.getDateInstance();
		String format = dateFormat.format(new Date());
		model.addAttribute("someValue", format);
		model.addAttribute("nacimiento", date);
		return "controllers/simple";
	}
			
			
	//the regular expression [\\d]* insures the id to be numeric,
	//made of one or more digits.
	@RequestMapping(value = "/e/{id:[\\d]*}")
	public String byPathVariableAndRegExp(
			@PathVariable("id") Long id, Model model){
		model.addAttribute("method","e");
		model.addAttribute("someValue", id);
		return "controllers/simple";
	}
	@RequestMapping(value="/d/{id}")
	public String byPathVariable(
			@PathVariable("id") Long id, Model model){
		model.addAttribute("method","d");
		model.addAttribute("someValue", id);
		return "controllers/simple";
	}
	
	@RequestMapping(value="/c", params={"id=1123"})
	public String bySpecificNameValue(
			@RequestParam("id") Long id, Model model) {
	
		model.addAttribute("method","c");
		model.addAttribute("someValue", id);
		return "controllers/simple";
	}
	
	@RequestMapping("/b")
	public String byRequestParam(
			@RequestParam("id")Long id,Model model ) {
		
		model.addAttribute("method","b");
		model.addAttribute("someValue", id);
		return "controllers/simple";
	}
	
	@RequestMapping("/a")
	public String onlyByUrl() {
		return "controllers/simple";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model){
		logger.info("METHOD: index");
		
		model.addAttribute("modelId",987654321);
		return "controllers/index";
	}
}
