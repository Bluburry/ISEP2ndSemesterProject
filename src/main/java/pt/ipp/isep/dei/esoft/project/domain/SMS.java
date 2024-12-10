package pt.ipp.isep.dei.esoft.project.domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class SMS {

	private static final String FLDR = "./sem2Files/";
	private static final String FILE = "sms.txt";
	private static final String MESSAGE = "Your property has been listed and is now available across our services.";

	public static void notifyPropertyListing(String agentName, double agentPhoneNumber, double personPhoneNumber,
			String propertyID, Date adDate) {
		File file = new File(FLDR + FILE);
		String toWrite = String.format(
				"From: %.0f\tto: %.0f\n%s\nAgent responsible for the listing: %s\nProperty ID: %s\nDate created: %s",
				agentPhoneNumber,
				personPhoneNumber, MESSAGE, agentName, propertyID, adDate.toString());
		if (!file.exists()) {
			try {
				createFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			FileWriter writer = new FileWriter(file, true);
			writer.write(toWrite);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void createFile() throws IOException {
		File file = new File(FLDR + FILE);

		if (!file.exists()) {
			File fldr = new File(FLDR);
			if (!fldr.exists()) {
				if (fldr.mkdir())
					file.createNewFile();
				else
					throw new IOException("Failed to create directory: " + FLDR);
			} else
				file.createNewFile();
		}
	}
}
