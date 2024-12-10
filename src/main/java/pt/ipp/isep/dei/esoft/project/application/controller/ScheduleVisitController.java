package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.application.session.UserSession;

import java.util.List;

public class ScheduleVisitController {
    private AdvertisementRepository advertisementRepository;
    private static UserSession userSession;

    public ScheduleVisitController(){
        advertisementRepository=Repositories.getInstance().getAdvertisementRepository();
        userSession = ApplicationSession.getInstance().getCurrentSession();
    }


    public static List<AdvertisementDTO> getAdvertismentDTOListSorted(PropertyType keepPropertyTypeChoice, BusinessType keepBusinessTypeChoice) {
        return AdvertisementCollection.getAdvertisementDTOListSorted(keepPropertyTypeChoice, keepBusinessTypeChoice);
    }
    public Advertisement fromDTOtoAD(AdvertisementDTO adChoiceDTO) {
               return AdvertisementCollection.fromDTOtoAD(adChoiceDTO);
    }
    public BusinessType[] getBusinessTypeList() {
        return BusinessType.values();
    }

    public PropertyType[] getPropertyTypeList() {
        return PropertyType.values();
    }

    public  Boolean getVerifyVisitTimeFree(AdvertisementDTO adchoiceVisit, int year, int month, int day, int hour) {
        Advertisement ad=fromDTOtoAD(adchoiceVisit);
        return AdvertisementCollection.getVerifyVisitTimeFree(ad,  year,  month,  day,  hour);
    }
    public void introduceVisitToAdList(AdvertisementDTO adchoiceVisit, int year, int month, int day, int hour,String keepClientEmail) {
        Advertisement ad=fromDTOtoAD(adchoiceVisit);
        advertisementRepository.introduceVisitToAdList(ad, year,month, day, hour, keepClientEmail);
    }


    public String getClientUserEmail() {
            return userSession.getUserEmail();
    }
}
