package pt.ipp.isep.dei.esoft.project.ui.gui.ui;

import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.ListBookingRequestController;
import pt.ipp.isep.dei.esoft.project.domain.*;

public class BookingRequestListApplication extends Application {

	private ListBookingRequestController controller = new ListBookingRequestController();

	private BookingRequestListControllerScene1 sceneController1;
	private BookingRequestListControllerScene2 sceneController2;
	private BookingRequestListControllerScene3 sceneController3;

	private Stage stage;
	private Advertisement ad;
	private Visit visit;
	private List<Advertisement> adList;
	private List<Visit> visitList;
	private int dates[];

	@Override
	public void start(Stage stage) throws IOException {
		this.stage = stage;
		FXMLLoader loader = new FXMLLoader(
				BookingRequestListControllerScene1.class.getResource("ListBookingRequests_01.fxml"));
		Parent root = (Parent) loader.load();
		sceneController1 = loader.getController();
		sceneController1.setApplication(this);
		stage.setScene(new Scene(root));
		stage.show();
	}

	public static void main() {
		launch();
	}

	public ListBookingRequestController getController() {
		return controller;
	}

	public void setDates(int dates[]) {
		this.dates = dates;
	}

	public List<Advertisement> getAdsList() {
		return adList;
	}

	public void setAdsList() {
		adList = controller.getAdvertisements();
	}

	public Advertisement getAd() {
		return ad;
	}

	public void setAd(int choice) {
		ad = adList.get(choice);
	}

	public List<Visit> getVisitList() {
		return visitList;
	}

	public void setVisitList() {
		visitList = controller.getVisitsList(ad, dates[0], dates[1], dates[2], dates[3],
				dates[4], dates[5]);
	}

	public void setVisit(int choice) {
		visit = visitList.get(choice);
	}

	public void answerVisit(boolean status) {
		controller.answerVisit(ad, visit, status);
	}

	public void toScene1() throws IOException {
		try {
			FXMLLoader loader = new FXMLLoader(
					BookingRequestListControllerScene1.class.getResource("ListBookingRequests_01.fxml"));
			Parent root = (Parent) loader.load();
			sceneController1 = loader.getController();
			stage.setScene(new Scene(root));
			sceneController1.setApplication(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void toScene2() {
		try {
			FXMLLoader loader = new FXMLLoader(
					BookingRequestListControllerScene1.class.getResource("ListBookingRequests_02.fxml"));
			Parent root = (Parent) loader.load();
			sceneController2 = loader.getController();
			sceneController2.setApplication(this);
			sceneController2.setAds(adList);
			stage.setScene(new Scene(root));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void toScene3() {
		try {
			FXMLLoader loader = new FXMLLoader(
					BookingRequestListControllerScene1.class.getResource("ListBookingRequests_03.fxml"));
			Parent root = (Parent) loader.load();
			sceneController3 = loader.getController();
			sceneController3.setApplication(this);
			sceneController3.setVisits(this.visitList);
			stage.setScene(new Scene(root));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() throws IOException {
		stage.close();
	}
}
