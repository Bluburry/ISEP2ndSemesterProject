package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.PersonRepository;
import pt.ipp.isep.dei.esoft.project.repository.StoreRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Store;

import java.util.List;

public class RegisterEmployeeController {
    /**
     * Access to the class Repositories
     */
    private Repositories repositories;

    /**
     * Access to the class AuthenticationRepository
     */
    private AuthenticationRepository authenticationRepository;

    /**
     * Access to the class PersonRepository
     */
    private PersonRepository personRepository;

    /**
     * Access to the class StoreRepository
     */
    private StoreRepository storeRepository;

    /**
     * Creates an instance of SubmitPropertyController
     * where gives the to repositories an instance of Repositories
     */
    public RegisterEmployeeController(){
        repositories = Repositories.getInstance();
        authenticationRepository = repositories.getAuthenticationRepository();
        personRepository = repositories.getPersonRepository();
        storeRepository = repositories.getStoreRepository();
    }

    /**
     * Create New Employee with Person's name, User Password, Person's Citizen Number
     * @param name Person's name
     * @param password User Password
     * @param citizenNum Person's Citizen Number
     * @param tin Person's Financial identification (tin)
     * @param email Person's Email
     * @param phoneNum Person's  phone Number
     * @param storeID Store Identification
     * @param street Street were is the property
     * @param zipCode Zip of the location of the property
     * @param stateUS State in the US were is the property
     * @param city City were is the property
     * @param district District were is the property
     * @param userRole Person Role in the Company
     * @return createEmployee
     */
    public boolean createEmployee(String name, String password, int citizenNum, int tin, String email, int phoneNum, String storeID, String street, String zipCode, String stateUS, String city, String district, String userRole){
        if(authenticationRepository.existsUser(email)){
            authenticationRepository.addUserWithRole(name, email, password, userRole);
            Employee employee = personRepository.addEmployeeWithLocation(email, name, citizenNum, tin, phoneNum, street, zipCode, stateUS, city, district, userRole);
            storeRepository.addEmployeeToStore(employee, storeID);
            return true;
        }
        return false;
    }

    /**
     * Create New Employee with Person's name, User Password, Person's Citizen Number
     * @param name Person's name
     * @param password User Password
     * @param citizenNum Person's Citizen Number
     * @param tin Person's Financial identification (tin)
     * @param email Person's Email
     * @param phoneNum Person's  phone Number
     * @param street Street were is the property
     * @param zipCode Zip of the location of the property
     * @param stateUS State in the US were is the property
     * @param city City were is the property
     * @param district District were is the property
     * @param userRole Person Role in the Company
     * @return createEmployee     */
    public boolean createEmployee(String name, String password, int citizenNum, int tin, String email, int phoneNum, List<String> storeIDs, String street, String zipCode, String stateUS, String city, String district, String userRole){
        if(authenticationRepository.existsUser(email)){
            authenticationRepository.addUserWithRole(name, email, password, userRole);
            Employee employee = personRepository.addEmployeeWithLocation(email, name, citizenNum, tin, phoneNum, street, zipCode, stateUS, city, district, userRole);
            storeRepository.addEmployeeToStores(employee, storeIDs);
            return true;
        }
        return false;
    }

    /**
     * Get Store List
     * @return storeList
     */
    public List<Store> getStoreList(){
        return storeRepository.getStoresList();
    }
}
