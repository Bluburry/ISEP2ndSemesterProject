package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ScheduleVisitController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ScheduleVisitUI implements Runnable {
    private Scanner sc;

    public ScheduleVisitUI() {
        controller = new ScheduleVisitController();
        sc = new Scanner(System.in);
    }

    private static ScheduleVisitController controller;
    private BusinessType keepBusinessTypeChoice;

    public BusinessType listAndChooseBusinessType() {
        int option;
        System.out.println("Please introduce Business Type choice:");
        System.out.printf("1 - %s\n", BusinessType.LEASE);
        System.out.printf("2 - %s\n", BusinessType.SALE);
        option = Integer.parseInt(sc.nextLine());
        switch (option) {
            case 1:
                keepBusinessTypeChoice = BusinessType.LEASE;
                break;
            case 2:
                keepBusinessTypeChoice = BusinessType.SALE;
                break;
        }
        return keepBusinessTypeChoice;
    }

    private PropertyType keepPropertyTypeChoice;

    public PropertyType listAndChoosePropertyType() {
        int option;
        System.out.println("Please introduce Property type choice:");
        System.out.printf("1 - %s\n", PropertyType.LAND.getPropertyType());
        System.out.printf("2 - %s\n", PropertyType.APARTMENT.getPropertyType());
        System.out.printf("3 - %s\n", PropertyType.HOUSE.getPropertyType());
        option = Integer.parseInt(sc.nextLine());
        switch (option) {
            case 1:
                keepPropertyTypeChoice = PropertyType.LAND;
                break;
            case 2:
                keepPropertyTypeChoice = PropertyType.APARTMENT;
                break;
            case 3:
                keepPropertyTypeChoice = PropertyType.HOUSE;
                break;
        }
        return keepPropertyTypeChoice;
    }

    public AdvertisementDTO displayandChooseAdvertisementDTO(PropertyType keepPropertyTypeChoice,
                                                             BusinessType keepBusinessTypeChoice) {
        List<AdvertisementDTO> adsDTOSorted = ScheduleVisitController.getAdvertismentDTOListSorted(keepPropertyTypeChoice,
                keepBusinessTypeChoice);
        displayAdvertisments(adsDTOSorted);
        return chooseFromList(adsDTOSorted);
    }

    public void displayAdvertisments(List<AdvertisementDTO> adsVisitListDTO) {
        for (int i = 0; i < adsVisitListDTO.size(); i++) {
            AdvertisementDTO adDTO = adsVisitListDTO.get(i);
            System.out.println();
            System.out.printf(" ____________ Property %d _________\n",(i+1));
            displayAdvertisementDTO(adDTO);
        }
    }

    public AdvertisementDTO chooseFromList(List<AdvertisementDTO> ads) {
        Scanner sc = new Scanner(System.in);
        AdvertisementDTO ad;
        System.out.println("\nPlease select which Advertisment you want to choose:");
        int option = Integer.parseInt(sc.nextLine());
        ad = ads.get(option - 1);
        return ad;
    }

    public void scheduleVisit(AdvertisementDTO adChoiceDTO) {
        boolean valid;
        int visitYear = 0;
        int visitMonth = 0;
        int visitDay = 0;
        int visitHour = 0;
        String keepClientEmail = getUserClientEmail();
        do {
            boolean valid2;
            do {
                System.out.println("Please introduce Date and Hour for the Visit");
                System.out.println("Year");
                int keepYear = Integer.parseInt(sc.nextLine());
                System.out.println("Month");
                int keepMonth = Integer.parseInt(sc.nextLine());
                System.out.println("Day");
                int keepDay = Integer.parseInt(sc.nextLine());
                System.out.println("Hour");
                int keepHour = Integer.parseInt(sc.nextLine());

                valid2 = controller.getVerifyVisitTimeFree(adChoiceDTO, keepYear, keepMonth, keepDay, keepHour);
                if (!valid2) {
                    System.out.println("This time is ocuppied please choose new time:");
                } else {
                    visitYear = keepYear;
                    visitMonth = keepMonth;
                    visitDay = keepDay;
                    visitHour = keepHour;
                }
            } while (!valid2);
            showConditionsOfVisit(adChoiceDTO, visitYear, visitMonth, visitDay, visitHour);
            valid = validateConditionsToVisit();
        } while (!valid);
        introduceVisitToAdList(adChoiceDTO, visitYear, visitMonth, visitDay, visitHour, keepClientEmail);
    }

    public void showConditionsOfVisit(AdvertisementDTO adchoiceVisitDTO,
                                      int visitYear, int visitMonth, int visitDay, int visitHour) {
        System.out.print(" _____________Property ___________\n");
        displayAdvertisementDTO(adchoiceVisitDTO);
        System.out.printf("The visit takes place in %d/%d/%d at %dh%n", visitYear, visitMonth, visitDay, visitHour);
    }

    public Boolean validateConditionsToVisit() {
        System.out.println("Do you agree with this date for the scheduled visit?");
        System.out.println("1-Yes; 2-No");
        int choice = Integer.parseInt(sc.nextLine());
        return choice == 1;
    }

    public void introduceVisitToAdList(AdvertisementDTO adchoiceVisit, int visitYear, int visitMonth, int visitDay, int visitHour, String keepClientEmail) {
        controller.introduceVisitToAdList(adchoiceVisit, visitYear, visitMonth, visitDay, visitHour, keepClientEmail);
    }

    public void displayOperationSucess() {
        System.out.println("The visit has been scheduled");
    }

    public String getUserClientEmail() {
        return controller.getClientUserEmail();
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

        System.out.printf("area %f mts; Distance from Center: %f kms: \n", keepArea, keepdistance);
        System.out.printf("Location: Street %s, State %s, City %s, District %s, ZIP Code: %s\n"
                , keepStreet, keepState, keepCity, keepDistrict, keepZip);

        if (keepPropertyType.getPropertyType().compareTo("HOUSE") == 0) {
            System.out.printf("Number of Bedrooms: %d, Number of Parking Spaces: %d", keepNumberBeedrooms, numberParking);
            if (numberBathrooms != 0)
                System.out.printf("Number of Bathrooms: %d", numberBathrooms);
            if (availableEquipment != null)
                System.out.printf("Available Equipment: %s", numberBathrooms);
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
            System.out.printf("Number of Bedrooms: %d, Number of Parking Spaces: %d", keepNumberBeedrooms, numberParking);
            if (numberBathrooms != 0)
                System.out.printf("Number of Bathrooms: %d", numberBathrooms);
            if (availableEquipment != null)
                System.out.printf("Available Equipment: %s", numberBathrooms);
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
        System.out.printf("Date of the Advertise: %d/%d/%d\n",keepDate.getYear(),keepDate.getMonth(),keepDate.getDay());
    }
        @Override
        public void run () {
            keepPropertyTypeChoice = listAndChoosePropertyType();
            keepBusinessTypeChoice = listAndChooseBusinessType();
            AdvertisementDTO adChoiceDTO = displayandChooseAdvertisementDTO(keepPropertyTypeChoice, keepBusinessTypeChoice);
            scheduleVisit(adChoiceDTO);
            displayOperationSucess();
        }

    }
