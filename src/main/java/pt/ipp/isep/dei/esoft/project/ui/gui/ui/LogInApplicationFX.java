package pt.ipp.isep.dei.esoft.project.ui.gui.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LogInApplicationFX extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            Parent root = FXMLLoader.load(RespondBookingRequestControllerFX.class.getResource("LogIn.fxml"));
            Scene scene =new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }


//        FXMLLoader fxmlLoader = new FXMLLoader(RespondBookingRequestControllerFX.class.getResource("LogIn.fxml"));
//        stage.initStyle(StageStyle.UNDECORATED); // sem borders
//        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
//        stage.setScene(scene);
//        stage.show();
    }

    public static void main() {
        launch();
    }
}
