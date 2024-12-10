package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class PropertyRepository {

    private List<Property> properties = new ArrayList<>();
    private static String SERFILE = "PropertyRepository.ser";

    public void savePropertyRepository() {
        Serialization.toWrite((List<Object>) (List<?>) properties, SERFILE);
    }

    public void loadPropertyRepository() {
        this.properties = (List<Property>) (List<?>) Serialization.toRead(SERFILE);
    }

    public Property createNewHouseProperty(PropertyType keepPropertyType, double keepAreaProperty,
            double keepDistanceCityCenter, List<String> keepPhotographs, int keepNumberBedrooms, int keepNumberParking,
            int keepNumberBathrooms, String keepAvailableEquipment, boolean keepBasement, boolean keepLoft,
            SunExposure keepSunExposure, String keepStreet, String keepZipCode, String keepStateUS, String keepCity,
            String keepDistrict) throws IllegalArgumentException {
        House newHouseProperty = new House(keepPropertyType, keepAreaProperty, keepDistanceCityCenter, keepPhotographs,
                keepStreet, keepZipCode, keepStateUS, keepCity, keepDistrict, keepNumberBedrooms, keepNumberParking,
                keepNumberBathrooms, keepAvailableEquipment, keepBasement, keepLoft, keepSunExposure);
        for (Property p : properties) {
            if (p instanceof House && p.equals(newHouseProperty))
                throw new IllegalArgumentException("A property with these specifications already exists in the system");
        }
        properties.add(newHouseProperty);
        return newHouseProperty;
    }

    public Property createNewApartmentProperty(PropertyType keepPropertyType, double keepAreaProperty,
            double keepDistanceCityCenter, List<String> keepPhotographs, int keepNumberBedrooms, int keepNumberParking,
            int keepNumberBathrooms, String keepAvailableEquipment, String keepStreet, String keepZipCode,
            String keepStateUS, String keepCity, String keepDistrict) throws IllegalArgumentException {
        Apartment newApartmentProperty = new Apartment(keepPropertyType, keepAreaProperty, keepDistanceCityCenter,
                keepPhotographs, keepStreet, keepZipCode, keepStateUS, keepCity, keepDistrict, keepNumberBedrooms,
                keepNumberParking, keepNumberBathrooms, keepAvailableEquipment);
        for (Property p : properties) {
            if (p instanceof Apartment && p.equals(newApartmentProperty))
                throw new IllegalArgumentException("A property with these specifications already exists in the system");
        }
        properties.add(newApartmentProperty);
        return newApartmentProperty;
    }

    public Property createNewProperty(PropertyType keepPropertyType, double keepAreaProperty,
            double keepDistanceCityCenter, List<String> keepPhotographs, String keepStreet, String keepZipCode,
            String keepStateUS, String keepCity, String keepDistrict) throws IllegalArgumentException {
        Property newLandProperty = new Land(keepPropertyType, keepAreaProperty, keepDistanceCityCenter, keepPhotographs,
                keepStreet, keepZipCode, keepStateUS, keepCity, keepDistrict);
        for (Property p : properties) {
            if (p instanceof Land && p.equals(newLandProperty))
                throw new IllegalArgumentException("A property with these specifications already exists in the system");
        }
        properties.add(newLandProperty);
        return newLandProperty;
    }

    public Property createNewHouseProperty(PropertyType keepPropertyType, double keepAreaProperty,
            double keepDistanceCityCenter, int keepNumberBedrooms, int keepNumberParking,
            int keepNumberBathrooms, String keepAvailableEquipment, String keepBasement, String keepLoft,
            String keepSunExposure, String Location) throws IllegalArgumentException {
        boolean basement = false;
        boolean loft = false;
        if (keepBasement.equals("Y"))
            basement = true;
        if (keepLoft.equals("Y"))
            loft = false;
        String[] locationData = deleteSpaces(Location);
        SunExposure sunExposure = SunExposure.NORTH;
        switch (keepSunExposure) {
            case "N":
                sunExposure = SunExposure.NORTH;
                break;
            case "S":
                sunExposure = SunExposure.SOUTH;
                break;
            case "W":
                sunExposure = SunExposure.WEST;
                break;
            case "E":
                sunExposure = SunExposure.EAST;
                break;
        }
        House newHouseProperty = new House(keepPropertyType, keepAreaProperty, keepDistanceCityCenter,
                locationData[0], locationData[locationData.length - 1], locationData[locationData.length - 2],
                locationData[locationData.length - 3], legacyLocationDistrict(locationData), keepNumberBedrooms,
                keepNumberParking,
                keepNumberBathrooms, keepAvailableEquipment, basement, loft, sunExposure);
        for (Property p : properties) {
            if (p instanceof House && p.equals(newHouseProperty))
                throw new IllegalArgumentException("A property with these specifications already exists in the system");
        }
        properties.add(newHouseProperty);
        return newHouseProperty;
    }

    public Property createNewApartmentProperty(PropertyType keepPropertyType, double keepAreaProperty,
            double keepDistanceCityCenter, int keepNumberBedrooms, int keepNumberParking,
            int keepNumberBathrooms, String keepAvailableEquipment, String Location) throws IllegalArgumentException {
        String[] locationData = deleteSpaces(Location);
        Apartment newApartmentProperty = new Apartment(keepPropertyType, keepAreaProperty, keepDistanceCityCenter,
                locationData[0], locationData[locationData.length - 1], locationData[locationData.length - 2],
                locationData[locationData.length - 3], legacyLocationDistrict(locationData), keepNumberBedrooms,
                keepNumberParking, keepNumberBathrooms, keepAvailableEquipment);
        for (Property p : properties) {
            if (p instanceof Apartment && p.equals(newApartmentProperty))
                throw new IllegalArgumentException("A property with these specifications already exists in the system");
        }
        properties.add(newApartmentProperty);
        return newApartmentProperty;
    }

    public Property createNewProperty(PropertyType keepPropertyType, double keepAreaProperty,
            double keepDistanceCityCenter, String Location) throws IllegalArgumentException {
        String[] locationData = deleteSpaces(Location);
        Property newLandProperty = new Land(keepPropertyType, keepAreaProperty, keepDistanceCityCenter, locationData[0],
                locationData[locationData.length - 1], locationData[locationData.length - 2],
                locationData[locationData.length - 3], legacyLocationDistrict(locationData));
        for (Property p : properties) {
            if (p instanceof Land && p.equals(newLandProperty))
                throw new IllegalArgumentException("A property with these specifications already exists in the system");
        }
        properties.add(newLandProperty);
        return newLandProperty;
    }

    public List<Property> getPropertyList() {
        return List.copyOf(properties);
    }

    public List<Property> getPropertyListByPropertyType(PropertyType propertyType) throws IllegalArgumentException {
        List<Property> returnList = new ArrayList<>();
        validPropertyType(propertyType);
        for (Property property : properties) {
            if (property.getPropertyType().equals(propertyType.getPropertyType()))
                returnList.add(property);
        }
        return List.copyOf(returnList);
    }

    // may be unnecesary
    private void validPropertyType(PropertyType propertyType) {
        PropertyType possibleTypes[] = PropertyType.values();
        boolean isExistingType = false;
        for (PropertyType pt : possibleTypes) {
            if (propertyType.getPropertyType().equals(pt.getPropertyType())) {
                isExistingType = true;
                break;
            }
        }
        if (!isExistingType)
            throw new IllegalArgumentException("Invalid sun exposure");
    }

    private String legacyLocationDistrict(String[] location) {
        if (location.length == 4) {
            return "Legacy District";
        }
        return location[1];
    }

    private String[] deleteSpaces(String location) {
        String[] locationData = location.replace(", ", ",").split(",");
        if (locationData[locationData.length - 1].length() > 5) {
            locationData[locationData.length - 1] = locationData[locationData.length - 1].replace(" ", "");
        }
        return locationData;
    }

    public String getPropertyID(Property property) {
        String prop = property.getPropertyType();
        int id = 0;
        for (Property p : properties) {
            if (p.getPropertyType().equals(property.getPropertyType()))
                id++;
            if (p.equals(property))
                break;
        }
        return prop + id;
    }
}
