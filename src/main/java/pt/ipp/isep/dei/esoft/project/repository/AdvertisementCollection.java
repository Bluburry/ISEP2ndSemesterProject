package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.exceptions.AcceptedOfferNotFoundException;

import java.util.*;


public class AdvertisementCollection {

    private Repositories repositories;

    private AdvertisementRepository advertisementRepository;
    private PersonRepository personRepository;

    private static AdvertisementMapper advertisementMapper;

    public AdvertisementCollection(){
        repositories=Repositories.getInstance();
        advertisementRepository=repositories.getAdvertisementRepository();
        personRepository=repositories.getPersonRepository();
    }

    public static List<AdvertisementDTO> getAdvertisementDTOListSorted(PropertyType keepPropertyTypeChoice, BusinessType keepBusinessTypeChoice) {
        AdvertisementRepository advertisementRepository = Repositories.getInstance().getAdvertisementRepository();

        List<Advertisement> ads = advertisementRepository.getAdvertisements();
//        listar(ads);
        System.out.println();
        List<AdvertisementDTO> keepAdDTOList = createNewAdvertAllInformationList(ads, keepPropertyTypeChoice, keepBusinessTypeChoice);
//        listar1(keepAdDTOList);
        System.out.println();
        List<AdvertisementDTO> keepAdDTOListSorted = sortAdVisitListByDate(keepAdDTOList);
//        listar1(keepAdDTOListSorted);
        return keepAdDTOListSorted;
    }

    public static List<AdvertisementDTO> createNewAdvertAllInformationList(List<Advertisement> ads, PropertyType keepPropertyTypeChoice, BusinessType keepBusinessTypeChoice) {

        List<AdvertisementDTO> adsDTOList = new ArrayList<>();
        for (Advertisement ad : ads) {
            String adPropertyType = ad.getRequest().getProperty().getPropertyType();
            String choicePropertyType = keepPropertyTypeChoice.getPropertyType();
            String adBusinessType = ad.getRequest().getBusinessType().getBusinessType();
            String choiceBusinessType = keepBusinessTypeChoice.getBusinessType();
            if ((adPropertyType.compareTo(choicePropertyType) == 0) && (adBusinessType.compareTo(choiceBusinessType) == 0)) {
                AdvertisementDTO adDTO = AdvertisementMapper.toDTO(ad);
                adsDTOList.add(adDTO);
            }
        }
        return adsDTOList;
    }


