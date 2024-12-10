package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class RequestRepository {
    private List<Request> request = new ArrayList<>();
    private static String SERFILE = "RequestRepository.ser";

    public void saveRequestRepository() {
        Serialization.toWrite((List<Object>) (List<?>) request, SERFILE);
    }

    public void loadRequestRepository() {
        this.request = (List<Request>) (List<?>) Serialization.toRead(SERFILE);
    }

    public void createNewRequestSale(Property newAllProperty, BusinessType keepBusinessType, Person keepOwner,
            Employee keepAgent, double keepSalePrice) {
        RequestSale newRequest = new RequestSale(newAllProperty, keepBusinessType, keepOwner, keepAgent, keepSalePrice);
        for (Request re : request) {
            if (re instanceof RequestSale && re.equals(newRequest))
                throw new IllegalArgumentException("This request already exists in the system");
        }
        request.add(newRequest);
    }

    public void createNewRequestRent(Property newAllProperty, BusinessType keepBusinessType, Person keepOwner,
            Employee keepAgent, double keepRentPrice, int keepDuractionContract) {
        RequestLease newRequest = new RequestLease(newAllProperty, keepBusinessType, keepOwner, keepAgent,
                keepRentPrice,
                keepDuractionContract);
        for (Request re : request) {
            if (re instanceof RequestLease && re.equals(newRequest))
                throw new IllegalArgumentException("This request already exists in the system");
        }
        request.add(newRequest);
    }

    public Request createSaleRequest(Property newAllProperty, BusinessType keepBusinessType, Person keepOwner,
            Employee keepAgent, double keepSalePrice) {
        RequestSale newRequest = new RequestSale(newAllProperty, keepBusinessType, keepOwner, keepAgent, keepSalePrice);
        for (Request re : request) {
            if (re instanceof RequestSale && re.equals(newRequest))
                throw new IllegalArgumentException("This request already exists in the system");
        }
        request.add(newRequest);
        return newRequest;
    }

    public Request createRentRequest(Property newAllProperty, BusinessType keepBusinessType, Person keepOwner,
            Employee keepAgent, double keepRentPrice, int keepDuractionContract) {
        RequestLease newRequest = new RequestLease(newAllProperty, keepBusinessType, keepOwner, keepAgent,
                keepRentPrice,
                keepDuractionContract);
        for (Request re : request) {
            if (re instanceof RequestLease && re.equals(newRequest))
                throw new IllegalArgumentException("This request already exists in the system");
        }
        request.add(newRequest);
        return newRequest;
    }

    public List<Request> getRequestList() {
        return List.copyOf(request);
    }

    public List<RequestSale> getSaleRequestList() {
        List<RequestSale> returnList = new ArrayList<>();
        for (Request r : request) {
            if (r instanceof RequestSale)
                returnList.add((RequestSale) r);
        }
        return List.copyOf(returnList);
    }

    public List<RequestLease> getRentRequestList() {
        List<RequestLease> returnList = new ArrayList<>();
        for (Request r : request) {
            if (r instanceof RequestLease)
                returnList.add((RequestLease) r);
        }
        return List.copyOf(returnList);
    }

    public List<Request> getRequestsByAgent(Employee agent) {
        List<Request> returnList = new ArrayList<>();
        for (Request r : request) {
            if (r.getAgent().equals(agent))
                returnList.add(r);
        }
        return List.copyOf(returnList);
    }

    public List<Request> getRequestsByAgentEmail(String agentEmail) {
        List<Request> returnList = new ArrayList<>();
        for (Request r : request) {
            if (r.getAgent().getEmail().toString().equals(agentEmail))
                returnList.add(r);
        }
        return List.copyOf(returnList);
    }

    public void removeRequest(Request request) {
        // System.out.println(request.toString());
        this.request.remove(request);
    }
}
