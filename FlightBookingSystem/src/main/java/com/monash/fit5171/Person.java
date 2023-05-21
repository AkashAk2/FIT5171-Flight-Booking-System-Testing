package com.monash.fit5171;

/**
 * Represents a person with their personal information.
 */
public class Person //abstract class com.monash.fit5171.Person
{
    private String firstName;
    private String secondName;
    private int age;
    private String gender;

    /**
     * Default constructor.
     */
    public Person(){}

    /**
     * Constructor with parameters.
     *
     * @param firstName    The first name of the person
     * @param secondName   The second name of the person
     * @param age          The age of the person
     * @param gender       The gender of the person
     */
    public Person(String firstName, String secondName, int age, String gender){
        if (firstName == null || secondName == null || gender == null || age < 0) {
            throw new IllegalArgumentException("All fields are required");
        }
        this.age=age;
        this.firstName=firstName;
        this.secondName=secondName;
        this.gender=gender;
    }

    /**
     * Returns the age.
     *
     * @return The Age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age.
     *
     * @param age The person's age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Returns the gender.
     *
     * @return The gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender.
     *
     * @param gender The person's gender to set
     */
    public void setGender(String gender){
        // list of the expected gender options inputted by the user
        String[] expectedOptions = {"Woman", "Man", "Non-binary|gender diverse", "Prefer not to say", "Other"};

        // going through all the list
        boolean matchFound = false;
        for (String option : expectedOptions) {
            if (option.equalsIgnoreCase(gender)) {
                matchFound = true;
                break;
            }
        }

        // if no match found, exception is thrown
        if(!matchFound){
            throw new IllegalArgumentException("Gender input is incorrect");
        }

        this.gender = gender;
    }

    /**
     * Returns the firstname.
     *
     * @return The firstname
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Returns the second name.
     *
     * @return The second name
     */
    public String getSecondName() {
        return secondName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName The person's first name to set
     */
    public void setFirstName(String firstName) {
        // Checking if name is alphabetic
        boolean isAlphabetic = firstName.matches("[a-zA-Z]+");

        if (!isAlphabetic) {
            throw new IllegalArgumentException("Name must be alphabetical");
        }
        this.firstName = firstName;
    }

    /**
     * Sets the second name.
     *
     * @param secondName The person's second name to set
     */
    public void setSecondName(String secondName) {
        // Checking if name is alphabetic
        boolean isAlphabetic = secondName.matches("[a-zA-Z]+");

        if (!isAlphabetic) {
            throw new IllegalArgumentException("Name must be alphabetical");
        }

        this.secondName = secondName;
    }

    @Override
    public String toString()
    {
        return "com.monash.fit5171.Person{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
