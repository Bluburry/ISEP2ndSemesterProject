package pt.ipp.isep.dei.esoft.project.domain;

import pt.isep.lei.esoft.auth.domain.model.Email;

//import javax.print.DocFlavor;
//import java.util.Date;

public class OfferDTO  {

    /**
     * saves the date of the offer
     */
    private String date;

    /**
     * Offer status
     */
    private String status;

    /**
     * Offer value
     */
    private double offer;

    private String clientEmail;

    /**
     * Creates an instance of offer
     *
     * @param status
     * @param offer
     */
    public OfferDTO(String status, double offer, String clientEmail,String date) {
        this.clientEmail = clientEmail;
        this.status = status;
        this.offer = offer;
        this.date = date;
    }

    /**
     * Returns date
     * @return OfferDTO's date
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns status
     * @return OfferDTO's status
     */
    public String getStatus() {
        return status;
    }


    /**
     * Returns offer
     * @return OfferDTO's offer
     */
    public double getOffer() {
        return offer;
    }

    /**
     * Return email
     * @return Client's email
     */
    public String getEmail() {
        return clientEmail;
    }

}
