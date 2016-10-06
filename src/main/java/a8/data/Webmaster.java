package a8.data;

import java.util.Date;

import a8.utils.DateUtils;

public class Webmaster extends Person {

	private Date creationDate; 
	
	public Webmaster() {
		super.setFirstName("Sophie");
		super.setLastName("Ochoa");
		
		this.creationDate = new Date();
	}

	@Override
	public String toString() {
		String creationDateString = DateUtils.convertUtilDate2String(creationDate);
		return getFirstName() + " " + getLastName() + " (" + creationDateString + ")";
	}
}
