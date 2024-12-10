package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Collections;
import java.util.Date;
import java.util.Random;

import pt.ipp.isep.dei.esoft.project.exceptions.AcceptedOfferNotFoundException;

import java.util.List;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;

public class Advertisement implements Comparable<Advertisement>, Serializable {
    /**
     * saves da date of the advertisement
     */
    private Date date;

    /**
     * saves the request that led to this advertisement
     */
    private Request request;

    /**
     * saves the type of commission that will be used
     * for this advertisement (fixed or percentage)
     */
    private CommissionType commissionType;

    /**
     * the value of the commission to be applied,
     * will be either a percentage or a flat rate
     */
    private double commissionValue;

    private Store store;

    private List<Visit> visits;

    private List<Offer> offers;

    /**
     * Creates an instance of Advertisement
     * requires a request instance, a commissionType, and a commissionValue
     *
     * @param request         Request of Ad
     * @param commissionType  Commission Type of Ad
     * @param commissionValue Commission Value of Ad
     */
    public Advertisement(Request request, CommissionType commissionType, double commissionValue, Store store)
            throws IllegalArgumentException {
        validateValues(request, commissionType, commissionValue, store);
        this.request = request;
        this.commissionType = commissionType;
        this.commissionValue = commissionValue;
        this.date = new Date();
        this.visits = new ArrayList<>();
        this.offers = new ArrayList<>();
        this.store = store;
    }

    /**
     * Returns request
     *
     * @return request
     */
    public Request getRequest() {
        return request;
    }

    /**
     * Return commission type
     *
     * @return commissionType
     */
    public CommissionType getCommissionType() {
        return commissionType;
    }

    /**
     * Return commission value
     *
     * @return commission value
     */
    public double getCommissionValue() {
        return commissionValue;
    }

    /**
     * Returns date of advertisemente
     *
     * @return date
     */
    public Date getDate() {
        return date;
    }

    public Store getStore() {
        return store;
    }

    public List<Visit> getVisits() {
        List<Visit> toSend = new ArrayList<>(visits);
        Collections.sort(toSend);
        return List.copyOf(toSend);
    }

    public List<Visit> getVisitsByAlgorithm(String algorithm, int beginYear, int beginMonth, int beginDay, int endYear,
            int endMonth, int endDay) {
        List<Visit> toSend = new ArrayList<>();
        List<Visit> limitted = new ArrayList<>();
        for (Visit visit : visits)
            if (visit.comparetoDate(beginYear, beginMonth, beginDay) >= 0
                    && visit.comparetoDate(endYear, endMonth, endDay) <= 0
                    && visit.getStatus().equals(VisitStatus.PENDING.getVisitStatus()))
                limitted.add(visit);
        if (algorithm.equals("MaybeInsertion"))
            toSend = maybeInsertion(limitted);
        else if (algorithm.equals("MergeSort")) {
            mergeSort(limitted);
            toSend = limitted;
        } else if (algorithm.equals("QuicksortLite"))
            toSend = quicksortLite(limitted);
        else
            Collections.sort(toSend);
        return List.copyOf(toSend);
    }

    public List<Offer> getOffers() {
        List<Offer> toSend = new ArrayList<>(offers);
        Collections.sort(toSend);
        return List.copyOf(toSend);
    }

    public void addVisit(int year, int month, int day, int hour, String clientEmail) throws IllegalArgumentException {
        for (Visit v : visits) {
            if (v.checkVisitAlreadyScheduled(year, month, day, hour))
                throw new IllegalArgumentException(
                        "There is a visit already booked for this date and time, please try another one.");
        }
        visits.add(new Visit(year, month, day, hour, clientEmail));
    }

    public void addOffer(double offer, String clientEmail) throws IllegalArgumentException {
        if (Objects.isNull(clientEmail) || clientEmail.isEmpty() || clientEmail.isBlank() || offer < 0)
            throw new IllegalArgumentException("Invalid offer");
        offers.add(new Offer(offer, clientEmail));
    }

    /**
     * Get Advertisements with Property Filters
     *
     * @param keepBusinessType   Advertisement Business Type
     * @param keepPropertyType   Advertisement Property Type
     * @param keepNumberBedrooms Advertisement Numver of Bedrooms
     * @return boolean
     */
    public Boolean getAdsAndPropertyFilters(BusinessType keepBusinessType, PropertyType keepPropertyType,
            int keepNumberBedrooms) {
        if (keepBusinessType != null && !keepBusinessType.equals(request.getBusinessType())) {
            return false;
        }

        if (keepPropertyType != null) {
            if (!keepPropertyType.getPropertyType().equals(request.getProperty().getPropertyType())) {
                return false;
            }
            if (keepPropertyType.getPropertyType().equals("APARTMENT")) {
                if (((Apartment) request.getProperty()).getNumberBedrooms() != keepNumberBedrooms
                        && keepNumberBedrooms != -1) {
                    return false;
                }
            } else if (keepPropertyType.getPropertyType().equals("HOUSE")) {
                if (((House) request.getProperty()).getNumberBedrooms() != keepNumberBedrooms
                        && keepNumberBedrooms != -1) {
                    return false;
                }
            }
        } else if (keepNumberBedrooms != -1) {
            if (!request.getProperty().getPropertyType().equals("LAND")) {
                if (request.getProperty().getPropertyType().equals("APARTMENT")) {
                    if (((Apartment) request.getProperty()).getNumberBedrooms() != keepNumberBedrooms) {
                        return false;
                    }
                } else if (request.getProperty().getPropertyType().equals("HOUSE")) {
                    if (((House) request.getProperty()).getNumberBedrooms() != keepNumberBedrooms) {
                        return false;
                    }
                }
            } else if (request.getProperty().getPropertyType().equals("LAND")) {
                return false;
            }
        }

        return true;
    }

