package pt.ipp.isep.dei.esoft.project.application.controller;

import java.util.List;

import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.application.session.UserSession;
import pt.ipp.isep.dei.esoft.project.domain.*;

public class ListBookingRequestController {

	/**
	 * Access to the repositores
	 */
	private Repositories repositories;

	/**
	 * Access to the application session
	 */
	private ApplicationSession applicationSession;

	/**
	 * Access to the user session
	 */
	private UserSession userSession;

	/**
	 * Access to the person repository
	 */
	private PersonRepository personRepository;
	/**
	 * Access to the advertisement repository
	 */
	private AdvertisementRepository advertisementRepository;

	private Employee agent;

	private Config config;

	private String sorting;

	public ListBookingRequestController() {
		repositories = Repositories.getInstance();
		agent = getAgentFromSession();
		advertisementRepository = repositories.getAdvertisementRepository();
		config = Config.getInstance();
		sorting = config.getSortingAlgorithm();
	}

	public Employee getAgentFromSession() {
		applicationSession = ApplicationSession.getInstance();
		userSession = applicationSession.getCurrentSession();
		personRepository = repositories.getPersonRepository();
		agent = (Employee) personRepository.getPersonByEmail(userSession.getUserEmail());
		return agent;
	}

	public List<Advertisement> getAdvertisements() {
		return advertisementRepository.getAdvertisementsByAgent(agent);
	}

	public List<Visit> getVisitsList(Advertisement ad, int startYear, int startMonth, int startDay, int endYear,
			int endMonth, int endDay) {
		return advertisementRepository.getAdVisitsByDate(ad, sorting, startYear, startMonth, startDay, endYear,
				endMonth, endDay);
	}

	public void answerVisit(Advertisement ad, Visit visit, boolean status) {
		advertisementRepository.respondToVisit(ad, visit, status);
	}
}
