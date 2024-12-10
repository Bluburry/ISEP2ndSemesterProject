package pt.ipp.isep.dei.esoft.project.exceptions;

public class AcceptedOfferNotFoundException extends Exception {

	public AcceptedOfferNotFoundException(String str) {
		super(str);
	}

	public AcceptedOfferNotFoundException() {
		super("Advertisement does not contain an accepted offer.");
	}
}
