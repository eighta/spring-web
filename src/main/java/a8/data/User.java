package a8.data;

import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class User {

    private String name;
    private String location;
    private Integer _secretNumber = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	private void calculateSecretNumber(){
		
		int minimum = 1;
		int maximum = 11;
		
		Random rand = new Random();
		_secretNumber = minimum + rand.nextInt((maximum - minimum) + 1);
	}
	
	@Override
    public String toString() {
        return "User [name=" + name + ", location=" + location + "]";
    }

	public Integer getSecretNumber() {
		return _secretNumber;
	}

	public void setSecretNumber(Integer secretNumber) {
		this._secretNumber = secretNumber;
	}

}
