package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.List;
// import java.io.IOException;
// import java.util.Objects;

// import javafx.application.Application;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.gui.ui.LogInApplicationFX;

@SuppressWarnings("unused")
public class Bootstrap implements Runnable {

        Stage primaryStage;

        // Add some task categories to the repository as bootstrap
        public void run() {
                // Serialization.loadAll();
                // LogInApplicationFX.main();
                Config.getInstance();
                loadSerialization();

                // addStores();
                // addProperties();
                // addRequests();
                // addAdvertisements();
                addUsers();

        }

        private void addUsers() {
                // TODO: add Authentication users here: should be created for each user in the
                // organization
                AuthenticationRepository authenticationRepository = Repositories.getInstance()
                                .getAuthenticationRepository();
                PersonRepository personRepository = Repositories.getInstance().getPersonRepository();

                authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN,
                                AuthenticationController.ROLE_ADMIN);
                authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT,
                                AuthenticationController.ROLE_AGENT);
                authenticationRepository.addUserRole(AuthenticationController.ROLE_STORE_MANAGER,
                                AuthenticationController.ROLE_STORE_MANAGER);
                authenticationRepository.addUserRole(AuthenticationController.ROLE_STORE_NETWORK_MANAGER,
                                AuthenticationController.ROLE_STORE_NETWORK_MANAGER);
                authenticationRepository.addUserRole(AuthenticationController.ROLE_CLIENT,
                                AuthenticationController.ROLE_CLIENT);
                authenticationRepository.addUserRole(AuthenticationController.ROLE_OWNER,
                                AuthenticationController.ROLE_OWNER);

                authenticationRepository.addUserWithRole("Main Administrator", "admin@this.app", "admin",
                                AuthenticationController.ROLE_ADMIN);
                authenticationRepository.addUserWithRole("Owner", "owner@this.app", "owner",
                                AuthenticationController.ROLE_OWNER);
                authenticationRepository.addUserWithRole("Alberto", "Fonseca@owner.pt", "client",
                                AuthenticationController.ROLE_OWNER);
                authenticationRepository.addUserWithRole("Agent", "agent@this.app", "agent",
                                AuthenticationController.ROLE_AGENT);
                authenticationRepository.addUserWithRole("Humberto", "AgenteHumber@emprese.pt", "humber",
                                AuthenticationController.ROLE_AGENT);
                authenticationRepository.addUserWithRole("Joao", "AgenteJoao@emprese.pt", "joao",
                                AuthenticationController.ROLE_AGENT);
                authenticationRepository.addUserWithRole("Client", "client@this.app", "client",
                                AuthenticationController.ROLE_CLIENT);
                authenticationRepository.addUserWithRole("joaquim", "joaquim@this.app", "client",
                                AuthenticationController.ROLE_CLIENT);
                authenticationRepository.addUserWithRole("Network Manager", "manager@this.app", "manager",
                                AuthenticationController.ROLE_STORE_NETWORK_MANAGER);
                authenticationRepository.addUserWithRole("Store Manager", "StoreMang@this.app", "storemang",
                                AuthenticationController.ROLE_STORE_MANAGER);
        }

        private void addStores() {
                StoreRepository storeRepository = Repositories.getInstance().getStoreRepository();

                Store store1 = new Store("Store1", "Store1@gmail.com", 968593194, "rua", "12345", "State", "City",
                                "District");
                Store store2 = new Store("Store2", "Store2@gmail.com", 968593114, "rua2", "12343", "State2", "City2",
                                "District3");
                Store store3 = new Store("Store3", "Store3@gmail.com", 968593124, "rua22", "12342", "State1", "City3",
                                "District2");

                storeRepository.addNewStore(store1);
                storeRepository.addNewStore(store2);
                storeRepository.addNewStore(store3);
        }

        private void addProperties() {

                PropertyRepository propertyRepository = Repositories.getInstance().getPropertyRepository();
                List<String> photos = new ArrayList<>();

                photos.add("abc.jpg");
                photos.add("teste.jpg");
                photos.add("def.jpg");
                // SunExposure n;
                // n=sunExposureRepository.getSunExposureTypeList().get(0);

                propertyRepository.createNewHouseProperty(PropertyType.HOUSE, 320, 15, photos, 2, 3, 3, "no equip",
                                true, true,
                                SunExposure.NORTH, "rua", "12345", "State", "City", "District");
                propertyRepository.createNewApartmentProperty(PropertyType.APARTMENT, 320, 15, photos, 2, 3, 2,
                                "no equip",
                                "Rua", "12345", "State", "City", "Districty");
                propertyRepository.createNewProperty(PropertyType.LAND, 320, 15, photos, "Rua", "12345", "State",
                                "City",
                                "Districty");
        }

        private void addAdvertisements() {
                PropertyRepository propertyRepository = Repositories.getInstance().getPropertyRepository();
                // RequestRepository requestRepository =
                // Repositories.getInstance().getRequestRepository();
                PersonRepository personRepository = Repositories.getInstance().getPersonRepository();
                AuthenticationRepository authenticationRepository = Repositories.getInstance()
                                .getAuthenticationRepository();
                AdvertisementRepository advetisementRepository = Repositories.getInstance()
                                .getAdvertisementRepository();
                List<String> photos = new ArrayList<>();

                photos.add("abc.jpg");
                photos.add("teste.jpg");
                photos.add("def.jpg");

                Property property1 = propertyRepository.createNewHouseProperty(PropertyType.HOUSE, 320, 15, photos, 2,
                                3, 3,
                                "no equip", true, true, SunExposure.EAST, "rua", "12345", "etatS", "City", "District");
                Property property2 = propertyRepository.createNewApartmentProperty(PropertyType.APARTMENT, 320, 15,
                                photos, 2,
                                3, 2, "no equip", "Rua", "12345", "etatS2", "City", "Districty");
                Property property3 = propertyRepository.createNewProperty(PropertyType.LAND, 320, 15, photos, "Rua",
                                "12345",
                                "etatS2", "City", "Districty");
                Property property4 = propertyRepository.createNewProperty(PropertyType.LAND, 5000, 150, photos, "Rua",
                                "12345",
                                "etatS6", "City", "Districty");
                Property property5 = propertyRepository.createNewApartmentProperty(PropertyType.APARTMENT, 220, 10,
                                photos, 2,
                                3, 2, "Frige", "Rua", "12345", "State", "City123", "Districty");
                Property property6 = propertyRepository.createNewHouseProperty(PropertyType.HOUSE, 320, 15, photos, 2,
                                3, 3,
                                "Frige, Micro, Air Condition", false, true, SunExposure.SOUTH, "rua", "12345",
                                "State12332",
                                "City123",
                                "District6574");

                authenticationRepository.addUserWithRole("Fonseca", "Fonseca@owner.pt", "client",
                                AuthenticationController.ROLE_OWNER);
                authenticationRepository.addUserWithRole("Fonseca", "Agent1@emprese.pt", "agent",
                                AuthenticationController.ROLE_AGENT);

                Person owner1 = personRepository.getPersonByEmail("Fonseca@owner.pt");

                Person person1 = personRepository.getPersonByEmail("humberto@owner.pt");

                Employee employee1 = (Employee) personRepository.getPersonByEmail("AgenteJoao@emprese.pt"); // personRepository.createEmployee("AgenteJoao@emprese.pt",
                                                                                                            // "joao",
                                                                                                            // 123456789,
                                                                                                            // 789456123,
                                                                                                            // 2041852963,
                                                                                                            // AuthenticationController.ROLE_AGENT);

                RequestLease req1 = new RequestLease(property1, BusinessType.LEASE, owner1, employee1, 150, 15);
                RequestSale req2 = new RequestSale(property2, BusinessType.SALE, owner1, employee1, 15000);
                RequestSale req3 = new RequestSale(property3, BusinessType.SALE, owner1, employee1, 150000);
                RequestLease req4 = new RequestLease(property3, BusinessType.LEASE, owner1, employee1, 155, 24);
                RequestSale req5 = new RequestSale(property6, BusinessType.SALE, owner1, employee1, 150000);
                RequestLease req6 = new RequestLease(property5, BusinessType.LEASE, owner1, employee1, 155, 48);
                RequestSale req7 = new RequestSale(property4, BusinessType.SALE, owner1, employee1, 15000);

                StoreRepository storeRepository = Repositories.getInstance().getStoreRepository();

                List<Store> store = storeRepository.getStoresList();

                Advertisement a = advetisementRepository.createNewAdvertisement(req1, CommissionType.PERCENTAGE, 120,
                                store.get(0));
                Advertisement b = advetisementRepository.createNewAdvertisement(req2, CommissionType.PERCENTAGE, 12000,
                                store.get(0));
                Advertisement c = advetisementRepository.createNewAdvertisement(req3, CommissionType.FIXED, 100000,
                                store.get(0));
                advetisementRepository.createNewAdvertisement(req4, CommissionType.PERCENTAGE, 150, store.get(1));
                advetisementRepository.createNewAdvertisement(req5, CommissionType.FIXED, 100000, store.get(0));
                advetisementRepository.createNewAdvertisement(req6, CommissionType.PERCENTAGE, 150, store.get(1));
                advetisementRepository.createNewAdvertisement(req7, CommissionType.PERCENTAGE, 150, store.get(2));

                a.addVisit(2024, 3, 25, 10, "humberto@owner.pt");
                a.addVisit(2024, 4, 25, 10, "asd@owner.pt");
                a.addVisit(2024, 5, 25, 10, "abc@owner.pt");
                a.addVisit(2024, 6, 25, 10, "wasd@owner.pt");
                a.addVisit(2024, 7, 25, 10, "dsa@owner.pt");

                b.addVisit(2024, 3, 25, 10, "humberto@owner.pt");
                b.addVisit(2024, 3, 25, 11, "asd@owner.pt");
                b.addVisit(2024, 4, 25, 10, "abc@owner.pt");
                b.addVisit(2024, 5, 25, 10, "wasd@owner.pt");
                b.addVisit(2024, 6, 25, 10, "dsa@owner.pt");

                c.addVisit(2024, 1, 10, 10, "humberto@owner.pt");
                c.addVisit(2024, 1, 25, 10, "asd@owner.pt");
                c.addVisit(2024, 2, 1, 10, "abc@owner.pt");
                c.addVisit(2024, 5, 25, 10, "wasd@owner.pt");
                c.addVisit(2024, 6, 25, 10, "dsa@owner.pt");

                advetisementRepository.introduceVisitToAdList(a, 2023, 10, 25, 13, "client1@this.app");
                advetisementRepository.introduceVisitToAdList(b, 2023, 11, 26, 13, "client3@this.app");
                advetisementRepository.introduceVisitToAdList(c, 2023, 10, 25, 13, "client2@this.app");
                advetisementRepository.introduceVisitToAdList(a, 2023, 10, 22, 18, "client1@this.app");
                advetisementRepository.introduceVisitToAdList(a, 2023, 11, 25, 16, "client2@this.app");
                advetisementRepository.introduceVisitToAdList(b, 2023, 9, 25, 13, "client2@this.app");
                advetisementRepository.introduceVisitToAdList(c, 2023, 7, 2, 14, "client4@this.app");
                advetisementRepository.introduceVisitToAdList(a, 2023, 12, 25, 17, "client1@this.app");
                advetisementRepository.introduceVisitToAdList(b, 2023, 12, 25, 17, "client2@this.app");
                advetisementRepository.introduceVisitToAdList(c, 2023, 12, 25, 17, "client3@this.app");

                /*
                 * a.addOffer(OfferStatus.PENDING, 125, "client@this.app");
                 * b.addOffer(OfferStatus.PENDING, 12500, "client@this.app");
                 * a.addOffer(OfferStatus.PENDING, 123, "joaquim@this.app");
                 * b.addOffer(OfferStatus.PENDING, 13000, "joaquim@this.app");
                 */

        }

        private void addRequests() {
                PropertyRepository propertyRepository = Repositories.getInstance().getPropertyRepository();
                RequestRepository requestRepository = Repositories.getInstance().getRequestRepository();
                PersonRepository personRepository = Repositories.getInstance().getPersonRepository();
                AuthenticationRepository authenticationRepository = Repositories.getInstance()
                                .getAuthenticationRepository();
                List<String> photos = new ArrayList<>();

                photos.add("abc.jpg");
                photos.add("teste.jpg");
                photos.add("def.jpg");

                Property property11 = propertyRepository.createNewHouseProperty(PropertyType.HOUSE, 320, 15, photos, 2,
                                3, 3, "no equip", true, true, SunExposure.EAST, "rua123", "12345", "State123",
                                "City123",
                                "District123");
                Property property21 = propertyRepository.createNewApartmentProperty(PropertyType.APARTMENT, 320, 15,
                                photos, 2, 3, 2, "no equip", "Rua", "12345", "State2", "City2", "District2");
                Property property31 = propertyRepository.createNewProperty(PropertyType.LAND, 320, 15, photos, "Rua",
                                "12345", "State3", "City3", "District3");
                Property property41 = propertyRepository.createNewProperty(PropertyType.LAND, 5000, 150, photos, "Rua",
                                "12345", "State4", "City5", "District5");
                Property property51 = propertyRepository.createNewApartmentProperty(PropertyType.APARTMENT, 220, 10,
                                photos, 2, 3, 2, "Frige", "Rua", "12345", "State6", "City6", "District6");
                Property property61 = propertyRepository.createNewHouseProperty(PropertyType.HOUSE, 320, 15, photos, 2,
                                3, 3, "Frige, Micro, Air Condition", false, true, SunExposure.SOUTH, "rua", "12345",
                                "State7",
                                "City7", "District7");

                Person owner1 = personRepository.getPersonByEmail("Fonseca@owner.pt");

                Employee employee1 = (Employee) personRepository.getPersonByEmail("AgenteJoao@emprese.pt");

                requestRepository.createNewRequestRent(property11, BusinessType.LEASE, owner1, employee1, 150, 15);
                requestRepository.createNewRequestSale(property21, BusinessType.SALE, owner1, employee1, 15000);
                requestRepository.createNewRequestSale(property31, BusinessType.SALE, owner1, employee1, 150000);
                requestRepository.createNewRequestRent(property31, BusinessType.LEASE, owner1, employee1, 155, 24);
                requestRepository.createNewRequestSale(property61, BusinessType.SALE, owner1, employee1, 150000);
                requestRepository.createNewRequestRent(property51, BusinessType.LEASE, owner1, employee1, 155, 48);
                requestRepository.createNewRequestSale(property41, BusinessType.SALE, owner1, employee1, 15000);
        }

        private void loadSerialization() {
                AdvertisementRepository advertisementRepository = Repositories.getInstance()
                                .getAdvertisementRepository();
                PersonRepository personRepository = Repositories.getInstance().getPersonRepository();
                PropertyRepository propertyRepository = Repositories.getInstance().getPropertyRepository();
                RequestRepository requestRepository = Repositories.getInstance().getRequestRepository();
                StoreRepository storeRepository = Repositories.getInstance().getStoreRepository();
                advertisementRepository.loadAdvertisementRepository();
                personRepository.loadPersonRepository();
                propertyRepository.loadPropertyRepository();
                requestRepository.loadRequestRepository();
                storeRepository.loadStoreRepository();
        }
}
