import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PassengerTest {
    private Person person;

    @BeforeEach
    public void setup(){
        person = Mockito.mock(Person.class);

        Mockito.when(person.getFirstName()).thenReturn("John");
        Mockito.when(person.getSecondName()).thenReturn("Guzman");
        Mockito.when(person.getAge()).thenReturn(25);
        Mockito.when(person.getGender()).thenReturn("Man");
    }

    @Test
    public void testPassengerFieldsNotNull(){
        // Test with all required fields
        Passenger passenger = new Passenger(person.getFirstName(), person.getSecondName(), person.getAge(), person.getGender(), "JohnGuzman@gmail.com"
                , "0424123909", "ABC123A", "5217123412340987", 123);

        assertNotNull(passenger);

        // Test with missing fields
        assertThrows(IllegalArgumentException.class, () -> new Passenger(null, person.getSecondName(), person.getAge(), person.getGender(),
                "JohnGuzman@gmail.com", "0412378923", "AB123456", "5217278345677856", 123));
        assertThrows(IllegalArgumentException.class, () -> new Passenger(person.getFirstName(), null, person.getAge(), person.getGender(),
                "JohnGuzman@gmail.com", "0412378923", "AB123456", "5217278345677856", 123));
        assertThrows(IllegalArgumentException.class, () -> new Passenger(person.getFirstName(), person.getSecondName(), -1, person.getGender(),
                "JohnGuzman@gmail.com", "0412378923", "AB123456", "5217278345677856", 123));
        assertThrows(IllegalArgumentException.class, () -> new Passenger(person.getFirstName(), person.getSecondName(), person.getAge(), null,
                "JohnGuzman@gmail.com", "0412378923", "AB123456", "5217278345677856", 123));
        assertThrows(IllegalArgumentException.class, () -> new Passenger(person.getFirstName(), person.getSecondName(), person.getAge(), person.getGender(),
                null, "0412378923", "AB123456", "5217278345677856", 123));
        assertThrows(IllegalArgumentException.class, () -> new Passenger(person.getFirstName(), person.getSecondName(), person.getAge(), person.getGender(),
                "JohnGuzman@gmail.com", null, "AB123456", "5217278345677856", 123));
        assertThrows(IllegalArgumentException.class, () -> new Passenger(person.getFirstName(), person.getSecondName(), person.getAge(), person.getGender(),
                "JohnGuzman@gmail.com", "0412378923", null, "5217278345677856", 123));
        assertThrows(IllegalArgumentException.class, () -> new Passenger(person.getFirstName(), person.getSecondName(), person.getAge(), person.getGender(),
                "JohnGuzman@gmail.com", "0412378923", "AB123456", null, 123));
        assertThrows(IllegalArgumentException.class, () -> new Passenger(person.getFirstName(), person.getSecondName(), person.getAge(), person.getGender(),
                "JohnGuzman@gmail.com", "0412378923", "AB123456", null, -1));
    }

    @Test
    public void testPhonePattern(){
        Passenger passenger = new Passenger();

        // Testing with incorrect input
        assertThrows(IllegalArgumentException.class, () -> passenger.setPhoneNumber("0123123"));

        // Testing with correct input
        passenger.setPhoneNumber("0424123909");
        assertEquals("0424123909", passenger.getPhoneNumber());
    }

    @Test
    public void testEmailPattern(){
        Passenger passenger = new Passenger();

        // Testing with incorrect input
        assertThrows(IllegalArgumentException.class, () -> passenger.setEmail("asdfs@"));

        // Testing with correct input
        passenger.setEmail(("JohnSmith@gmail.com"));
        assertEquals("JohnSmith@gmail.com", passenger.getEmail());
    }

    @Test
    public void testPassportCharacters(){
        Passenger passenger = new Passenger();

        // Testing with incorrect input
        assertThrows(IllegalArgumentException.class, () -> passenger.setPassport("AOOOOOOOOBC123A"));

        // Testing with correct input
        passenger.setPassport(("ABC123A"));
        assertEquals("ABC123A", passenger.getPassport());
    }

    @Test
    public void testPassengersFieldFollowsPerson(){
        Passenger passenger = new Passenger(person.getFirstName(), person.getSecondName(), person.getAge(), person.getGender(), "JohnGuzman@gmail.com"
                , "0424123909", "ABC123A", "5217123412340987", 123);

        // passenger's first name, last name, gender, age follows the person's fields
        Assertions.assertEquals(person.getFirstName(), passenger.getFirstName());
        Assertions.assertEquals(person.getSecondName(), passenger.getSecondName());
        Assertions.assertEquals(person.getAge(), passenger.getAge());
        Assertions.assertEquals(person.getGender(), passenger.getGender());
    }

    @Test
    void getEmail() {
        Passenger passenger = new Passenger("Justin", "Boston", 66, "male", "justinboston66@gmail.com",
                "0412123123", "J123123", "5217521712341234", 123);
        String expectedEmail = "justinboston66@gmail.com";
        String actualEmail = passenger.getEmail();
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    void getPhoneNumber() {
        Passenger passenger = new Passenger("Justin", "Boston", 66, "male", "justinboston66@gmail.com",
                "0412123123", "J123123", "5217521712341234", 123);
        String expectedPhoneNumber = "0412123123";
        String actualPhoneNumber = passenger.getPhoneNumber();
        assertEquals(expectedPhoneNumber, actualPhoneNumber);
    }
}