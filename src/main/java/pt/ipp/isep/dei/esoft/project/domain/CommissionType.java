package pt.ipp.isep.dei.esoft.project.domain;

public enum CommissionType {
    
    FIXED("FIXED"),
    PERCENTAGE("PERCENTAGE");

    private final String description;

    private CommissionType(String description) {
        this.description = description;
    }

    public String getCommissionType() {
        return description;
    }
}