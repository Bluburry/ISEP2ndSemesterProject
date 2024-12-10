package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Advertisement;
import pt.ipp.isep.dei.esoft.project.domain.BruteForceAlgorithm;
import pt.ipp.isep.dei.esoft.project.domain.Pair;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.repository.AdvertisementCollection;
import pt.ipp.isep.dei.esoft.project.repository.AdvertisementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.StoreRepository;

import java.util.List;

public class DivideSetOfStoresController {

    private StoreRepository storeRepository;

    private BruteForceAlgorithm algorithm;

    private AdvertisementCollection collection;

    public DivideSetOfStoresController(){
        storeRepository=Repositories.getInstance().getStoreRepository();
        algorithm=new BruteForceAlgorithm();
        collection=new AdvertisementCollection();
    }

    public Pair<List<Pair<String,Integer>>,List<Pair<String,Integer>>> setOfStoresDivided(){
        List<Store> stores=storeRepository.getStoresList();
        List<Pair<String,Integer>> set=collection.divideAdsByStore(stores);
        return algorithm.start(set);
    }

    public double getTime(){
        return  algorithm.getInTime();
    }

    public int getDiff(){
        return algorithm.getDifference();
    }


}
