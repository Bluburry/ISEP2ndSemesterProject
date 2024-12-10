package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterEmployeeController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pt.ipp.isep.dei.esoft.project.domain.Person;
import pt.ipp.isep.dei.esoft.project.domain.Store;

public class RegisterEmployeeUI implements Runnable{

    RegisterEmployeeController registerEmployeeController;
    AuthenticationController authController;
    private Scanner sc;
    private String name;
    private String password;
    private int citizenNum;
    private int tin;
    private String email;
    private int phoneNum;
    private String street;
    private String zipCode;
    private String stateUS;
    private String city;
    private String district;
    private String storeID;
    private String userRole;

    public RegisterEmployeeUI() {
        authController = new AuthenticationController();
        registerEmployeeController = new RegisterEmployeeController();
        sc = new Scanner(System.in);
    }

    public void requestData(){
        System.out.println("\nName: ");
        name = sc.nextLine();
        System.out.println("\nE-mail: ");
        email = sc.nextLine();
        System.out.println("\nPassword: ");
        password = sc.nextLine();
        System.out.println("\nPassport Number: ");
        citizenNum = Integer.parseInt(sc.nextLine());
        System.out.println("\nTax Number Identification: ");
        tin = Integer.parseInt(sc.nextLine());
        System.out.println("\nPhone Number: ");
        phoneNum = Integer.parseInt(sc.nextLine());
    }

    public void requestLocation(){
        System.out.println("\nStreet Name: ");
        street = sc.nextLine();
        System.out.println("\nZip-Code: ");
        zipCode = sc.nextLine();
        System.out.println("\nState: ");
        stateUS = sc.nextLine();
        System.out.println("\nCity: ");
        city = sc.nextLine();
        System.out.println("\nDistrict: ");
        district = sc.nextLine();
    }

    public void getUserRoleList(){
        List<String> userRoles = authController.getEmployeeRoles();
        for (int i = 0; i < userRoles.size(); i++) {
            System.out.println((i+1) + " - " + userRoles.get(i));
        }
        userRole = userRoles.get(sc.nextInt()-1);
    }

    public void getStoreList(){
        int i = 0;
        List<Store> storeList = registerEmployeeController.getStoreList();
        for(Store store : storeList){
            System.out.println(++i + " - " + store.getName());
        }
        storeID = storeList.get(sc.nextInt()-1).getIdStore();
    }

    public List<String> getChosenStores(){
        int i = 0;
        List<Store> storeList = registerEmployeeController.getStoreList();
        List<String> storeIDs = new ArrayList<>();
        for(Store store : storeList){
            System.out.println(++i + " - " + store.getName());
        }
        System.out.println("Type '0' to stop");
        int option = sc.nextInt();

        while(option > 0){
            storeIDs.add(storeList.get(option).getIdStore());
            option = sc.nextInt();
        }
        return storeIDs;
    }

    public void displayData(){

    }

    public void displayData(List<String> Stores){

    }

    @Override
    public void run() {
        System.out.println("\nRegister Employee");
        requestData();
        System.out.println("\nEmployee's address");
        requestLocation();
        System.out.println("\nEmployee's role");
        getUserRoleList();
        System.out.println("\nEmployee's store");
        if(userRole == AuthenticationController.ROLE_STORE_NETWORK_MANAGER){
            System.out.println("Choose the network manager's stores");
            List<String> storeIDs = getChosenStores();
            registerEmployeeController.createEmployee(name, password, citizenNum, tin, email, phoneNum, storeIDs, street, zipCode, stateUS, city, district, userRole);

        }else{
            getStoreList();
            registerEmployeeController.createEmployee(name, password, citizenNum, tin, email, phoneNum, storeID, street, zipCode, stateUS, city, district, userRole);
        }
    }
}
