import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PassengerTest {
    private Passenger passenger;

    @BeforeEach
    public void setUp(){
        passenger = new Passenger("John", "Wick", 19, "Man", "johnwick@gmail.com", "0412345678", "A12345678", "1234123412341234", 123);
    }

    @Test
    public void testRequiredFields() {
        assertNotNull(passenger.getFirstName());
        assertNotNull(passenger.getSecondName());
        assertNotEquals(0, passenger.getAge());
        assertNotNull(passenger.getGender());
        assertNotNull(passenger.getEmail());
        assertNotNull(passenger.getPhoneNumber());
        assertNotNull(passenger.getPassport());
        assertNotNull(passenger.getCardNumber());
        assertNotEquals(0, passenger.getSecurityCode());
    }

    @Test
    public void testValidPhoneNumber() {
        assertTrue(passenger.getPhoneNumber().matches("^0[45]\\d{8}$"));
    }

    @Test
    public void testValidEmail() {
        assertTrue(passenger.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"));
    }

    @Test
    public void testValidPassportNumber() {
        assertTrue(passenger.getPassport().length() <= 9);
    }

    @Test
    public void testPassengerDetailsFromPerson() {
        assertEquals("John", passenger.getFirstName());
        assertEquals("Wick", passenger.getSecondName());
        assertEquals(19, passenger.getAge());
        assertEquals("Man", passenger.getGender());
    }
}