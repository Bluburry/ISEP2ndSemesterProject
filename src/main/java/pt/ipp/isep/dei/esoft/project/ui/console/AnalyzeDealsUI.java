package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AnalyzeDealsController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AnalyzeDealsUI implements Runnable{
    private final AnalyzeDealsController controller;
    private String keepAlgorithmType;
    private List<RequestSale> houseList = new ArrayList<>();
    private List<RequestSale> apartmentList = new ArrayList<>();
    private double[][] parameters;
    private List<Double> dependentVariable = new ArrayList<>();
    private List<Double> independentVariable = new ArrayList<>();
    private int option;
    Scanner sc = new Scanner(System.in);

    @Override
    public void run() {
        if(checkRequest()){
            getLinearRegressionAlgorithm();
            getAnalyze();
        }

    }

    public AnalyzeDealsUI(){
        controller = new AnalyzeDealsController();
    }

    private boolean checkRequest(){
        if(controller.getApartmentList().isEmpty()  && controller.getHouseList().isEmpty()){
            System.out.println("There is no data to do the regression.");
            return false;
        }
        return true;
    }

    private void simpleLinearAnalyzes(){

        while(option!=-1) {
            System.out.println("Simple Linear Regression Algorithm:\n");
            System.out.println("Which variable you want to use?\n");
            System.out.print("1. Property Area\n");
            System.out.print("2. Distance from the center\n");
            System.out.print("3. Number of bedrooms\n");
            System.out.print("4. Number of bathrooms\n");
            System.out.print("5. Number of partking spaces\n");
            System.out.print("Type your option:\n");

            try {
                option = Integer.parseInt(sc.nextLine());
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Invalid input. Please enter a valid option (an integer).");
                continue;
            }

            switch (option) {
                case 1:

                    houseList.addAll(controller.getHouseList());
                    for (RequestSale house : houseList) {
                        dependentVariable.add(house.getSalePrice());
                        independentVariable.add(house.getProperty().getArea());
                    }

                    apartmentList.addAll(controller.getApartmentList());
                    for (RequestSale apartment : apartmentList) {
                        dependentVariable.add(apartment.getSalePrice());
                        independentVariable.add(apartment.getProperty().getArea());
                    }

                    option = -1;
                    break;
                case 2:

                    houseList.addAll(controller.getHouseList());
                    for (RequestSale house : houseList) {
                        dependentVariable.add(house.getSalePrice());
                        independentVariable.add(house.getProperty().getDistance());
                    }
                    apartmentList.addAll(controller.getApartmentList());
                    for (RequestSale apartment : apartmentList) {
                        dependentVariable.add(apartment.getSalePrice());
                        independentVariable.add(apartment.getProperty().getDistance());
                    }

                    option = -1;
                    break;
                case 3:

                    houseList.addAll(controller.getHouseList());
                    for (RequestSale house : houseList) {
                        dependentVariable.add(house.getSalePrice());
                        independentVariable.add((double) ((House) house.getProperty()).getNumberBedrooms());
                    }

                    apartmentList.addAll(controller.getApartmentList());
                    for (RequestSale apartment : apartmentList) {
                        dependentVariable.add(apartment.getSalePrice());
                        independentVariable.add((double) ((Apartment) apartment.getProperty()).getNumberBedrooms());
                    }

                    option = -1;
                    break;
                case 4:

                    houseList.addAll(controller.getHouseList());
                    for (RequestSale house : houseList) {
                        dependentVariable.add(house.getSalePrice());
                        independentVariable.add((double) ((House) house.getProperty()).getNumberBathrooms());
                    }

                    apartmentList.addAll(controller.getApartmentList());
                    for (RequestSale apartment : apartmentList) {
                        dependentVariable.add(apartment.getSalePrice());
                        independentVariable.add((double) ((Apartment) apartment.getProperty()).getNumberBathrooms());
                    }

                    option = -1;
                    break;
                case 5:

                    houseList.addAll(controller.getHouseList());
                    for (RequestSale house : houseList) {
                        dependentVariable.add(house.getSalePrice());
                        independentVariable.add((double) ((House) house.getProperty()).getNumberParking());
                    }

                    apartmentList.addAll(controller.getApartmentList());
                    for (RequestSale apartment : apartmentList) {
                        dependentVariable.add(apartment.getSalePrice());
                        independentVariable.add((double) ((Apartment) apartment.getProperty()).getNumberParking());
                    }

                    option = -1;
                    break;
            }
        }
        option=0;
        keepAlgorithmType="simple";
    }

    private void multiLinearAnalyzes(){

        System.out.println("Multi Linear Regression Algorithm:\n");

        houseList.addAll(controller.getHouseList());
        for (RequestSale house : houseList) {
            dependentVariable.add(house.getSalePrice());
            independentVariable.add(house.getProperty().getArea());
            independentVariable.add(house.getProperty().getDistance());
            independentVariable.add((double) ((House) house.getProperty()).getNumberBedrooms());
            independentVariable.add((double) ((House) house.getProperty()).getNumberBathrooms());
            independentVariable.add((double) ((House) house.getProperty()).getNumberParking());
        }
        apartmentList.addAll(controller.getApartmentList());
        for (RequestSale apartment : apartmentList) {
            dependentVariable.add(apartment.getSalePrice());
            independentVariable.add(apartment.getProperty().getArea());
            independentVariable.add(apartment.getProperty().getDistance());
            independentVariable.add((double) ((Apartment) apartment.getProperty()).getNumberBedrooms());
            independentVariable.add((double) ((Apartment) apartment.getProperty()).getNumberBathrooms());
            independentVariable.add((double) ((Apartment) apartment.getProperty()).getNumberParking());
        }

        keepAlgorithmType="multi";
    }

    private void getLinearRegressionAlgorithm(){
        while(option!=-1){
            System.out.println("Linear Regression Algorithms:\n");
            System.out.print("1. Simple Linear\n");
            System.out.print("2. Multi Linear\n");
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
                    simpleLinearAnalyzes();
                    option=-1;
                    break;
                case 2:
                    multiLinearAnalyzes();
                    option=-1;
                    break;
            }
        }
        option=0;
    }

    private void getAnalyze(){
        if(keepAlgorithmType=="simple"){
            parameters = new double[houseList.size()][2];
            for(int i=0;i<houseList.size();i++){
                parameters[i][0] = independentVariable.get(i);
                parameters[i][1] = dependentVariable.get(i);
            }
            controller.setSimpleParameters(parameters);
            System.out.println(controller.getSimpleAnalyzes());
        }else if(keepAlgorithmType=="multi"){
            parameters = new double[houseList.size()][6];
            for(int i=0;i<houseList.size();i++){
                parameters[i][0] = dependentVariable.get(i);
                parameters[i][1] = independentVariable.get(i);
                parameters[i][2] = independentVariable.get(i + houseList.size());
                parameters[i][3] = independentVariable.get(i + (houseList.size())*2);
                parameters[i][4] = independentVariable.get(i + (houseList.size())*3);
                parameters[i][5] = independentVariable.get(i + (houseList.size())*4);
            }
            controller.setMultiParameters(parameters);
            System.out.println(controller.getMultiAnalyzes());
        }else{
            System.out.println("No algorithm.");
        }
    }
}


