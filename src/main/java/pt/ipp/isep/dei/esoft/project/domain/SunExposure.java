package pt.ipp.isep.dei.esoft.project.domain;

public enum SunExposure {

    NORTH("NORTH"),
    SOUTH("SOUTH"),
    EAST("EAST"),
    WEST("WEST");

    private final String description;

    private SunExposure(String description) {
        this.description = description;
    }

    public String getSunExposure() {
        return description;
    }
}