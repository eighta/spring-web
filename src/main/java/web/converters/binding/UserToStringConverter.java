package web.converters.binding;

import org.springframework.binding.convert.converters.TwoWayConverter;

import a8.data.User;

public class UserToStringConverter implements TwoWayConverter{

	@Override
	public Object convertSourceToTargetClass(Object object, Class<?> clazz) throws Exception {
		String string = (String) object;
		
		User user = new User();
		user.setName(string);
		
		return user;
	}

	@Override
	public Class<?> getSourceClass() {
		return String.class;
	}

	@Override
	public Class<?> getTargetClass() {
		return User.class;
	}

	@Override
	public Object convertTargetToSourceClass(Object object, Class<?> clazz) throws Exception {
		
		if(object != null){
			User user = (User) object;
			return user.getName();
		}else{
			return "";
		}
		
	}

}
