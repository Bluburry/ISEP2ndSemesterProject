package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.MakeOfferController;
import pt.ipp.isep.dei.esoft.project.application.controller.ScheduleVisitController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MakeOfferUI implements Runnable{
    private Scanner sc;
    public MakeOfferUI() {
        controller = new MakeOfferController();
        sc = new Scanner(System.in);
    }
    private MakeOfferController controller;
    private PropertyType keepPropertyTypeChoice;
    private BusinessType keepBusinessTypeChoice;

    public AdvertisementDTO chooseAdvertisement() {
        keepPropertyTypeChoice = listAndChoosePropertyType();
        keepBusinessTypeChoice = listAndChooseBusinessType();
        return listAndDisplayandChooseAdvertisementDTO(keepPropertyTypeChoice,
                keepBusinessTypeChoice);
    }
    public PropertyType listAndChoosePropertyType() {
        PropertyType[] propertyType = controller.getPropertyTypeList();
        int i = 0;
        int option;
        System.out.println("Please introduce Property type choice:");
        for (PropertyType businessTypeChoice : propertyType) {
            System.out.println((++i) + " - " + businessTypeChoice.getPropertyType());
        }
        option = Integer.parseInt(sc.nextLine());
        switch (option) {
            case 1:
                for (PropertyType type : propertyType) {
                    if (type.getPropertyType().compareTo("LAND") == 0) {
                        keepPropertyTypeChoice = type;
                    }
                }
                break;
            case 2:
                for (PropertyType type : propertyType) {
                    if (type.getPropertyType().compareTo("APARTMENT") == 0) {
                        keepPropertyTypeChoice = type;
                    }
                }
                break;
            case 3:
                for (PropertyType type : propertyType) {
                    if (type.getPropertyType().compareTo("HOUSE") == 0) {
                        keepPropertyTypeChoice = type;
                    }
                }
                break;
        }
        return keepPropertyTypeChoice;
    }
    public BusinessType listAndChooseBusinessType() {
        BusinessType[] businessType = controller.getBusinessTypeList();
        int i = 0;
        int option;
        System.out.println("Please introduce Business Type choice:");
        for (BusinessType businessTypeChoice : businessType) {
            System.out.println((++i) + " - " + businessTypeChoice.getBusinessType());
        }
        option = Integer.parseInt(sc.nextLine());
        switch (option) {
            case 1:
                for (BusinessType type : businessType) {
                    if (type.getBusinessType().compareTo("LEASE") == 0) {
                        keepBusinessTypeChoice = type;
                    }
                }
                break;
            case 2:
                for (BusinessType type : businessType) {
                    if (type.getBusinessType().compareTo("SALE") == 0) {
                        keepBusinessTypeChoice = type;
                    }
                }
                break;
        }
        return keepBusinessTypeChoice;
    }
    public AdvertisementDTO listAndDisplayandChooseAdvertisementDTO(PropertyType keepPropertyTypeChoice,
                                                                    BusinessType keepBusinessTypeChoice) {
        List<AdvertisementDTO> ads = ScheduleVisitController.getAdvertismentDTOListSorted(keepPropertyTypeChoice,
                keepBusinessTypeChoice);
        displayAdvertisments(ads, keepPropertyTypeChoice, keepBusinessTypeChoice);
        return chooseFromList(ads);
    }
    public void displayAdvertisments(List<AdvertisementDTO> adsVisitListDTO, PropertyType keepPropertyTypeChoice,
                                     BusinessType keepBusinessTypeChoice) {
        for (int i = 0; i < adsVisitListDTO.size(); i++) {

            PropertyType keepPropertyType = adsVisitListDTO.get(i).getPropertyType();
            BusinessType keepBusinessType = adsVisitListDTO.get(i).getBusinessType();

            if ((keepPropertyTypeChoice.equals(keepPropertyType)) && (keepBusinessTypeChoice.equals(keepBusinessType))) {
                System.out.printf(" _____________Property %d ___________\n", (i + 1));
                displayAdvertisementDTO(adsVisitListDTO.get(i));
            }
        }
    }

    public void displayAdvertisementDTO(AdvertisementDTO adDTO){

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
        System.out.printf("Property Type: %s; Business Type: %s\n", keepPropertyType.getPropertyType(),keepBusinessType.getBusinessType());
    }

    public AdvertisementDTO chooseFromList(List<AdvertisementDTO> ads) {
        Scanner sc = new Scanner(System.in);
        AdvertisementDTO ad;
        System.out.println("\nPlease select which Advertisment you want to choose:");
        int option = Integer.parseInt(sc.nextLine());
        ad = ads.get(option - 1);
        return ad;
    }

    public void registerNewOffer(AdvertisementDTO adChoiceDTO,Advertisement adchoiceOffer) {
        boolean confirmation=false;
        double keepClientOffer;
        String keepClientEmail;
        do{
            keepClientEmail = getUserClientEmail();
            keepClientOffer = registerOffer(adchoiceOffer,keepClientEmail);
            confirmation = showConditionsOfOfferAndConfirm(adChoiceDTO,keepClientOffer,keepClientEmail);
        } while (confirmation==false);
        addNewOfferToAd(adchoiceOffer,keepClientOffer,keepClientEmail);
    }

    private void addNewOfferToAd(Advertisement adchoiceOffer, double keepClientOffer,String keepClientEmail) {
        controller.addNewOfferToAd(adchoiceOffer, keepClientOffer,keepClientEmail);
    }

    private double registerOffer(Advertisement adchoiceOffer,String keepClientEmail) {
        boolean confirmation1;
        int verifyOffersInThisAd;
        boolean confirmation2=false;
        int clientOffersInThisAd;
        boolean confirmation3=false;
        double keepClientOffer=0;
        do{
            keepClientOffer = makeOffer(adchoiceOffer);
            clientOffersInThisAd = verifyOtherClientOffers(adchoiceOffer,keepClientEmail);
            if (clientOffersInThisAd==0){
                confirmation3=true;
            } else {
                System.out.println("this Client allready made a Offer to this Advertisement");
            }
            confirmation1 = verifyOfferValueLowerSalePrice (adchoiceOffer,keepClientOffer);
            if ((!confirmation1)&&(confirmation3)){
                System.out.println("This Offer is above the Sale Price");
            }
            verifyOffersInThisAd = verifyOtherOffers(adchoiceOffer,keepClientOffer);
            if (verifyOffersInThisAd == 1) {
                System.out.println("There are at leat one Offer bigger than yours");
                confirmation2 = true;
            } else if (verifyOffersInThisAd == 2) {
                System.out.println("There are at leat one Offer equal than yours,the offer placed previously should be considered first when selling the property ");
                confirmation2 = true;
            } else if (verifyOffersInThisAd == 0) {
                confirmation2 = true;
            }
        } while ((!confirmation1)||(!confirmation2)||(!confirmation3));
        return keepClientOffer;
    }

    private int verifyOtherClientOffers(Advertisement adchoiceOffer, String keepClientEmail) {
        int confirmation = controller.verifyOtherClientOffers(adchoiceOffer,keepClientEmail);
        return confirmation;
    }

    private int verifyOtherOffers(Advertisement adchoiceOffer, double keepClientOffer) {
        int confirmation = controller.verifyOtherOffers(adchoiceOffer,keepClientOffer);
        return confirmation;
    }
    public double makeOffer (Advertisement ad){
        double clientOffer=0;
        BusinessType adBusinessType = ad.getRequest().getBusinessType();
        if (adBusinessType.getBusinessType().compareTo("SALE")==0){
            System.out.println("Please Make your Sale Offer:");
            clientOffer = Double.parseDouble(sc.nextLine());
        }
        if (adBusinessType.getBusinessType().compareTo("LEASE")==0){
            System.out.println("Please Make your Rent Offer:");
            clientOffer = Double.parseDouble(sc.nextLine());
        }
        return clientOffer;
    }
    public String getUserClientEmail() {
        return controller.getClientUserEmail();
    }
    private boolean verifyOfferValueLowerSalePrice (Advertisement adchoiceOffer, double keepClientOffer) {
        boolean confirmation = controller.verifyOfferValueLowerSalePrice(adchoiceOffer,keepClientOffer);
        return confirmation;
    }

    public boolean showConditionsOfOfferAndConfirm(AdvertisementDTO adchoiceVisitDTO, double keepClientOffer, String keepClientEmail)  {
        boolean confirmation=false;
        System.out.print(" _____________Property ___________\n");
        displayAdvertisementDTO(adchoiceVisitDTO);
        System.out.printf("Your order is : %.2f $ and Your Email: %s\n", keepClientOffer, keepClientEmail);
        return confirmation = validateConditionsOfOffer();
    }
    public Boolean validateConditionsOfOffer() {
        System.out.println("Do you agree with this Offer?");
        System.out.println("1-Yes; 2-No");
        int choice = Integer.parseInt(sc.nextLine());
        return choice == 1;
    }

    public void displayOperationSucess() {
        System.out.println("The Offer is Registered");
    }

    @Override
    public void run() {
        AdvertisementDTO adChoiceDTO = chooseAdvertisement();
        Advertisement adchoiceOffer = AdvertisementMapper.fromDTOtoAD(adChoiceDTO);
        registerNewOffer(adChoiceDTO,adchoiceOffer);
        displayOperationSucess();
    }
}
