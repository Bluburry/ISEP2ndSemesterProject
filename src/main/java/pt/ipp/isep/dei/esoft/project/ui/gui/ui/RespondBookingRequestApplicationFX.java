package pt.ipp.isep.dei.esoft.project.ui.gui.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.RespondBookingRequestController;

import java.io.IOException;

public class RespondBookingRequestApplicationFX extends Application  {
    private final RespondBookingRequestControllerFX respondBookingRequestControllerFX = new RespondBookingRequestControllerFX();
    private final RespondBookingRequestController controller = new RespondBookingRequestController();

    @Override
    public void start(Stage stage) throws IOException{

        FXMLLoader fxmlLoader = new FXMLLoader(DealsController.class.getResource("RespondBookingRequest_00.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setScene(scene);
        stage.show();
    }

    public static void main() {
        launch();
    }

}
