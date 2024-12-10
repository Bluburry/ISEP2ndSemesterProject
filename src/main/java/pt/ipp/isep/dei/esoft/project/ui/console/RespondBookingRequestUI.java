package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RespondBookingRequestController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.RespondBookingRequestApplicationFX;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class RespondBookingRequestUI implements Runnable {
    private Scanner sc;

    public RespondBookingRequestUI() {
        controller = new RespondBookingRequestController();
        sc = new Scanner(System.in);
    }

    private RespondBookingRequestController controller;
    @Override
    public void run() {
        String keepAgentEmail = controller.getAgentEmail();
        String keepAgentName = controller.getAgentName(keepAgentEmail);
        double keepAgentPhone = controller.getAgentPhone(keepAgentEmail);
        RespondBookingRequestApplicationFX.main();

        AdvertisementDTO keepAdVisitDTO = displayAndChooseAdsDTOAgent(keepAgentEmail);
        Visit keepVisitRequest = listAndChooseVisit(keepAdVisitDTO);
        String keepEmailClientVisitRequest = getClientEmailFromVisit(keepVisitRequest);
        String keepNameClientVisitRequest = getClientNameFromEmail(keepEmailClientVisitRequest);
        displayAndRespondToVisitRequest(keepAdVisitDTO, keepVisitRequest, keepEmailClientVisitRequest
                , keepNameClientVisitRequest, keepAgentEmail, keepAgentName, keepAgentPhone);

        test(keepAgentName, keepAgentPhone);


    }
    private String getClientNameFromEmail(String keepEmailClientVisitRequest) {
        return controller.getClientNameFromEmail(keepEmailClientVisitRequest);
    }
    private String getClientEmailFromVisit(Visit keepVisitRequest) {
        return controller.getClientEmailFromVisit(keepVisitRequest);
    }
    private void displayAndRespondToVisitRequest(AdvertisementDTO keepAdVisitDTO, Visit keepVisitRequest
            , String keepEmailClientVisitRequest, String keepNameClientVisitRequest
            , String keepAgentEmail, String keepAgentName, double keepAgentPhone) {
        System.out.println("Request Visit Property:\n");
        displayAdvertisementDTO(keepAdVisitDTO);
        System.out.println("Request to visit:\n");
        displayVisit(keepVisitRequest);
        String emailDomain = verifyEmailPlatform();
        respondToRequestVisit(keepAdVisitDTO, keepVisitRequest, keepEmailClientVisitRequest
                , keepNameClientVisitRequest, keepAgentEmail, keepAgentName, keepAgentPhone, emailDomain);
    }

    private String verifyEmailPlatform() {
        return controller.verifyEmailPlatform();
    }

    private void respondToRequestVisit(AdvertisementDTO keepAdVisitDTO, Visit keepVisitRequest
            , String keepEmailClientVisitRequest, String keepNameClientVisitRequest
            , String keepAgentEmail, String keepAgentName, double keepAgentPhone, String emailDomain) {
        System.out.println("Choose:\n 1 - Accept Visit\n 2 - Dont Accept Visit\n 3 - Visit Continues Pending\n");
        int option = Integer.parseInt(sc.nextLine());
        switch (option) {
            case (2):
                controller.sendMessageVisitRejected(keepEmailClientVisitRequest, keepNameClientVisitRequest
                        , keepAgentEmail, keepAgentName, keepAgentPhone, emailDomain);
                controller.changeVisitStatusRejected(keepAdVisitDTO, keepVisitRequest);
                break;
            case (1):
                controller.sendMessageVisitAccepted(keepEmailClientVisitRequest, keepNameClientVisitRequest
                        , keepAgentEmail, keepAgentName, keepAgentPhone, emailDomain);

                controller.changeVisitStatusAccepted(keepAdVisitDTO, keepVisitRequest);

                break;
        }
    }
    private AdvertisementDTO displayAndChooseAdsDTOAgent(String keepAgentEmail) {
        List<AdvertisementDTO> keepListAdvVisitDTO = controller.getAdsListAgentDTO(keepAgentEmail);
        System.out.println("Please Choose the Property to Schedule Visit:");
        int cont = 1;
        int option;
        for (AdvertisementDTO ad : keepListAdvVisitDTO) {
            System.out.printf("_______________ Property %d _______________\n", cont);
            displayAdvertisementDTO(ad);
            cont++;
        }
        option = Integer.parseInt(sc.nextLine());
        return keepListAdvVisitDTO.get(option - 1);
    }
    private Visit listAndChooseVisit(AdvertisementDTO adDTO) {
        int cont = 1;
        List<Visit> visits = adDTO.getVisits();
        if (visits.isEmpty()) {
            System.out.println("There are no Schedule Visits for this Property");
        } else {
            System.out.println("Choose hour to Schedule Visit");
            for (Visit v : visits) {
                if (v.getStatus().compareTo(VisitStatus.PENDING.getVisitStatus()) == 0) {
                    System.out.printf("%d - ", cont);
                    displayVisit(v);
                    cont++;
                }
            }
            if (cont == 1)
                System.out.println("All Visits in this Property are Scheduled");
        }
        int option = Integer.parseInt(sc.nextLine());
        return visits.get(option - 1);
    }
    private void displayVisit(Visit v) {
        System.out.printf("Date: %d/%d/%d at %dh - to %s\n", v.getYear(), v.getMonth(), v.getDay(), v.getHour(), v.getClientEmail());
    }
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

        System.out.printf("area %.2f mts; Distance from Center: %.2f kms: \n", keepArea, keepdistance);
        System.out.printf("Location: Street %s, State %s, City %s, District %s, ZIP Code: %s\n"
                , keepStreet, keepState, keepCity, keepDistrict, keepZip);

        if (keepPropertyType.getPropertyType().compareTo("HOUSE") == 0) {
            System.out.printf("Number of Bedrooms: %d, Number of Parking Spaces: %d, ", keepNumberBeedrooms, numberParking);
            if (numberBathrooms != 0)
                System.out.printf("Number of Bathrooms: %d, ", numberBathrooms);
            if (availableEquipment != null)
                System.out.printf("Available Equipment: %s", availableEquipment);
            System.out.println();
            if (basement != null)
                if (basement)
                    System.out.println("Property has a basement");
                else
                    System.out.println("Property doesn't have a basement");
            if (loft != null)
                if (loft)
                    System.out.println("Property is habitable");
                else
                    System.out.println("Property is not habitable");
            if (sun != null)
                System.out.printf("Property has Sun Exposure in %s direction", sun.getSunExposure());
            System.out.println();
        }

        if (keepPropertyType.getPropertyType().compareTo("APARTMENT") == 0) {
            System.out.printf("Number of Bedrooms: %d, Number of Parking Spaces: %d, ", keepNumberBeedrooms, numberParking);
            if (numberBathrooms != 0)
                System.out.printf("Number of Bathrooms: %d, ", numberBathrooms);
            if (availableEquipment != null)
                System.out.printf("Available Equipment: %s", availableEquipment);
            System.out.println();
        }
        if (keepPhotographs != null) {
            for (String keepPhotograph : keepPhotographs) {

                System.out.println(keepPhotograph);
            }
        }

        if (keepBusinessType.getBusinessType().compareTo("SALE") == 0) {
            System.out.printf("Sale Price: %.2f €", keepSalePrice);
            System.out.println();
        }

        if (keepBusinessType.getBusinessType().compareTo("LEASE") == 0) {
            System.out.printf("Lease Price: %.2f €", keepRentPrice);
            System.out.printf("Duration of Contract: %d months", keepDurationOfContract);
            System.out.println();
        }
        System.out.printf("date of the Advertisement: %d/%d/%d\n", keepDate.getYear(), keepDate.getMonth(), keepDate.getDay());
        System.out.printf("Property Type: %s; Business Type: %s\n", keepPropertyType.getPropertyType(), keepBusinessType.getBusinessType());
    }
    private void test(String keepAgentName, double getAgentPhone) {
        System.out.println(keepAgentName);
        System.out.println(getAgentPhone);
    }

}
