package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.StoreRepository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {

    @Test
    void ensureStoreCreation() {
        Store test = new Store("dunno", "test@gmail.com", 1234567890);
        Store test2 = new Store("onnud", "tset@gmail.com", 1234567890, "placeholder", "12345", "Kansas", "Wichita",
                "dunno");
        assertNotNull(test);
        assertNotNull(test2);
    }

    Store test = new Store("onnud", "tset@gmail.com", 1234567890, "placeholder", "12345", "Kansas", "Wichita",
            "dunno");

    @Test
    void ensureStoreGets() {
        assertEquals("onnud", test.getName());
        assertEquals("tset@gmail.com", test.getEmailAddress());
        assertEquals(1234567890, test.getPhoneNumber());
        assertEquals("placeholder", test.getStreet());
        assertEquals("12345", test.getZip());
        assertEquals("Kansas", test.getState());
        assertEquals("dunno", test.getDistrict());
    }

    @Test
    void testLocationToString() {
        String confirm = "Kansas, Wichita, dunno, placeholder, 12345";
        assertEquals(confirm, test.locationToString());
    }

    Employee employeeTest1 = new Employee("janesmith@gmail.com", "Jane Smith", 999000888, 888000999, 999888000,
            "holderplace",
            "67890", "Arkansas", "Little Rock", "onnud", "agent");
    Employee employeeTest2 = new Employee("johnsmith@gmail.com", "John Smith", 666777888, 777666888, 888777666,
            "placeholder",
            "12345", "Kansas", "Wichita", "dunno", "agent");

    @Test
    void testEmployeeList() {
        test.addEmployeeToStore(employeeTest1);
        test.addEmployeeToStore(employeeTest2);
        assertEquals(employeeTest1, test.getEmployeeList().get(0));
        assertEquals(employeeTest2, test.getEmployeeList().get(1));

    }

    @Test
    void testEquals() {
        Store test1 = new Store("onnud", "tset@gmail.com", 1234567890, "placeholder", "12345", "Kansas", "Wichita",
                "dunno");
        Store test2 = new Store("onnud", "tset@gmail.com", 1234567890, "placeholder", "12345", "Kansas", "Wichita",
                "dunno");
        assertTrue(test1.equals(test2));
    }

    StoreRepository repo = new StoreRepository();

    @Test
    void testCreation() {
        repo.createNewStore("onnud", "tset@gmail.com", 1234567890);
        Store test1 = new Store("onnud", "tset@gmail.com", 1234567890);
        assertNotNull(repo.getStoresList());
        assertEquals(test1, repo.getStoresList().get(0));
    }

}