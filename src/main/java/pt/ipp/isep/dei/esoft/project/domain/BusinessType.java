package pt.ipp.isep.dei.esoft.project.domain;

public enum BusinessType {

    LEASE("LEASE"),
    SALE("SALE");

    private final String description;

    private BusinessType(String description) {
        this.description = description;
    }

    public String getBusinessType() {
        return description;
    }
}
