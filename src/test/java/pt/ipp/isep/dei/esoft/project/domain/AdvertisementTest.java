package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import pt.ipp.isep.dei.esoft.project.exceptions.AcceptedOfferNotFoundException;
import pt.ipp.isep.dei.esoft.project.repository.AdvertisementRepository;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("unused")
class AdvertisementTest {

        Person personTest1 = new Person("johndoe@gmail.com", "John Doe", 333444555, 444333555, 555444333);
        Person personTest2 = new Person("janedoe@gmail.com", "Jane Doe", 111222333, 222111333, 333222111);
        Employee employeeTest1 = new Employee("janesmith@gmail.com", "Jane Smith", 999000888, 888000999, 999888000,
                        "holderplace",
                        "67890", "Arkansas", "Little Rock", "onnud", "agent");
        Employee employeeTest2 = new Employee("johnsmith@gmail.com", "John Smith", 666777888, 777666888, 888777666,
                        "placeholder",
                        "12345", "Kansas", "Wichita", "dunno", "agent");

        List<String> photosTest = new ArrayList<String>(Arrays.asList("asd.jpg", "abc.jpg", "qwe.png", "def.png"));
        Property landTest = new Land(PropertyType.LAND, 123, 321, photosTest, "placeholder", "12345", "Kansas",
                        "Wichita",
                        "dunno");
        Property apartmentTest = new Apartment(PropertyType.APARTMENT, 456, 654, photosTest, "holderplace",
                        "67890", "Arkansas", "Little Rock", "onnud", 2, 3, 4, "fridge; AC; central heating;");
        Property houseTest = new House(PropertyType.HOUSE, 789, 987, photosTest, "plaholderce",
                        "13579", "Washington", "Small Stone", "something", 3, 5, 2,
                        "fridge; AC; central heating; garage; lawn; garden", true, false, SunExposure.NORTH);

        RequestLease testLease1 = new RequestLease(landTest, BusinessType.LEASE, personTest1, employeeTest1, 122.32,
                        24);
        RequestSale testSale1 = new RequestSale(landTest, BusinessType.SALE, personTest2, employeeTest2, 1000.59);
        RequestLease testLease2 = new RequestLease(apartmentTest, BusinessType.LEASE, personTest2, employeeTest1,
                        122.32,
                        24);
        RequestSale testSale2 = new RequestSale(apartmentTest, BusinessType.SALE, personTest1, employeeTest2, 1000.59);
        RequestLease testLease3 = new RequestLease(houseTest, BusinessType.LEASE, personTest2, employeeTest2, 122.32,
                        24);
        RequestSale testSale3 = new RequestSale(houseTest, BusinessType.SALE, personTest1, employeeTest1, 1000.59);

        Store storeTest = new Store("onnud", "tset@gmail.com", 1234567890, "placeholder", "12345", "Kansas", "Wichita",
                        "dunno");

        Advertisement advertisementTest = new Advertisement(testLease1, CommissionType.FIXED, 1230, storeTest);

        Offer testOffer = new Offer(1234, "janedoe@gmail.com");

        Visit testVisit = new Visit(2025, 11, 23, 11, "dummyEmail@gmail.com");

        @Test
        void ensureAdvertisementCreation() {
                assertNotNull(advertisementTest);
        }

        {
                advertisementTest.addVisit(2025, 11, 23, 11, "dummyEmail@gmail.com");
                advertisementTest.addOffer(1234, "janedoe@gmail.com");
        }

        @Test
        void testAdvertisementGets() {
                assertEquals(testLease1, advertisementTest.getRequest());
                assertEquals(CommissionType.FIXED, advertisementTest.getCommissionType());
                assertEquals(1230, advertisementTest.getCommissionValue());
                assertEquals(storeTest, advertisementTest.getStore());
                assertEquals(testVisit, advertisementTest.getVisits().get(0));
                assertEquals(testOffer, advertisementTest.getOffers().get(0));
        }

        @Test
        void testAdvertisementToString() {
                String confirm = String.format("Agent: %s - %s\nRequest made by: %s",
                                advertisementTest.getRequest().getAgent().getName(),
                                advertisementTest.getRequest().getAgent().getEmail().getEmail(),
                                advertisementTest.getRequest().toString());
                assertEquals(confirm, advertisementTest.toString());
        }

        @Test
        void testAdvertisementEquals() {
                Advertisement test = new Advertisement(testLease1, CommissionType.FIXED, 1230, storeTest);
                assertTrue(advertisementTest.equals(test));
        }

        @Test
        void testAdvertisementIllegalArguments() {
                assertThrows(IllegalArgumentException.class, () -> {
                        new Advertisement(null, CommissionType.FIXED, 1230, storeTest);
                });
                assertThrows(IllegalArgumentException.class, () -> {
                        new Advertisement(testLease1, null, 1230, storeTest);
                });
                assertThrows(IllegalArgumentException.class, () -> {
                        new Advertisement(testLease1, CommissionType.FIXED, -123, storeTest);
                });
                assertThrows(IllegalArgumentException.class, () -> {
                        new Advertisement(testLease1, CommissionType.FIXED, 1230, null);
                });
        }

