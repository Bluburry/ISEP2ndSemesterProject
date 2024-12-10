package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Land implements Property, Location, Serializable {

  /**
   * Shows choice of property type
   */
  private PropertyType propertyType;

  /**
   * Area of the Land in square meters
   */
  private double area;

  /**
   * Distance from the Land to the City in Kilometers
   */
  private double distance;

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

  /**
   * List of Photos of the Land
   */
  private List<String> photographs;

  /**
   * Creates instance with Property Type, Area of the Land in square meters,
   * Distance from the Land to the City in Kilometers, Street were is the Land,
   * Zip of the location of the Land, State in the US were is the Land,
   * City were is the Land, District were is the Land
   * 
   * @param propertyType Property Type Land
   * @param area         Area of the Land in square meters
   * @param distance     Distance to the city center in Kilometers
   * @param photographs  Property photos
   * @param street       Street where the property is located
   * @param zip          ZipCode of the property's address
   * @param state        State where the property is located
   * @param city         City where the property is located
   * @param district     District where the property is located
   */
  public Land(PropertyType propertyType, double area, double distance, List<String> photographs, String street,
      String zip, String state, String city, String district) throws IllegalArgumentException {
    validatePropertyArguments(propertyType, area, distance, photographs);
    validateLocation(zip, street, state, city, district);
    this.propertyType = propertyType;
    this.area = area;
    this.distance = distance;
    this.photographs = photographs;
    this.street = street;
    this.zip = zip;
    this.state = state;
    this.city = city;
    this.district = district;
  }

  // For use in US17
  /**
   * Creates instance with Property Type, Area of the Land in square meters,
   * Distance from the Land to the City in Kilometers, Street were is the Land,
   * Zip of the location of the Land, State in the US were is the Land,
   * City were is the Land, District were is the Land
   * 
   * @param propertyType Property Type Land
   * @param area         Area of the Land in square meters
   * @param distance     Distance to the city center in Kilometers
   * @param street       Street where the property is located
   * @param zip          ZipCode of the property's address
   * @param state        State where the property is located
   * @param city         City where the property is located
   * @param district     District where the property is located
   */
  public Land(PropertyType propertyType, double area, double distance, String street,
      String zip, String state, String city, String district) throws IllegalArgumentException {
    if (!validPropertyType(propertyType))
      throw new IllegalArgumentException("Invalid property type");
    else if ((area < 0) && (distance < 0))
      throw new IllegalArgumentException("Invalid values (area or distance)");
    validateLocation(zip, street, state, city, district);
    this.propertyType = propertyType;
    this.area = area;
    this.distance = distance;
    this.photographs = new ArrayList<>();
    this.street = street;
    this.zip = zip;
    this.state = state;
    this.city = city;
    this.district = district;
  }

  /**
   * Returns Area of the Land in square meters
   * 
   * @return Area of the Land in square meters
   */
  public double getArea() {
    return area;
  }

  /**
   * Distance from the Land to the City in Kilometers
   * 
   * @return Distance from the Land to the City in Kilometers
   */
  public double getDistance() {
    return distance;
  }

  /**
   * Returns Property Type
   * 
   * @return Property Type
   */
  public String getPropertyType() {
    return propertyType.getPropertyType();
  }

  /**
   * Returns Photo of the Property
   * 
   * @return Photo of the Property
   */
  public List<String> getPhotographs() {
    return photographs;
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
   * Returns Property's location (Street, Zip Code, State, City, District)
   * 
   * @return Property's location (Street, Zip Code, State, City, District)
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
   * Test validation of data with Property Type, Area of the Land in square
   * meters, Distance from the Land to the City in Kilometers.
   * 
   * @param propertyType Property Type
   * @param area         Area of the Land in square meters
   * @param distance     Distance from the Land to the City in Kilometers
   * @param photographs  Property photos
   * @return true or false
   */
  public void validatePropertyArguments(PropertyType propertyType, double area, double distance,
      List<String> photographs) throws IllegalArgumentException {
    if (!validPropertyType(propertyType))
      throw new IllegalArgumentException("Invalid property type");
    else if (!validatePhotos(photographs))
      throw new IllegalArgumentException("Invalid photo list");
    else if ((area < 0) || (distance < 0))
      throw new IllegalArgumentException("Invalid values (area or distance)");
  }

  /**
   * Validates property type by checking if it exists in the PropertyType enum
   * 
   * @return true or false
   */
  // TODO: check this method to check which solution works
  public boolean validPropertyType(PropertyType propertyType) {
    /*
     * PropertyType possibleTypes[] = PropertyType.values();
     * boolean isExistingType = false;
     * for (PropertyType pt : possibleTypes) {
     * if (propertyType.getPropertyType().equals(pt.getPropertyType())) {
     * isExistingType = true;
     * break;
     * }
     * }
     * return isExistingType;
     */
    return propertyType.equals(PropertyType.LAND); // does this work?
    // return
    // propertyType.getPropertyType().equals(PropertyType.LAND.getPropertyType());
  }

  /**
   * Validates the list of photographs
   * Mostly just check if the list is null or empty
   * and if the image is in the png or jpg format
   */
  public boolean validatePhotos(List<String> photographs) {
    if (photographs == null || photographs.isEmpty())
      return false;
    for (String photo : photographs) {
      if (photo.length() < 5)
        return false;
      if (photo.charAt(photo.length() - 4) == '.' && photo.charAt(photo.length() - 3) == 'p'
          && photo.charAt(photo.length() - 2) == 'n' && photo.charAt(photo.length() - 1) == 'g') {
        continue;
      } else if (photo.charAt(photo.length() - 4) == '.' && photo.charAt(photo.length() - 3) == 'j'
          && photo.charAt(photo.length() - 2) == 'p' && photo.charAt(photo.length() - 1) == 'g') {
        continue;
      } else
        return false;
    }
    return true;
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
      return "Location is not properly defined for this property.";
    else
      return String.format(this.state + ", " + this.city + ", " + this.district + ", " + this.street + ", " + this.zip);
  }

  public String toString() {
    return String.format(
        "%s\nProperty type: %s\nArea in square meters: %.2f | Distance from city center: %.2f", locationToString(),
        propertyType.getPropertyType(), area, distance);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof Land || o instanceof Apartment || o instanceof House))
      return false;
    if (!(o instanceof Land))
      return false;
    Land r = (Land) o;
    if (this.getArea() != r.getArea())
      return false;
    else if (this.getDistance() != r.getDistance())
      return false;
    else if (!(this.getStreet().equals(r.getStreet())))
      return false;
    else if (!(this.getState().equals(r.getState())))
      return false;
    else if (!(this.getCity().equals(r.getCity())))
      return false;
    else if (!(this.getDistrict().equals(r.getDistrict())))
      return false;
    else if (!(this.getZip().equals(r.getZip())))
      return false;
    else
      return true;
  }

}
