package pt.ipp.isep.dei.esoft.project.application.controller;


import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.application.session.UserSession;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.List;

public class SubmitPropertyController {

    /**
     * Access to the class Repositories
     */
    private Repositories repositories;

    /**
     * Access to the class PropertyTypeRepository
     */
    private PropertyRepository propertyRepository;

    /**
     * Access to the class PersonRepository
     */
    private PersonRepository personRepository;

    /**
     * Access to the class UserSession
     */
    private static UserSession userSession;

    /**
     * Access to the class RequestRepository
     */
    private RequestRepository requestRepository;

    /**
     * Creates an instance of SubmitPropertyController
     * where gives the to repositories an instance of Repositories
     */
    public SubmitPropertyController() {
        repositories = Repositories.getInstance();
        propertyRepository = repositories.getPropertyRepository();
        personRepository = repositories.getPersonRepository();
        userSession = ApplicationSession.getInstance().getCurrentSession();
        requestRepository = repositories.getRequestRepository();
    }

    /**
     * Get Property Type List
     * @return propertyTypeList
     */
    public PropertyType[] getPropertyTypeList() {
        return PropertyType.values();
    }

    /**
     * Get Sun Exposure Type List
     * @return sunExposureTypeList
     */
    public SunExposure[] getSunExposureTypeList() {
        return SunExposure.values();
    }

    /**
     * Creates instance with Property Type Property, area of the property in square meters,
     * Distance from the Property to the City in Kilometers, Photos of the Property,
     * Number of Bedrooms in a Property, Number of Parking Spaces in a Property,
     * Number of Bathrooms in the Property, Available Equipment (Frige, Air Conditioning, others...)
     * in a Property, Shows if there is a basement in the Property, Shows if the Property is Habitable,
     * Direction of Sun Exposure, Street were is the Property, ZipCode of the location of the Property,
     * State in the US were is the Property, City were is the Property, District were is the Property
     * @param keepPropertyType Property Type House
     * @param keepAreaProperty area of the property in square meters
     * @param keepDistanceCityCenter Distance from the Property to the City in Kilometers
     * @param keepPhotographs Photos of the Property
     * @param keepNumberBedrooms Number of Bedrooms in a Property
     * @param keepNumberParking Number of Parking Spaces in a Property
     * @param keepNumberBathrooms Number of Bathrooms in the Property
     * @param keepAvailableEquipment Available Equipment (Frige, Air Conditioning, others...) in a Property
     * @param keepBasement Shows if there is a basement in the Property
     * @param keepLoft Shows if the Property is Habitable
     * @param keepSunExposure Direction of Sun Exposure
     * @param keepStreet Street were is the Property
     * @param keepZipCode ZipCode of the location of the Property
     * @param keepStateUS State in the US were is the Property
     * @param keepCity City were is the Property
     * @param keepDistrict District were is the Property
     * @return newHouseProperty
     */
    public Property createNewHouseProperty(PropertyType keepPropertyType, double keepAreaProperty, double keepDistanceCityCenter, List<String> keepPhotographs, int keepNumberBedrooms, int keepNumberParking, int keepNumberBathrooms, String keepAvailableEquipment, boolean keepBasement, boolean keepLoft, SunExposure keepSunExposure, String keepStreet, String keepZipCode, String keepStateUS, String keepCity, String keepDistrict) {
        return propertyRepository.createNewHouseProperty(keepPropertyType, keepAreaProperty, keepDistanceCityCenter, keepPhotographs, keepNumberBedrooms, keepNumberParking, keepNumberBathrooms, keepAvailableEquipment, keepBasement, keepLoft, keepSunExposure, keepStreet, keepZipCode, keepStateUS, keepCity, keepDistrict);
    }

    /**
     * Creates instance with Property Type Property, area of the property in square meters,
     * Distance from the Property to the City in Kilometers, Photos of the Property,
     * Number of Bedrooms in a Property, Number of Parking Spaces in a Property,
     * Number of Bathrooms in the Property, Available Equipment (Frige, Air Conditioning, others...)
     * in a Property, Street were is the Property, ZipCode of the location of the Property,
     * State in the US were is the Property, City were is the Property, District were is the Property
     * @param keepPropertyType Property Type Property
     * @param keepAreaProperty area of the property in square meters
     * @param keepDistanceCityCenter Distance from the Property to the City in Kilometers
     * @param keepPhotographs Photos of the Property
     * @param keepNumberBedrooms Number of Bedrooms in a Property
     * @param keepNumberParking Number of Parking Spaces in a Property
     * @param keepNumberBathrooms Number of Bathrooms in the Property
     * @param keepAvailableEquipment Available Equipment (Frige, Air Conditioning, others...) in a Property
     * @param keepStreet Street were is the Property
     * @param keepZipCode ZipCode of the location of the Property
     * @param keepStateUS State in the US were is the Property
     * @param keepCity City were is the Property
     * @param keepDistrict District were is the Property
     * @return newApartmentProperty
     */
    public Property createNewApartmentProperty(PropertyType keepPropertyType, double keepAreaProperty, double keepDistanceCityCenter, List<String> keepPhotographs, int keepNumberBedrooms, int keepNumberParking, int keepNumberBathrooms, String keepAvailableEquipment, String keepStreet, String keepZipCode, String keepStateUS, String keepCity, String keepDistrict) {
        return propertyRepository.createNewApartmentProperty(keepPropertyType, keepAreaProperty, keepDistanceCityCenter, keepPhotographs, keepNumberBedrooms, keepNumberParking, keepNumberBathrooms, keepAvailableEquipment, keepStreet, keepZipCode, keepStateUS, keepCity, keepDistrict);
    }

