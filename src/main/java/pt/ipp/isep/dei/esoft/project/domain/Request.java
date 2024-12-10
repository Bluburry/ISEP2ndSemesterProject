package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Date;

public interface Request {

    Property getProperty();

    BusinessType getBusinessType();

    Person getOwner();

    Employee getAgent();

    Date getDate();

    boolean validateRequest(Property property, BusinessType businessType, Person owner, Employee agent, double... args);

    boolean confirmBusinessType(BusinessType businessType);

    String toString();

    boolean equals(Object o);
}
