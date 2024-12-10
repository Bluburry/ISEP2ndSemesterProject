package pt.ipp.isep.dei.esoft.project.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OfferMapper {
    /**
     * Transforms Offer into OfferDTO
     * @param offer
     * @return OfferDTO
     */
    public static OfferDTO toDTO(Offer offer) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");

        String dateString = dateFormat.format(offer.getDate());

        return new OfferDTO(offer.getStatus(), offer.getOffer(), offer.getEmail().getEmail(),
                dateString);
    }

    /**
     * Transforms OfferDTO into Offer
     * @param offerDTO
     * @param advertisement
     * @return Offer
     * @throws ParseException
     */
    public static Offer fromDTOtoAD(OfferDTO offerDTO,Advertisement advertisement ) throws ParseException {
        Offer offer=null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date date2=dateFormat.parse(offerDTO.getDate());
        for (Offer offer1:advertisement.getOffers()) {
            String date1String=dateFormat.format(offer1.getDate());
            Date date1 = dateFormat.parse(date1String);
            if(offer1.getEmail().getEmail().equals(offerDTO.getEmail()) && offer1.getOffer()== offerDTO.getOffer()
                    && offer1.getStatus().equals(offerDTO.getStatus()) && date1.compareTo(date2)==0){
                offer=offer1;
            }
        }
        return offer;
    }
}