    /**
     * Creates instance with Property Type Property, area of the property in square meters,
     * Distance from the Property to the City in Kilometers, Photos of the Property,
     * Street were is the Property, ZipCode of the location of the Property,
     * State in the US were is the Property, City were is the Property, District were is the Property
     * @param keepPropertyType Property Type Property
     * @param keepAreaProperty area of the property in square meters
     * @param keepDistanceCityCenter Distance from the Property to the City in Kilometers
     * @param keepPhotographs Photos of the Property
     * @param keepStreet Street were is the Property
     * @param keepZipCode ZipCode of the location of the Property
     * @param keepStateUS State in the US were is the Property
     * @param keepCity City were is the Property
     * @param keepDistrict District were is the Property
     * @return newProperty
     */
    public Property createNewProperty(PropertyType keepPropertyType, double keepAreaProperty, double keepDistanceCityCenter, List<String> keepPhotographs, String keepStreet, String keepZipCode, String keepStateUS, String keepCity, String keepDistrict) {
        return propertyRepository.createNewProperty(keepPropertyType, keepAreaProperty, keepDistanceCityCenter, keepPhotographs, keepStreet, keepZipCode, keepStateUS, keepCity, keepDistrict);
    }

    /**
     * Get Business Type List
     * @return businessTypeList
     */
    public BusinessType[] getBusinessTypeList() {
        return BusinessType.values();
    }

    /**
     * Get Agent List
     * @return agentList
     */
    public List<Employee> getAgentList() {
        System.out.println(personRepository.getPersons().size());
        List<Employee> agentList = personRepository.getAgentList();
        return agentList;
    }

    /**
     * Get Owner Email
     * @return ownerEmail
     */
    public static String getOwnerEmail() {
        return userSession.getUserEmail();
    }

    /**
     * Get Person List
     * @return personList
     */
    public List<Person> getPersons() {
        return personRepository.getPersons();
    }

    /**
     * Creates instance with New Property of the Request, Owner of Property of the Request,
     * Business Type of the Request, Agent of the Request, Sale Price of the Request
     * @param newAllProperty New Property of the Request
     * @param keepOwner Owner of Property of the Request
     * @param keepBusinessType Business Type of the Request
     * @param keepAgent Agent of the Request
     * @param keepSalePrice Sale Price of the Request
     */
    public void createNewRequestSale(Property newAllProperty, BusinessType keepBusinessType, Person keepOwner, Employee keepAgent, double keepSalePrice) {
        requestRepository.createNewRequestSale(newAllProperty, keepBusinessType, keepOwner, keepAgent, keepSalePrice);
    }

    /**
     * Creates instance with New Property of the Request, Owner of Property of the Request,
     * Business Type of the Request, Agent of the Request, Rent Price of the Lease in the Request,
     * Duraction Contract of the Lease in the Request
     * @param keepOwner Owner of Property of the Request
     * @param keepBusinessType Business Type of the Request
     * @param keepAgent Agent of the Request
     * @param keepRentPrice Rent Price of the Lease in the Request
     * @param keepDuractionContract Duraction Contract of the Lease in the Request
     */
    public void createNewRequestRent(Property newAllProperty, Person keepOwner, BusinessType keepBusinessType, Employee keepAgent, double keepRentPrice, int keepDuractionContract) {
        requestRepository.createNewRequestRent(newAllProperty, keepBusinessType, keepOwner, keepAgent, keepRentPrice, keepDuractionContract);
    }

    /**
     * Get NonEmployee Owner by Email Owner
     * @param keepOwnerEmail Owner Email
     * @return NonEmployee Owner
     */
    public Person getOwnerPerson(String keepOwnerEmail) {
        return personRepository.getPersonByEmail(keepOwnerEmail);
    }
}
