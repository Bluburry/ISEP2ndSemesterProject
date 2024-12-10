package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.DivideSetOfStoresController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import java.util.List;

public class DivideSetOfStoresUI implements Runnable {

	private DivideSetOfStoresController controller;
	public DivideSetOfStoresUI() {
		controller = new DivideSetOfStoresController();
	}
	public void run() {
		Pair<List<Pair<String,Integer>>,List<Pair<String,Integer>>> div=controller.setOfStoresDivided();
		double time=controller.getTime();
		int diff=controller.getDiff();
		if (diff==Integer.MAX_VALUE){
			diff=0;
		}
		System.out.print("\n\nFIRST \n[");
		for (Pair<String,Integer> p:div.getFirst()) {
			System.out.print("["+p.getFirst()+","+p.getSecond()+"]");
		}
		System.out.print("]\n\nSECOND \n[");
		for (Pair<String,Integer> p:div.getSecond()) {
			System.out.print("["+p.getFirst()+","+p.getSecond()+"]");
		}
		System.out.print("]");
		System.out.println("\n\nDifference between the two sublits is "+diff+"\n");
		System.out.println("The Algorithm took "+time+" milliseconds!");
	}


}
