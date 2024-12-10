package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Person;
import pt.ipp.isep.dei.esoft.project.domain.Serialization;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class PersonRepository {

    private List<Person> persons = new ArrayList<>();
    private static String SERFILE = "PersonRepository.ser";

    public void savePersonRepository() {
        Serialization.toWrite((List<Object>) (List<?>) persons, SERFILE);
    }

    public void loadPersonRepository() {
        this.persons = (List<Person>) (List<?>) Serialization.toRead(SERFILE);
    }

    public void createNewAgent(Employee newEmployee) {
        for (Person p : persons) {
            if (p.equals(newEmployee))
                throw new IllegalArgumentException("This employee has already been saved.");
        }
        persons.add(newEmployee);
    }

    public void addPersonWithLocation(String keepEmail, String keepName, int keepCitizenNumber, int keepTin,
            int keepPhoneNumber, String keepStreet, String keepZipCode, String keepStateUS, String keepCity,
            String keepDistrict) throws IllegalArgumentException {
        for (Person p : persons) {
            if (p.getEmail().getEmail().equals(keepEmail))
                throw new IllegalArgumentException("A person with this e-mail has already been saved to the system.");
        }
        Person person = new Person(keepEmail, keepName, keepCitizenNumber, keepTin,
                keepPhoneNumber, keepStreet, keepZipCode, keepStateUS, keepCity, keepDistrict);
        persons.add(person);
    }

    public void addPerson(String keepEmail, String keepName, int keepCitizenNumber, int keepTin,
            int keepPhoneNumber) throws IllegalArgumentException {
        for (Person p : persons) {
            if (p.getEmail().getEmail().equals(keepEmail))
                throw new IllegalArgumentException("A person with this e-mail has already been saved to the system.");
        }
        Person person = new Person(keepEmail, keepName, keepCitizenNumber,
                keepTin, keepPhoneNumber);
        persons.add(person);
    }

    public Employee addEmployee(String keepEmail, String keepName, int keepCitizenNumber, int keepTin,
            int keepPhoneNumber, String role) throws IllegalArgumentException {
        for (Person p : persons) {
            if (p.getEmail().getEmail().equals(keepEmail))
                throw new IllegalArgumentException("A person with this e-mail has already been saved to the system.");
        }
        Employee person = new Employee(keepEmail, keepName, keepCitizenNumber, keepTin, keepPhoneNumber, role);
        persons.add(person);
        return person;
    }

    public Employee addEmployeeWithLocation(String keepEmail, String keepName, int keepCitizenNumber, int keepTin,
            int keepPhoneNumber, String keepStreet, String keepZipCode, String keepStateUS, String keepCity,
            String keepDistrict, String role) throws IllegalArgumentException {
        for (Person p : persons) {
            if (p.getEmail().getEmail().equals(keepEmail))
                throw new IllegalArgumentException("A person with this e-mail has already been saved to the system.");
        }
        Employee person = new Employee(keepEmail, keepName, keepCitizenNumber, keepTin, keepPhoneNumber, keepStreet,
                keepZipCode, keepStateUS,
                keepCity, keepDistrict, role);
        persons.add(person);
        return person;
    }

    public Person createPersonWithLocation(String keepEmail, String keepName, int keepCitizenNumber, int keepTin,
            int keepPhoneNumber, String keepStreet, String keepZipCode, String keepStateUS, String keepCity,
            String keepDistrict) throws IllegalArgumentException {
        for (Person p : persons) {
            if (p.getEmail().getEmail().equals(keepEmail))
                throw new IllegalArgumentException("A person with this e-mail has already been saved to the system.");
        }
        Person person = new Person(keepEmail, keepName, keepCitizenNumber, keepTin,
                keepPhoneNumber, keepStreet, keepZipCode, keepStateUS, keepCity, keepDistrict);
        persons.add(person);
        return person;
    }

    public Person createPerson(String keepEmail, String keepName, double keepCitizenNumber, double keepTin,
            double keepPhoneNumber) throws IllegalArgumentException {
        for (Person p : persons) {
            if (p.getEmail().getEmail().equals(keepEmail))
                throw new IllegalArgumentException("A person with this e-mail has already been saved to the system.");
        }
        Person person = new Person(keepEmail, keepName, keepCitizenNumber,
                keepTin, keepPhoneNumber);
        persons.add(person);
        return person;
    }

    public Employee createEmployee(String keepEmail, String keepName, double keepCitizenNumber, double keepTin,
            double keepPhoneNumber, String role) throws IllegalArgumentException {
        for (Person p : persons) {
            if (p.getEmail().getEmail().equals(keepEmail))
                throw new IllegalArgumentException("A person with this e-mail has already been saved to the system.");
        }
        Employee person = new Employee(keepEmail, keepName, keepCitizenNumber, keepTin, keepPhoneNumber, role);
        persons.add(person);
        return person;
    }

    public Employee createEmployeeWithLocation(String keepEmail, String keepName, double keepCitizenNumber,
            double keepTin,
            double keepPhoneNumber, String keepStreet, String keepZipCode, String keepStateUS, String keepCity,
            String keepDistrict, String role) throws IllegalArgumentException {
        for (Person p : persons) {
            if (p.getEmail().getEmail().equals(keepEmail))
                throw new IllegalArgumentException("A person with this e-mail has already been saved to the system.");
        }
        Employee person = new Employee(keepEmail, keepName, keepCitizenNumber, keepTin, keepPhoneNumber, keepStreet,
                keepZipCode, keepStateUS,
                keepCity, keepDistrict, role);
        persons.add(person);
        return person;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of business
     * types.
     *
     * @return The list of business types.
     */

    public List<Person> getPersons() {
        return List.copyOf(persons);
    }

    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        for (Person person : persons) {
            if (person instanceof Employee)
                employees.add((Employee) person);
        }
        return List.copyOf(employees);
    }

    public List<Employee> getEmployeesByRole(String role) {
        List<Employee> returnEmployees = new ArrayList<>();
        List<Employee> employees = getEmployees();
        for (Employee employee : employees) {
            if (employee.getRole().equals(role))
                returnEmployees.add(employee);
        }
        return List.copyOf(employees);
    }

    public boolean exists(Person p) {
        for (Person person : persons) {
            if (p.equals(person))
                return true;
        }
        return false;
    }

    public Person findPerson(int numberCitizen) {
        for (Person person : persons) {
            if (person.getCitizenID() == numberCitizen)
                return person;
        }
        return null;
    }

    public Person getPersonByEmail(String keepOwnerEmail) {
        Person owner = null;
        for (Person person : persons) {
            if (person.getEmail().getEmail().equals(keepOwnerEmail))
                owner = person;
        }
        return owner;
    }

    public List<Employee> getAgentList() {
        List<Employee> agentList = new ArrayList<>();
        for (Person agent : persons) {
            if (agent instanceof Employee && ((Employee) agent).getRole().equals(AuthenticationController.ROLE_AGENT)) {
                agentList.add((Employee) agent);
            }
        }
        return agentList;
    }

    public String getPersonNameFromEmail(String keepAgentEmail) {
        String personName = null;
        List<Employee> employeeList = getEmployees();
        for (Employee employee : employeeList) {
            if (employee.getEmail().getEmail().compareTo(keepAgentEmail) == 0)
                personName = employee.getName();
        }
        return personName;
    }

    public String getClientNameFromEmail(String clientEmail) {
        String personName = null;
        List<Person> personList = getPersons();
        for (Person p : personList) {
            if (p.getEmail().getEmail().compareTo(clientEmail) == 0)
                personName = p.getName();
        }
        return personName;
    }

    public double getPersonPhoneFromEmail(String keepAgentEmail) {
        double personPhone = 0;
        List<Employee> employeeList = getEmployees();
        for (Employee employee : employeeList) {
            if (employee.getEmail().getEmail().compareTo(keepAgentEmail) == 0)
                personPhone = employee.getPhoneNumber();
        }
        return personPhone;
    }

    /*
     * public Employee addEmployeeWithoutRole(String name, String email, int
     * numberCitizen, int tin, int phoneNumber) {
     * Employee person = new Employee(name, email, numberCitizen, tin, phoneNumber);
     * return person;
     * }
     */
}
