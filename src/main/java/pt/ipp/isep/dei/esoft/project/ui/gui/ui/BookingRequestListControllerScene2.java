package pt.ipp.isep.dei.esoft.project.ui.gui.ui;

import java.util.List;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.domain.*;

public class BookingRequestListControllerScene2 {

	private BookingRequestListApplication app;
	private List<Advertisement> ads;

	@FXML
	private Button next02;
	@FXML
	private Button back02;
	@FXML
	private Label incorrectAd;
	@FXML
	private TextField adChoice;
	@FXML
	private TextArea adListing;

	public void setApplication(BookingRequestListApplication app) {
		this.app = app;
	}

	public void setAds(List<Advertisement> ads) {
		this.ads = ads;
		setListings();
	}

	private void setListings() {
		String adsText = "";
		int i = 0;
		for (Advertisement ad : ads) {
			adsText += String.format("Advertisement #%d\n%s\n", ++i, ad.toString());
		}
		adListing.setText(adsText);
	}

	@FXML
	private void next02Click() {
		try {
			validateChoice(adChoice.getText());
			app.setAd(Integer.parseInt(adChoice.getText()) - 1);
			app.setVisitList();
			cleanText();
			app.toScene3();
		} catch (Exception e) {
			adChoice.clear();
		}
	}

	@FXML
	private void back02Click() {
		try {
			cleanText();
			app.toScene1();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cleanText() {
		adListing.clear();
		adChoice.clear();
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
			if (i < 1 || i > ads.size())
				throw new IllegalArgumentException();
		}
	}
}
