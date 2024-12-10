package pt.ipp.isep.dei.esoft.project.ui.gui.ui;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.ListDealsController;
import pt.ipp.isep.dei.esoft.project.domain.Advertisement;

import java.io.IOException;
import java.util.List;

public class DealsController {
    @FXML
    private Label firstSelectTitle;
    @FXML
    private ToggleGroup sortAlg;
    @FXML
    private ToggleGroup sortOrder;

    @FXML
    private TextArea DoneDeals;
    @FXML
    private Group firstPage;
    @FXML
    private Group secondPage;
    private ListDealsController listDealsController = new ListDealsController();

    @FXML
    protected void ListDealsFX() {
        //firstSelectTitle.setText(listDealsController.listDeals());
        RadioButton selectedAlg = (RadioButton) sortAlg.getSelectedToggle();
        RadioButton selectedOrder = (RadioButton) sortOrder.getSelectedToggle();
        //firstSelectTitle.setText(selectedAlg.getId());
        /*FXMLLoader fxmlLoader = new FXMLLoader(DealsController.class.getResource("ListDeals_02.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setScene(scene);
        stage.show();*/
        firstPage.setDisable(true);
        firstPage.setOpacity(0);
        secondPage.setDisable(false);
        secondPage.setOpacity(1);
        List<Advertisement> sortedAds = listDealsController.listDeals(Integer.parseInt(selectedAlg.getId()), Integer.parseInt(selectedOrder.getId()));
        try{
            for (Advertisement ad : sortedAds) {
                DoneDeals.appendText(ad.adAndDealToString() + "\n\n\n");
            }
        }catch(Exception e){}
    }

    @FXML
    protected void close(){
        Stage stage = (Stage) firstSelectTitle.getScene().getWindow();
        stage.close();
    }

}
