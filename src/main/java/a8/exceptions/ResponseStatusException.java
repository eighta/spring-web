package a8.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.GONE, reason="El item solicitado no fue encontrado")
public class ResponseStatusException extends RuntimeException {

	private static final long serialVersionUID = 974882012553766983L;

}
