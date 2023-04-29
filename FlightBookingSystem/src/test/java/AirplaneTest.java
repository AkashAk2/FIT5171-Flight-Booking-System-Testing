import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AirplaneTest {

    @Test
    public void testSetAirplaneID() {
        Airplane airplane = new Airplane(1, "Boeing 737", 20, 200, 10);
        airplane.setAirplaneID(2);
        assertEquals(2, airplane.getAirplaneID());
    }

    @Test
    public void testSetBusinessSitsNumber() {
        Airplane airplane = new Airplane(1, "Boeing 737", 20, 200, 10);
        airplane.setBusinessSitsNumber(30);
        assertEquals(30, airplane.getBusinessSitsNumber());
    }

    @Test
    public void testSetEconomySitsNumber() {
        Airplane airplane = new Airplane(1, "Boeing 737", 20, 200, 10);
        airplane.setEconomySitsNumber(250);
        assertEquals(250, airplane.getEconomySitsNumber());
    }

    @Test
    public void testSetCrewSitsNumber() {
        Airplane airplane = new Airplane(1, "Boeing 737", 20, 200, 10);
        airplane.setCrewSitsNumber(15);
        assertEquals(15, airplane.getCrewSitsNumber());
    }

    @Test
    public void testSetInvalidSeatNumber() {
        Airplane airplane = new Airplane(1, "Boeing 737", 20, 200, 10);
        assertThrows(IllegalArgumentException.class, () -> airplane.setEconomySitsNumber(350));
    }

}
