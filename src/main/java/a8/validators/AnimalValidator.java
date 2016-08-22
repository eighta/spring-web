package a8.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import a8.data.Animal;

@Component
public class AnimalValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Animal.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		final String EMPTY_FIELD_DEFAULT_MSG = "Este campo no puede ser vacio (DEFAULT MSG)";
		
		ValidationUtils.rejectIfEmpty(errors,"name","animal.name.empty",EMPTY_FIELD_DEFAULT_MSG);
		ValidationUtils.rejectIfEmpty(errors,"age","animal.age.empty",EMPTY_FIELD_DEFAULT_MSG);
		
		Animal animal = (Animal) target;
	}

}
