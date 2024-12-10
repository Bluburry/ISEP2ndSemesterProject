package pt.ipp.isep.dei.esoft.project.ui.console;


import pt.ipp.isep.dei.esoft.project.application.controller.RegisterUserController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;

import java.util.Scanner;


public class RegisterUserUI implements  Runnable{

    RegisterUserController controller;

    AuthenticationController authController;

    private String keepEmail;
    private String keepName;
    private String keepPassword;
    private int keepCitizenNumber;
    private int keepTin;

    private int keepPhoneNumber;

    private Scanner sc;

    private boolean option_location;

    private String keepStreet;
    private String keepZipCode;
    private String keepStateUS;
    private String keepCity;
    private String keepDistrict;

    public RegisterUserUI() {
        authController=new AuthenticationController();
        controller=new RegisterUserController();
        sc=new Scanner(System.in);
    }

    public void askForLocation(){
        System.out.println("\nStreet Name\n");
        keepStreet=sc.nextLine();
        System.out.println("\nZip-Code\n");
        keepZipCode=sc.nextLine();
        System.out.println("\nState\n");
        keepStateUS=sc.nextLine();
        System.out.println("\nCity\n");
        keepCity=sc.nextLine();
        System.out.println("\nDistrict\n");
        keepDistrict=sc.nextLine();
    }

    public void askForData(){
        System.out.println("\nName\n");
        keepName=sc.nextLine();
        System.out.println("\nE-mail\n");
        keepEmail=sc.nextLine();
        System.out.println("\nPassword\n");
        keepPassword=sc.nextLine();
        System.out.println("\nTax Number Identification\n");
        keepTin=Integer.parseInt(sc.nextLine());
        System.out.println("\nCitizen Number\n");
        keepCitizenNumber=Integer.parseInt(sc.nextLine());
        System.out.println("\nPhone Number\n");
        keepPhoneNumber=Integer.parseInt(sc.nextLine());
    }

    public String[] getClientAndOwnerRoles(){
        String[] roles=new String[2];
        int pos=0;
        roles[0]=AuthenticationController.ROLE_OWNER;
        roles[1]=AuthenticationController.ROLE_CLIENT;
        return roles;
    }

    public void run() {
        System.out.println("\nRegister User\n");
        askForData();
        System.out.println("\nWant to add location?(yes/no)\n");
        if(sc.nextLine().compareTo("yes")==0){
            option_location=true;
            askForLocation();
        }else option_location=false;
        controller.registerUserWithClientAndOwnerPerms(getClientAndOwnerRoles(),keepName,keepEmail,keepPassword,keepCitizenNumber,keepPhoneNumber,keepTin,keepStreet,keepZipCode,keepStateUS,keepCity,keepDistrict,option_location);

    }
}

