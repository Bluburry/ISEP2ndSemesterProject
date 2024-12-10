package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void ensureCreation() {
        Person test = new Person("johndoe@gmail.com", "John Doe", 333444555, 444333555, 555444333);
        Person test2 = new Employee("janesmith@gmail.com", "Jane Smith", 999000888, 888000999, 999888000,
                "holderplace",
                "67890", "Arkansas", "Little Rock", "onnud", "agent");
        assertNotNull(test);
        assertNotNull(test2);
    }

    @Test
    void ensureCreationWithLocation() {
        Person test = new Person("johnsmith@gmail.com", "John Smith", 666777888, 777666888, 888777666, "placeholder",
                "12345", "Kansas", "Wichita", "dunno");
        assertNotNull(test, "test is null.");
    }

    @Test
    void ensureGets() {
        Person test = new Person("johnsmith@gmail.com", "John Smith", 666777888, 777666888, 888777666, "placeholder",
                "12345", "Kansas", "Wichita", "dunno");
        assertEquals("johnsmith@gmail.com", test.getEmail().getEmail());
        assertEquals("John Smith", test.getName());
        assertEquals(666777888, test.getCitizenID());
        assertEquals(777666888, test.getTin());
        assertEquals(888777666, test.getPhoneNumber());
        assertEquals("placeholder", test.getStreet());
        assertEquals("12345", test.getZip());
        assertEquals("Kansas", test.getState());
        assertEquals("Wichita", test.getCity());
        assertEquals("dunno", test.getDistrict());
    }

    Person personTest1 = new Person("johndoe@gmail.com", "John Doe", 333444555, 444333555, 555444333);
    Person personTest2 = new Person("janedoe@gmail.com", "Jane Doe", 111222333, 222111333, 333222111);
    Person personTest3 = new Person("johnsmith@gmail.com", "John Smith", 666777888, 777666888, 888777666, "placeholder",
            "12345", "Kansas", "Wichita", "dunno");
    Person personTest4 = new Person("janesmith@gmail.com", "Jane Smith", 999000888, 888000999, 999888000, "holderplace",
            "67890", "Arkansas", "Little Rock", "onnud");

    @Test
    void testEquals() {
        Person test = new Person("johndoe@gmail.com", "John Doe", 333444555, 444333555, 555444333);
        assertTrue(test.equals(personTest1));
    }

    // Using Repository from now on
    PersonRepository personRepository = new PersonRepository();//Repositories.getInstance().getPersonRepository();

    @Test
    void ensurePersonIsCreatedNoLocation() {
        Person test = personRepository.createPerson("johndoe@gmail.com", "John Doe", 333444555, 444333555, 555444333);
        assertEquals(personTest1, test);
    }

    @Test
    void ensurePersonIsCreatedWithLocation() {
        Person test = personRepository.createPersonWithLocation("johnsmith@gmail.com", "John Smith", 666777888,
                777666888, 888777666, "placeholder", "12345", "Kansas", "Wichita", "dunno");
        assertEquals(personTest3, test);
    }

    // Testing Exceptions
    @Test
    void testingInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            personRepository.addPerson("asd", "John Doe", 333444555, 444333555, 555444333);
        });
    }

    @Test
    void testingEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> {
            personRepository.addPerson("johnsmith@gmail.com", "", 333444555, 444333555, 555444333);
        });
    }

    @Test
    void testingInvalidID() {
        assertThrows(IllegalArgumentException.class, () -> {
            personRepository.addPerson("johnsmith@gmail.com", "John Doe", 100, 444333555, 555444333);
        });
    }

    @Test
    void testingInvalidTin() {
        assertThrows(IllegalArgumentException.class, () -> {
            personRepository.addPerson("johnsmith@gmail.com", "John Doe", 333444555, 100, 555444333);
        });
    }

    @Test
    void testingInvalidPhoneNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            personRepository.addPerson("johnsmith@gmail.com", "John Doe", 333444555, 444333555, 100);
        });
    }

    @Test
    void testingInvalidZip() {
        assertThrows(IllegalArgumentException.class, () -> {
            personRepository.addPersonWithLocation("johnsmith@gmail.com", "John Smith", 666777888,
                    777666888, 888777666, "placeholder", "12", "Kansas", "Wichita", "dunno");
        });
    }

    @Test
    void testingEmptyStreet() {
        assertThrows(IllegalArgumentException.class, () -> {
            personRepository.addPersonWithLocation("johnsmith@gmail.com", "John Smith", 666777888,
                    777666888, 888777666, "", "12345", "Kansas", "Wichita", "dunno");
        });
    }

    @Test
    void testingEmptyCity() {
        assertThrows(IllegalArgumentException.class, () -> {
            personRepository.addPersonWithLocation("johnsmith@gmail.com", "John Smith", 666777888,
                    777666888, 888777666, "placeholder", "12345", "Kansas", "", "dunno");
        });
    }

    @Test
    void testingEmptyDistrict() {
        assertThrows(IllegalArgumentException.class, () -> {
            personRepository.addPersonWithLocation("johnsmith@gmail.com", "John Smith", 666777888,
                    777666888, 888777666, "placeholder", "12345", "Kansas", "Wichita", "");
        });
    }

    @Test
    void testingLocationToString() {
        Person test = personRepository.createPersonWithLocation("johnsmith@gmail.com", "John Smith", 666777888,
                777666888, 888777666, "placeholder", "12345", "Kansas", "Wichita", "dunno");
        String confirm = "Kansas, Wichita, dunno, placeholder, 12345";
        assertEquals(confirm, test.locationToString());
    }

    @Test
    void testingLocationToStringOnPersonWithNoLocation() {
        Person test = personRepository.createPerson("johndoe@gmail.com", "John Doe", 333444555, 444333555, 555444333);
        String confirm = "Location is not properly defined for this person.";
        assertEquals(confirm, test.locationToString());
    }

    @Test
    void testingFindPerson() {
        Person test = personRepository.createPerson("janedoe@gmail.com", "Jane Doe", 111222333, 222111333, 333222111);
        assertEquals(test, personRepository.findPerson(111222333));
    }

    @Test
    void testingGetPersonByEmail() {
        Person test = personRepository.createPerson("janetest@gmail.com", "Jane Doe", 111222333, 222111333, 333222111);
        assertEquals(test, personRepository.getPersonByEmail("janetest@gmail.com"));
    }

    // Testing person lists
    /*
     * List<Person> personList = new ArrayList<Person>();
     * personList.add(personTest1);
     * personList.add(personTest2);
     * personList.add(personTest3);
     * personList.add(personTest4);
     */

    @Test
    void testingGetPersons() {
        List<Person> listTest = new ArrayList<Person>();
        PersonRepository repoTest = new PersonRepository();

        Person test1 = repoTest.createPerson("johndoe@gmail.com", "John Doe", 333444555, 444333555, 555444333);
        Person test2 = repoTest.createPerson("janedoe@gmail.com", "Jane Doe", 111222333, 222111333, 333222111);
        Person test3 = repoTest.createPersonWithLocation("johnsmith@gmail.com", "John Smith", 666777888,
                777666888, 888777666, "placeholder",
                "12345", "Kansas", "Wichita", "dunno");
        Person test4 = repoTest.createPersonWithLocation("janesmith@gmail.com", "Jane Smith", 999000888,
                888000999, 999888000, "holderplace",
                "67890", "Arkansas", "Little Rock", "onnud");

        listTest.add(test1);
        listTest.add(test2);
        listTest.add(test3);
        listTest.add(test4);

        assertEquals(listTest, repoTest.getPersons());

    }

    // Creating and testing agents

    Employee employeeTest = personRepository.addEmployeeWithLocation("janesmithtest@gmail.com", "Jane Smith", 123456789,
            888000999, 999888000, "holderplace",
            "67890", "Arkansas", "Little Rock", "onnud", "AGENT");

    @Test
    void testingFindPersonWithEmployee() {
        assertEquals(employeeTest, personRepository.findPerson(123456789));
    }

    @Test
    void testingGetPersonEmployee() {
        assertEquals(employeeTest, personRepository.getPersonByEmail("janesmithtest@gmail.com"));
    }

    @Test
    void testingEmployeeList() {
        List<Employee> listTest = new ArrayList<Employee>();
        PersonRepository repoTest = new PersonRepository();
        Employee test = repoTest.createEmployeeWithLocation("janesmithtest@gmail.com", "Jane Smith", 123456789,
                888000999, 999888000, "holderplace",
                "67890", "Arkansas", "Little Rock", "onnud", "AGENT");
        listTest.add(test);
        assertEquals(listTest, repoTest.getEmployees());
    }

    @Test
    void testingAgentList() {
        List<Employee> listTest = new ArrayList<Employee>();
        PersonRepository repoTest = new PersonRepository();
        Employee test = repoTest.createEmployeeWithLocation("janesmithtest@gmail.com", "Jane Smith", 123456789,
                888000999, 999888000, "holderplace",
                "67890", "Arkansas", "Little Rock", "onnud", "AGENT");
        listTest.add(test);
        assertEquals(listTest, repoTest.getAgentList());
    }

    @Test
    void testingEmployeeListByRole() {
        List<Employee> listTest = new ArrayList<Employee>();
        PersonRepository repoTest = new PersonRepository();
        Employee test = repoTest.createEmployeeWithLocation("janesmithtest@gmail.com", "Jane Smith", 123456789,
                888000999, 999888000, "holderplace",
                "67890", "Arkansas", "Little Rock", "onnud", "AGENT");
        listTest.add(test);
        assertEquals(listTest, repoTest.getEmployeesByRole("AGENT"));
    }
}