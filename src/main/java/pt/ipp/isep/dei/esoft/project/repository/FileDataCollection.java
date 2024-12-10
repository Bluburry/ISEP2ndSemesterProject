package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.PersonRepository;
import pt.ipp.isep.dei.esoft.project.repository.PropertyRepository;
import pt.ipp.isep.dei.esoft.project.repository.StoreRepository;
import pt.ipp.isep.dei.esoft.project.repository.RequestRepository;
import pt.ipp.isep.dei.esoft.project.repository.AdvertisementRepository;

//import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
public class FileDataCollection {

    /**
     * Access to the class Repositories
     */
    private Repositories repositories;

    /**
     * Access to the class PersonRepository
     */
    private PersonRepository personRepository;

    /**
     * Access to the class StoreRepository
     */
    private StoreRepository storeRepository;

    /**
     * Access to the class PropertyRepository
     */
    private PropertyRepository propertyRepository;

    /**
     * Access to the class RequestRepository
     */
    private RequestRepository requestRepository;

    /**
     * Access to the class AdvertisementRepository
     */
    private AdvertisementRepository advertisementRepository;

    public FileDataCollection(){
        repositories = Repositories.getInstance();
        personRepository = repositories.getPersonRepository();
        storeRepository = repositories.getStoreRepository();
        propertyRepository = repositories.getPropertyRepository();
        requestRepository = repositories.getRequestRepository();
        advertisementRepository = repositories.getAdvertisementRepository();
    }

    public static List<String> addDataToList(String value, int index, List<Integer> givenChecklist, List<String> givenList){
        boolean locationFound = false;
        int listIndex = 0;
        while(!locationFound && listIndex < givenChecklist.size()) {
            if(index == givenChecklist.get(listIndex)) {
                givenList.add(value);
                locationFound = true;
            }
            listIndex++;
        }
        return givenList;
    }

    public Request createRequest(List<String> ownerData, List<String> propertyData, List<String> requestData, Employee agent){
        Person person = personRepository.createPerson(ownerData.get(3), ownerData.get(0), Double.parseDouble(ownerData.get(2).replace("-", "")), Double.parseDouble(ownerData.get(1)), Double.parseDouble(ownerData.get(4).replace("-", "")));
        Property property;
        Request request;
        switch (propertyData.get(0)){
            case "house":
                String availableEquipment = propertyData.get(7) + ";" + propertyData.get(8);
                property = propertyRepository.createNewHouseProperty(PropertyType.HOUSE, Double.parseDouble(propertyData.get(1)), Double.parseDouble(propertyData.get(3)), Integer.parseInt(propertyData.get(4)), Integer.parseInt(propertyData.get(6)), Integer.parseInt(propertyData.get(5)), availableEquipment, propertyData.get(9), propertyData.get(10), propertyData.get(11), propertyData.get(2));
                break;
            case "apartment":
                availableEquipment = propertyData.get(7) + ";" + propertyData.get(8);
                property = propertyRepository.createNewApartmentProperty(PropertyType.APARTMENT, Double.parseDouble(propertyData.get(1)), Double.parseDouble(propertyData.get(3)), Integer.parseInt(propertyData.get(4)), Integer.parseInt(propertyData.get(6)), Integer.parseInt(propertyData.get(5)), availableEquipment, propertyData.get(2));
                break;
            case "land":
                property = propertyRepository.createNewProperty(PropertyType.LAND, Double.parseDouble(propertyData.get(1)), Double.parseDouble(propertyData.get(3)), propertyData.get(2));
                break;
            default:
                property = propertyRepository.createNewProperty(PropertyType.APARTMENT, Double.parseDouble(propertyData.get(1)), Double.parseDouble(propertyData.get(3)), propertyData.get(2));
        }
        request = requestType(property, person, agent, propertyData, requestData);
        return request;
    }

    public Request requestType(Property property, Person person, Employee agent, List<String> propertyData, List<String> requestData){
        Request request;
        switch (requestData.get(0)){
            case "sale":
                request = requestRepository.createSaleRequest(property, BusinessType.SALE, person, agent, Double.parseDouble(propertyData.get(13)));
                break;
            case "lease":
                request = requestRepository.createRentRequest(property, BusinessType.LEASE, person, agent, Double.parseDouble(propertyData.get(13)), Integer.parseInt(requestData.get(1)));
                break;
            default:
                request = requestRepository.createSaleRequest(property, BusinessType.SALE, person, agent, Double.parseDouble(propertyData.get(13)));
        }
        return request;
    }


    public boolean importInformation(String fileLocation) throws FileNotFoundException{

        Scanner fileReader = new Scanner(new File(fileLocation));
        List<Integer> ownerIndex = new ArrayList<>();
        List<Integer> propertyIndex = new ArrayList<>();
        List<Integer> storeIndex = new ArrayList<>();
        List<Integer> requestIndex = new ArrayList<>();
        List<String> ownerData = new ArrayList<>();
        List<String> propertyData = new ArrayList<>();
        List<String> storeData = new ArrayList<>();
        List<String> requestData = new ArrayList<>();

        Employee agent = personRepository.createEmployee("legacy@realstateUSA.com", "Legacy Agent", 110000001, 1100000001, 1100000000, "AGENT");

        int count = 0;
        int line = 0;
        for (String column : fileReader.nextLine().split(";")) {
            if (column.split("_")[0].equals("owner")){
                ownerIndex.add(count);
            }else if (column.split("_")[0].equals("property")){
                propertyIndex.add(count);
            }else if (column.split("_")[0].equals("store")){
                storeIndex.add(count);
            }else if (column.equals("commission(%)") || column.split("_")[0].equals("contract") || column.split("_")[0].equals("type")){
                requestIndex.add(count);
            }
            count++;
        }
        while(fileReader.hasNextLine()) {
            count = 0;
            line++;
            for (String column : fileReader.nextLine().split(";")) {
                ownerData = addDataToList(column, count, ownerIndex, ownerData);
                propertyData = addDataToList(column, count, propertyIndex, propertyData);
                storeData = addDataToList(column, count, storeIndex, storeData);
                requestData = addDataToList(column, count, requestIndex, requestData);
                count++;
            }

            try{
                Store store = storeRepository.addStore(storeData.get(1), storeData.get(2), storeData.get(3), storeData.get(4));
                advertisementRepository.createNewAdvertisement(createRequest(ownerData, propertyData, requestData, agent), CommissionType.PERCENTAGE, Double.parseDouble(requestData.get(0)), store);
            }catch(IllegalArgumentException e){
                System.out.println("Data is invalid in line " + line);
            }
            ownerData = new ArrayList<>();
            propertyData = new ArrayList<>();
            storeData = new ArrayList<>();
            requestData = new ArrayList<>();
        }
        fileReader.close();
        return true;
    }
}
