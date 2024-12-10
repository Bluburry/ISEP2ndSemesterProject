package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AcceptOrDeclineOffersController;
import pt.ipp.isep.dei.esoft.project.domain.*;

//import java.sql.SQLOutput;
import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AcceptOrDeclineOffersUI implements Runnable {
    private AcceptOrDeclineOffersController controller;
    Scanner sc = new Scanner(System.in);
    private int option =0;
    private int optionOffer;
    private AdvertisementDTO keepAdvertisementDTO;
    private OfferDTO keppOfferDTO;
    private List<AdvertisementDTO> advertisementDTOS;
    private List<OfferDTO> offerDTOS;
    public AcceptOrDeclineOffersUI(){
        controller=new AcceptOrDeclineOffersController();
    }


    public void displayListedProperties(){
        advertisementDTOS=controller.listedPropertiesWithOffers();
        int i =0;
        optionOffer = 0;

        if(advertisementDTOS.isEmpty()){
            System.out.println("\nNo Advertisements to list...\n");
        }else{
            for (AdvertisementDTO ad:advertisementDTOS) {
                offerDTOS=ad.getOffers();
                displayAdvertisement(ad);
                if(offerDTOS.isEmpty()){
                    System.out.println("\nNo Offers to list...\n");
                }else{
                    for(OfferDTO of:offerDTOS){
                        i++;
                        System.out.print("\n" + i + ". ");
                        displayOffers(of);
                    }
                }
            }
        }

        do{
            System.out.println("\nPlease input the number of the offer you want to accept or decline.");
            try {
                optionOffer = Integer.parseInt(sc.nextLine());
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Invalid input. Please enter a valid option (an integer).");
                optionOffer=-1;
            }
        }while(optionOffer==-1 | optionOffer >  i);

        do{
            System.out.println("\n");
            System.out.print("Want to accept or decline the offer?\n");
            System.out.print("1. Accept\n");
            System.out.println("2. Decline\n");
            System.out.print("Type your option:\n");

            try {
                option = Integer.parseInt(sc.nextLine());
            } catch (InputMismatchException e) {
                sc.next();
                System.out.println("Invalid input. Please enter a valid option (an integer).");
                option = -1;
            }
            i=0;
            for (AdvertisementDTO ad:advertisementDTOS) {
                offerDTOS=ad.getOffers();
                if(offerDTOS.isEmpty()){
                }else{
                    for(OfferDTO of:offerDTOS){
                        i++;
                        if(i==optionOffer){
                            keepAdvertisementDTO=ad;
                            keppOfferDTO=of;
                        }
                    }
                }
            }

            switch (option){
                case 1:
                    try {
                        controller.acceptOffer(keepAdvertisementDTO, keppOfferDTO);
                        deleteAllOffers(keepAdvertisementDTO);
                    }catch (ParseException e){

                    }
                    break;
                case 2:
                    try{
                        controller.declineOffer(keepAdvertisementDTO,keppOfferDTO);
                        deleteOneOffer(keepAdvertisementDTO,keppOfferDTO);
                    }catch (ParseException e){

                    }
                    break;
            }
        }while(option==-1);
    }

    public void displayOffers(OfferDTO offerDTO){
        System.out.println("Offer");
        System.out.println("\nDate: " + offerDTO.getDate() + "\nstatus: " + offerDTO.getStatus() + "\nOffer value: " + offerDTO.getOffer() + "\nClient Email: " + offerDTO.getEmail());
    }

    public void displayAdvertisement(AdvertisementDTO advertisementDTO){
        PropertyType keepPropertyType = advertisementDTO.getPropertyType();
        BusinessType keepBusinessType = advertisementDTO.getBusinessType();

        double keepArea = advertisementDTO.getArea();
        double keepdistance = advertisementDTO.getDistance();

        String keepStreet = advertisementDTO.getStreet();
        String keepZip = advertisementDTO.getZip();
        String keepState = advertisementDTO.getState();
        String keepCity = advertisementDTO.getCity();
        String keepDistrict = advertisementDTO.getDistrict();

        List<String> keepPhotographs = advertisementDTO.getPhotographs();

        int keepNumberBeedrooms = advertisementDTO.getNumberBedrooms();
        int numberParking = advertisementDTO.getNumberParking();
        int numberBathrooms = advertisementDTO.getNumberBathrooms();
        String availableEquipment = advertisementDTO.getAvailableEquipment();
        Boolean basement = advertisementDTO.getBasement();
        Boolean loft = advertisementDTO.getLoft();
        SunExposure sun = advertisementDTO.getSun();

        double keepSalePrice = advertisementDTO.getSalePrice();
        double keepRentPrice = advertisementDTO.getRentPrice();
        int keepDurationOfContract = advertisementDTO.getDurationOfContract();

        Date keepDate = advertisementDTO.getDate();
        System.out.println("\n\nAdvertisement:");
        System.out.printf("\narea %.2f mts; Distance from Center: %.2f kms: \n", keepArea, keepdistance);
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
        System.out.printf("date of the Advertisement: %d-%d-%d\n", keepDate.getYear(), keepDate.getMonth(), keepDate.getDay());
        System.out.printf("Property Type: %s; Business Type: %s\n", keepPropertyType.getPropertyType(),keepBusinessType.getBusinessType());
    }

    public void deleteAllOffers(AdvertisementDTO dto){dto.getOffers().removeAll(offerDTOS);}

    public void deleteOneOffer(AdvertisementDTO ad, OfferDTO offerDTO){ad.getOffers().remove(offerDTO);}



    @Override
    public void run() {
        displayListedProperties();
    }
}