    /**
     * Creates a List<AdvertisementDTO> from a List<Advertisement>
     * @param ads List<Advertisement>
     * @return List<AdvertisementDTO>
     */
    public static List<AdvertisementDTO> createNewAdvertList(List<Advertisement> ads) {

        List<AdvertisementDTO> adsDTOList = new ArrayList<>();
        for (Advertisement ad : ads) {
            AdvertisementDTO adDTO = AdvertisementMapper.toDTO(ad);
            adsDTOList.add(adDTO);
        }
        return adsDTOList;
    }
    public static List<AdvertisementDTO> sortAdVisitListByDate(List<AdvertisementDTO> adsVisitList) {
        Comparator<AdvertisementDTO> c1 = new Comparator<>() {
            public int compare(AdvertisementDTO a1, AdvertisementDTO a2) {
                Date d1 = a1.getDate();
                Date d2 = a2.getDate();
                if (d1.compareTo(d2) < 0) {
                    return -1;
                } else if (d1.compareTo(d2) > 0) {
                    return 1;
                } else {
                    return 0;
                }
            }

        };
        adsVisitList.sort(c1);
        return adsVisitList;
    }
    /**
     * Sorts a List<AdvertisementDTO> by date
     * @param adsVisitList List<AdvertisementDTO>
     * @return List<AdvertisementDTO>
     */
    public static List<AdvertisementDTO> sortListByDate(List<AdvertisementDTO> adsVisitList) {
        Comparator<AdvertisementDTO> c1 = new Comparator<>() {
            public int compare(AdvertisementDTO a1, AdvertisementDTO a2) {
                Date d1 = a1.getDate();
                Date d2 = a2.getDate();
                if (d1.compareTo(d2)<0 ) {
                    return -1;
                } else if (d1.compareTo(d2)>0) {
                    return 1;
                } else {
                    return 0;
                }
            }

        };
        adsVisitList.sort(c1);
        return adsVisitList;
    }
    /**
     * Sorts a List<OfferDTO> by value
     * @param osList List<OfferDTO>
     * @return List<OfferDTO>
     */
    public static List<OfferDTO> sortOffersByValue(List<OfferDTO> osList) {
        Comparator<OfferDTO> c1 = new Comparator<>() {
            public int compare(OfferDTO o1, OfferDTO o2) {
                Double d1 = o1.getOffer();
                Double d2 = o2.getOffer();
                if (d1.compareTo(d2)<0 ) {
                    return -1;
                } else if (d1.compareTo(d2)>0) {
                    return 1;
                } else {
                    return 0;
                }
            }

        };
        osList.sort(c1);
        return osList;
    }
    /**
     * Sorts a List<AdvertisementDTO> by Rental Price
     * @param adsList List<AdvertisementDTO>
     * @return List<AdvertisementDTO>
     */
    public static List<AdvertisementDTO> sortListByRentalPrice(List<AdvertisementDTO> adsList) {
        Comparator <AdvertisementDTO> rentalPriceSort = new Comparator<>() {
            @Override
            public int compare(AdvertisementDTO o1, AdvertisementDTO o2) {

                return Double.compare(o1.getRentPrice(),o2.getRentPrice());

            }
        };
        adsList.sort(rentalPriceSort);
        return adsList;
    }
    /**
     * Sorts a List<AdvertisementDTO> by State Sort
     * @param adsList List<AdvertisementDTO>
     * @return List<AdvertisementDTO>
     */
    public static List<AdvertisementDTO> sortListByStateSort(List<AdvertisementDTO> adsList) {
        Comparator <AdvertisementDTO> stateSort = new Comparator<>() {
            @Override
            public int compare(AdvertisementDTO o1, AdvertisementDTO o2) {
                String state1 = null;
                String state2 = null;
                Advertisement ot1 = advertisementMapper.fromDTOtoAD(o1);
                Advertisement ot2 = advertisementMapper.fromDTOtoAD(o2);

                if (ot1.getRequest().getProperty() instanceof House) {
                    House propH1 = (House) ot1.getRequest().getProperty();
                    state1 = propH1.getState();
                } else if (ot1.getRequest().getProperty() instanceof Apartment) {
                    Apartment propA1 = (Apartment) ot1.getRequest().getProperty();
                    state1 = propA1.getState();
                } else if (ot1.getRequest().getProperty() instanceof Land) {
                    Land propL1 = (Land) ot1.getRequest().getProperty();
                    state1 = propL1.getState();
                }

                if (ot2.getRequest().getProperty() instanceof House) {
                    House propH2 = (House) ot2.getRequest().getProperty();
                    state2 = propH2.getState();
                } else if (ot2.getRequest().getProperty() instanceof Apartment) {
                    Apartment propA2 = (Apartment) ot2.getRequest().getProperty();
                    state2 = propA2.getState();
                } else if (ot2.getRequest().getProperty() instanceof Land) {
                    Land propL2 = (Land) ot2.getRequest().getProperty();
                    state2 = propL2.getState();
                }

                return state1.compareTo(state2);
            }
        };
        adsList.sort(stateSort);
        for(AdvertisementDTO advertisementDTO: adsList){
            System.out.println(advertisementDTO.getState());
        }
        return adsList;
    };

