import java.sql.Timestamp;
import java.util.Calendar;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Flight {
    private int flightID;
    private String departTo;
    private String departFrom;
    private String code;
    private String company;
    private String dateFrom;
    private String timeFrom;
    private String dateTo;
    private String timeTo;
    private Airplane airplane;

    public Flight() {}

    public Flight(int flightID, String departTo, String departFrom, String code, String company, String dateFrom, String timeFrom, String dateTo, String timeTo, Airplane airplane) {
        setFlightID(flightID);
        setDepartTo(departTo);
        setDepartFrom(departFrom);
        setCode(code);
        setCompany(company);
        setDateFrom(dateFrom);
        setTimeFrom(timeFrom);
        setDateTo(dateTo);
        setTimeTo(timeTo);
        setAirplane(airplane);
    }

    /**
     * Validates the date format.
     *
     * @param date The date string to validate
     * @throws IllegalArgumentException if the date is not in the "dd/MM/yy" format
     */
    private void validateDateFormat(String date) {
        String pattern = "dd/MM/yy";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDate.parse(date, formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("Date must be in dd/MM/yy format.");
        }
    }

    /**
     * Validates the time format.
     *
     * @param time The time string to validate
     * @throws IllegalArgumentException if the time is not in the "HH:mm:ss" format
     */
    private void validateTimeFormat(String time) {
        String pattern = "HH:mm:ss";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            String timeString = time.trim();
            LocalTime.parse(timeString, formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("Time must be in HH:mm:ss format.");
        }
    }

    // Setters

    /**
     * Sets the flight ID.
     *
     * @param flightID The flight ID to set
     * @throws IllegalArgumentException if the flightID is less than or equal to 0
     */
    public void setFlightID(int flightID) {
        if (flightID <= 0) {
            throw new IllegalArgumentException("Flight ID must be greater than 0.");
        }
        this.flightID = flightID;
    }

    /**
     * Sets the departure city.
     *
     * @param departTo The departure city to set
     * @throws IllegalArgumentException if the departTo is null or empty
     */
    public void setDepartTo(String departTo) {
        if (departTo == null || departTo.isEmpty()) {
            throw new IllegalArgumentException("Departure city must be non-empty.");
        }
        this.departTo = departTo;
    }

    /**
     * Sets the destination city.
     *
     * @param departFrom The destination city to set
     * @throws IllegalArgumentException if the departFrom is null or empty
     */
    public void setDepartFrom(String departFrom) {
        if (departFrom == null || departFrom.isEmpty()) {
            throw new IllegalArgumentException("Destination city must be non-empty.");
        }
        this.departFrom = departFrom;
    }

    /**
     * Sets the flight code.
     *
     * @param code The flight code to set
     * @throws IllegalArgumentException if the code is null or empty
     */
    public void setCode(String code) {
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("Flight code must be non-empty.");
        }
        this.code = code;
    }

    /**
     * Sets the flight company.
     *
     * @param company The flight company to set
     * @throws IllegalArgumentException if the company is null or empty
     */
    public void setCompany(String company) {
        if (company == null || company.isEmpty()) {
            throw new IllegalArgumentException("Company must be non-empty.");
        }
        this.company = company;
    }

    /**
     * Sets the departure date.
     *
     * @param dateFrom The departure date to set
     * @throws IllegalArgumentException if the dateFrom is not in the "dd/MM/yy" format
     */
    public void setDateFrom(String dateFrom) {
        validateDateFormat(dateFrom);
        this.dateFrom = dateFrom;
    }

    /**
     * Sets the departure time.
     *
     * @param timeFrom The departure time to set
     * @throws IllegalArgumentException if the timeFrom is not in the "HH:mm:ss" format
     */
    public void setTimeFrom(String timeFrom) {
        validateTimeFormat(timeFrom);
        this.timeFrom = timeFrom;
    }

    /**
     * Sets the arrival date.
     *
     * @param dateTo The arrival date to set
     * @throws IllegalArgumentException if the dateTo is not in the "dd/MM/yy" format
     */
    public void setDateTo(String dateTo) {
        validateDateFormat(dateTo);
        this.dateTo = dateTo;
    }

    /**
     * Sets the arrival time.
     *
     * @param timeTo The arrival time to set
     * @throws IllegalArgumentException if the timeTo is not in the "HH:mm:ss" format
     */
    public void setTimeTo(String timeTo) {
        validateTimeFormat(timeTo);
        this.timeTo = timeTo;
    }

    /**
     * Sets the associated Airplane object.
     *
     * @param airplane The Airplane object to set
     * @throws IllegalArgumentException if the airplane is null
     */
    public void setAirplane(Airplane airplane) {
        if (airplane == null) {
            throw new IllegalArgumentException("Airplane must not be null.");
        }
        this.airplane = airplane;
    }

    // Getters

    public int getFlightID() {
        return flightID;
    }

    public String getDepartTo() {
        return departTo;
    }

    public String getDepartFrom() {
        return departFrom;
    }

    public String getCode() {
        return code;
    }

    public String getCompany() {
        return company;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public String toString()
    {
        return "Flight{" +
                "flightID=" + flightID +
                ", departTo='" + departTo + '\'' +
                ", departFrom='" + departFrom + '\'' +
                ", code='" + code + '\'' +
                ", company='" + company + '\'' +
                ", dateFrom='" + dateFrom + '\'' +
                ", timeFrom='" + timeFrom + '\'' +
                ", dateTo='" + dateTo + '\'' +
                ", timeTo='" + timeTo + '\'' +
                ", airplane=" + airplane +
                '}';
    }
}
