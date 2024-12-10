package pt.ipp.isep.dei.esoft.project.exceptions;

public class PersonNotFoundException extends Exception {

	public PersonNotFoundException() {
		super("Person was not found in the system.");
	}

	public PersonNotFoundException(String str) {
		super (str);
	}
	
}
