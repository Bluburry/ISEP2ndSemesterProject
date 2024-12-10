package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.exceptions.AcceptedOfferNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Collections;

@SuppressWarnings("unchecked")
public class AdvertisementRepository {
	private List<Advertisement> advertisements = new ArrayList<>();
	private static String SERFILE = "AdvertisementRepository.ser";

	public void saveAdvertisementRepository() {
		Serialization.toWrite((List<Object>) (List<?>) advertisements, SERFILE);
	}

	public void loadAdvertisementRepository() {
		this.advertisements = (List<Advertisement>) (List<?>) Serialization.toRead(SERFILE);
	}

	public Advertisement createNewAdvertisement(Request request, CommissionType commissionType, double commissionValue,
			Store store)
			throws IllegalArgumentException {
		if (checkAlreadyExisting(request))
			throw new IllegalArgumentException("An advertisement for this request already exists.");
		Advertisement newAdvertisement = new Advertisement(request, commissionType, commissionValue, store);
		advertisements.add(newAdvertisement);
		newAdvertisement.getStore().incNumAds();
		return newAdvertisement;
	}

	public boolean checkAlreadyExisting(Request request) {
		for (Advertisement ad : advertisements) {
			if (ad.getRequest().equals(request))
				return true;
		}
		return false;
	}

	public List<Advertisement> getAdvertisementsByAgent(Employee employee) {
		List<Advertisement> agentProperties = new ArrayList<>();
		if (!advertisements.isEmpty()) {
			for (Advertisement a : advertisements) {
				if (a.getRequest().getAgent().equals(employee)) {
					agentProperties.add(a);
				}
			}
		}
		return List.copyOf(agentProperties);
	}

	public List<Advertisement> getAdvertisementsForAgent(Employee agent) {
		List<Advertisement> agentAds = new ArrayList<>();
		for (Advertisement a : advertisements) {
			if (a.getRequest().getAgent().equals(agent)) {
				agentAds.add(a);
			}
		}
		return List.copyOf(agentAds);
	}

	public List<Advertisement> getListedProperties(BusinessType keepBusinessType, PropertyType keepPropertyType,
			int keepNumberBedrooms) {
		List<Advertisement> filteredProperties = new ArrayList<>();
		if (!advertisements.isEmpty()) {
			for (Advertisement a : advertisements) {
				if (a.getAdsAndPropertyFilters(keepBusinessType, keepPropertyType, keepNumberBedrooms)) {
					filteredProperties.add(a);
				}
			}
		}
		return filteredProperties;
	}

	public List<Advertisement> getAdvertisements() {
		Collections.sort(advertisements);
		return List.copyOf(advertisements);
	}

	public void introduceVisitToAdList(Advertisement ad, int year, int month, int day, int hour, String clientEmail)
			throws IllegalArgumentException {
		if (Objects.isNull(ad))
			throw new IllegalArgumentException();
		ad.addVisit(year, month, day, hour, clientEmail);
	}

	public List<Visit> getAdVisits(Advertisement ad) {
		return List.copyOf(ad.getVisits());
	}

	public void introduceOfferToAdList(Advertisement ad, double offer, String clientEmail)
			throws IllegalArgumentException {
		if (Objects.isNull(ad))
			throw new IllegalArgumentException();
		ad.addOffer(offer, clientEmail);
	}

	public List<Offer> getAdOffers(Advertisement ad) {
		return List.copyOf(ad.getOffers());
	}

	public static boolean verifyOfferValueLowerSalePrice(Advertisement adchoiceOffer, double keepClientOffer) {
		boolean confirmation = false;
		Request adRequest = adchoiceOffer.getRequest();
		double price;
		if (adRequest instanceof RequestSale) {
			RequestSale req = (RequestSale) adRequest;
			price = req.getSalePrice();
		} else {
			RequestLease req = (RequestLease) adRequest;
			price = req.getRentPrice();
		}
		if (keepClientOffer <= price) {
			confirmation = true;
		}
		return confirmation;
	}

	public List<Visit> getAdVisitsByDate(Advertisement ad, String algorithm, int startYear, int startMonth,
			int startDay, int endYear, int endMonth, int endDay) {
		return ad.getVisitsByAlgorithm(algorithm, startYear, startMonth, startDay, endYear, endMonth, endDay);
	}

	public void respondToVisit(Advertisement advertisement, Visit visit, boolean accept) {
		if (accept) {
			advertisement.changeStatus(visit, "ACCEPTED");
			rejectOtherVisitsWithSameTime(advertisement, visit);
		} else
			advertisement.changeStatus(visit, "REJECTED");
	}

	private void rejectOtherVisitsWithSameTime(Advertisement advertisement, Visit visit) {
		for (Advertisement ad : advertisements) {
			if (ad.getRequest().getAgent().equals(advertisement.getRequest().getAgent()) && !ad.equals(advertisement)) {
				try {
					ad.changeStatus(visit, "REJECTED");
				} catch (Exception e) {
					continue;
				}
			}
		}
	}

