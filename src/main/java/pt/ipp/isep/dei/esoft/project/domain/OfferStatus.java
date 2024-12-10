package pt.ipp.isep.dei.esoft.project.domain;

public enum OfferStatus {
        PENDING("PENDING"),
        APPROVED("APPROVED"),
        DENIED("DENIED");

        private final String status;

        private OfferStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

}
