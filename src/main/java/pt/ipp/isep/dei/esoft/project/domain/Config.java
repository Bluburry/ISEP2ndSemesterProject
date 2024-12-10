package pt.ipp.isep.dei.esoft.project.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	private String company_designation;
	private String sorting_algorithm;
	private String email_format;

	private static final String CONFIGURATION_FILE = "sem2Files/config.properties";
	private static final String COMPANY_DESIGNATION = "Company.Designation";
	private static final String SORTING_ALGORITHM = "Sorting.Algorithm";
	private static final String EMAIL_FORMAT = "Email.Format";

	public Config() {
		Properties prop = getInfo();
		company_designation = prop.getProperty(COMPANY_DESIGNATION);
		sorting_algorithm = prop.getProperty(SORTING_ALGORITHM);
		email_format = prop.getProperty(EMAIL_FORMAT);
	}

	public String getSortingAlgorithm() {
		return sorting_algorithm;
	}

	public String getCompanyDesignation() {
		return company_designation;
	}

	public String getEmailFormat() {
		return email_format;
	}

	private Properties getInfo() {
		Properties props = new Properties();
		try {
			InputStream input = new FileInputStream(CONFIGURATION_FILE);
			props.load(input);
			input.close();
		} catch (FileNotFoundException fe) {
			try {
				createConfig();
			} catch (IOException ie) {
				ie.printStackTrace();
			}
			getInfo();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (props.getProperty(COMPANY_DESIGNATION) == null || props.getProperty(SORTING_ALGORITHM) == null
				|| props.getProperty(EMAIL_FORMAT) == null) {
			try {
				createConfig();
				props = getInfo();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return props;
	}

	private void createConfig() throws IOException {
		File config = new File(CONFIGURATION_FILE);
		Properties props = new Properties();

		if (!config.exists()) {
			File fldr = new File(config.getParent());
			if (fldr.mkdir())
				config.createNewFile();
			else
				throw new IOException("Failed to create directory: " + fldr.toString());
		}

		try {
			FileWriter output = new FileWriter(config);
			props.setProperty(COMPANY_DESIGNATION, "REAL ESTATE USA");
			props.setProperty(SORTING_ALGORITHM, "MergeSort");
			props.setProperty(EMAIL_FORMAT, "DEI");
			props.store(output, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// just in case it's needed
	private static Config singleton = null;

	public static Config getInstance() {
		if (singleton == null)
			synchronized (Config.class) {
				singleton = new Config();
			}
		return singleton;
	}
}
