package pt.ipp.isep.dei.esoft.project.domain;

public enum VisitStatus {

    PENDING("PENDING"),
    ACCEPTED("ACCEPTED"),
    REJECTED("REJECTED");

    private final String description;

    private VisitStatus(String description) {
        this.description = description;
    }

    public String getVisitStatus() {
        return description;
    }

}
