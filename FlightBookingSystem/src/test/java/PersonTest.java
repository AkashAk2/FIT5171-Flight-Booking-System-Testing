import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonTest {
    private Passenger passenger;

    @BeforeEach
    public void setUp(){
        passenger = new Passenger("John", "Wick", 19, "Man", "johnwick@gmail.com", "0412345678", "A123456789", "1234123412341234", 123);
    }

    @Test
    public void testPersonAllFields() {
        assertEquals("John", passenger.getFirstName());
        assertEquals("Wick", passenger.getSecondName());
        assertEquals(19, passenger.getAge());
        assertEquals("Man", passenger.getGender());
    }

    @Test
    public void testValidGender() {
        Set<String> validGenders = new HashSet<>();
        validGenders.add("Woman");
        validGenders.add("Man");
        validGenders.add("Non-binary|gender diverse");
        validGenders.add("Prefer not to say");
        validGenders.add("Other");

        assertTrue(validGenders.contains(passenger.getGender()));
    }

    @Test
    public void testFirstNameAndLastName() {
        assertTrue(passenger.getFirstName().matches("^[a-zA-Z]*$"));
        assertTrue(passenger.getSecondName().matches("^[a-zA-Z]*$"));
    }
}
