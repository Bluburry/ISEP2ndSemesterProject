package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ImportFileController;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Person;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.domain.Advertisement;

public class ImportFileUI implements Runnable{

    ImportFileController importFileController;
    private Scanner sc;
    private String fileLocation;
    public ImportFileUI() {
        importFileController = new ImportFileController();
        sc = new Scanner(System.in);
    }

    public void requestData(){
        System.out.println("File path:");
        fileLocation = sc.nextLine();
    }

    @Override
    public void run() {
        requestData();
        try{
            importFileController.importInformation(fileLocation);
        }catch(FileNotFoundException e){
            System.out.println("The file does not exist or can't be found");
        }
        displayOperationSucess();
    }
    public void displayOperationSucess() {
        System.out.println("\n\nThe File was imported to the System Successfully");
    }
}
