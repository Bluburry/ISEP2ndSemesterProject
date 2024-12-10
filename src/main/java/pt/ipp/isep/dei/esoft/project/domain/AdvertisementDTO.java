package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Date;
import java.util.List;

public class AdvertisementDTO {

    private final double keepArea;
    private final double keepdistance;
    private final PropertyType keepPropertyType;
    private final BusinessType keepBusinessType;
    private final int keepNumberBedrooms;
    private final int numberParking ;
    private final int numberBathrooms ;
    private final String availableEquipment ;
    private final Boolean basement ;
    private final Boolean loft ;
    private final SunExposure sun ;
    private final String keepStreet;
    private final String keepZip;
    private final String keepState;
    private final String keepCity;
    private final String keepDistrict;
    private final List<String> keepPhotographs;

    private final List<OfferDTO> offers;
    private final List<Visit> visits;
    private final double keepSalePrice;
    private final double keepRentPrice;
    private final int keepDurationOfContract;
    private final Date keepDate;

    public AdvertisementDTO(double keepArea, double keepdistance, PropertyType keepPropertyType, BusinessType keepBusinessType,
                            int keepNumberBedrooms, int numberParking, int numberBathrooms,
                            String availableEquipment,  Boolean basement, Boolean loft, SunExposure sun,
                            String keepStreet, String keepZip, String keepState, String keepCity, String keepDistrict,
                            List<String> keepPhotographs, double keepSalePrice, double keepRentPrice, int keepDurationOfContract,
                            Date keepDate,List<OfferDTO> offers, List<Visit> visits){
        this.keepArea=keepArea;
        this.keepdistance =keepdistance;
        this.keepPropertyType =keepPropertyType;
        this.keepBusinessType=keepBusinessType;
        this.keepNumberBedrooms =keepNumberBedrooms;
        this.numberParking =numberParking;
        this.numberBathrooms =numberBathrooms;
        this.availableEquipment =availableEquipment;
        this.basement =basement;
        this.loft =loft;
        this.sun =sun;
        this.keepStreet =keepStreet;
        this.keepZip =keepZip;
        this.keepState =keepState;
        this.keepCity =keepCity;
        this.keepDistrict =keepDistrict;
        this.keepPhotographs =keepPhotographs;
        this.keepSalePrice =keepSalePrice;
        this.keepRentPrice=keepRentPrice;
        this.keepDurationOfContract= keepDurationOfContract;
        this.keepDate =keepDate;
        this.offers=offers;
        this.visits=visits;
    }

    public double getArea() {
        return keepArea;
    }

    public double getDistance() {
        return keepdistance;
    }

    public PropertyType getPropertyType() {
        return keepPropertyType;
    }

    public BusinessType getBusinessType() {
        return keepBusinessType;
    }

    public int getNumberBedrooms(){
        return keepNumberBedrooms;
    }

    public String getStreet() {
        return keepStreet;
    }

    public String getZip() {
        return keepZip;
    }

    public String getState() {
        return keepState;
    }

    public String getCity() {
        return keepCity;
    }

    public String getDistrict() {
        return keepDistrict;
    }

    public List<String> getPhotographs() {
        return keepPhotographs;
    }

    public double getSalePrice() {
        return keepSalePrice;
    }

    public Date getDate() {
        return keepDate;
    }

    public int getNumberParking() {
        return numberParking;
    }

    public int getNumberBathrooms() {
        return numberBathrooms;
    }

    public String getAvailableEquipment() {
        return availableEquipment;
    }

    public Boolean getBasement() {
        return basement;
    }

    public Boolean getLoft() {
        return loft;
    }

    public SunExposure getSun() {
        return sun;
    }

    public double getRentPrice() {
        return keepRentPrice;
    }

    public List<OfferDTO> getOffers() {
        return offers;
    }

    public int getDurationOfContract() {
        return keepDurationOfContract;
    }

    public List<Visit> getVisits() {
        return visits;
    }
}
