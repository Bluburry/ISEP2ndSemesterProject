package pt.ipp.isep.dei.esoft.project.ui.gui.ui;

import java.util.List;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import pt.ipp.isep.dei.esoft.project.domain.*;

public class BookingRequestListControllerScene3 {

	private BookingRequestListApplication app;
	private List<Visit> visits;

	@FXML
	private Button next03;
	@FXML
	private Button back03;
	@FXML
	private Label incorrectVisit;
	@FXML
	private TextField visitChoice;
	@FXML
	private TextArea visitListing;
	@FXML
	private ToggleGroup choice;
	@FXML
	private RadioButton reject;
	@FXML
	private RadioButton accept;

	public void setApplication(BookingRequestListApplication app) {
		this.app = app;
	}

	public void setVisits(List<Visit> visits) {
		this.visits = visits;
		setListings();
	}

	private void setListings() {
		String visitsText = "";
		int i = 0;
		for (Visit v : visits) {
			visitsText += String.format("Visit #%d\n%s", ++i, v.toString());
		}
		visitListing.setText(visitsText);
	}

	@FXML
	private void next03Click() {
		boolean choice = false;
		try {
			if (accept.isPressed())
				choice = true;
			validateChoice(visitChoice.getText());
			app.setVisit(Integer.parseInt(visitChoice.getText()) - 1);
			app.answerVisit(choice);
			cleanText();
			app.toScene2();
		} catch (Exception e) {
			visitChoice.clear();
		}
	}

	@FXML
	private void back03Click() {
		try {
			cleanText();
			app.toScene2();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cleanText() {
		visitListing.clear();
		visitChoice.clear();
	}

	private void validateChoice(String choice) {
		if (Objects.isNull(choice) || choice.isEmpty() || choice.isBlank())
			throw new IllegalArgumentException();
		else {
			int i;
			try {
				i = Integer.parseInt(choice);
			} catch (Exception e) {
				throw new IllegalArgumentException();
			}
			if (i < 1 || i > visits.size())
				throw new IllegalArgumentException();
		}
	}

}
