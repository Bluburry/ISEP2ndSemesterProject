package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.PersonRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

public class RegisterUserController {

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
     * Creates an instance of SubmitPropertyController
     * where gives the to repositories an instance of Repositories
     */
    public RegisterUserController(){
        repositories=Repositories.getInstance();
        authenticationRepository= repositories.getAuthenticationRepository();
        personRepository=repositories.getPersonRepository();
    }

    /**
     * Register User with Type of Role of the Person to Register, Name of the Person to Register,
     * Email of the Person to Register, Password of the Person to Register, Person's Citizen Number,
     * Person's  phone Number, Person's Financial identification (tin), Street were is the Property,
     * ZipCode of the location of the Property, State in the US were is the Property,
     * City were is the Property, District were is the Property,
     * option PersonWithLocation or PersonWithoutLocation
     * @param roles Type of Role of the Person to Register
     * @param keepName Name of the Person to Register
     * @param keepEmail Email of the Person to Register
     * @param keepPassword Password of the Person to Register
     * @param keepCitizenNumber Person's Citizen Number
     * @param keepPhoneNumber Person's  phone Number
     * @param keepTin Person's Financial identification (tin)
     * @param keepStreet Street were is the Property
     * @param keepZipCode ZipCode of the location of the Property
     * @param keepStateUS State in the US were is the Property
     * @param keepCity City were is the Property
     * @param keepDistrict District were is the Property
     * @param option option PersonWithLocation or PersonWithoutLocation
     * @return registerUser
     */
    public boolean registerUserWithClientAndOwnerPerms(String[] roles, String keepName, String keepEmail, String keepPassword, int keepCitizenNumber, int keepPhoneNumber, int keepTin, String keepStreet, String keepZipCode, String keepStateUS, String keepCity, String keepDistrict,boolean option){

        boolean result=authenticationRepository.addUserWithRoles(keepName,keepEmail,keepPassword,roles);
        if (result){
            if(option){
                personRepository.addPersonWithLocation(keepEmail,keepName,keepCitizenNumber,keepTin,keepPhoneNumber,keepStreet,keepZipCode,keepStateUS,keepCity,keepDistrict);
            }else {
                personRepository.addPerson(keepEmail, keepName, keepCitizenNumber, keepTin, keepPhoneNumber);
            }
        }else return false;

        return true;
    }

}
