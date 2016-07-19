package web.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import a8.exceptions.DummyDataAccessException;
import a8.exceptions.EightaException;
import a8.exceptions.ResponseStatusException;
import a8.exceptions.SophieException;

@Controller
@RequestMapping("/tasks/errors")
public class ExceptionsController {

	@RequestMapping(method=RequestMethod.GET, path="/f")
	public String throwException4ControllerAdvice(){
		
		/* XXX CONTINUAR LA IMPLMENTACION: PAGINA: 106
		if(true){
			throw new ResponseStatusException();
		}*/
		
		return "NO ALCANZA A LLEGAR ACA";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/f")
	public String throwResponseStatusException(){
		
		if(true){
			throw new ResponseStatusException();
		}
		
		return "NO ALCANZA A LLEGAR ACA";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/c")
	public String sendError404(HttpServletResponse response) throws IOException{
		
		//response.setStatus("", arg1); DEPRECIADO
		response.sendError(404, "response.sendError(404) - Programado en el Controller");
		return "errors/simple";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/b")
	public String throwError407(){
		return "errors/throw_error_407";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/d")
	public String throwDataAccessException(){
		
		if(true){
			throw new DummyDataAccessException("LANZADA PROGRAMATICAMENTE");
		}
		
		return "NO ALCANZA A LLEGAR ACA";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/e")
	public String throwSophieException(){
		
		if(true){
			throw new SophieException("LANZADA PROGRAMATICAMENTE!");
			//SophieException sera maneja por el metodo
			//@ExceptionHandler(SophieException.class)
			//que se encuentra aqui mismo
		}
		
		return "NO ALCANZA A LLEGAR ACA";
	}
	
	@ExceptionHandler(SophieException.class)
	//STATUS-CODE: POR DEFECTO ES 200 ??<<< OJO, SI SE DEFINE
	//EL JSP COMO PAGINA DE ERROR, AUTOMATICAMENTE EL STATUS-CODE=500 
	public String manejadorDeException(Exception e){
		return "errors/sophie_handler";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/a")
	public String throwEightaException(){
		
		if(true){
			throw new EightaException("LANZADA PROGRAMATICAMENTE!");
		}
		
		return "NO ALCANZA A LLEGAR ACA";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "errors/index";
	}
}
