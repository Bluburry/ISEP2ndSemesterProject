package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListPropertiesController;
import pt.ipp.isep.dei.esoft.project.domain.Advertisement;
import pt.ipp.isep.dei.esoft.project.domain.BusinessType;
import pt.ipp.isep.dei.esoft.project.domain.PropertyType;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ListPropertiesUI implements Runnable{

    private final ListPropertiesController controller;

    private BusinessType keepBusinessType;
    private String stringBusinessType;
    private PropertyType keepPropertyType;
    private String stringPropertyType;
    private int keepNumberOfBedrooms = -1;
    private String keepSortMethod;
    private int option =0;
    private int stringInvalido=0;
    Scanner sc = new Scanner(System.in);

    public ListPropertiesUI(){
        controller=new ListPropertiesController();
    }
    public void displayListedProperties(){
        List<Advertisement> ads=controller.getListedProperties(keepSortMethod,keepBusinessType,keepPropertyType,keepNumberOfBedrooms);
        if(ads.isEmpty()){
            System.out.println("\nNo Advertisements to list...\n");
        }else{
            for (Advertisement ad:ads) {

                System.out.println("\nPublish Date:"+ad.getDate()+ "\nBusiness Type: "+ad.getRequest().getBusinessType().getBusinessType() +"\nValue: " + controller.getPrice(ad) + "\n"+ad.getRequest().getProperty().toString()+ "\nOwner: "+ad.getRequest().getOwner().getName() +" - "+ad.getRequest().getOwner().getEmail());
            }
        }
    }

    private void listAndChoseBusinessType() {
        BusinessType[] businessTypes=controller.getBusinessTypeList();
        for (BusinessType bus:businessTypes) {
            System.out.println("\n"+bus.getBusinessType());
        }
        System.out.println("\nWrite the Business Type you want.");
        stringBusinessType = sc.nextLine();

        for(BusinessType bt : businessTypes){
            if(stringBusinessType.equals(bt.getBusinessType())){
                keepBusinessType=bt;
                break;
            }else{
                stringInvalido=1;
            }
        }
        if(stringInvalido==1){
            System.out.println("Business type invalido.");
        }
    }

    private void listAndChosePropertyType() {
        PropertyType[] propertyTypes=controller.getPropertyTypeList();
        for (PropertyType bus:propertyTypes) {
            System.out.println("\n"+bus.getPropertyType());
        }
        System.out.println("\nWrite the Property Type you want.");
        stringPropertyType = sc.nextLine();

        for(PropertyType pt : propertyTypes){
            if(stringPropertyType.equals(pt.getPropertyType())){
                keepPropertyType=pt;
                stringInvalido=0;
                break;
            }else{
                stringInvalido=1;
            }
        }
        if(stringInvalido==1){
            System.out.println("Property type invalido.");
        }

    }

    public void getKeepNumberOfBedrooms() {
        System.out.println("\n Please input the number of bedrooms: \n");
        keepNumberOfBedrooms = Integer.parseInt(sc.nextLine());
    }

    private void listAndChoseSortMethod() {
        do{
            System.out.println("\n");
            System.out.print("Do you want to sort the list?\n");
            System.out.print("1. Yes\n");
            System.out.println("2. No\n");
            System.out.print("Type your option:\n");

            try {
                option = Integer.parseInt(sc.nextLine());
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Invalid input. Please enter a valid option (an integer).");
                option = -1;
            }

            System.out.print("\n");
        }while(option == -1);


        if(option == 1){
            while(option!=-1){
                System.out.println("Sorting Methods:\n");
                System.out.print("1. Price\n");
                System.out.print("2. City\n");
                System.out.print("3. State\n");
                System.out.println("4. None\n");
                System.out.print("Type your option:\n");

                try {
                    option = Integer.parseInt(sc.nextLine());
                } catch (InputMismatchException e) {
                    sc.nextLine();
                    System.out.println("Invalid input. Please enter a valid option (an integer).");
                    continue;
                }

                switch (option){
                    case 1:
                        keepSortMethod="Price";
                        if(stringBusinessType==null){
                            listAndChoseBusinessType();
                        }
                        option=-1;
                        break;
                    case 2:
                        keepSortMethod="City";
                        option=-1;
                        break;
                    case 3:
                        keepSortMethod="State";
                        option=-1;
                        break;
                    case 4:
                        keepSortMethod="Default";
                        option=-1;
                        break;
                }
            }
        }
    }

    private void getAsksFilters(){
        do{
            System.out.println("\n");
            System.out.println("Display Listed Properties:\n");
            System.out.print("Do you want to use filters?\n");
            System.out.print("1. Yes\n");
            System.out.println("2. No\n");
            System.out.print("Type your option:\n");

            try {
                option = Integer.parseInt(sc.nextLine());
            } catch (InputMismatchException e) {
                sc.next();
                System.out.println("Invalid input. Please enter a valid option (an integer).");
                option = -1;
            }

            System.out.print("\n");
        }while(option==-1);

        if(option == 1){
            do {
                System.out.println("Filters:\n");
                System.out.print("1. Business type\n");
                System.out.print("2. Number of bedrooms\n");
                System.out.print("3. Property Type\n");
                System.out.println("4. None\n");
                System.out.print("Type your option:\n");

                try {
                    option = Integer.parseInt(sc.nextLine());
                } catch (InputMismatchException e) {
                    sc.next();
                    System.out.println("Invalid input. Please enter a valid option (an integer).");
                    continue;
                }

                switch (option) {
                    case 1:
                        listAndChoseBusinessType();
                        break;
                    case 2:
                        getKeepNumberOfBedrooms();
                        break;
                    case 3:
                        listAndChosePropertyType();
                        break;
                }
            } while (option != 4);
        }
    }



    public void run() {

        getAsksFilters();

        listAndChoseSortMethod();

        displayListedProperties();
    }


}
