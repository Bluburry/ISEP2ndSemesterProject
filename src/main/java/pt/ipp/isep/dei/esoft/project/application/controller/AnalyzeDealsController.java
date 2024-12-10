package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.RequestRepository;

import java.util.ArrayList;
import java.util.List;

public class AnalyzeDealsController {

    private Repositories repositories;
    private SimpleLinear simpleLinear;
    private MultiLinear multiLinear;
    private RequestRepository requestRepository;
    private List<RequestSale> houseList;
    private List<RequestSale> apartmentList;

    public AnalyzeDealsController(){
        repositories = Repositories.getInstance();
        simpleLinear = new SimpleLinear();
        multiLinear = new MultiLinear();
    }
    public String getSimpleAnalyzes(){
        return simpleLinear.generateAnalysisReport();
    }

    public String getMultiAnalyzes(){
        return multiLinear.generateAnalysisReport();
    }

    public void setSimpleParameters(double[][] parameters){
        simpleLinear.addValues(parameters);
    }

    public void setMultiParameters(double[][] parameters){
        multiLinear.addValues(parameters);
    }

    public List<RequestSale> getHouseList(){
        houseList = new ArrayList<>();
        requestRepository = repositories.getRequestRepository();
        if (requestRepository != null) {
            for (RequestSale request:requestRepository.getSaleRequestList()){
                if(request.getProperty() instanceof House){
                    houseList.add(request);
                }
            }
        } else {
        }

        return houseList;
    }

    public List<RequestSale> getApartmentList(){
        apartmentList = new ArrayList<>();
        requestRepository = repositories.getRequestRepository();
        if (requestRepository != null) {
            for (RequestSale request:requestRepository.getSaleRequestList()){
                if(request.getProperty() instanceof Apartment){
                    apartmentList.add(request);
                }
            }
        } else {

        }


        return apartmentList;
    }
}
