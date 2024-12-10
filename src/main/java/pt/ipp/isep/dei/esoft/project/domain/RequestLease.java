package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Date;

public class RequestLease implements Request, Comparable<Request>, Serializable {

    public Date date;
    public BusinessType businessType;
    public Property property;
    public Person owner;
    public Employee agent;
    public int durationOfContract;
    public double rentPrice;

    public RequestLease(Property property, BusinessType businessType, Person owner, Employee agent, double rentPrice,
            int durationOfContract) {
        if (!validateRequest(property, businessType, owner, agent, durationOfContract, rentPrice))
            throw new IllegalArgumentException("Request arguments are not valid, please try again.");
        this.property = property;
        this.businessType = businessType;
        this.owner = owner;
        this.agent = agent;
        this.durationOfContract = durationOfContract;
        this.rentPrice = rentPrice;
        this.date = new Date();
    }

    public Property getProperty() {
        return property;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public Person getOwner() {
        return owner;
    }

    public Employee getAgent() {
        return agent;
    }

    public int getContractDuration() {
        return durationOfContract;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public Date getDate() {
        return date;
    }

    public boolean validateRequest(Property property, BusinessType businessType, Person owner, Employee agent,
            double... args) {
        if (property == null)
            return false;
        else if (businessType == null || !confirmBusinessType(businessType))
            return false;
        else if (owner == null)
            return false;
        else if (agent == null)
            return false;
        else {
            for (double d : args) {
                if (d <= 0)
                    return false;
            }
        }
        return true;
    }

    public boolean confirmBusinessType(BusinessType businessType) {
        return BusinessType.LEASE.getBusinessType().equals(businessType.getBusinessType());
    }

    @Override
    public int compareTo(Request r) {
        if (this.getDate().before(r.getDate()))
            return -1;
        else if (this.getDate().after(r.getDate()))
            return 1;
        else
            return 0;
    }

    @Override
    public String toString() {
        return String.format("%s - %s\nBusiness Type: %s\nPrice: %.2f$/month - duration: %d\nProperty information:\n%s",
                owner.getName(),
                owner.getEmail().toString(), businessType.getBusinessType(),
                rentPrice, durationOfContract, property.toString());
    }

    @Override
    public boolean equals(Object o) throws IllegalArgumentException {
        if (o == null || !(o instanceof RequestLease || o instanceof RequestSale))
            return false;
        if (!(o instanceof RequestLease))
            return false;
        RequestLease r = (RequestLease) o;
        if (!this.getOwner().equals(r.getOwner()))
            return false;
        else if (!this.getAgent().equals(r.getAgent()))
            return false;
        else if (!this.getProperty().equals(r.getProperty()))
            return false;
        else if (!this.getBusinessType().getBusinessType().equals(r.getBusinessType().getBusinessType()))
            return false;
        else if (this.getContractDuration() != r.getContractDuration())
            return false;
        else if (this.getRentPrice() != r.getRentPrice())
            return false;
        else
            return true;
    }
}
