package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AdvertisementCollection;
import pt.ipp.isep.dei.esoft.project.repository.AdvertisementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.*;

public class ListPropertiesController {

    /**
     * Access to the class Repositories
     */
    private Repositories repositories;

    /**
     * Access to the class AdvertisementRepository
     */
    private AdvertisementRepository advertisementRepository;
    private Double price;

    private AdvertisementMapper advertisementMapper;

    /**
     * Access to the class AdvertisementCollection
     */
    private AdvertisementCollection advertisementCollection;

    /**
     * Creates an instance of ListPropertiesController
     * where gives the to repositories an instance of Repositories
     */
    public ListPropertiesController(){
        repositories=Repositories.getInstance();
    }

    /**
     * returns List<BusinessType>
     * @return List<BusinessType>
     */
    public BusinessType[] getBusinessTypeList(){
        return BusinessType.values();
    }

    /**
     * returns List<PropertyType>
     * @return List<PropertyType>
     */
    public PropertyType[] getPropertyTypeList(){
        return PropertyType.values();
    }

    /**
     * Returns List<Advertisement> that have a sorted Properties List
     * @param keepSortMethod Sort Method
     * @param keepBusinessType Business Type
     * @param keepPropertyType Property Type
     * @param keepNumberBedrooms Number of Bedrooms in a Property
     * @return List<Advertisement>
     */
    public List<Advertisement> getListedProperties(String keepSortMethod,BusinessType keepBusinessType,PropertyType keepPropertyType,int keepNumberBedrooms){
        advertisementRepository=repositories.getAdvertisementRepository();
        List<Advertisement> listedProperties=advertisementRepository.getListedProperties(keepBusinessType,keepPropertyType,keepNumberBedrooms);

        if(keepSortMethod == "Price"){
            if(keepBusinessType.getBusinessType().compareTo("LEASE")==0){
                keepSortMethod="Rental";
            } else if (keepBusinessType.getBusinessType().compareTo("SALE")==0) {
                keepSortMethod="Sale";
            }
        }

        if(keepSortMethod == null){
            return listedProperties;
        }else{
            return sortPropertiesList(listedProperties,keepSortMethod);
        }
    }

    /**
     * Return the listedProperties sorted according to the keepSortMethod
     * @param listedProperties Property List
     * @param keepSortMethod Sort Method
     * @return List<Advertisement>
     */
    private List<Advertisement> sortPropertiesList(List<Advertisement> listedProperties,String keepSortMethod){
        List<Advertisement> sortedPropertiesList = new ArrayList<>();

        switch (keepSortMethod){
            case "Rental":
                for(AdvertisementDTO ad: advertisementCollection.sortListByRentalPrice(AdvertisementCollection.createNewAdvertList(listedProperties))){
                    sortedPropertiesList.add(advertisementMapper.fromDTOtoAD(ad));
                }
                break;
            case "Sale":
                for(AdvertisementDTO ad: advertisementCollection.sortListBySalePrice(AdvertisementCollection.createNewAdvertList(listedProperties))){
                    sortedPropertiesList.add(advertisementMapper.fromDTOtoAD(ad));
                }
                break;
            case "City":
                for(AdvertisementDTO ad: advertisementCollection.sortListByCitySort(AdvertisementCollection.createNewAdvertList(listedProperties))){
                    sortedPropertiesList.add(advertisementMapper.fromDTOtoAD(ad));
                }
                break;
            case "State":
                for(AdvertisementDTO ad: advertisementCollection.sortListByStateSort(AdvertisementCollection.createNewAdvertList(listedProperties))){
                    sortedPropertiesList.add(advertisementMapper.fromDTOtoAD(ad));
                }
                break;
            case "Default":
                for(AdvertisementDTO ad: advertisementCollection.sortAdVisitListByDate(AdvertisementCollection.createNewAdvertList(listedProperties))){
                    sortedPropertiesList.add(advertisementMapper.fromDTOtoAD(ad));
                }
                break;
        }

        return sortedPropertiesList;

    }

    /**
     * Returns the price
     * @param adv
     * @return price
     */

    public Double getPrice(Advertisement adv){
        if( advertisementMapper.toDTO(adv).getSalePrice() == 0){
            price = advertisementMapper.toDTO(adv).getRentPrice();
        }else{
            price = advertisementMapper.toDTO(adv).getSalePrice();
        }

        return price;
    }
}