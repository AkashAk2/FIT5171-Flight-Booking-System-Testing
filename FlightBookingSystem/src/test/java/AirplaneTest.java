import com.monash.fit5171.Airplane;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// This class is responsible for testing the com.monash.fit5171.Airplane class.
class AirplaneTest {
    // Declare an com.monash.fit5171.Airplane instance to be used in test cases.
    private Airplane airplane;
    // Set up the com.monash.fit5171.Airplane instance before each test case.
    @BeforeEach
    public void setUp() {
        // Initialize the airplane object with example data.
        airplane = new Airplane(
                1,
                "Boeing 737",
                30,
                150,
                10);
    }

    // Test if the getters and setters of the com.monash.fit5171.Airplane class work as expected.
    @Test
    void testAirplaneAttributes() {
        // Test getters
        assertEquals(1, airplane.getAirplaneID());
        assertEquals("Boeing 737", airplane.getAirplaneModel());
        assertEquals(30, airplane.getBusinessSitsNumber());
        assertEquals(150, airplane.getEconomySitsNumber());
        assertEquals(10, airplane.getCrewSitsNumber());

        // Test setter
        airplane.setAirplaneID(2);
        airplane.setAirplaneModel("Boeing 747");
        airplane.setBusinessSitsNumber(40);
        airplane.setEconomySitsNumber(200);
        airplane.setCrewSitsNumber(15);

        assertEquals(2, airplane.getAirplaneID());
        assertEquals("Boeing 747", airplane.getAirplaneModel());
        assertEquals(40, airplane.getBusinessSitsNumber());
        assertEquals(200, airplane.getEconomySitsNumber());
        assertEquals(15, airplane.getCrewSitsNumber());
    }

    // Test if an IllegalArgumentException is thrown when setting the airplane model to null.
    @Test
    void testAirplaneModelCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> airplane.setAirplaneModel(null));
    }

    @Test
    void testInvalidSeatNumbers() {
        assertThrows(IllegalArgumentException.class, () -> airplane.setBusinessSitsNumber(-1));
        assertThrows(IllegalArgumentException.class, () -> airplane.setEconomySitsNumber(301));
        assertThrows(IllegalArgumentException.class, () -> airplane.setCrewSitsNumber(0));
    }
    // Test if seat number ranges are correctly enforced.
    @Test
    public void testSeatNumberRange() {
        airplane.setBusinessSitsNumber(1);
        airplane.setEconomySitsNumber(299);
        airplane.setCrewSitsNumber(1);

        assertTrue(airplane.getBusinessSitsNumber() >= 1 && airplane.getBusinessSitsNumber() <= 300);
        assertTrue(airplane.getEconomySitsNumber() >= 1 && airplane.getEconomySitsNumber() <= 300);
        assertTrue(airplane.getCrewSitsNumber() >= 1 && airplane.getCrewSitsNumber() <= 300);
    }
    // Test if the toString method of the com.monash.fit5171.Airplane class returns the correct string representation.
    @Test
    public void testToString() {
        String expectedString = "com.monash.fit5171.Airplane{model=Boeing 737, business sits=30, economy sits=150, crew sits=10}";

        // Call the toString method of the airplane object
        String actualString = airplane.toString();

        // Assert that the actual string matches the expected string
        Assertions.assertEquals(expectedString, actualString);
    }

    // Test if the addAirplane method correctly adds an airplane to the airplaneData map.
    @Test
    public void testAddAirplane() {
        // Add the airplane to the airplaneData map
        Airplane.addAirplane(airplane);

        // Retrieve the airplane using the airplane ID
        Airplane retrievedAirplane = Airplane.getAirPlaneInfo(1);

        // Assert that the retrieved airplane matches the added airplane
        Assertions.assertEquals(airplane, retrievedAirplane);
    }

    // Test if the getAirPlaneInfo method returns null when trying to retrieve a non-existent airplane ID.
    @Test
    public void testGetAirPlaneInfo_NonexistentID() {
        // Retrieve an airplane using a non-existent ID
        Airplane retrievedAirplane = Airplane.getAirPlaneInfo(2);

        // Assert that the retrieved airplane is null
        Assertions.assertNull(retrievedAirplane);
    }
}
