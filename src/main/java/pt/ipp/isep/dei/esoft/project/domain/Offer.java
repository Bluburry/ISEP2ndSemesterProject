package pt.ipp.isep.dei.esoft.project.domain;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import pt.isep.lei.esoft.auth.domain.model.Email;

public class Offer implements Comparable<Offer>, Serializable {

    /**
     * saves the date of the offer
     */
    private Date date;

    /**
     * Offer status
     */
    private OfferStatus status;

    /**
     * Offer value
     */
    private double offer;

    private Email clientEmail;

    /**
     * Creates an instance of offer
     * 
     * @param status
     * @param offer
     */
    public Offer(double offer, String clientEmail) throws IllegalArgumentException {
        /*
         * try {
         * this.clientEmail = new Email(clientEmail);
         * } catch (Exception e) {
         * throw new IllegalArgumentException("Invalid offer, email is not accepted.");
         * }
         */
        this.clientEmail = new Email(clientEmail);
        this.status = OfferStatus.PENDING;
        this.offer = offer;
        date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public String getStatus() {
        return status.getStatus();
    }

    public void setStatus(OfferStatus status) {
        validOfferStatus(status);
        this.status = status;
    }

    public double getOffer() {
        return offer;
    }

    public Email getEmail() {
        return clientEmail;
    }

    private void validOfferStatus(OfferStatus offerStatus) {
        OfferStatus possibleTypes[] = OfferStatus.values();
        boolean exists = false;
        for (OfferStatus of : possibleTypes) {
            if (offerStatus.getStatus().equals(of.getStatus())) {
                exists = true;
                break;
            }
        }
        if (!exists)
            throw new IllegalArgumentException("Invalid offer, status is not of an acceptable type.");
    }

    public boolean equals(Email clientEmail) {
        return this.clientEmail.equals(clientEmail);
    }

    public boolean canClientMakeNewOffer(Email clientEmail) {
        if (this.equals(clientEmail) && !this.getStatus().equals(OfferStatus.DENIED.getStatus()))
            return false;
        else
            return true;
    }

    @Override
    public int compareTo(Offer o) {
        if (this.getOffer() < o.getOffer())
            return -1;
        else if (this.getOffer() > o.getOffer())
            return 1;
        else
            return 0;
    }

    private void writeObject(ObjectOutputStream opst) throws IOException {
        opst.writeObject(clientEmail.getEmail());
        opst.writeObject(date);
        opst.writeDouble(offer);
        opst.writeObject(status);
    }

    private void readObject(ObjectInputStream ipst) throws IOException, ClassNotFoundException, ParseException {
        clientEmail = new Email((String) ipst.readObject());
        date = (Date) ipst.readObject();
        offer = ipst.readDouble();
        status = (OfferStatus) ipst.readObject();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Offer))
            return false;
        else {
            Offer v = (Offer) o;
            if (this.offer == v.getOffer() && this.clientEmail.getEmail().equals(v.getEmail().getEmail()))
                return true;
            else
                return false;
        }
    }

    @Override
    public String toString() {
        return String.format("Status: %s | Offer : %.2f | Email: %s\n", status.getStatus(), offer,
                clientEmail.getEmail());
    }
}
