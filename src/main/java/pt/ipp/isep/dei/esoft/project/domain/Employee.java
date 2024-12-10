package pt.ipp.isep.dei.esoft.project.domain;

public class Employee extends Person {

  /**
   * Shows the employee Role in the Company
   */
  private String role;

  /**
   * Creates instance with Employee's Email,
   * 
   * @param email       Employee's Email
   * @param name        Employee's name
   * @param citizenID   Employee's Citizen Number
   * @param tin         Employee's Financial identification (tin)
   * @param phoneNumber Employee's phone Number
   * @param street      Location's Street
   * @param zip         Location's Zip Code
   * @param state       Location's State in USA
   * @param city        Location's City
   * @param district    Location's District
   * @param role        Employee's Role
   */
  public Employee(String email, String name, double citizenID, double tin, double phoneNumber, String street,
      String zip,
      String state, String city, String district, String role) throws IllegalArgumentException {
    super(email, name, citizenID, tin, phoneNumber, street, zip, state, city, district);
    this.role = role;
  }

  /**
   * Creates instance with Employee's Email
   * 
   * @param name        Employee's name
   * @param email       Employee's Email
   * @param citizenID   Employee's Citizen Number
   * @param tin         Employee's Financial identification (tin)
   * @param phoneNumber Employee's phone Number
   * @param role        Employee's Role
   */
  public Employee(String email, String name, double citizenID, double tin, double phoneNumber, String role)
      throws IllegalArgumentException {
    super(email, name, citizenID, tin, phoneNumber);
    this.role = role;
  }

  /**
   * Returns Employee's role in Company
   * 
   * @return Employee's role in Company
   */
  public String getRole() {
    return role;
  }

  /**
   * Modifies acording Employee's role in Company
   * 
   * @param role Employee's role in Company
   */
  public void setRole(String role) {
    this.role = role;
  }
}
