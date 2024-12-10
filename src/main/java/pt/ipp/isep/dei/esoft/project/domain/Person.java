package pt.ipp.isep.dei.esoft.project.domain;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import pt.isep.lei.esoft.auth.domain.model.Email;

public class Person implements Location, Serializable {
  /**
   * Saves Person's Email
   */
  private Email email;

  /**
   * Saves Person's name
   */
  private String name;

  /**
   * Saves Person's Citizen Number
   */
  private double citizenID;

  /**
   * Saves Person's Financial identification (tin)
   */
  private double tin;

  /**
   * Saves Person's phone Number
   */
  private double phoneNumber;

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
  
  private static float CHECKER = 9999999999L;

  /**
   * Creates instance with Person's Email,
   * 
   * @param email       Person's Email
   * @param name        Person's name
   * @param citizenID   Person's Citizen Number
   * @param tin         Person's Financial identification (tin)
   * @param phoneNumber Person's phone Number
   * @param street      Location's Street
   * @param zip         Location's Zip Code
   * @param state       Location's State in USA
   * @param city        Location's City
   * @param district    Location's District
   */
  public Person(String email, String name, double citizenID, double tin, double phoneNumber, String street, String zip,
      String state, String city, String district) throws IllegalArgumentException {
    validateValues(citizenID, tin, phoneNumber);
    validateLocation(zip, street, state, city, district);
    if (name == null || name.isEmpty())
      throw new IllegalArgumentException("Name is empty or null");
    this.email = new Email(email);
    this.name = name;
    this.citizenID = citizenID;
    this.tin = tin;
    this.phoneNumber = phoneNumber;
    this.street = street;
    this.zip = zip;
    this.state = state;
    this.city = city;
    this.district = district;
  }

  /**
   * Creates instance with Person's Email
   * 
   * @param name        Person's name
   * @param email       Person's Email
   * @param citizenID   Person's Citizen Number
   * @param tin         Person's Financial identification (tin)
   * @param phoneNumber Person's phone Number
   */
  public Person(String email, String name, double citizenID, double tin, double phoneNumber)
      throws IllegalArgumentException {
    validateValues(citizenID, tin, phoneNumber);
    if (name == null || name.isEmpty())
      throw new IllegalArgumentException("Name is empty or null");
    this.email = new Email(email);
    this.name = name;
    this.citizenID = citizenID;
    this.tin = tin;
    this.phoneNumber = phoneNumber;
  }

  /**
   * Returns Person's Email
   * 
   * @return Person's Email
   */
  public Email getEmail() {
    return email;
  }

  /**
   * Modifies Person's Email
   * 
   * @param email Person's Email
   */
  public void setEmail(String email) {
    try {
      this.email = new Email(email);
    } catch (IllegalArgumentException e) {
      throw e;
    }
  }

  /**
   * Returns Person's name
   * 
   * @return Person's name
   */
  public String getName() {
    return name;
  }

  /**
   * Modifies Person's name
   * 
   * @param name Person's name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns Person's Citizen ID
   * 
   * @return Person's Citizen ID
   */
  public double getCitizenID() {
    return citizenID;
  }

  /**
   * Modifies Person's Citizen ID
   * 
   * @param citizenID Person's Citizen IDs
   */
  public void setCitizenID(double citizenID) {
    if (validateInput(citizenID))
      this.citizenID = citizenID;
    else
      throw new IllegalArgumentException("Argument not valid, please try again.");
  }

  /**
   * Returns Person's Financial identification (tin)
   * 
   * @return Person's Financial identification (tin)
   */
  public double getTin() {
    return tin;
  }

  /**
   * Modifies Person's Financial identification (tin)
   * 
   * @param tin Person's Financial identification (tin)
   */
  public void setTin(double tin) {
    if (validateInput(tin))
      this.tin = tin;
    else
      throw new IllegalArgumentException("Argument not valid, please try again.");
  }

  /**
   * Returns Person's phone Number
   * 
   * @return Person's phone Number
   */
  public double getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Modifies Person's phone Number
   * 
   * @param phoneNumber Person's phone Number
   */
  public void setPhoneNumber(double phoneNumber) {
    if (validateInput(phoneNumber))
      this.phoneNumber = phoneNumber;
    else
      throw new IllegalArgumentException("Argument not valid, please try again.");
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

  // TODO: needs to be revised
  public void validateValues(double citizenID, double tin, double phoneNumber) {
    if (citizenID < 100000000 || citizenID > CHECKER)
      throw new IllegalArgumentException("Invalid ID");
    else if (tin < 100000000 || tin > CHECKER)
      throw new IllegalArgumentException("Invalid TIN");
    else if (phoneNumber < 100000000 || phoneNumber > CHECKER)
      throw new IllegalArgumentException("Invalid phone number");
  }

  public boolean validateInput(double toValidate) {
    return (toValidate < 10000000 || toValidate > CHECKER);
  }

  @Override
  public boolean equals(Object object) {
    Email email;
    if (object == null || !(object instanceof Person || object instanceof Employee))
      return false;
    else {
      email = ((Person) object).getEmail();
      return this.getEmail().equals(email);
    }
    // Don't think this would work anymore
    // return this.getEmail().equals(((PersonInterface) object).getEmail());
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
    boolean error = false;
    String exception = "";
    validateZip(zip);
    if (street == null || street.isEmpty()) {
      exception = "Invalid street.";
      error = true;
    } else if (!error && (state == null || state.isEmpty())) {
      exception = "Invalid state.";
      error = true;
    } else if (!error && (city == null || city.isEmpty())) {
      exception = "Invalid city.";
      error = true;
    } else if (!error && (district == null || district.isEmpty())) {
      exception = "Invalid city.";
      error = true;
    }
    if (error)
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
      return String.format(this.state + ", " + this.city + ", " + this.district + ", " + this.street + ", " + this.zip);
  }

  private void writeObject(ObjectOutputStream opst) throws IOException {
    opst.writeObject(email.getEmail());
    opst.writeObject(name);
    opst.writeDouble(citizenID);
    opst.writeDouble(tin);
    opst.writeDouble(phoneNumber);
    opst.writeObject(street);
    opst.writeObject(zip);
    opst.writeObject(state);
    opst.writeObject(city);
    opst.writeObject(district);
  }

  private void readObject(ObjectInputStream ipst) throws IOException, ClassNotFoundException {
    email = new Email((String) ipst.readObject());
    name = (String) ipst.readObject();
    citizenID = ipst.readDouble();
    tin = ipst.readDouble();
    phoneNumber = ipst.readDouble();
    street = (String) ipst.readObject();
    zip = (String) ipst.readObject();
    state = (String) ipst.readObject();
    city = (String) ipst.readObject();
    district = (String) ipst.readObject();
  }
}
