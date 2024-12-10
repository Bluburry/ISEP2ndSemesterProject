package pt.ipp.isep.dei.esoft.project.application.controller;

import java.util.ArrayList;

import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.application.session.UserSession;
import pt.ipp.isep.dei.esoft.project.domain.*;

public class ListPropertyRequestController {

	/**
	 * Access to the repositores
	 */
	private Repositories repositories;

	/**
	 * Access to the application session
	 */
	private ApplicationSession applicationSession;

	private StoreRepository storeRepository;
	/**
	 * Access to the user session
	 */
	private UserSession userSession;

	/**
	 * Access to the person repository
	 */
	private PersonRepository personRepository;
	/**
	 * Access to the request repository
	 */
	private RequestRepository requestRepository;
	/**
	 * Access to the advertisement repository
	 */
	private AdvertisementRepository advertisementRepository;
	private Employee employee;

	/**
	 * initializes this class and its basic necessities
	 */
	public ListPropertyRequestController() {
		repositories = Repositories.getInstance();
	}

	public Employee getAgentFromSession() {
		String agentEmail;
		Employee agent;
		applicationSession = ApplicationSession.getInstance();
		userSession = applicationSession.getCurrentSession();
		agentEmail = userSession.getUserEmail();
		personRepository = repositories.getPersonRepository();
		agent = (Employee) personRepository.getPersonByEmail(agentEmail);
		return agent;
	}

	public ArrayList<Request> getRequestList() throws IllegalArgumentException {
		if (employee == null)
			employee = getAgentFromSession();
		if (requestRepository == null)
			requestRepository = repositories.getRequestRepository();
		return new ArrayList<>(requestRepository.getRequestsByAgent(employee));
	}

	public void removeRequest(Request request) {
		requestRepository.removeRequest(request);
	}

	/**
	 * gets and returns the list of commission types
	 * 
	 * @return List<CommissionType>
	 */
	public CommissionType[] getCommissionType() {
		return CommissionType.values();
	}

	public void approveRequest(Request request, CommissionType commissionType, Double commissionValue)
			throws IllegalArgumentException {
		if (advertisementRepository == null)
			advertisementRepository = repositories.getAdvertisementRepository();

		storeRepository=repositories.getStoreRepository();
		Store store=storeRepository.getStoreWithEmployee(request.getAgent());
		advertisementRepository.createNewAdvertisement(request, commissionType, commissionValue,store);
		removeRequest(request);
	}
}