    /**
     * Sorts a List<AdvertisementDTO> by City Sort
     * @param adsList List<AdvertisementDTO>
     * @return List<AdvertisementDTO>
     */
    public static List<AdvertisementDTO> sortListByCitySort(List<AdvertisementDTO> adsList) {
        Comparator<AdvertisementDTO> citySort = new Comparator<>() {
            @Override
            public int compare(AdvertisementDTO o1, AdvertisementDTO o2) {
                String city1 = null;
                String city2 = null;
                Advertisement ot1 = advertisementMapper.fromDTOtoAD(o1);
                Advertisement ot2 = advertisementMapper.fromDTOtoAD(o2);

                if (ot1.getRequest().getProperty() instanceof House) {
                    House propH1 = (House) ot1.getRequest().getProperty();
                    city1 = propH1.getCity();
                } else if (ot1.getRequest().getProperty() instanceof Apartment) {
                    Apartment propA1 = (Apartment) ot1.getRequest().getProperty();
                    city1 = propA1.getCity();
                } else if (ot1.getRequest().getProperty() instanceof Land) {
                    Land propL1 = (Land) ot1.getRequest().getProperty();
                    city1 = propL1.getCity();
                }

                if (ot2.getRequest().getProperty() instanceof House) {
                    House propH2 = (House) ot2.getRequest().getProperty();
                    city2 = propH2.getCity();
                } else if (ot2.getRequest().getProperty() instanceof Apartment) {
                    Apartment propA2 = (Apartment) ot2.getRequest().getProperty();
                    city2 = propA2.getCity();
                } else if (ot2.getRequest().getProperty() instanceof Land) {
                    Land propL2 = (Land) ot2.getRequest().getProperty();
                    city2 = propL2.getCity();
                }

                return city1.compareTo(city2);
            }
        };

        adsList.sort(citySort);
        return adsList;
    }


    /**
     * Sorts a List<AdvertisementDTO> by Sale Price
     * @param adsList List<AdvertisementDTO>
     * @return List<AdvertisementDTO>
     */
    public static List<AdvertisementDTO> sortListBySalePrice(List<AdvertisementDTO> adsList) {
        Comparator <AdvertisementDTO> salePriceSort = new Comparator<>() {
            public int compare(AdvertisementDTO o1, AdvertisementDTO o2) {

                return Double.compare(o1.getSalePrice(),o2.getSalePrice());

            }
        };
        adsList.sort(salePriceSort);
        return adsList;
    }
    public static Advertisement fromDTOtoAD(AdvertisementDTO adChoiceDTO) {
        return AdvertisementMapper.fromDTOtoAD(adChoiceDTO);
    }
    public static boolean getVerifyVisitTimeFree(Advertisement adchoiceVisit, int year, int month, int day, int hour) {
        List<Visit> visits = adchoiceVisit.getVisits();
        boolean valid = true;
        for (Visit value : visits) {
            int adYear = value.getYear();
            int adMonth = value.getMonth();
            int adDay = value.getDay();
            int adHour = value.getHour();
            if ((year == adYear) && (month == adMonth) && (day == adDay) && (hour == adHour)) {
                valid = false;
                break;
            }
        }
        return valid;
    }
    private static void listar(List<Advertisement> ads) {
        for (Advertisement ad : ads) {
            if (ad != null) {
                System.out.printf("comission %s %s, %.2f€%n", ad.getRequest().getProperty().getPropertyType(),
                        ad.getRequest().getBusinessType(), ad.getCommissionValue());
            }
        }
    }
    private static void listar1(List<AdvertisementDTO> ads) {
        int i = 0;
        for (AdvertisementDTO ad : ads) {
            if (ad != null) {
                i++;
                System.out.printf("%d - area %f, %.2f€%n, %d-%d-%d\n", i, ad.getArea(), ad.getDistance(), ad.getDate().getYear(), ad.getDate().getMonth(), ad.getDate().getDay());
                System.out.printf("comission %s %s", ad.getPropertyType().getPropertyType(), ad.getBusinessType());
            }
        }
    }
    public static int verifyOtherOffers(Advertisement adchoiceOffer, double keepClientOffer) {
        int confirmation = 0;
        List<Offer> offers = adchoiceOffer.getOffers();
        for (Offer offer : offers) {
            if (keepClientOffer < offer.getOffer()) {
                confirmation = 1;
            } else if ((keepClientOffer == offer.getOffer()) && (confirmation != 1)) {
                confirmation = 2;
            }
        }
         return confirmation;
    }
    public static int verifyOtherClientOffers(Advertisement adchoiceOffer, String keepClientEmail) {
        List<Offer> offers = adchoiceOffer.getOffers();
        int otherClientOffersInThisAd = 0;
        for (Offer offer : offers) {
            if ((keepClientEmail.compareTo(offer.getEmail().getEmail())==0)&&(offer.getStatus().compareTo(OfferStatus.PENDING.getStatus())==0)) {
                otherClientOffersInThisAd++;
            }
        }
        return otherClientOffersInThisAd;
    }
    public List<AdvertisementDTO> getAdsListAgentDTO(String keepAgentEmail) {
        Employee keepAgent = (Employee) personRepository.getPersonByEmail(keepAgentEmail);
        List <Advertisement> adsListAgent = advertisementRepository.getAdvertisementsByAgent(keepAgent);
        return createNewAdvertList(adsListAgent);
    }


