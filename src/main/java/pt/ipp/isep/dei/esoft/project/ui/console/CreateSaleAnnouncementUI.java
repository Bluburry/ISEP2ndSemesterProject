package pt.ipp.isep.dei.esoft.project.ui.console;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateSaleAnnouncementController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.exceptions.PersonNotFoundException;

public class CreateSaleAnnouncementUI implements Runnable {
	private final CreateSaleAnnouncementController controller;

	private boolean basement;
	private boolean loft;
	private int numBedrooms;
	private int numParkingSpaces;
	private int numBathrooms;
	private int durationOfContract;
	private double area;
	private double distance;
	private double salePrice;
	private double rentalPrice;
	private double commissionValue;
	private String email;
	private String state;
	private String city;
	private String district;
	private String street;
	private String zip;
	private String availableEquipment;
	private Person person;
	private List<String> photograph = new ArrayList<>();
	private PropertyType propertyType;
	private PropertyType[] propertyTypes;
	private BusinessType businessType;
	private BusinessType[] businessTypes;
	private SunExposure sunExposure;
	private SunExposure[] sunExposures;
	private CommissionType commissionType;
	private CommissionType[] commissionTypes;
	private Advertisement advertisement;

	Scanner read = new Scanner(System.in);

	private void getOwnerInfo() throws PersonNotFoundException {
		System.out.println("Please input the owner's e-mail");
		email = read.nextLine();
		person = controller.getOwner(email);
	}

	public void getPropertyInfo() {
		int selection = 0;

		System.out.println("Please input the property information as prompted");
		while (true) {
			System.out.println("Area in square meters: ");
			try {
				area = Double.parseDouble(read.nextLine());
			} catch (Exception e) {
				System.out.println("Please enter a valid area.");
				read.next();
				continue;
			}
			break;
		}

		while (true) {
			System.out.println("Distance from city center: ");
			try {
				distance = Double.parseDouble(read.nextLine());
			} catch (Exception e) {
				System.out.println("Please enter a valid area.");
				read.next();
				continue;
			}
			break;
		}

		System.out.println("Regarding the location:");
		System.out.println("\t- state: ");
		state = read.nextLine();
		System.out.println("\t- city: ");
		city = read.nextLine();
		System.out.println("\t- district: ");
		district = read.nextLine();
		System.out.println("\t- street: ");
		street = read.nextLine();
		System.out.println("\t- zip code: ");
		zip = read.nextLine();

		System.out.println("Please select the business type for the property:");
		businessTypes = controller.getBusinessTypeList();
		while (true) {
			int i = 0;
			for (BusinessType type : businessTypes) {
				System.out.printf("-> %d. %s\n", ++i, type.toString());
			}
			System.out.println("Your selection: ");
			try {
				selection = Integer.parseInt(read.nextLine());
			} catch (Exception e) {
				System.out.println("Invalid selection, please try again.");
				read.next();
				continue;
			}
			if (selection < 1 || selection > businessTypes.length) {
				System.out.println("Invalid selection, please try again.");
				continue;
			} else {
				businessType = businessTypes[selection - 1];
				break;
			}
		}

		if (businessType.getBusinessType().equals("SALE")) {
			while (true) {
				System.out.println("Price: ");
				try {
					salePrice = Double.parseDouble(read.nextLine());
				} catch (Exception e) {
					System.out.println("Please enter a valid area.");
					read.next();
					continue;
				}
				break;
			}
		} else {
			while (true) {
				System.out.println("Price: ");
				try {
					rentalPrice = Double.parseDouble(read.nextLine());
				} catch (Exception e) {
					System.out.println("Please enter a valid area.");
					read.next();
					continue;
				}
				break;
			}
			while (true) {
				System.out.println("Contract duration: ");
				try {
					durationOfContract = Integer.parseInt(read.nextLine());
				} catch (Exception e) {
					System.out.println("Please enter a valid area.");
					read.next();
					continue;
				}
				break;
			}
		}

		System.out.println("Please select the property type:");
		propertyTypes = controller.getPropertyTypeList();
		while (true) {
			int i = 0;
			for (PropertyType type : propertyTypes) {
				System.out.printf("-> %d. %s\n", ++i, type.toString());
			}
			System.out.println("Your selection: ");
			try {
				selection = Integer.parseInt(read.nextLine());
			} catch (Exception e) {
				System.out.println("Invalid selection, please try again.");
				read.next();
				continue;
			}
			if (selection < 1 || selection > propertyTypes.length) {
				System.out.println("Invalid selection, please try again.");
				continue;
			} else {
				propertyType = propertyTypes[selection - 1];
				break;
			}
		}

		if (!propertyType.getPropertyType().equals("LAND")) {
			while (true) {
				System.out.println("Number of bedrooms: ");
				try {
					numBedrooms = Integer.parseInt(read.nextLine());
				} catch (Exception e) {
					System.out.println("Please enter a valid number.");
					read.next();
					continue;
				}
				if (numBedrooms < 0) {
					System.out.println("Please enter a valid number.");
					read.next();
					continue;
				}
				break;
			}

			while (true) {
				System.out.println("Number of parking spaces: ");
				try {
					numParkingSpaces = Integer.parseInt(read.nextLine());
				} catch (Exception e) {
					System.out.println("Please enter a valid number.");
					read.next();
					continue;
				}
				if (numParkingSpaces < 0) {
					System.out.println("Please enter a valid number.");
					read.next();
					continue;
				}
				break;
			}

			while (true) {
				System.out.println(
						"The property information asked in this next section is optional, do you wish to skip this part?");
				System.out.printf("1 - Yes\b2 - No");
				try {
					selection = Integer.parseInt(read.nextLine());
				} catch (Exception e) {
					System.out.println("Please enter a valid selection.");
					read.next();
					continue;
				}
				break;
			}

			if (selection == 2) {
				while (true) {
					System.out.println("Number of bathrooms: ");
					try {
						numBathrooms = Integer.parseInt(read.nextLine());
					} catch (Exception e) {
						System.out.println("Please enter a valid selection.");
						read.next();
						continue;
					}
					break;
				}
				System.out.println("Please write what equipment can be found in the property (ex.: fridge, AC, etc.).");
				availableEquipment = read.nextLine();

				if (propertyType.getPropertyType().equals("HOUSE")) {
					while (true) {
						System.out.printf("Is there a basement?\n1 - Yes\n2 - No\n");
						try {
							selection = Integer.parseInt(read.nextLine());
						} catch (Exception e) {
							System.out.println("Please enter a valid number.");
							read.next();
							continue;
						}
						if (selection != 1 && selection != 2) {
							System.out.println("Please enter a valid number.");
							continue;
						} else if (selection == 1)
							basement = true;
						else
							basement = false;
						break;
					}

					while (true) {
						System.out.printf("Is there an inhabitable loft?\n1 - Yes\n2 - No\n");
						try {
							selection = Integer.parseInt(read.nextLine());
						} catch (Exception e) {
							System.out.println("Please enter a valid number.");
							read.next();
							continue;
						}
						if (selection != 1 && selection != 2) {
							System.out.println("Please enter a valid number.");
							continue;
						} else if (selection == 1)
							loft = true;
						else
							loft = false;
						break;
					}

					System.out.println("Please select where the property is exposed to the sun:");
					sunExposures = controller.getSunExposureTypeList();
					while (true) {
						int i = 0;
						for (SunExposure type : sunExposures) {
							System.out.printf("-> %d. %s\n", ++i, type.toString());
						}
						System.out.println("Your selection: ");
						try {
							selection = Integer.parseInt(read.nextLine());
						} catch (Exception e) {
							System.out.println("Invalid selection, please try again.");
							read.next();
							continue;
						}
						if (selection < 1 || selection > sunExposures.length) {
							System.out.println("Invalid selection, please try again.");
							continue;
						} else {
							sunExposure = sunExposures[selection - 1];
							break;
						}
					}
				}
			} else {
				numBathrooms = 0;
				availableEquipment = "none";
				basement = false;
				loft = false;
				sunExposure = SunExposure.NORTH;
			}
		}

		selection = 1;
		System.out.println("Please input at least of photograph of the property.");
		while (selection == 1) {
			photograph.add(read.nextLine());
			System.out.printf("Do you wish to submit any more photos?\n1 - Yes\n2 - No\n");
			try {
				selection = Integer.parseInt(read.nextLine());
				if (selection != 1) {
					selection = 2;
				}
			} catch (Exception e) {
				System.out.println("Invalid option, advancing.");
				selection = 2;
			}
		}

	}

