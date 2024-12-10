package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Advertisement;
import pt.ipp.isep.dei.esoft.project.repository.AdvertisementCollection;
import pt.ipp.isep.dei.esoft.project.repository.AdvertisementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;

public class ListDealsController {
    /**
     * Access to the class Repositories
     */
    private Repositories repositories;

    /**
     * Access to the class AdvertisementRepository
     */
    private AdvertisementCollection advertisementCollection;
    public ListDealsController(){
        repositories = Repositories.getInstance();
        advertisementCollection = new AdvertisementCollection();
    }
    public List<Advertisement> listDeals(int sortAlg, int order){
        return advertisementCollection.madeDealsSort(sortAlg, order);
    }
}
