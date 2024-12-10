package pt.ipp.isep.dei.esoft.project.repository;

public class Repositories {

    private static final Repositories instance = new Repositories();
    PersonRepository personRepository = new PersonRepository();
    AuthenticationRepository authenticationRepository = new AuthenticationRepository();
    StoreRepository storeRepository = new StoreRepository();
    AdvertisementRepository advertisementRepository = new AdvertisementRepository();
    RequestRepository requestRepository = new RequestRepository();
    PropertyRepository propertyRepository = new PropertyRepository();


    private Repositories() {
    }

    public static Repositories getInstance() {
        return instance;
    }

    public PersonRepository getPersonRepository() {
        return personRepository;
    }

    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    public StoreRepository getStoreRepository() {
        return storeRepository;
    }

    public AdvertisementRepository getAdvertisementRepository() {
        return advertisementRepository;
    }

    public RequestRepository getRequestRepository() {
        return requestRepository;
    }

    public PropertyRepository getPropertyRepository() {
        return propertyRepository;
    }


}
