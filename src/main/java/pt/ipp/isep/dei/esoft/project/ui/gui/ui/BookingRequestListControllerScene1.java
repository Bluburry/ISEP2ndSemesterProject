package pt.ipp.isep.dei.esoft.project.ui.gui.ui;

import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class BookingRequestListControllerScene1 {// implements Initializable {

	private int dates[] = new int[6];
	private BookingRequestListApplication app;

	@FXML
	private Button next01;
	@FXML
	private Button back01;
	@FXML
	private TextField startYear;
	@FXML
	private TextField startMonth;
	@FXML
	private TextField startDay;
	@FXML
	private TextField endYear;
	@FXML
	private TextField endMonth;
	@FXML
	private TextField endDay;

	public void setApplication(BookingRequestListApplication app) {
		this.app = app;
	}

	@FXML
	private void next01Click() {
		String dateText[] = new String[6];
		dateText[0] = startYear.getText();
		dateText[1] = startMonth.getText();
		dateText[2] = startDay.getText();
		dateText[3] = endYear.getText();
		dateText[4] = endMonth.getText();
		dateText[5] = endDay.getText();
		try {
			validateText(dateText);
			for (int i = 0; i < dateText.length && i < dates.length; i++)
				dates[i] = Integer.parseInt(dateText[i]);
			app.setDates(dates);
			app.setAdsList();
			cleanText();
			app.toScene2();
		} catch (IllegalArgumentException e) {
			cleanText();
		}
	}

	@FXML
	private void back01Click() {
		try {
			app.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void validateText(String[] text) throws IllegalArgumentException {
		for (int i = 0; i < text.length; i++)
			if (Objects.isNull(text[i]) || text[i].isEmpty() || text[i].isBlank())
				throw new IllegalArgumentException();
	}

	private void cleanText() {
		startYear.clear();
		startMonth.clear();
		startDay.clear();
		endYear.clear();
		endMonth.clear();
		endDay.clear();
	}

}