        @Test
        void testAdvertisementChangeVisitStatus() {
                advertisementTest.changeStatus(testVisit, "REJECTED");
                assertEquals("REJECTED", advertisementTest.getVisits().get(0).getStatus());
        }

        @Test
        void testOfferMethods() {
                assertThrows(AcceptedOfferNotFoundException.class, () -> {
                        advertisementTest.getAcceptedOffer();
                });
                advertisementTest.getOffers().get(0).setStatus(OfferStatus.APPROVED);
                try {
                        assertEquals(advertisementTest.getOffers().get(0), advertisementTest.getAcceptedOffer());
                } catch (AcceptedOfferNotFoundException e) {
                        e.printStackTrace();
                }
        }

        AdvertisementRepository repo = new AdvertisementRepository();
        Advertisement repoAdTest = repo.createNewAdvertisement(testLease1, CommissionType.FIXED, 1230, storeTest);

        @Test
        void ensureAdvertisementRepoCreation() {
                assertNotNull(repoAdTest);
                assertEquals(advertisementTest, repoAdTest);
                assertEquals(advertisementTest, repo.getAdvertisements().get(0));

        }

        Advertisement adTest1 = repo.createNewAdvertisement(testSale1, CommissionType.PERCENTAGE, 13, storeTest);
        Advertisement adTest2 = repo.createNewAdvertisement(testLease3, CommissionType.PERCENTAGE, 16, storeTest);
        Advertisement adTest3 = repo.createNewAdvertisement(testSale2, CommissionType.FIXED, 13, storeTest);
        Advertisement adTest4 = repo.createNewAdvertisement(testSale3, CommissionType.FIXED, 13, storeTest);

        @Test
        void testAdRepoList() {
                List<Advertisement> testList = new ArrayList<>(
                                Arrays.asList(repoAdTest, adTest1, adTest2, adTest3, adTest4));
                assertEquals(testList, repo.getAdvertisements());

        }

        @Test
        void testAdRepoListByAgent() {
                List<Advertisement> testList = new ArrayList<>(Arrays.asList(repoAdTest, adTest4));
                assertEquals(testList, repo.getAdvertisementsByAgent(employeeTest1));
        }

        {
                repo.introduceVisitToAdList(adTest1, 2025, 11, 23, 11, "dummyEmail@gmail.com");
                repo.introduceOfferToAdList(adTest1, 1234, "janedoe@gmail.com");
        }

        @Test
        void testRepoAddVisitAndOffer() {
                assertEquals(testVisit, adTest1.getVisits().get(0));
                assertEquals(testOffer, adTest1.getOffers().get(0));
        }

        @Test
        void testRepoGetVisitAndOffer() {
                assertEquals(testVisit, repo.getAdVisits(adTest1).get(0));
                assertEquals(testOffer, repo.getAdOffers(adTest1).get(0));
        }

        @Test
        void testRepoAddVisitOfferThrows() {
                assertThrows(IllegalArgumentException.class, () -> {
                        repo.introduceVisitToAdList(adTest1, 2024, 2, -3, 10, "dummyEmail@gmail.com");
                });
                assertThrows(IllegalArgumentException.class, () -> {
                        repo.introduceOfferToAdList(adTest1, -2, "janedoe@gmail.com");
                });
                assertThrows(IllegalArgumentException.class, () -> {
                        repo.introduceVisitToAdList(null, 2024, 2, 3, 10, "dummyEmail@gmail.com");
                });
                assertThrows(IllegalArgumentException.class, () -> {
                        repo.introduceOfferToAdList(null, 12345, "janedoe@gmail.com");
                });
                assertThrows(IllegalArgumentException.class, () -> {
                        repo.introduceVisitToAdList(adTest1, 2024, 2, 3, 10, null);
                });
                assertThrows(IllegalArgumentException.class, () -> {
                        repo.introduceOfferToAdList(adTest1, 12345, null);
                });
                assertThrows(IllegalArgumentException.class, () -> {
                        repo.introduceVisitToAdList(adTest1, -2024, 2, 3, 10, "dummyEmail@gmail.com");
                });
                assertThrows(IllegalArgumentException.class, () -> {
                        repo.introduceVisitToAdList(adTest1, 2024, 2, -3, 10, "dummyEmail@gmail.com");
                });
                assertThrows(IllegalArgumentException.class, () -> {
                        repo.introduceVisitToAdList(adTest1, 2024, -2, 3, -10, "dummyEmail@gmail.com");
                });
        }

        @Test
        void testRepoRespondToVisit() {
                Visit v = new Visit(2024, 11, 23, 11, "dummyEmail@gmail.com");
                repo.introduceVisitToAdList(adTest1, 2024, 11, 23, 11, "dummyEmail@gmail.com");
                repo.respondToVisit(adTest1, adTest1.getVisits().get(1), false);
                assertEquals(VisitStatus.REJECTED.getVisitStatus(),
                                repo.getAdvertisements().get(1).getVisits().get(1).getStatus());
        }
}