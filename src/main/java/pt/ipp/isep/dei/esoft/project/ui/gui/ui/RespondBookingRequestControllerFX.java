package pt.ipp.isep.dei.esoft.project.ui.gui.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.RespondBookingRequestController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


public class RespondBookingRequestControllerFX implements Initializable {

    private final RespondBookingRequestController controller = new RespondBookingRequestController();
    @FXML
    private final ImageView brandingImageView = new ImageView();
    @Override
    public void  initialize (URL url, ResourceBundle resourceBundle) {
        File brandingFile= new File("sem2Files/images/logo.PNG");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);
    }

    @FXML
    private Label test;
    @FXML
    private Label keepAgentNameLabel = new Label();
    @FXML
    private Label keepAgentEmailLabel = new Label();
    @FXML
    private Label keepAgentPhoneLabel1 = new Label();
    @FXML
    private Group firstPage;
    @FXML
    private Group secondPage;
    @FXML
    private Group thirdPage;
    @FXML
    private Group fourPage;
    private String keepAgentEmail = controller.getAgentEmail();
    private String keepAgentName = controller.getAgentName(keepAgentEmail);
    private double keepAgentPhone = controller.getAgentPhone(keepAgentEmail);



    private AdvertisementDTO keepAdvVisitDTO;
    private Visit keepVisitDTO;

    @FXML
    protected void onActionImportButton() {
        keepAgentNameLabel.setText(keepAgentName);
        keepAgentEmailLabel.setText(keepAgentEmail);
        keepAgentPhoneLabel1.setText(String.format("%.0f",keepAgentPhone));
    }
    @FXML
    protected void confirmStage1() throws IOException {
        Stage stage= new Stage();
        FXMLLoader fxmlLoader1 = new FXMLLoader(DealsController.class.getResource("RespondBookingRequest_01.fxml"));
        Scene scene1 = new Scene(fxmlLoader1.load(), 1200, 800);
        stage.setScene(scene1);
        stage.show();
    }
    @FXML
    private TextArea chooseProperty01TextArea = new TextArea();
    @FXML
    private TextArea displayPropertyVisitsTextArea = new TextArea();
    @FXML
    private TextArea displayConfirmationTextArea = new TextArea();
    @FXML
    private TextField keepAdVisitDTOChoiceTextField= new TextField();
    @FXML
    private TextField keepVisitChoiceTextField= new TextField();
    @FXML
    private Label checkNumberFormatValidationLabel= new Label();
    @FXML
    public AdvertisementDTO displayAdsDTOAgent() {
        List<AdvertisementDTO> keepListAdvVisitDTO = controller.getAdsListAgentDTO(keepAgentEmail);
        chooseProperty01TextArea.appendText("Please Choose the Property to Schedule Visit:\n");
        int cont = 1;
        int option=1;
        for (AdvertisementDTO ad : keepListAdvVisitDTO) {
            String textPropertyidentifier = "\n_______________ Property number: " + cont + " _______________\n";
            chooseProperty01TextArea.appendText(textPropertyidentifier);
            displayAdvertisementDTO(ad);
            cont++;
        }
        try{
          option = Integer.parseInt(keepAdVisitDTOChoiceTextField.getText());
        }catch(Exception e){
            checkNumberFormatValidationLabel.setOpacity(1);
            checkNumberFormatValidationLabel.setText("Not a valid number");
        }
        return keepListAdvVisitDTO.get(option - 1);
    }
    @FXML
    public void displayAdvertisementDTO(AdvertisementDTO adDTO) {

        PropertyType keepPropertyType = adDTO.getPropertyType();
        BusinessType keepBusinessType = adDTO.getBusinessType();

        double keepArea = adDTO.getArea();
        double keepdistance = adDTO.getDistance();

        String keepStreet = adDTO.getStreet();
        String keepZip = adDTO.getZip();
        String keepState = adDTO.getState();
        String keepCity = adDTO.getCity();
        String keepDistrict = adDTO.getDistrict();

        List<String> keepPhotographs = adDTO.getPhotographs();

        int keepNumberBeedrooms = adDTO.getNumberBedrooms();
        int numberParking = adDTO.getNumberParking();
        int numberBathrooms = adDTO.getNumberBathrooms();
        String availableEquipment = adDTO.getAvailableEquipment();
        Boolean basement = adDTO.getBasement();
        Boolean loft = adDTO.getLoft();
        SunExposure sun = adDTO.getSun();

        double keepSalePrice = adDTO.getSalePrice();
        double keepRentPrice = adDTO.getRentPrice();
        int keepDurationOfContract = adDTO.getDurationOfContract();

        Date keepDate = adDTO.getDate();

        chooseProperty01TextArea.appendText(String.format("Location: Street %s, State %s, City %s, District %s, ZIP Code: %s\n"
                , keepStreet, keepState, keepCity, keepDistrict, keepZip));
        chooseProperty01TextArea.appendText(String.format("area %.2f mts; Distance from Center: %.2f kms: \n", keepArea, keepdistance));

        if (keepPropertyType.getPropertyType().compareTo("HOUSE") == 0) {
            chooseProperty01TextArea.appendText(String.format("Number of Bedrooms: %d, Number of Parking Spaces: %d, ", keepNumberBeedrooms, numberParking));
            if (numberBathrooms != 0)
                chooseProperty01TextArea.appendText(String.format("Number of Bathrooms: %d, ", numberBathrooms));
            if (availableEquipment != null)
                chooseProperty01TextArea.appendText(String.format("Available Equipment: %s\n", availableEquipment));
            if (basement != null)
                if (basement)
                    chooseProperty01TextArea.appendText("Property has a basement");
                else
                    chooseProperty01TextArea.appendText("Property doesn't have a basement");
            if (loft != null)
                if (loft)
                    chooseProperty01TextArea.appendText("Property is habitable");
                else
                    chooseProperty01TextArea.appendText("Property is not habitable");
            if (sun != null)
                chooseProperty01TextArea.appendText(String.format("Property has Sun Exposure in %s direction\n", sun.getSunExposure()));
        }

        if (keepPropertyType.getPropertyType().compareTo("APARTMENT") == 0) {
            chooseProperty01TextArea.appendText(String.format("Number of Bedrooms: %d, Number of Parking Spaces: %d, ", keepNumberBeedrooms, numberParking));
            if (numberBathrooms != 0)
                chooseProperty01TextArea.appendText(String.format("Number of Bathrooms: %d, ", numberBathrooms));
            if (availableEquipment != null)
                chooseProperty01TextArea.appendText(String.format("Available Equipment: %s", availableEquipment));
            System.out.println();
        }
        if (keepPhotographs != null) {
            for (String keepPhotograph : keepPhotographs) {

                chooseProperty01TextArea.appendText(String.format(keepPhotograph));
            }
        }

        if (keepBusinessType.getBusinessType().compareTo("SALE") == 0) {
            chooseProperty01TextArea.appendText(String.format("Sale Price: %.2f €\n", keepSalePrice));
        }

        if (keepBusinessType.getBusinessType().compareTo("LEASE") == 0) {
            chooseProperty01TextArea.appendText(String.format("Lease Price: %.2f €", keepRentPrice));
            chooseProperty01TextArea.appendText(String.format("Duration of Contract: %d months\n", keepDurationOfContract));
        }
        chooseProperty01TextArea.appendText(String.format("date of the Advertisement: %s\n", keepDate.getDate()));
        chooseProperty01TextArea.appendText(String.format("Property Type: %s; Business Type: %s\n", keepPropertyType.getPropertyType(),
                keepBusinessType.getBusinessType()));
    }
    @FXML
    private Visit listAndChooseVisit(AdvertisementDTO adDTO) {
        int cont = 1;
        List<Visit> visits = adDTO.getVisits();
        if (visits.isEmpty()) {
            displayPropertyVisitsTextArea.appendText("There are no Schedule Visits for this Property\n");
        } else {
            displayPropertyVisitsTextArea.appendText("Choose Visit:\n");
            for (Visit v : visits) {
                if (v.getStatus().compareTo(VisitStatus.PENDING.getVisitStatus()) == 0) {
                    displayPropertyVisitsTextArea.appendText(String.format("%d - ", cont));
                    displayVisit(v);
                    cont++;
                }
            }
            if (cont == 1)
                displayPropertyVisitsTextArea.appendText("All Visits in this Property are Scheduled");
        }
        int option = Integer.parseInt(keepAdVisitDTOChoiceTextField.getText());
        return visits.get(option - 1);
    }
    @FXML
    private void displayVisit(Visit v) {
        displayPropertyVisitsTextArea.appendText(String.format("Date: %d/%d/%d at %dh - to %s\n", v.getYear(), v.getMonth(), v.getDay(), v.getHour(), v.getClientEmail()));
    }
    @FXML
    protected void firstPageSwitch(){
        firstPage.setDisable(true);
        firstPage.setOpacity(0);
        secondPage.setDisable(false);
        secondPage.setOpacity(1);
        keepAdvVisitDTO=displayAdsDTOAgent();
    }
    @FXML
    protected void secondPageSwitch(){
        secondPage.setDisable(true);
        secondPage.setOpacity(0);
        thirdPage.setDisable(false);
        thirdPage.setOpacity(1);
        keepVisitDTO=listAndChooseVisit(keepAdvVisitDTO);
    }
    @FXML
    protected void thirdPageSwitch(){
        thirdPage.setDisable(true);
        thirdPage.setOpacity(0);
        fourPage.setDisable(false);
        fourPage.setOpacity(1);
        displayForConfirmation(keepAdvVisitDTO,keepVisitDTO);
    }
    private void displayForConfirmation(AdvertisementDTO adDTO, Visit v) {
        PropertyType keepPropertyType = adDTO.getPropertyType();
        BusinessType keepBusinessType = adDTO.getBusinessType();

        double keepArea = adDTO.getArea();
        double keepdistance = adDTO.getDistance();

        String keepStreet = adDTO.getStreet();
        String keepZip = adDTO.getZip();
        String keepState = adDTO.getState();
        String keepCity = adDTO.getCity();
        String keepDistrict = adDTO.getDistrict();

        List<String> keepPhotographs = adDTO.getPhotographs();

        int keepNumberBeedrooms = adDTO.getNumberBedrooms();
        int numberParking = adDTO.getNumberParking();
        int numberBathrooms = adDTO.getNumberBathrooms();
        String availableEquipment = adDTO.getAvailableEquipment();
        Boolean basement = adDTO.getBasement();
        Boolean loft = adDTO.getLoft();
        SunExposure sun = adDTO.getSun();

        double keepSalePrice = adDTO.getSalePrice();
        double keepRentPrice = adDTO.getRentPrice();
        int keepDurationOfContract = adDTO.getDurationOfContract();

        Date keepDate = adDTO.getDate();

        displayConfirmationTextArea.appendText(String.format("Location: Street %s, State %s, City %s, District %s, ZIP Code: %s\n"
                , keepStreet, keepState, keepCity, keepDistrict, keepZip));
        displayConfirmationTextArea.appendText(String.format("area %.2f mts; Distance from Center: %.2f kms: \n", keepArea, keepdistance));

        if (keepPropertyType.getPropertyType().compareTo("HOUSE") == 0) {
            displayConfirmationTextArea.appendText(String.format("Number of Bedrooms: %d, Number of Parking Spaces: %d, ", keepNumberBeedrooms, numberParking));
            if (numberBathrooms != 0)
                displayConfirmationTextArea.appendText(String.format("Number of Bathrooms: %d, ", numberBathrooms));
            if (availableEquipment != null)
                displayConfirmationTextArea.appendText(String.format("Available Equipment: %s\n", availableEquipment));
            if (basement != null)
                if (basement)
                    displayConfirmationTextArea.appendText("Property has a basement\n");
                else
                    displayConfirmationTextArea.appendText("Property doesn't have a basement\n");
            if (loft != null)
                if (loft)
                    displayConfirmationTextArea.appendText("Property is habitable\n");
                else
                    displayConfirmationTextArea.appendText("Property is not habitable\n");
            if (sun != null)
                displayConfirmationTextArea.appendText(String.format("Property has Sun Exposure in %s direction\n", sun.getSunExposure()));
        }

        if (keepPropertyType.getPropertyType().compareTo("APARTMENT") == 0) {
            displayConfirmationTextArea.appendText(String.format("Number of Bedrooms: %d, Number of Parking Spaces: %d, ", keepNumberBeedrooms, numberParking));
            if (numberBathrooms != 0)
                displayConfirmationTextArea.appendText(String.format("Number of Bathrooms: %d, ", numberBathrooms));
            if (availableEquipment != null)
                displayConfirmationTextArea.appendText(String.format("Available Equipment: %s", availableEquipment));
            System.out.println();
        }
        if (keepPhotographs != null) {
            for (String keepPhotograph : keepPhotographs) {

                displayConfirmationTextArea.appendText(String.format(keepPhotograph));
            }
        }

        if (keepBusinessType.getBusinessType().compareTo("SALE") == 0) {
            displayConfirmationTextArea.appendText(String.format("Sale Price: %.2f €\n", keepSalePrice));
        }

        if (keepBusinessType.getBusinessType().compareTo("LEASE") == 0) {
            displayConfirmationTextArea.appendText(String.format("Lease Price: %.2f €\n", keepRentPrice));
            displayConfirmationTextArea.appendText(String.format("Duration of Contract: %d months\n", keepDurationOfContract));
        }
        displayConfirmationTextArea.appendText(String.format("date of the Advertisement: %s\n", keepDate.getDate()));
        displayConfirmationTextArea.appendText(String.format("Property Type: %s; Business Type: %s\n", keepPropertyType.getPropertyType(),
                keepBusinessType.getBusinessType()));

        displayConfirmationTextArea.appendText("\nVisit:\n");

        displayConfirmationTextArea.appendText(String.format("Date: %d/%d/%d at %dh - with %s: %s\n", v.getYear(), v.getMonth(), v.getDay(), v.getHour(), controller.getClientNameFromEmail(v.getClientEmail()), v.getClientEmail()));
    }
    @FXML
    private Button cancelButton = new Button();
    @FXML
    protected void close1(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Button acceptedVisitButton = new Button();
    @FXML
    private Button rejectedVisitButton = new Button();
    @FXML
    private Button cancelButton2 = new Button();
    @FXML
    private Button reportButton = new Button();
    private String keepClientEmail = "cliente@this.app";
    private String keepNameEmail = "Cliente Name";

    private String emailDomain = controller.verifyEmailPlatform();
    @FXML
    public void rejectVisit(){
        controller.sendMessageVisitRejected(keepClientEmail, keepNameEmail
                , keepAgentEmail, keepAgentName, keepAgentPhone, emailDomain);
        controller.changeVisitStatusRejected(keepAdvVisitDTO, keepVisitDTO);

    }
    @FXML
    public void acceptedVisit(){
        controller.sendMessageVisitAccepted(keepClientEmail, keepNameEmail
                , keepAgentEmail, keepAgentName, keepAgentPhone, emailDomain);
        controller.changeVisitStatusAccepted(keepAdvVisitDTO, keepVisitDTO);

    }

}
