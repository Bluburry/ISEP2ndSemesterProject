package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;

public interface Property {

	double getArea();

	double getDistance();

	String getPropertyType();

	List<String> getPhotographs();

	void validatePropertyArguments(PropertyType propertyType, double area, double distance, List<String> photographs);

	boolean validPropertyType(PropertyType propertyType);

	boolean validatePhotos(List<String> photographs);

	String toString();

	boolean equals(Object o);
}