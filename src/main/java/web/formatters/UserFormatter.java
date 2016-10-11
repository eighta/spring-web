package web.formatters;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import a8.data.User;

public class UserFormatter implements Formatter<User>{

	@Override
	public String print(User object, Locale locale) {
		return object.getName();
	}

	@Override
	public User parse(String text, Locale locale) throws ParseException {
		User user = new User();
		user.setName(text);
		return user;
	}

}
