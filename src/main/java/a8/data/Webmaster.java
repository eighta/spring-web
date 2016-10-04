package a8.data;

public class Webmaster extends Person {

	public Webmaster() {
		super.setFirstName("Sophie");
		super.setLastName("Ochoa");
	}

	@Override
	public String toString() {
		return getFirstName() + " " + getLastName();
	}
}
