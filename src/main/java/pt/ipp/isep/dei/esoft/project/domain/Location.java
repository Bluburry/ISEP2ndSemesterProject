package pt.ipp.isep.dei.esoft.project.domain;

public interface Location {

    String getStreet();

    String getZip();

    String getState();

    String getCity();

    String getDistrict();

    String getLocation();

    void setStreet(String street);

    void setZip(String zip);

    void setState(String state);

    void setCity(String city);

    void setDistrict(String district);

    void setLocation(String street, String zip, String state, String city, String district);

    void validateZip(String zip);

    void validateLocation(String zip, String street, String state, String city, String district);

    String locationToString();
}