	public void createAdvertisment() {
		int selection = 0;

		commissionTypes = controller.getCommissionTypeList();
		System.out.println("Please select the commission type:");
		while (true) {
			int i = 0;
			for (CommissionType type : commissionTypes) {
				System.out.printf("-> %d. %s\n", ++i, type.toString());
			}
			System.out.println("Your selection: ");
			try {
				selection = read.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid selection, please try again.");
				read.next();
				continue;
			}
			if (selection < 1 || selection > commissionTypes.length)
				System.out.println("Invalid selection, please try again.");
			else {
				commissionType = commissionTypes[selection - 1];
				break;
			}
		}

		while (true) {
			System.out.println("Commission value: ");
			try {
				commissionValue = read.nextDouble();
			} catch (Exception e) {
				System.out.println("Invalid input, please try again");
				read.next();
				continue;
			}
			break;
		}

		advertisement = controller.createAdvertisment((Person) person, commissionType, commissionValue, state, city,
				district,
				street, zip, area, distance, salePrice, rentalPrice, durationOfContract, propertyType, photograph,
				numBedrooms, numParkingSpaces, numBathrooms, availableEquipment, basement, loft, sunExposure,
				businessType);
	}

	public void run() {
		int choice = 0;
		boolean repeat = true;
		while (repeat) {
			try {
				getOwnerInfo();
				getPropertyInfo();
				createAdvertisment();
				System.out.printf("Here is the created advertisement\n%s\n", advertisement.toString());
				System.out.printf("Do you wish to create another advertisement or quit?\n1 - New\t2- Quit\n");
			} catch (PersonNotFoundException e) {
				System.out.printf("%s\nDo you wish to try again?\n1 - Yes\t2 - No\n", e.getMessage());
			}
			while (true) {
				try {
					choice = Integer.parseInt(read.nextLine());
				} catch (Exception e) {
					System.out.println("Invalid choice, please try again:");
					read.next();
					continue;
				}
				if (choice != 2 && choice != 1) {
					System.out.println("Invalid choice, please try again:");
					continue;
				}
				break;
			}
			if (choice != 1)
				repeat = false;
		}
	}

	public CreateSaleAnnouncementUI() {
		controller = new CreateSaleAnnouncementController();
	}
}