    private void validateValues(Request request, CommissionType commissionType, double commissionValue, Store store) {
        if (request == null)
            throw new IllegalArgumentException("Request is null.");
        else if (store == null)
            throw new IllegalArgumentException("Store is null.");
        else if (!validCommissionType(commissionType))
            throw new IllegalArgumentException("Commission Type is not valid.");
        else if (commissionValue <= 0)
            throw new IllegalArgumentException("Commission value is not valid.");
    }

    /**
     * Validates commission type by checking if it exists in the CommissionType enum
     * 
     * @return true or false
     */
    private boolean validCommissionType(CommissionType commissionType) {
        if (commissionType == null)
            return false;
        CommissionType possibleTypes[] = CommissionType.values();
        boolean isExistingType = false;
        for (CommissionType pt : possibleTypes) {
            if (commissionType.getCommissionType().equals(pt.getCommissionType())) {
                isExistingType = true;
                break;
            }
        }
        return isExistingType;
    }

    @Override
    public String toString() {
        return String.format("Agent: %s - %s\nRequest made by: %s", this.request.getAgent().getName(),
                this.request.getAgent().getEmail().getEmail(), this.request.toString());
    }

    @Override
    public int compareTo(Advertisement o) {
        if (this.getDate().before(o.getDate()))
            return -1;
        else if (this.getDate().after(o.getDate()))
            return 1;
        else
            return 0;
    }

    public void changeStatus(Visit visit, String status) {
        Visit visitSave = null;
        for (Visit v : visits) {
            if (v.equals(visit)) {
                visitSave = v;
                break;
            }
        }
        if (visitSave == null)
            throw new IllegalArgumentException("Visit doesn't exist for this advertisement.");
        switch (status) {
            case ("PENDING"):
                visitSave.resetStatus();
                break;
            case ("ACCEPTED"):
                visitSave.acceptStatus();
                break;
            case ("REJECTED"):
                visitSave.rejectStatus();
                break;
            default:
                throw new IllegalArgumentException("Invalid status.");
        }
    }

    @Override
    public boolean equals(Object o) {
        Advertisement ad;
        if (o == null || !(o instanceof Advertisement))
            return false;
        else {
            ad = (Advertisement) o;
            if (this.getRequest().equals(ad.getRequest())
                    && this.commissionType.getCommissionType().equals(ad.getCommissionType().getCommissionType())
                    && this.commissionValue == ad.getCommissionValue() && this.getStore().equals(ad.getStore()))
                return true;
            else
                return false;
        }
    }

    public Offer getAcceptedOffer() throws AcceptedOfferNotFoundException {
        Offer of = null;
        for (Offer o : offers) {
            if (o.getStatus().equals(OfferStatus.APPROVED.getStatus())) {
                of = o;
                break;
            }
        }
        if (of == null)
            throw new AcceptedOfferNotFoundException();
        return of;
    }

    public String adAndDealToString() throws AcceptedOfferNotFoundException {
        Offer of = this.getAcceptedOffer();
        return String.format("%s\n%s\n", this.toString(), of.toString());
    }

    private List<Visit> maybeInsertion(List<Visit> l) {
        List<Visit> toReturn = new ArrayList<>();
        int test[] = new int[l.size()];
        int test2[] = new int[l.size()];
        for (int i = 0, k = 0; i < test.length; i++, k = 0) {
            for (int j = 0; j < test.length; j++) {
                if (i == j)
                    continue;
                if (l.get(i).compareTo(l.get(j)) > 0)
                    k++;
            }
            test[i] = k;
        }
        for (int i = 0; i < test.length; i++) {
            for (int j = 0; j < test2.length; j++) {
                if (i == test[j]) {
                    test2[i] = j;
                    break;
                }
            }
        }
        for (int i = 0; i < test2.length; i++)
            toReturn.add(l.get(test2[i]));
        return toReturn;
    }

    private void mergeSort(List<Visit> l) {
        if (l.size() >= 2) {
            List<Visit> left = new ArrayList<>();
            List<Visit> right = new ArrayList<>();
            left.addAll(l.subList(0, l.size() / 2));
            right.addAll(l.subList(l.size() / 2, l.size()));
            mergeSort(left);
            mergeSort(right);
            merge(l, left, right);
        }
    }

    private void merge(List<Visit> l, List<Visit> left, List<Visit> right) {
        int i = 0, j = 0;
        for (int k = 0; k < l.size(); k++) {
            if (j >= right.size() || (i < left.size() && left.get(i).compareTo(right.get(j)) < 0)) {
                l.set(k, left.get(i));
                i++;
            } else if (i >= left.size() || (j < right.size() && left.get(i).compareTo(right.get(j)) > 0)) {
                l.set(k, right.get(j));
                j++;
            }
        }
    }

    private List<Visit> quicksortLite(List<Visit> l) {
        List<Visit> s = new ArrayList<>();
        Random r = new Random();
        qsHelper(s, l, r);
        return s;
    }

    private void qsHelper(List<Visit> s, List<Visit> l, Random r) {
        if (l.size() >= 2) {
            List<Visit> helper1 = new ArrayList<>();
            List<Visit> helper2 = new ArrayList<>();
            int n = r.nextInt(l.size());
            while (n == 0 || n == l.size() - 1)
                n = r.nextInt(l.size());
            for (int i = 0; i < l.size(); i++) {
                if (i == n)
                    continue;
                else if (l.get(i).compareTo(l.get(n)) < 0)
                    helper1.add(l.get(i));
                else
                    helper2.add(l.get(i));
            }
            helper2.add(l.get(n));
            qsHelper(s, helper1, r);
            qsHelper(s, helper2, r);
        } else if (l.size() > 0)
            s.add(l.get(0));
    }

}
