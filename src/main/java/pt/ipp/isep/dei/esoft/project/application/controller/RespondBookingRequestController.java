package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.application.session.UserSession;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AdvertisementCollection;
import pt.ipp.isep.dei.esoft.project.repository.AdvertisementRepository;
import pt.ipp.isep.dei.esoft.project.repository.PersonRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;

public class RespondBookingRequestController {
    private static UserSession userSession;
    private static AdvertisementCollection advertisementCollection;
    private static AdvertisementRepository advertisementRepository;
    private static PersonRepository personRepository;
    private static ApplicationSession applicationSession;

    public RespondBookingRequestController(){
        personRepository = Repositories.getInstance().getPersonRepository();
        advertisementRepository = Repositories.getInstance().getAdvertisementRepository();
        advertisementCollection=new AdvertisementCollection();
        userSession = ApplicationSession.getInstance().getCurrentSession();
        applicationSession = ApplicationSession.getInstance();
    }
    public String getAgentEmail() {
        return userSession.getUserEmail();
    }
    public String getAgentName(String keepAgentEmail) {
        return personRepository.getPersonNameFromEmail(keepAgentEmail);
    }
    public double getAgentPhone(String keepAgentEmail) {
        return personRepository.getPersonPhoneFromEmail(keepAgentEmail);
    }
    public List<AdvertisementDTO> getAdsListAgentDTO(String keepAgentEmail) {
        return advertisementCollection.getAdsListAgentDTO(keepAgentEmail);
    }
    public void changeVisitStatusRejected(AdvertisementDTO keepAdVisitDTO, Visit keepVisitRequest) {
        advertisementRepository.changeVisitStatusRejected(AdvertisementMapper.fromDTOtoAD(keepAdVisitDTO), keepVisitRequest);
    }
    public void changeVisitStatusAccepted(AdvertisementDTO keepAdVisitDTO, Visit keepVisitRequest) {
        advertisementRepository.changeVisitStatusAccepted(AdvertisementMapper.fromDTOtoAD(keepAdVisitDTO), keepVisitRequest);
    }
    public String getClientEmailFromVisit(Visit keepVisitRequest) {
        return personRepository.getPersonByEmail(advertisementCollection.getEmailFromVisit(keepVisitRequest)).getEmail().getEmail();
    }
    public String getClientNameFromEmail(String keepEmailClientVisitRequest) {
        return personRepository.getClientNameFromEmail(keepEmailClientVisitRequest);
    }

    public void sendMessageVisitRejected(String keepEmailClientVisitRequest, String keepNameClientVisitRequest
            , String keepAgentEmail, String keepAgentName, double keepAgentPhone, String emailDomain) {
        SendEmailRespondBookingRequestDEI.sendMessageVisitRejected( keepEmailClientVisitRequest,  keepNameClientVisitRequest
                ,  keepAgentEmail,  keepAgentName,  keepAgentPhone,  emailDomain);
    }

    public String verifyEmailPlatform() {
        String emailPlatform = Config.getInstance().getEmailFormat();
        if ((emailPlatform.compareTo("GMAIL")==0)||(emailPlatform.compareTo("DEI")==0)){
            return emailPlatform;
        } else {
            return "No Email Domain installed";
        }
    }

    public void sendMessageVisitAccepted(String keepEmailClientVisitRequest, String keepNameClientVisitRequest
            , String keepAgentEmail, String keepAgentName, double keepAgentPhone, String emailDomain) {
        SendEmailRespondBookingRequestDEI.sendMessageVisitAccepted(keepEmailClientVisitRequest,  keepNameClientVisitRequest
                ,  keepAgentEmail,  keepAgentName,  keepAgentPhone,  emailDomain);


    }
}
