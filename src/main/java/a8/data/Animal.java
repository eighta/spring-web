package a8.data;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

public class Animal implements Serializable{

	@Pattern(regexp = "doggie", message="Debe ser {2}")
	private String name;
	
	private User user;
	
	@Max(4)
	private Integer age;
	
	public Animal(){
//		user = new User();
//		user.setName("eight-a");
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
