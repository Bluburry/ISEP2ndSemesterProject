package pt.ipp.isep.dei.esoft.project.ui.console;

import java.util.Scanner;
import java.util.ArrayList;

import pt.ipp.isep.dei.esoft.project.application.controller.ListPropertyRequestController;
import pt.ipp.isep.dei.esoft.project.domain.*;

public class ListPropertyRequestUI implements Runnable {
	private final ListPropertyRequestController controller;

	private Request request;
	private ArrayList<Request> requestList;
	private CommissionType commissionTypes[];
	private CommissionType commissionType;
	private double commissionValue;
	// private Advertisement advertisement;

	Scanner read = new Scanner(System.in);

	private void displayRequestListAndGetRequest() {
		requestList = controller.getRequestList();
	}

	private void handleUserChoice() {
		int i = 0, choice;
		System.out.println("Please choose a request by inputting the number shown before the request.");
		for (Request r : requestList) {
			i++;
			System.out.printf("\nRequest number %d:\n", i);
			System.out.println(r.toString());
			System.out.println();
		}
		System.out.println("Please input your choice");
		while (true) {
			try {
				choice = Integer.parseInt(read.nextLine());
			} catch (Exception e) {
				System.out.println("Invalid choice, please try again:");
				read.next();
				continue;
			}
			choice--;
			if (choice < 0 || choice > i) {
				System.out.println("Invalid choice, please try again:");
				read.next();
				continue;
			}
			break;
		}
		request = requestList.get(choice);
	}

	private void handleRequest() {
		int i = 0, choice;
		System.out.println("Do you wish to accept or reject request?\n1 - Accept\t2-Reject\t0 - Cancel and return");
		while (true) {
			try {
				choice = Integer.parseInt(read.nextLine());
			} catch (Exception e) {
				System.out.println("Invalid choice, please try again:");
				read.next();
				continue;
			}
			if (choice != 1 && choice != 2 && choice != 0) {
				System.out.println("Invalid choice, please try again:");
				read.next();
				continue;
			}
			break;
		}
		if (choice == 0)
			return;
		else if (choice == 2)
			controller.removeRequest(request);
		else if (choice == 1) {
			choice = -1;
			System.out.println("Please input the commission value:\n");
			while (true) {
				try {
					commissionValue = Double.parseDouble(read.nextLine());
				} catch (Exception e) {
					System.out.println("Invalid value, please try again:");
					continue;
				}
				break;
			}
			commissionTypes = controller.getCommissionType();
			System.out.println("Please choose a commission type by inputting the number shown before them.");
			for (CommissionType c : commissionTypes) {
				i++;
				System.out.printf("Request number %d:\n", i);
				System.out.println(c.getCommissionType());
			}
			System.out.println("Please input your choice.");
			while (true) {
				try {
					choice = Integer.parseInt(read.nextLine());
				} catch (Exception e) {
					System.out.println("Invalid choice, please try again:");
					continue;
				}
				if (choice < 0 || choice > i) {
					System.out.println("Invalid choice, please try again:");
					continue;
				}
				break;
			}
			commissionType = commissionTypes[choice - 1];
			try {
				controller.approveRequest(request, commissionType, commissionValue);
			} catch (IllegalArgumentException e) {
				System.out.println(
						"Error, request wasn't rejected, nor was the advertisement created.\nError message:\n");
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void run() {
		boolean wantsContinue = true;
		int choice;
		while (wantsContinue) {
			displayRequestListAndGetRequest();
			handleUserChoice();
			handleRequest();
			System.out.println("Do you wish to continue?\nType 1 for yes, any other option will return.");
			while (true) {
				try {
					choice = Integer.parseInt(read.nextLine());
				} catch (Exception e) {
					System.out.println("Invalid choice, please try again:");
					read.next();
					continue;
				}
				if (choice != 1)
					wantsContinue = false;
				break;
			}
		}
	}

	public ListPropertyRequestUI() {
		controller = new ListPropertyRequestController();
	}

}
