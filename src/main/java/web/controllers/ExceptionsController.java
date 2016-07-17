package web.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import a8.exceptions.EightaException;
import a8.exceptions.SophieException;

@Controller
@RequestMapping("/tasks/errors")
public class ExceptionsController {

	
	@ExceptionHandler(SophieException.class)
	//STATUS-CODE: POR DEFECTO ES 200
	public String manejadorDeException(Exception e){
		System.out.println("==========================================");
		System.out.println(e);
		
		return "errors/sophie_handler";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/c")
	public String sendError404(HttpServletResponse response) throws IOException{
		
		//response.setStatus("", arg1); DEPRECIADO
		response.sendError(404, "Error 404 - Programado en el Controller");
		
		return "errors/simple";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/b")
	public String throwError407(){
		return "errors/throw_error_407";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/d")
	public String throwDataAccessException(){
		
		if(true){
			//clase anonima
		throw new DataAccessException("ERROR DE ACCESO SIMBOLICO A DATOS"){
			private static final long serialVersionUID = -5021377731205019910L;};
		}
		
		return "NO ALCANZA A LLEGAR ACA";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/e")
	public String throwSophieException(){
		
		if(true){
		throw new SophieException();
		}
		
		return "NO ALCANZA A LLEGAR ACA";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/a")
	public String throwEightaException(){
		
		if(true){
		throw new EightaException();
		}
		
		return "NO ALCANZA A LLEGAR ACA";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return "errors/index";
	}
}