	public void changeVisitStatusAccepted(Advertisement keepAdVisit, Visit keepVisitRequest) {
		if (keepAdVisit != null)
			keepAdVisit.changeStatus(keepVisitRequest, VisitStatus.ACCEPTED.getVisitStatus());
		List<Visit> visitsAD = keepAdVisit.getVisits();
		for (Visit v : visitsAD) {
			if ((!v.equals(keepVisitRequest)) && (v.getYear() == keepVisitRequest.getYear())
					&& (v.getMonth() == keepVisitRequest.getMonth()) && (v.getDay() == keepVisitRequest.getDay())
					&& (v.getHour() == keepVisitRequest.getHour())) {
				if (keepAdVisit != null)
					keepAdVisit.changeStatus(v, VisitStatus.REJECTED.getVisitStatus());
			}
		}
	}

	public void changeVisitStatusRejected(Advertisement keepAdVisit, Visit keepVisitRequest) {
		if (keepAdVisit != null)
			keepAdVisit.changeStatus(keepVisitRequest, VisitStatus.REJECTED.getVisitStatus());
	}

	public List<Visit> getVisitsSameTime(Visit visit) {
		List<Visit> toSend = new ArrayList<Visit>();
		List<Visit> compare;
		for (Advertisement ad : advertisements) {
			compare = ad.getVisits();
			for (Visit v : compare) {
				if (v.compareTo(visit) == 0) {
					toSend.add(v);
				}
			}
		}
		return toSend;
	}

	public List<Advertisement> madeDealsSort(int sortAlg, int order) {
		List<Advertisement> sortedAds = new ArrayList<>();
		for (Advertisement dealsMade : advertisements) {
			for (Offer offer : dealsMade.getOffers()) {
				if (offer.getStatus().equals(OfferStatus.APPROVED.getStatus())) {
					sortedAds.add(dealsMade);
					break;
				}
			}
		}
		try {
			switch (sortAlg) {
				case 1:
					mergeSort(sortedAds, order);
					break;
				case 2:
					sortedAds = bubbleSort(sortedAds, order);
					break;
			}
		} catch (Exception e) {
		}

		return sortedAds;
	}

	private void mergeSort(List<Advertisement> l, int order) throws AcceptedOfferNotFoundException { // n log(n) ?
		if (l.size() >= 2) {
			List<Advertisement> left = new ArrayList<>();
			List<Advertisement> right = new ArrayList<>();
			left.addAll(l.subList(0, l.size() / 2));
			right.addAll(l.subList(l.size() / 2, l.size()));
			mergeSort(left, order);
			mergeSort(right, order);
			merge(l, left, right, order);
		}
	}

	private void merge(List<Advertisement> l, List<Advertisement> left, List<Advertisement> right, int order)
			throws AcceptedOfferNotFoundException {
		Double area1;
		Double area2;
		int i = 0, j = 0;
		switch (order) {
			case 1:
				for (int k = 0; k < l.size(); k++) {
					area1 = left.get(i).getRequest().getProperty().getArea();
					area2 = right.get(j).getRequest().getProperty().getArea();
					if (j >= right.size() || (i < left.size() && area1 < area2)) {
						l.set(k, left.get(i));
						i++;
					} else if (i >= left.size() || (j < right.size() && area1 > area2)) {
						l.set(k, right.get(j));
						j++;
					}
				}
				break;
			case 2:
				for (int k = 0; k < l.size(); k++) {
					area1 = left.get(i).getRequest().getProperty().getArea();
					area2 = right.get(j).getRequest().getProperty().getArea();
					if (j >= right.size() || (i < left.size() && area1 > area2)) {
						l.set(k, left.get(i));
						i++;
					} else if (i >= left.size() || (j < right.size() && area1 < area2)) {
						l.set(k, right.get(j));
						j++;
					}
				}
				break;
			default:
				for (int k = 0; k < l.size(); k++) {
					Date offerDate1 = left.get(i).getAcceptedOffer().getDate();
					Date offerDate2 = right.get(j).getAcceptedOffer().getDate();
					if (j >= right.size() || (i < left.size() && offerDate1.after(offerDate2))) {
						l.set(k, left.get(i));
						i++;
					} else if (i >= left.size() || (j < right.size() && offerDate1.before(offerDate2))) {
						l.set(k, right.get(j));
						j++;
					}
				}
				break;
		}
	}

	private static List<Advertisement> bubbleSort(List<Advertisement> sortedAds, int order)
			throws AcceptedOfferNotFoundException {
		Advertisement tempValue;
		Double area1;
		Double area2;
		Date offerDate1 = null;
		Date offerDate2 = null;

		for (int i = 0; i < sortedAds.size() - 1; i++) {
			for (int j = 0; j < sortedAds.size() - i - 1; j++) {
				area1 = sortedAds.get(j).getRequest().getProperty().getArea();
				area2 = sortedAds.get(j + 1).getRequest().getProperty().getArea();
				if (order == 3) {
					offerDate1 = sortedAds.get(j).getAcceptedOffer().getDate();
					offerDate2 = sortedAds.get(j + 1).getAcceptedOffer().getDate();
				}
				if ((area1 > area2 && order == 1) || (order == 3 && offerDate1.after(offerDate2))) {
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
