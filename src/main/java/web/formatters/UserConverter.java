package web.formatters;

import org.springframework.core.convert.converter.Converter;

import a8.data.User;

public class UserConverter implements Converter<String,User>{

	@Override
	public User convert(String source) {
		User user = new User();
		user.setName(source);
		return user;
	}

}
