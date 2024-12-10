package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.SubmitPropertyController;
import pt.ipp.isep.dei.esoft.project.domain.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SubmitPropertyUI implements Runnable {

    private final SubmitPropertyController controller;
    private PropertyType keepPropertyType;
    private double keepAreaProperty;
    private double keepDistanceCityCenter;
    private List<String> keepPhotographs;
    private String keepStreet, keepZipCode, keepStateUS, keepCity, keepDistrict, keepAvailableEquipment;
    private int keepNumberBedrooms, keepNumberParking, keepNumberBathrooms, keepDurationContract;
    private boolean keepBasement;
    private boolean keepLoft;
    private SunExposure keepSunExposure;
    private Property newAllProperty;
    private BusinessType keepBusinessType;
    private double keepSalePrice, keepRentPrice;
    private Employee keepAgent;
    private String keepOwnerEmail;
    private Person keepOwner;

    Scanner sc = new Scanner(System.in);


    public SubmitPropertyUI() {
        controller = new SubmitPropertyController();
    }

    public void listAndChoosePropertyType() {
        PropertyType[] propertyType = controller.getPropertyTypeList();
        int i = 0;
        int option = 0;
        System.out.println("Please select property type:");
        for (PropertyType property : propertyType) {
            System.out.println((++i) + " - " + property.getPropertyType());
        }
        option = Integer.parseInt(sc.nextLine());
        if ((option == 1) || (option == 2) || (option == 3)) {
            switch (option) {
                case 1:
                    for (PropertyType type : propertyType) {
                        if (type.getPropertyType().equals("LAND")) {
                            keepPropertyType = type;
                        }
                    }
                    break;
                case 2:
                    for (PropertyType type : propertyType) {
                        if (type.getPropertyType().equals("APARTMENT")) {
                            keepPropertyType = type;
                        }
                    }
                    break;
                case 3:
                    for (PropertyType type : propertyType) {
                        if (type.getPropertyType().equals("HOUSE")) {
                            keepPropertyType = type;
                        }
                    }
                    break;
            }
        } else {
            System.out.println("Option not correct. Choose again");
            option = Integer.parseInt(sc.nextLine());
        }
    }

    public void getDataProperty() {
        System.out.println("Area Of Property");
        keepAreaProperty = Double.parseDouble(sc.nextLine());
        System.out.println("\nDistance from City");
        keepDistanceCityCenter = Double.parseDouble(sc.nextLine());
        System.out.println("\nIntroduce Location of Property");
        System.out.println("Street: ");
        keepStreet = sc.nextLine();
        System.out.println("\nZip Code :");
        keepZipCode = sc.nextLine();
        System.out.println("\nState US:");
        keepStateUS = sc.nextLine();
        System.out.println("\nCity:");
        keepCity = sc.nextLine();
        System.out.println("\nDistrict:");
        keepDistrict = sc.nextLine();

        int option;
        do {
            System.out.println("\nPlease introduce Photo");
            String keepPhotograph = sc.nextLine();
            List<String> keepPhotographs = new ArrayList<>();
            keepPhotographs.add(keepPhotograph);
            System.out.println("Want to introduce more photos? 1-Yes; 2-No");
            option = Integer.parseInt(sc.nextLine());
            }while (option == 1);
        if ((keepPropertyType.getPropertyType().compareTo("APARTMENT") == 0 || (keepPropertyType.getPropertyType().compareTo("HOUSE") == 0))) {
            System.out.println();
            System.out.println("Introduce number of Bedrooms:");
            keepNumberBedrooms = Integer.parseInt(sc.nextLine());
            System.out.println();
            System.out.println("Introduce number of Parking Spaces:");
            keepNumberParking = Integer.parseInt(sc.nextLine());
            System.out.println();
            System.out.println("Does The Property have Avaliable Equipment 1-Yes; 2-No");
            int options = Integer.parseInt(sc.nextLine());
            if (options == 1) {
                System.out.println("What Avaliable Equipment does the Property have:");
                keepAvailableEquipment = sc.nextLine();
            }
            System.out.println();
            System.out.println("This information is Optional. How many Bathrooms does the Property have?");
            options = Integer.parseInt(sc.nextLine());
            if (options != 0) {
                keepNumberBathrooms = options;
            }
        }
        if (keepPropertyType.getPropertyType().compareTo("HOUSE") == 0) {
            System.out.println();
            System.out.println("This information is Optional. Does The Property have a Basement: 1-Yes; 2-No");
            int options = Integer.parseInt(sc.nextLine());
            keepBasement = options == 1;
            System.out.println();
            System.out.println("This information is Optional. Is The Property Habitable: 1-Yes; 2-No");
            options = Integer.parseInt(sc.nextLine());
            keepLoft = options == 1;
            listAndChoseSunExposureType();
        }
    }

    public void listAndChoseSunExposureType() {
        SunExposure[] sunExposure = controller.getSunExposureTypeList();
        int i = 0;
        int option;
        System.out.println("This information is Optional. What is the Property Sun Exposure Direction:\n");
        System.out.println("1 - NORTH; 2 - SOUTH; 3 - EAST; 4 - WEST;\n");
        for (SunExposure sunExposureOption : sunExposure) {
            System.out.println((++i) + " - " + sunExposureOption.getSunExposure());
        }
        option = Integer.parseInt(sc.nextLine());
        switch (option) {
            case 1:
                for (SunExposure type : sunExposure) {
                    if (type.getSunExposure().compareTo("NORTH")==0) {
                        keepSunExposure = type;
                    }
                }
                break;
            case 2:
                for (SunExposure type : sunExposure) {
                    if (type.getSunExposure().compareTo("SOUTH")==0) {
                        keepSunExposure = type;
                    }
                }
                break;
            case 3:
                for (SunExposure type : sunExposure) {
                    if (type.getSunExposure().compareTo("EAST")==0) {
                        keepSunExposure = type;
                    }
                }
                break;
            case 4:
                for (SunExposure type : sunExposure) {
                    if (type.getSunExposure().compareTo("WEST")==0) {
                        keepSunExposure = type;
                    }
                }
                break;
        }
    }

    public void displayPropertyInformation() {
        System.out.print("\nProperty Information\n");
        System.out.printf("Area Of Property: %-10.2f\n", keepAreaProperty);
        System.out.printf("Distance from City: %-10.2f\n", keepDistanceCityCenter);
        System.out.printf("Street: %-30s\n", keepStreet);
        System.out.printf("Zip Code: %-30s\n", keepZipCode);
        System.out.printf("State US: %-30s\n", keepStateUS);
        System.out.printf("City: %-30s\n", keepCity);
        System.out.printf("District: %-30s\n", keepDistrict);
        if (keepPropertyType.getPropertyType().compareTo("HOUSE") == 0) {
            System.out.printf("Number of Bedrooms: %-30d\n", keepNumberBedrooms);
            System.out.printf("Number of Parking Spaces: %-30d\n", keepNumberParking);
            if (keepAvailableEquipment != null) {
                System.out.printf("Avaliable Equipment in the Property:  %-30s\n", keepAvailableEquipment);
            }
            if (keepNumberBathrooms != 0) {
                System.out.printf("Number of Bathrooms in the Property:  %-30d\n", keepNumberBathrooms);
            }
            if (keepBasement) {
                System.out.println("The Property has a Basement.");
            }
            if (keepLoft) {
                System.out.println("The Property is Habitable.");
            }
            if (keepSunExposure != null) {
                System.out.printf("The Property Sun Exposure Direction is:  %-30s\n", keepSunExposure.getSunExposure());
            }
        }
        if (keepPropertyType.getPropertyType().compareTo("APARTMENT") == 0) {
            System.out.printf("Number of Bedrooms: %-30d\n", keepNumberBedrooms);
            System.out.printf("Number of Parking Spaces: %-30d\n", keepNumberParking);
            if (keepAvailableEquipment != null) {
                System.out.printf("Avaliable Equipment in the Property:\n  %-30s\n", keepAvailableEquipment);
            }
            if (keepNumberBathrooms != 0) {
                System.out.printf("Number of Bathrooms in the Property:  %-30d\n", keepNumberBathrooms);
            }
        }
    }

    public boolean getValidationForConditions() {
        System.out.println("Please Confirm if all the Information Correct\n 1 - Yes; 2 - No\n");
        int option = Integer.parseInt(sc.nextLine());
        return option == 1;
    }

    public void createNewAllProperty() {
        if (keepPropertyType.getPropertyType().compareTo("HOUSE") == 0) {
            newAllProperty = createNewHouseProperty(keepPropertyType, keepAreaProperty, keepDistanceCityCenter, keepPhotographs, keepNumberBedrooms, keepNumberParking, keepNumberBathrooms, keepAvailableEquipment, keepBasement, keepLoft, keepSunExposure, keepStreet, keepZipCode, keepStateUS, keepCity, keepDistrict);
        } else if (keepPropertyType.getPropertyType().compareTo("APARTMENT") == 0) {
            newAllProperty = createNewApartmentProperty(keepPropertyType, keepAreaProperty, keepDistanceCityCenter, keepPhotographs, keepNumberBedrooms, keepNumberParking, keepNumberBathrooms, keepAvailableEquipment, keepStreet, keepZipCode, keepStateUS, keepCity, keepDistrict);
        } else if (keepPropertyType.getPropertyType().compareTo("LAND") == 0) {
            newAllProperty = createNewProperty(keepPropertyType, keepAreaProperty, keepDistanceCityCenter, keepPhotographs, keepStreet, keepZipCode, keepStateUS, keepCity, keepDistrict);
        }
    }

    public Property createNewHouseProperty(PropertyType keepPropertyType, double keepAreaProperty, double keepDistanceCityCenter, List<String> keepPhotographs, int keepNumberBedrooms, int keepNumberParking, int keepNumberBathrooms, String keepAvailableEquipment, boolean keepBasement, boolean keepLoft, SunExposure keepSunExposure, String keepStreet, String keepZipCode, String keepStateUS, String keepCity, String keepDistrict) {
        return controller.createNewHouseProperty(keepPropertyType, keepAreaProperty, keepDistanceCityCenter, keepPhotographs, keepNumberBedrooms, keepNumberParking, keepNumberBathrooms, keepAvailableEquipment, keepBasement, keepLoft, keepSunExposure, keepStreet, keepZipCode, keepStateUS, keepCity, keepDistrict);
        //keepNewHouse;
    }

    public Property createNewApartmentProperty(PropertyType keepPropertyType, double keepAreaProperty, double keepDistanceCityCenter, List<String> keepPhotographs, int keepNumberBedrooms, int keepNumberParking, int keepNumberBathrooms, String keepAvailableEquipment, String keepStreet, String keepZipCode, String keepStateUS, String keepCity, String keepDistrict) {
        return controller.createNewApartmentProperty(keepPropertyType, keepAreaProperty, keepDistanceCityCenter, keepPhotographs, keepNumberBedrooms, keepNumberParking, keepNumberBathrooms, keepAvailableEquipment, keepStreet, keepZipCode, keepStateUS, keepCity, keepDistrict);
    }

    public Property createNewProperty(PropertyType keepPropertyType, double keepAreaProperty, double keepDistanceCityCenter, List<String> keepPhotographs, String keepStreet, String keepZipCode, String keepStateUS, String keepCity, String keepDistrict) {
        return controller.createNewProperty(keepPropertyType, keepAreaProperty, keepDistanceCityCenter, keepPhotographs, keepStreet, keepZipCode, keepStateUS, keepCity, keepDistrict);
    }

    public void listAndChooseBusinessType() {
        BusinessType[] businessType = controller.getBusinessTypeList();
        int i = 0;
        int option;
        System.out.println("Please select the Business Type:");
        for (BusinessType businessTypeChoice : businessType) {
            System.out.println((++i) + " - " + businessTypeChoice.getBusinessType());
        }
        option = Integer.parseInt(sc.nextLine());
        switch (option) {
            case 1:
                for (BusinessType type : businessType) {
                    if (type.getBusinessType().compareTo("LEASE")==0) {
                        keepBusinessType = type;
                    }
                }
                break;
            case 2:
                for (BusinessType type : businessType) {
                    if (type.getBusinessType().compareTo("SALE")==0) {
                        keepBusinessType = type;
                    }
                }
                break;
        }
    }

    public void addConditionsOfBusiness() {
        if (keepBusinessType.getBusinessType().compareTo("SALE") == 0) {
            System.out.println("What is the sale price:\n");
            keepSalePrice = Double.parseDouble(sc.nextLine());
        } else if (keepBusinessType.getBusinessType().compareTo("LEASE") == 0) {
            System.out.println("What is the rent price:\n");
            keepRentPrice = Double.parseDouble(sc.nextLine());
            System.out.println("What is the durantion of the contract in months:\n");
            keepDurationContract = Integer.parseInt(sc.nextLine());
        }
    }

    public void listAndChooseAgent() {

        List<Employee> agentList = controller.getAgentList();

        int option;
        System.out.println("\nChoose Agent: ");
        for(int i=0;i< agentList.size();i++){
            System.out.println((i+1)+"-> "+agentList.get(i).getName()+" - "+agentList.get(i).getEmail().getEmail());
        }
        System.out.println("\nPlease select which agent is responsable:");
        option = Integer.parseInt(sc.nextLine());
        keepAgent=agentList.get(option-1);
    }

    public void getOwnerUserEmail() {
        keepOwnerEmail = controller.getOwnerEmail();
    }

    public void getOwnerPerson(String keepOwnerEmail) {
        keepOwner = (Person) controller.getOwnerPerson(keepOwnerEmail);
    }

    public void displayBusinessInformation() {
        System.out.println("\nBusiness Information\n");
        if (keepBusinessType.getBusinessType().compareTo("SALE") == 0) {
            System.out.printf("The Property is for sale for: %.2f\n", keepSalePrice);
        }
        if (keepBusinessType.getBusinessType().compareTo("LEASE") == 0) {
            System.out.printf("The Property is for Lease for %.2f a month,\n Duration of contract: %3d months\n", keepRentPrice, keepDurationContract);
        }
        System.out.printf("The Agent Responsable is: %s - %s\n", keepAgent.getName(), keepAgent.getEmail());
    }

    public void createNewRequest() {
        if (keepBusinessType.getBusinessType().compareTo("SALE") == 0) {
            createNewRequestSale(newAllProperty, keepOwner, keepBusinessType, keepAgent, keepSalePrice);
        }
        if (keepBusinessType.getBusinessType().compareTo("LEASE") == 0) {
            createNewRequestRent(newAllProperty, keepOwner, keepBusinessType, keepAgent, keepRentPrice, keepDurationContract);
        }
    }

    private void createNewRequestSale(Property newAllProperty, Person keepOwner, BusinessType keepBusinessType, Employee keepAgent, double keepSalePrice) {
        controller.createNewRequestSale(newAllProperty, keepBusinessType, keepOwner, keepAgent, keepSalePrice);
    }

    private void createNewRequestRent(Property newAllProperty, Person keepOwner, BusinessType keepBusinessType, Employee keepAgent, double keepRentPrice, int keepDurationContract) {
        controller.createNewRequestRent(newAllProperty, keepOwner, keepBusinessType, keepAgent, keepRentPrice, keepDurationContract);
    }

    public void displayOperationSucess() {
        System.out.println("The Operation is concluded with sucess");
    }

    public void run() {

        do {
            listAndChoosePropertyType();
            getDataProperty();
            displayPropertyInformation();
        } while (!getValidationForConditions());
        createNewAllProperty();
        do {
            listAndChooseBusinessType();
            addConditionsOfBusiness();
            listAndChooseAgent();
            displayBusinessInformation();
        } while (!getValidationForConditions());
        getOwnerUserEmail();
        getOwnerPerson(keepOwnerEmail);
        createNewRequest();
        displayOperationSucess();
    }
}