    public String getEmailFromVisit(Visit keepVisitRequest) {
        return (String) keepVisitRequest.getClientEmail();
    }

    public List<Pair<String,Integer>> divideAdsByStore(List<Store> stores){
        List<Pair<String,Integer>> set=new ArrayList<>();
        for (Store a:stores) {
            set.add(new Pair<>(a.getIdStore(),a.getNumAds()));
        }
        return set;
    }

    public List<Advertisement> madeDealsSort(int sortAlg, int order) {
        List<Advertisement> sortedAds = new ArrayList<>();
        for (Advertisement dealsMade : advertisementRepository.getAdvertisements()) {
            for (Offer offer : dealsMade.getOffers()) {
                if (offer.getStatus().equals(OfferStatus.APPROVED.getStatus())) {
                    sortedAds.add(dealsMade);
                    break;
                }
            }
        }
        try{
            switch (sortAlg) {
                case 1:
                    sortedAds = selectionSort(sortedAds, order);
                    break;
                case 2:
                    sortedAds = bubbleSort(sortedAds, order);
                    break;
            }
        }catch(Exception e){}

        return sortedAds;
    }

    public List<Advertisement> selectionSort(List<Advertisement> sortedAds, int order) throws AcceptedOfferNotFoundException {
        int n = sortedAds.size();
        Double area1;
        Double area2;
        Date offerDate1 = null;
        Date offerDate2 = null;
        for (int i = 0; i < n - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < n; j++){
                area1 = sortedAds.get(j).getRequest().getProperty().getArea();
                area2 = sortedAds.get(index).getRequest().getProperty().getArea();
                if (order == 3) {
                    offerDate1 = sortedAds.get(j).getAcceptedOffer().getDate();
                    offerDate2 = sortedAds.get(j + 1).getAcceptedOffer().getDate();
                }
                if ((area1 < area2 && order == 1) || (order == 3 && offerDate1.after(offerDate2)) || (area1 > area2 && order == 2)) {
                    index = j;//searching for lowest index
                }
            }
            Advertisement temp = sortedAds.get(index);
            sortedAds.set(index, sortedAds.get(i));
            sortedAds.set(i, temp);
        }
        return sortedAds;
    }

    private static List<Advertisement> bubbleSort(List<Advertisement> sortedAds, int order) throws AcceptedOfferNotFoundException {
        Advertisement tempValue;
        Double area1;
        Double area2;
        Date offerDate1 = null;
        Date offerDate2 = null;

        for (int i = 0; i < sortedAds.size() - 1; i++) {
            for (int j = 0; j < sortedAds.size() - i - 1; j++) {
                area1 = sortedAds.get(j).getRequest().getProperty().getArea();
                area2 = sortedAds.get(j + 1).getRequest().getProperty().getArea();
                if(order == 3){
                    offerDate1 = sortedAds.get(j).getAcceptedOffer().getDate();
                    offerDate2 = sortedAds.get(j+1).getAcceptedOffer().getDate();
                }
                if ((area1 > area2 && order == 1) || (order == 3 && offerDate1.after(offerDate2))){
                    tempValue = sortedAds.get(j + 1);
                    sortedAds.set(j + 1, sortedAds.get(j));
                    sortedAds.set(j, tempValue);
                } else if (area1 < area2 && order == 2) {
                    tempValue = sortedAds.get(j);
                    sortedAds.set(j, sortedAds.get(j + 1));
                    sortedAds.set(j + 1, tempValue);
                }
            }
        }
        return sortedAds;
    }
}
