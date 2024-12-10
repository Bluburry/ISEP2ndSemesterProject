package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.AdvertisementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdvertisementMapper {
    public static AdvertisementDTO toDTO(Advertisement ad) {

        Request req = ad.getRequest();
        Property prop = req.getProperty();

        double keepArea = 0;
        double keepdistance = 0;
        int keepNumberBedrooms = 0;
        int keepNumberParking = 0;
        int keepNumberBathrooms = 0;
        String keepAvailableEquipment = null;
        String keepStreet=null, keepZip=null, keepState=null, keepCity=null, keepDistrict=null;
        String propType = prop.getPropertyType();
        PropertyType propt=null;

            List<String> keepPhotographs=null;
//        if ((propType.compareTo(PropertyType.HOUSE.getPropertyType()) == 0) || ((propType.compareTo(PropertyType.APARTMENT.getPropertyType()) == 0))) {
        if (prop instanceof Land) {
            Land propL = (Land) prop;
            propt= PropertyType.LAND;
            keepArea = propL.getArea();
            keepdistance = propL.getDistance();
            keepStreet = propL.getStreet();
            keepZip = propL.getZip();
            keepState = propL.getState();
            keepCity = propL.getCity();
            keepDistrict = propL.getDistrict();
            keepPhotographs = propL.getPhotographs();
        }

        if (prop instanceof Apartment) {
            Apartment propA = (Apartment) prop;
            propt= PropertyType.APARTMENT;
            keepArea = propA.getArea();
            keepdistance = propA.getDistance();
            keepNumberBedrooms = propA.getNumberBedrooms();
            keepNumberParking = propA.getNumberParking();
            keepNumberBathrooms = propA.getNumberBathrooms();
            keepAvailableEquipment = propA.getAvailableEquipment();
            keepStreet = propA.getStreet();
            keepZip = propA.getZip();
            keepState = propA.getState();
            keepCity = propA.getCity();
            keepDistrict = propA.getDistrict();
            keepPhotographs = prop.getPhotographs();
        }

        boolean keepBasement = false;
        boolean keepLoft = false;
        String keepSun = null;
        SunExposure sun = null;
        if (prop instanceof House) {
            House propH = (House) prop;
            propt= PropertyType.HOUSE;
            keepArea = propH.getArea();
            keepdistance = propH.getDistance();
            keepNumberBedrooms = propH.getNumberBedrooms();
            keepNumberParking = propH.getNumberParking();
            keepNumberBathrooms = propH.getNumberBathrooms();
            keepAvailableEquipment = propH.getAvailableEquipment();
            keepStreet = propH.getStreet();
            keepZip = propH.getZip();
            keepState = propH.getState();
            keepCity = propH.getCity();
            keepDistrict = propH.getDistrict();
            keepPhotographs = prop.getPhotographs();
            keepBasement = propH.getBasement();
            keepLoft = propH.getLoft();
            keepSun = propH.getSunExposure();
            switch (keepSun) {
                case ("NORTH"):
                    sun = SunExposure.NORTH;
                    break;
                case ("SOUTH"):
                    sun = SunExposure.SOUTH;
                    break;
                case ("EAST"):
                    sun = SunExposure.EAST;
                    break;
                case ("WEST"):
                    sun = SunExposure.WEST;
                    break;
            }

        }
        BusinessType BusType = req.getBusinessType();
        double keepSalePrice = 0;
        double keepRentPrice = 0;
        int keepDuractionOfContract = 0;
        if (req instanceof RequestSale) {
            RequestSale reqS = (RequestSale) req;
            keepSalePrice = reqS.getSalePrice();
        }
        if (req instanceof RequestLease) {
            RequestLease reqL = (RequestLease) req;
            keepRentPrice = reqL.getRentPrice();
            keepDuractionOfContract = reqL.getContractDuration();
        }
        Date keepDate = ad.getDate();

        List<OfferDTO> offers=new ArrayList<>();
        for (Offer o: ad.getOffers()) {
            offers.add(OfferMapper.toDTO(o));
        }
        List<Visit> visits=ad.getVisits();

        AdvertisementDTO DA = new AdvertisementDTO(keepArea, keepdistance, propt, BusType,
                keepNumberBedrooms, keepNumberParking, keepNumberBathrooms, keepAvailableEquipment, keepBasement, keepLoft,
                sun, keepStreet, keepZip, keepState, keepCity, keepDistrict, keepPhotographs, keepSalePrice, keepRentPrice,
                keepDuractionOfContract, keepDate,offers, visits);

        return DA;
    }


    public static Advertisement fromDTOtoAD(AdvertisementDTO adDTO) {
        AdvertisementRepository advertisementRepository = Repositories.getInstance().getAdvertisementRepository();
        List<Advertisement> ads = advertisementRepository.getAdvertisements();
        double areaDTO = adDTO.getArea();
        double distanceDTO = adDTO.getDistance();
        int numberBedroomsDTO = adDTO.getNumberBedrooms();
        int numberParkingDTO = adDTO.getNumberParking();
        int numberBathroomsDTO = adDTO.getNumberBathrooms();
        String availableEquipmentDTO = adDTO.getAvailableEquipment();
        boolean basementDTO = adDTO.getBasement();
        boolean loftDTO = adDTO.getLoft();
        SunExposure sunDTO = adDTO.getSun();
        String streetDTO = adDTO.getStreet();
        String zipDTO = adDTO.getZip();
        String stateDTO = adDTO.getState();
        String cityDTO = adDTO.getCity();
        String districtDTO = adDTO.getDistrict();
        List<String> photographsDTO = adDTO.getPhotographs();
        double salePriceDTO = adDTO.getSalePrice();
        double rentPriceDTO = adDTO.getRentPrice();
        int duractionOfContractDTO = adDTO.getDurationOfContract();
        for (Advertisement ad : ads) {
            Request req = ad.getRequest();
            Property prop = req.getProperty();
//            String propt = prop.getPropertyType();
            double areaAD = 0;
            double distanceAD = 0;
            String streetAD = null;
            String zipAD = null;
            String stateAD = null;
            String cityAD = null;
            String districtAD = null;
            List<String> photographsAD = null;
//            if (propt.compareTo(PropertyType.LAND.getPropertyType())==0){
            if (prop instanceof Land) {
                Land propL = (Land) prop;
                areaAD = propL.getArea();
                distanceAD = propL.getDistance();
                streetAD = propL.getStreet();
                zipAD = propL.getZip();
                stateAD = propL.getState();
                cityAD = propL.getCity();
                districtAD = propL.getDistrict();
                photographsAD = propL.getPhotographs();
            }
            int numberBedroomsAD = 0;
            int numberParkingAD = 0;
            int numberBathroomsAD = 0;
            String availableEquipmentAD = null;
//            if (propt.compareTo(PropertyType.APARTMENT.getPropertyType())==0){
            if (prop instanceof Apartment) {
                Apartment propA = (Apartment) prop;
                areaAD = propA.getArea();
                distanceAD = propA.getDistance();
                streetAD = propA.getStreet();
                zipAD = propA.getZip();
                stateAD = propA.getState();
                cityAD = propA.getCity();
                districtAD = propA.getDistrict();
                photographsAD = propA.getPhotographs();
                numberBedroomsAD = propA.getNumberBedrooms();
                numberParkingAD = propA.getNumberParking();
                numberBathroomsAD = propA.getNumberBathrooms();
                availableEquipmentAD = propA.getAvailableEquipment();
            }
            boolean basementAD = false;
            boolean loftAD = false;
            String sunAD = null;
//            if (propt.compareTo(PropertyType.HOUSE.getPropertyType()) == 0) {
            if (prop instanceof House) {
                House propH = (House) prop;
                areaAD = propH.getArea();
                distanceAD = propH.getDistance();
                streetAD = propH.getStreet();
                zipAD = propH.getZip();
                stateAD = propH.getState();
                cityAD = propH.getCity();
                districtAD = propH.getDistrict();
                photographsAD = propH.getPhotographs();
                numberBedroomsAD = propH.getNumberBedrooms();
                numberParkingAD = propH.getNumberParking();
                numberBathroomsAD = propH.getNumberBathrooms();
                availableEquipmentAD = propH.getAvailableEquipment();
                basementAD = propH.getBasement();
                loftAD = propH.getBasement();
                sunAD = propH.getSunExposure().toString();
            }

//            if (propt.compareTo(PropertyType.HOUSE.getPropertyType()) == 0) {
            BusinessType busType = req.getBusinessType();
            double salePriceAD = 0;
            double rentPriceAD = 0;
            int duractionOfContractAD = 0;
            if (req instanceof RequestSale) {
                RequestSale reqS = (RequestSale) req;
                salePriceAD = reqS.getSalePrice();
            }
            if (req instanceof RequestLease) {
                RequestLease reqL = (RequestLease) req;
                rentPriceAD = reqL.getRentPrice();
                duractionOfContractAD = reqL.getContractDuration();
            }
            if ((areaDTO == areaAD) && (distanceDTO == distanceAD) && (numberBedroomsDTO == numberBedroomsAD)
                    && (numberParkingDTO == numberParkingAD) && (numberBathroomsDTO == numberBathroomsAD)
                    && (basementDTO == basementAD)
                    && (streetDTO.compareTo(streetAD) == 0) && (zipDTO.compareTo(zipAD) == 0)
                    && (stateDTO.compareTo(stateAD) == 0) && (cityDTO.compareTo(cityAD) == 0) && (districtDTO.compareTo(districtAD) == 0)
                    && (salePriceDTO == salePriceAD) && (rentPriceDTO == rentPriceAD)
                    && (duractionOfContractDTO == duractionOfContractAD)) {
                return ad;

            }
        }
        return null;
    }
}