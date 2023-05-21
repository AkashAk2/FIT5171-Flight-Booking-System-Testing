package com.monash.mainclasses;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an com.monash.fit5171.Airplane with airplane ID, model, and seating information.
 */
public class Airplane {
    private int airplaneID;
    private String airplaneModel;
    private int businessSitsNumber;
    private int economySitsNumber;
    private int crewSitsNumber;
    private static Map<Integer, Airplane> airplaneData = new HashMap<>();


    /**
     * Constructs an com.monash.fit5171.Airplane with the specified attributes.
     *
     * @param airplaneID         Unique identifier for the airplane
     * @param airplaneModel      Model of the airplane
     * @param businessSitsNumber Number of business class seats
     * @param economySitsNumber  Number of economy class seats
     * @param crewSitsNumber     Number of crew seats
     */
    public Airplane(int airplaneID, String airplaneModel, int businessSitsNumber, int economySitsNumber, int crewSitsNumber)
    {
        this.airplaneID=airplaneID;
        this.airplaneModel = airplaneModel;
        this.businessSitsNumber = businessSitsNumber;
        this.economySitsNumber = economySitsNumber;
        this.crewSitsNumber = crewSitsNumber;
    }

    /**
     * Returns the airplane ID.
     *
     * @return The airplane ID
     */
    public int getAirplaneID() {
        return airplaneID;
    }

    /**
     * Sets the airplane ID.
     *
     * @param airplaneID The airplane ID to set
     */

    public void setAirplaneID(int airplaneID) {
        this.airplaneID = airplaneID;
    }

    /**
     * Returns the airplane model.
     *
     * @return The airplane model
     */

    public String getAirplaneModel() {
        return airplaneModel;
    }

    /**
     * Sets the airplane model.
     *
     * @param airplaneModel The airplane model to set
     * @throws IllegalArgumentException if airplaneModel is null
     */

    public void setAirplaneModel(String airplaneModel) {
        if (airplaneModel == null) {
            throw new IllegalArgumentException("com.monash.fit5171.Airplane model cannot be null");
        }
        else {
            this.airplaneModel = airplaneModel;
        }
    }

    /**
     * Returns the number of business class seats.
     *
     * @return The number of business class seats
     */
    public int getBusinessSitsNumber() {
        return businessSitsNumber;
    }

    /**
     * Sets the number of business class seats.
     *
     * @param businessSitsNumber The number of business class seats to set
     * @throws IllegalArgumentException if businessSitsNumber is not in the range [1, 300]
     */
    public void setBusinessSitsNumber(int businessSitsNumber) {
        if (businessSitsNumber < 1 || businessSitsNumber > 300) {
            throw new IllegalArgumentException("Business seats number must be in the range of 1 - 300");
        }
        else {
            this.businessSitsNumber = businessSitsNumber;
        }
    }

    /**
     * Returns the number of economy class seats.
     *
     * @return The number of economy class seats
     */
    public int getEconomySitsNumber() {
        return economySitsNumber;
    }

    public void setEconomySitsNumber(int economSitsNumber) {

        if (economSitsNumber < 1 || economSitsNumber > 300) {
            throw new IllegalArgumentException("Economy seats number must be in the range of 1 - 300");
        }
        // Set the economy seat number if it's valid
        this.economySitsNumber = economSitsNumber;
    }

    public int getCrewSitsNumber() {
        return crewSitsNumber;
    }

    /**
     * Sets the number of crew seats.
     *
     * @param crewSitsNumber The number of crew seats to set
     * @throws IllegalArgumentException if crewSitsNumber is not in the range [1, 300]
     */
    public void setCrewSitsNumber(int crewSitsNumber) {
        if (crewSitsNumber < 1 || crewSitsNumber > 300) {
            throw new IllegalArgumentException("Crew seats number must be in the range [1, 300]");
        }
        else {
            this.crewSitsNumber = crewSitsNumber;
        }
    }

    /**
     * Returns a string representation of the com.monash.fit5171.Airplane object.
     *
     * @return A string representation of the com.monash.fit5171.Airplane object
     */
    public String toString()
    {
        return "com.monash.fit5171.Airplane{" +
                "model=" + getAirplaneModel() +
                ", business sits=" + getBusinessSitsNumber() +
                ", economy sits=" + getEconomySitsNumber() +
                ", crew sits=" + getCrewSitsNumber() +
                '}';
    }

    /**
     * Adds an com.monash.fit5171.Airplane to the static airplaneData map.
     *
     * @param airplane The com.monash.fit5171.Airplane object to add
     * @throws IllegalArgumentException if airplane is null
     */
    public static void addAirplane(Airplane airplane) {
        if (airplane == null) {
            throw new IllegalArgumentException("com.monash.fit5171.Airplane cannot be null");
        }
        else {
            airplaneData.put(airplane.getAirplaneID(), airplane);
        }
    }

    /**
     * Retrieves an com.monash.fit5171.Airplane object from the airplaneData map based on the airplane_id.
     *
     * @param airplaneId The airplane ID to search for
     * @return The com.monash.fit5171.Airplane object if found, or null if not found
     */
    public static Airplane getAirPlaneInfo(int airplaneId) {
        return airplaneData.get(airplaneId);
    }
}
