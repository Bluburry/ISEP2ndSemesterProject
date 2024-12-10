package pt.ipp.isep.dei.esoft.project.domain;

public enum PropertyType {
    
    LAND("LAND"),
    APARTMENT("APARTMENT"),
    HOUSE("HOUSE");

    private final String description;

    private PropertyType(String description) {
        this.description = description;
    }

    public String getPropertyType() {
        return description;
    }
}
