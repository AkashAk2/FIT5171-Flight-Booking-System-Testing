package com.monash.mainclasses;

/**
 * Represents a passenger with personal and contact information.
 */
public class Passenger extends Person
{
    private String email;
    private String phoneNumber;
    private String cardNumber;
    private int securityCode;
    private String passport;

    /**
     * Default constructor.
     */
    public Passenger(){}

    /**
     * Constructor with parameters.
     *
     * @param firstName    The first name of the passenger
     * @param secondName   The second name of the passenger
     * @param age          The age of the passenger
     * @param gender       The gender of the passenger
     * @param email        The email address of the passenger
     * @param phoneNumber  The phone number of the passenger
     * @param passport     The passport number of the passenger
     * @param cardNumber   The card number used for booking
     * @param securityCode The security code associated with the card
     */
    public Passenger(String firstName, String secondName, int age, String gender,String email, String phoneNumber,
                     String passport, String cardNumber,int securityCode)
    {
        super(firstName, secondName, age, gender);
        if (email == null || phoneNumber == null || passport == null || cardNumber == null || (securityCode > 0 && (securityCode < 100 || securityCode > 999))) {
            throw new IllegalArgumentException("All fields are required");}
        this.securityCode=securityCode;
        this.cardNumber=cardNumber;
        this.passport=passport;
        this.email=email;
        this.phoneNumber=phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        // correct email pattern
        boolean emailFollowsPattern = email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

        if (!emailFollowsPattern) {
            throw new IllegalArgumentException("Email is not in right format");
        }

        this.email = email;
    }

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    public String getSecondName() {
        return super.getSecondName();
    }

    @Override
    public void setSecondName(String secondName) {
        super.setSecondName(secondName);
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    /**
     * Returns the passport details.
     *
     * @return The passport details
     */
    public String getPassport() {
        return passport;
    }

    @Override
    public void setGender(String gender) {
        super.setGender(gender);
    }

    /**
     * Returns the phone number.
     *
     * @return The phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Returns the security code of the credit card.
     *
     * @return The security code
     */
    public int getSecurityCode() {
        return securityCode;
    }

    /**
     * Returns the card number.
     *
     * @return The card number
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets the card number.
     *
     * @param cardNumber The passenger's card number to set
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Sets the security code.
     *
     * @param securityCode The passenger's security code to set
     */
    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    @Override
    public void setAge(int age) {
        super.setAge(age);
    }

    /**
     * Sets the passport number.
     *
     * @param passport The passenger's passport to set
     */
    public void setPassport(String passport) {
        // Passport less than 10 characters
        if (!(passport.length() < 10)) {
            throw new IllegalArgumentException("Passport is not in the right format");
        }
        this.passport = passport;
    }

    @Override
    public String getGender() {
        return super.getGender();
    }

    /**
     * Sets the phone number.
     *
     * @param phoneNumber The passenger's phone number to set
     */
    public void setPhoneNumber(String phoneNumber) {
        // pattern for Australian phone number
        boolean phoneFollowsPattern = phoneNumber.matches("^(\\+?61|0) ?4 ?[0-9]{2}([ -]?[0-9]{3}){2}$");

        if (!phoneFollowsPattern) {
            throw new IllegalArgumentException("Phone must begin with +61 or 04 or 05 and followed by 8 digits");
        }

        this.phoneNumber = phoneNumber;
    }

    @Override
    public int getAge() {
        return super.getAge();
    }

    @Override
    public String toString()
    {
        return "com.monash.fit5171.Passenger{" + " Fullname= "+ super.getFirstName()+" "+super.getSecondName()+
                " ,email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", passport='" + passport +
                '}';
    }
}
