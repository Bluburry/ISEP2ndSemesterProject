package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.application.session.UserSession;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

public class MakeOfferController {
    private AdvertisementRepository advertisementRepository;
    private AdvertisementCollection advertisementCollection;
    private static UserSession userSession;

    public MakeOfferController() {
        advertisementRepository = Repositories.getInstance().getAdvertisementRepository();
        advertisementCollection = new AdvertisementCollection();
        userSession = ApplicationSession.getInstance().getCurrentSession();
    }

    public PropertyType[] getPropertyTypeList() {
        return PropertyType.values();
    }

    public BusinessType[] getBusinessTypeList() {
        return BusinessType.values();
    }

    public String getClientUserEmail() {
        return userSession.getUserEmail();
    }

    public boolean verifyOfferValueLowerSalePrice(Advertisement adchoiceOffer, double keepClientOffer) {
        boolean confirmation1 = AdvertisementRepository.verifyOfferValueLowerSalePrice(adchoiceOffer, keepClientOffer);
        return confirmation1;
    }

    public int verifyOtherOffers(Advertisement adchoiceOffer, double keepClientOffer) {
        int confirmation2 = AdvertisementCollection.verifyOtherOffers(adchoiceOffer, keepClientOffer);
        return confirmation2;
    }

    public int verifyOtherClientOffers(Advertisement adchoiceOffer, String keepClientEmail) {
        int confirmation3 = AdvertisementCollection.verifyOtherClientOffers(adchoiceOffer, keepClientEmail);
        return confirmation3;
    }

    public void addNewOfferToAd(Advertisement adchoiceOffer, double keepClientOffer, String keepClientEmail) {
        advertisementRepository.introduceOfferToAdList(adchoiceOffer, keepClientOffer, keepClientEmail);
    }
}
