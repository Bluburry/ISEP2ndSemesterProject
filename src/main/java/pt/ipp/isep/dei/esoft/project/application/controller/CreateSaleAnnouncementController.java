package pt.ipp.isep.dei.esoft.project.application.controller;

import java.util.List;

import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.application.session.UserSession;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.exceptions.PersonNotFoundException;

public class CreateSaleAnnouncementController {

	/**
	 * Access to the repositores
	 */
	private Repositories repositories;

	/**
	 * Access to the application session
	 */
	private ApplicationSession applicationSession;
	/**
	 * Access to the user session
	 */
	private UserSession userSession;

	/**
	 * Access to the person repository
	 */
	private PersonRepository personRepository;
	/**
	 * Access to the property repository
	 */
	private PropertyRepository propertyRepository;
	/**
	 * Access to the request repository
	 */
	private RequestRepository requestRepository;

	private StoreRepository storeRepository;
	/**
	 * Access to the advertisement repository
	 */
	private AdvertisementRepository advertisementRepository;

	/**
	 * initializes this class and its basic necessities
	 */
	public CreateSaleAnnouncementController() {
		repositories = Repositories.getInstance();
		personRepository = repositories.getPersonRepository();
		propertyRepository = repositories.getPropertyRepository();
		requestRepository = repositories.getRequestRepository();
		advertisementRepository = repositories.getAdvertisementRepository();
		storeRepository = repositories.getStoreRepository();
		applicationSession = ApplicationSession.getInstance();
		userSession = applicationSession.getCurrentSession();
	}

	/**
	 * Searches for the owner in the person repository and returns them
	 * If they're not found then a new Person is created with their information
	 * 
	 * @param name        Person Name
	 * @param email       Person Email
	 * @param citizenID   Person's Citizen Number
	 * @param tin         Person's Financial identification (tin)
	 * @param phoneNumber Person's phone Number
	 * @return Person
	 */
	public Person getOwner(String email) throws PersonNotFoundException {
		Person personToReturn;
		personToReturn = personRepository.getPersonByEmail(email);
		if (personToReturn == null)
			throw new PersonNotFoundException("There was no match for that e-mail in the system.");
		return personToReturn;
	}

	/**
	 * gets and returns the list of business types
	 * 
	 * @return List<BusinessType>
	 */

	public BusinessType[] getBusinessTypeList() {
		return BusinessType.values();
	}

	/**
	 * gets and returns the list of property types
	 * 
	 * @return List<PropertyType>
	 */

	public PropertyType[] getPropertyTypeList() {
		return PropertyType.values();
	}

	/**
	 * Get Sun Exposure Type List
	 * 
	 * @return sunExposureTypeList
	 */
	public SunExposure[] getSunExposureTypeList() {
		return SunExposure.values();
	}

	/**
	 * gets and returns the list of commission types
	 * 
	 * @return List<CommissionType>
	 */
	public CommissionType[] getCommissionTypeList() {
		return CommissionType.values();
	}

	/**
	 * Find the employee who is currently logged in
	 * Uses the app session and user session to get their e-mail
	 * Which is then used to search through the person repository
	 * 
	 * @return agent
	 */
	public Employee getAgentFromSession() {
		return (Employee) personRepository.getPersonByEmail(userSession.getUserEmail());
	}

	/**
	 * Creates instance with Property Owner, Commission Type, Commission Value,
	 * State in the US were is the property, City were is the property,
	 * District were is the property, Street were is the property ,
	 * Zip of the location of the property, Area of the Land in square meters,
	 * Distance from the property to the City in Kilometers, Sale price of the Sale,
	 * Rent price of the Lease, Duration Of Contract of the Lease ,Property Type ,
	 * Property photos , Number of Bedrooms of the property,
	 * Number of Parking Spaces in a property, Number of Bathrooms in the property,
	 * Available Equipment (Frige, Air Conditioning, others...) in a property,
	 * Shows if there is a basement in the House, Shows if the House is Habitable,
	 * Direction of Sun Exposure, Business Type (Lease or Sale)
	 * Creates an instance of advertisement
	 * Due the requirement of this class it also creates a new property and request
	 * 
	 * @param owner              Property Owner
	 * @param commissionType     Commission Type
	 * @param commissionValue    Commission Value
	 * @param state              State in the US were is the property
	 * @param city               City were is the property
	 * @param district           District were is the property
	 * @param street             Street were is the property
	 * @param zip                Zip of the location of the property
	 * @param area               Area of the Land in square meters
	 * @param distance           Distance from the property to the City in
	 *                           Kilometers
	 * @param salePrice          Sale price of the Sale
	 * @param rentPrice          Rent price of the Lease
	 * @param durationOfContract Duration Of Contract of the Lease
	 * @param propertyType       Property Type
	 * @param photograph         Property photos
	 * @param numBedrooms        Number of Bedrooms of the property
	 * @param numparkingSpaces   Number of Parking Spaces in a property
	 * @param numBathrooms       Number of Bathrooms in the property
	 * @param availableEquipment Available Equipment (Frige, Air Conditioning,
	 *                           others...) in a property
	 * @param basement           Shows if there is a basement in the House
	 * @param loft               Shows if the House is Habitable
	 * @param sunExposure        Direction of Sun Exposure
	 * @param businessType       Business Type (Lease or Sale)
	 * @return advertisement
	 */
	public Advertisement createAdvertisment(Person owner, CommissionType commissionType, double commissionValue,
			String state, String city, String district, String street, String zip, double area, double distance,
			double salePrice, double rentPrice, int durationOfContract, PropertyType propertyType,
			List<String> photograph, int numBedrooms, int numparkingSpaces, int numBathrooms, String availableEquipment,
			boolean basement, boolean loft, SunExposure sunExposure, BusinessType businessType) {
		Advertisement advertisement = null;
		Employee employee = getAgentFromSession();
		Property property;
		Request request;
		String propertyID;

		if (propertyType.getPropertyType().equals("LAND")) {
			property = propertyRepository.createNewProperty(propertyType, area, distance, photograph, street, zip,
					state, city, district);
		} else if (propertyType.getPropertyType().equals("APARTMENT")) {
			property = propertyRepository.createNewApartmentProperty(propertyType, area, distance, photograph,
					numBedrooms, numparkingSpaces, numBathrooms, availableEquipment, street, zip, state, city,
					district);
		} else {
			property = propertyRepository.createNewHouseProperty(propertyType, area, distance, photograph, numBedrooms,
					numparkingSpaces, numBathrooms, availableEquipment, basement, loft, sunExposure, street, zip, state,
					city, district);
		}

		if (businessType.getBusinessType().equals("SALE")) {
			request = requestRepository.createSaleRequest(property, businessType, owner, employee, salePrice);
		} else {
			request = requestRepository.createRentRequest(property, businessType, owner, employee, rentPrice,
					durationOfContract);
		}

		Store store = storeRepository.getStoreWithEmployee(request.getAgent());

		advertisement = advertisementRepository.createNewAdvertisement(request, commissionType, commissionValue, store);
		propertyID = propertyRepository.getPropertyID(advertisement.getRequest().getProperty());
		SMS.notifyPropertyListing(employee.getName(), employee.getPhoneNumber(), owner.getPhoneNumber(), propertyID,
				advertisement.getDate());

		return advertisement;
	}

}
