package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListDealsController;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.DealsApplication;

import java.util.Scanner;
public class ListDealsUI implements Runnable{
    ListDealsController listDealsController;
    private Scanner sc;
    private int algorithm;
    private int order;
    public ListDealsUI() {
        listDealsController = new ListDealsController();
        sc = new Scanner(System.in);
    }

    /*public void requestData(){
        System.out.println("----------Sorting algorithm----------\n1 - Merge Sort\n2 - Bubble Sort");
        algorithm = sc.nextInt();
        System.out.println("--------Sorting order (by m^2)--------\n1 - Ascending\n2 - Descending ");
        order = sc.nextInt();
    }*/

    @Override
    public void run() {
        DealsApplication.main();
        /*requestData();
        listDealsController.listDeals(algorithm, order);*/
    }
}
