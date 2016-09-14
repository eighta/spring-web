package a8.test.data;

public class CsvEmployee {

	private String id;
	private String name;
	private String age;
	private String country;

	public CsvEmployee(){}
	
	public CsvEmployee(String id, String name, String age, String country) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.country = country;
	}
	
	public final String getId() {
		return id;
	}
	public final void setId(String id) {
		this.id = id;
	}
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getAge() {
		return age;
	}
	public final void setAge(String age) {
		this.age = age;
	}
	public final String getCountry() {
		return country;
	}
	public final void setCountry(String country) {
		this.country = country;
	}

}
