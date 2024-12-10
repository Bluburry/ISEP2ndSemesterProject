package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Store implements Location, Serializable {

    private final String prefix = "STR";

    private static int numStores = 0;

    private String name;

    private String emailAddress;

    private double phoneNumber;

    private String idStore;

    /**
     * Saves the Location's Street
     */
    private String street;

    /**
     * Saves the Location's Zip Code
     */
    private String zip;

    /**
     * Saves the Location's State in USA
     */
    private String state;

    /**
     * Saves the Location's City
     */
    private String city;

    /**
     * Saves the Location's District
     */
    private String district;

    private List<Employee> employeeList = new ArrayList<>();

    private int numAds;

    public Store(String name, String emailAddress, double phoneNumber) {
        idStore = prefix + (++numStores);
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        numAds=0;
    }

    public Store(String name, String emailAddress, double phoneNumber, String street, String zip, String state,
            String city, String district) {
        idStore = prefix + (++numStores);
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.zip = zip;
        this.state = state;
        this.city = city;
        this.district = district;
        numAds=0;
    }

    public int getNumAds() {
        return numAds;
    }

    public void incNumAds(){
        numAds++;
    }

    public String getIdStore() {
        return idStore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public double getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(double phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void addEmployeeToStore(Employee employee) {
        employeeList.add(employee);
    }

    /**
     * Returns Location's Street
     *
     * @return Location's Street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Modifies Location's street
     *
     * @param street Location's street
     */
    public void setStreet(String street) {
        if (street == null || street.isEmpty())
            throw new IllegalArgumentException("Invalid argument, either street is null or empty");
        else
            this.street = street;
    }

    /**
     * Returns Location's Zip Code
     *
     * @return Location's Zip Code
     */
    public String getZip() {
        return zip;
    }

    /**
     * Modifies Location's zip
     *
     * @param zip Location's zip
     */
    public void setZip(String zip) throws IllegalArgumentException {
        validateZip(zip);
        this.zip = zip;
    }

    /**
     * Returns Location's State in USA
     *
     * @return Location's State in USA
     */
    public String getState() {
        return state;
    }

    /**
     * Modifies Location's state
     *
     * @param state Location's state
     */
    public void setState(String state) {
        if (state == null || state.isEmpty())
            throw new IllegalArgumentException("Invalid argument, either state is null or empty");
        else
            this.state = state;
    }

    /**
     * Returns Location's City
     *
     * @return Location's City
     */
    public String getCity() {
        return city;
    }

    /**
     * Modifies Location's city
     *
     * @param city Location's city
     */
    public void setCity(String city) {
        if (city == null || city.isEmpty())
            throw new IllegalArgumentException("Invalid argument, either city is null or empty");
        else
            this.city = city;
    }

    /**
     * Returns Location's District
     *
     * @return Location's District
     */
    public String getDistrict() {
        return district;
    }

    /**
     * Modifies Location's district
     *
     * @param district Location's district
     */
    public void setDistrict(String district) {
        if (district == null || district.isEmpty())
            throw new IllegalArgumentException("Invalid argument, either district is null or empty");
        else
            this.district = district;
    }

    /**
     * Returns Person's location (Street, Zip Code, State, City, District)
     *
     * @return Person's location (Street, Zip Code, State, City, District)
     */
    public String getLocation() {
        return this.locationToString();
    }

    /**
     * Modifies property's location (Street, Zip Code, State, City, District)
     *
     */
    public void setLocation(String street, String zip, String state, String city, String district)
            throws IllegalArgumentException {
        validateLocation(zip, street, state, city, district);
        this.street = street;
        this.zip = zip;
        this.state = state;
        this.city = city;
        this.district = district;
    }

    /**
     * validates user inputed location
     *
     * @param zip      Location's Zip Code
     * @param street   Location's street
     * @param state    Location's state
     * @param city     Location's city
     * @param district Location's district
     * @return wether the location is valid or not
     */
    public void validateLocation(String zip, String street, String state, String city, String district)
            throws IllegalArgumentException {
        String exception = "";
        validateZip(zip);
        if (street == null || street.isEmpty())
            exception = "Invalid street.";
        else if (exception.isEmpty() && (state == null || state.isEmpty()))
            exception = "Invalid state.";
        else if (exception.isEmpty() && (city == null || city.isEmpty()))
            exception = "Invalid city.";
        else if (exception.isEmpty() && (district == null || district.isEmpty()))
            exception = "Invalid city.";
        if (!exception.isEmpty())
            throw new IllegalArgumentException(exception);
    }

    /**
     * Validates zip code
     *
     * @param zip Location's Zip Code
     * @return true if it's valid, false otherwise
     */
    public void validateZip(String zip) {
        boolean acceptable = true;
        int zipTester = -1;
        if (zip.length() != 5)
            acceptable = false;
        try {
            zipTester = Integer.parseInt(zip);
        } catch (Exception e) {
            acceptable = false;
        }
        if (acceptable && zipTester < 0)
            acceptable = false;
        if (!acceptable)
            throw new IllegalArgumentException("Invalid zip code");
    }

    public String locationToString() {
        if (this.street == null || this.zip == null || this.state == null || this.city == null || this.district == null)
            return "Location is not properly defined for this person.";
        else
            return String.format(
                    this.state + ", " + this.city + ", " + this.district + ", " + this.street + ", " + this.zip);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Store))
            return false;
        Store r = (Store) o;
        if (this.getName() != r.getName())
            return false;
        else if (this.getEmailAddress() != r.getEmailAddress())
            return false;
        /*
         * else if (this.getIdStore() != r.getIdStore())
         * return false;
         */
        else if (this.getPhoneNumber() != r.getPhoneNumber())
            return false;
        else
            return true;
    }

}
