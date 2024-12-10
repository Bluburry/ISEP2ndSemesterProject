package pt.ipp.isep.dei.esoft.project.domain;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDateTime;

import pt.isep.lei.esoft.auth.domain.model.Email;

public class Visit implements Comparable<Visit>, Serializable {

    private int year;
    private int month;
    private int day;
    private int hour;
    private VisitStatus status;
    private Email clientEmail;

    public Visit(int year, int month, int day, int hour, String clientEmail) throws IllegalArgumentException {
        validateDate(year, month, day, hour);
        this.clientEmail = new Email(clientEmail);
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        status = VisitStatus.PENDING;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public String getClientEmail() {
        return clientEmail.getEmail();
    }

    public String getStatus() {
        return status.getVisitStatus();
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setClientEmail(String clientEmail) throws IllegalArgumentException {
        this.clientEmail = new Email(clientEmail);
    }

    public void resetStatus() {
        this.status = VisitStatus.PENDING;
    }

    public void acceptStatus() {
        this.status = VisitStatus.ACCEPTED;
    }

    public void rejectStatus() {
        this.status = VisitStatus.REJECTED;
    }

    private void validateDate(int year, int month, int day, int hour) {
        LocalDateTime tester = LocalDateTime.now();
        LocalDateTime time;
        try {
            time = LocalDateTime.of(year, month, day, hour, 0);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("Date is not acceptable, please try again");
        }
        if (time.isBefore(tester))
            throw new IllegalArgumentException("Date is not acceptable, please try again");
        if (year < 0 || hour < 9 || hour > 22)
            throw new IllegalArgumentException("Date is not acceptable, please try again");
    }

    public boolean checkVisitAlreadyScheduled(int year, int month, int day, int hour) {
        validateDate(year, month, day, hour);
        if (this.year != year || this.month != month || this.day != day || this.hour != hour)
            return false;
        else
            return true;
    }

    @Override
    public int compareTo(Visit v) {
        if (this.year == v.year && this.month == v.month && this.day == v.day && this.hour == v.hour)
            return 0;
        else if (this.year < v.getYear())
            return -1;
        else if (this.year == v.getYear()) {
            if (this.month < v.getMonth())
                return -1;
            else if (this.month == v.getMonth()) {
                if (this.day < v.getDay())
                    return -1;
                else if (this.day == v.getDay()) {
                    if (this.hour < v.getHour())
                        return -1;
                }
            }
        }
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        Visit v;
        if (o == null || !(o instanceof Visit))
            return false;
        else {
            v = (Visit) o;
            if (this.year == v.year && this.month == v.month && this.day == v.day && this.hour == v.hour)
                return true;
            else
                return false;
        }
    }

    @Override
    public String toString() {
        return String.format("Client e-mail: %s -> Schedule: %d, %d, %d, %d\n", this.getClientEmail(), this.getYear(),
                this.getMonth(), this.getDay(), this.getHour());
    }

    public int comparetoDate(int year, int month, int day) {
        if (this.year == year && this.month == month && this.day == day)
            return 0;
        else if (this.year < year
                || (this.year == year && (this.month < month || (this.month == month && this.day < day))))
            return -1;
        else
            return 1;
    }

    private void writeObject(ObjectOutputStream opst) throws IOException {
        opst.writeObject(clientEmail.getEmail());
        opst.writeInt(year);
        opst.writeInt(month);
        opst.writeInt(day);
        opst.writeInt(hour);
        opst.writeObject(status);
    }

    private void readObject(ObjectInputStream ipst) throws IOException, ClassNotFoundException, ParseException {
        clientEmail = new Email((String) ipst.readObject());
        year = ipst.readInt();
        month = ipst.readInt();
        day = ipst.readInt();
        hour = ipst.readInt();
        status = (VisitStatus) ipst.readObject();
    }
}
