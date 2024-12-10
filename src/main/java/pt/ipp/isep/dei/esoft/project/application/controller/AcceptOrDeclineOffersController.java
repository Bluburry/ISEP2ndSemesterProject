package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AdvertisementCollection;
import pt.ipp.isep.dei.esoft.project.repository.AdvertisementRepository;
import pt.ipp.isep.dei.esoft.project.repository.PersonRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class AcceptOrDeclineOffersController {

    /**
     * Access to the class Repositories
     */
    private Repositories repositories;

    /**
     * ccess to the class AdvertisementRepository
     */
    private AdvertisementRepository advertisementRepository;

    /**
     * Access to the class AdvertisementCollection
     */
    private AdvertisementCollection advertisementCollection;

    /**
     * Access to the class PersonRepository
     */
    private PersonRepository personRepository;

    /**
     * Access to the class ApplicationSession
     */
    private ApplicationSession applicationSession;

    /**
     * Access to the class OfferMapper
     */
    private OfferMapper offerMapper;

    /**
     * Access to the class AdvertisementMapper
     */
    private  AdvertisementMapper advertisementMapper;

    /**
     * Creates an instance of AcceptOrDeclineOffersController
     * where gives the repositories an instance of Repositories
     */
    public AcceptOrDeclineOffersController(){
        repositories=Repositories.getInstance();
        advertisementRepository=repositories.getAdvertisementRepository();
        applicationSession=ApplicationSession.getInstance();
        personRepository= repositories.getPersonRepository();
    }

    /**
     * Returns List<AdvertisementDTO> that have a Properties List with Offers
     * @return List<AdvertisementDTO>
     */
    public List<AdvertisementDTO> listedPropertiesWithOffers(){
        String agentEmail=applicationSession.getCurrentSession().getUserEmail();
        Employee agent=(Employee) personRepository.getPersonByEmail(agentEmail);
        List<Advertisement> advertisements=advertisementRepository.getAdvertisementsForAgent(agent);
        List<AdvertisementDTO> dtos=new ArrayList<>();
        List<OfferDTO> oDtos= new ArrayList<>();
        for (Advertisement a:advertisements) {
            dtos.add(AdvertisementMapper.toDTO(a));
            for(Offer o:a.getOffers()){
                oDtos.add(offerMapper.toDTO(o));
            }
            advertisementCollection.sortOffersByValue(oDtos);
        }
        advertisementCollection.sortListByDate(dtos);
        return dtos;
    }

    /**
     * Returns OfferDTO with the offer accepted
     * @param ad AdvertisementDTO
     * @param offerDTO OfferDTO
     * @return OfferDTO
     */
    public OfferDTO acceptOffer(AdvertisementDTO ad,OfferDTO  offerDTO) throws ParseException {
        Advertisement advertisement = advertisementMapper.fromDTOtoAD(ad);
        Offer offer = offerMapper.fromDTOtoAD(offerDTO,advertisement);
        sendEmailToUser(offer.getEmail().getEmail(),true);
        for(OfferDTO offers:ad.getOffers()){
            if(!offer.getEmail().getEmail().equals(offers.getEmail())){
                sendEmailToUser(offers.getEmail(),false);
            }
        }
        offer.setStatus(OfferStatus.APPROVED);

        return offerDTO;
    }

    /**
     * Returns OfferDTO with the offer declined
     * @param ad AdvertisementDTO
     * @param offerDTO offerDTO
     * @return OfferDTO
     */
    public OfferDTO declineOffer(AdvertisementDTO ad,OfferDTO  offerDTO) throws ParseException {
        Advertisement advertisement = advertisementMapper.fromDTOtoAD(ad);
        Offer offer = offerMapper.fromDTOtoAD(offerDTO,advertisement);
        sendEmailToUser(offer.getEmail().getEmail(),false);
        offer.setStatus(OfferStatus.DENIED);
        return offerDTO;
    }

    /**
     * Sends email to User
     * @param email String
     * @param offerDecision boolean
     */
    public void sendEmailToUser(String email, boolean offerDecision){
        try {
            File myObj = new File(email + ".txt");
            if (myObj.createNewFile()) {
                System.out.println("Email sent to: " + email);
            } else {
                System.out.println("Email already sent.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        if(offerDecision==true){
            try {
                FileWriter myWriter = new FileWriter(email + ".txt");
                myWriter.write("Your offer was accepted!");
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }else{
            try {
                FileWriter myWriter = new FileWriter(email + ".txt");
                myWriter.write("Your offer was decline!");
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
}
