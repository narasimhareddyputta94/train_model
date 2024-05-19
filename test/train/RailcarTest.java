package train;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RailcarTest {

    private Railcar railcar;

    @BeforeEach
    void setup() {
        System.out.println("Building fresh Railcar");
        try {
            railcar = new Railcar("ABC1234", RailcarType.BOXCAR, 60);
        } catch (InvalidParameterException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void setTypeNegative() {
        assertThrows(InvalidParameterException.class, () -> {
            railcar.setType(null);
        });
        assertNotNull(railcar.getType());
    }

    @Test
    void setTypePositive() {
        assertDoesNotThrow(() -> {
            railcar.setType(RailcarType.TANKER);
        });
        assertEquals(RailcarType.TANKER, railcar.getType());

    }

    @Test
    void setContentWeightNegative() {
        assertThrows(InvalidParameterException.class, () -> {
            railcar.setContentWeight(-1);
        });
        assertThrows(InvalidParameterException.class, () -> {
            railcar.setContentWeight(101);
        });
    }

    @Test
    void setContentWeightPositive() {
        assertDoesNotThrow(() -> {
            railcar.setContentWeight(0);
        });
        assertEquals(0, railcar.getWeight());

        assertDoesNotThrow(() -> {
            railcar.setContentWeight(100);
        });
        assertEquals(100, railcar.getWeight());

    }

    @Test
    void setSerialNumber() {
        assertThrows(InvalidParameterException.class, () -> {
            railcar = new Railcar("ABC123", RailcarType.BOXCAR, 60);
        });
        assertNotEquals("ABC123", railcar.getSerialNumber());
        assertThrows(InvalidParameterException.class, () -> {
            railcar = new Railcar("ABC12345", RailcarType.BOXCAR, 60);
        });
        assertNotEquals("ABC12345", railcar.getSerialNumber());
        assertThrows(InvalidParameterException.class, () -> {
            railcar = new Railcar("ABC123!", RailcarType.BOXCAR, 60);
        });
        assertNotEquals("ABC123!", railcar.getSerialNumber());
        assertThrows(InvalidParameterException.class, () -> {
            railcar = new Railcar(null, RailcarType.BOXCAR, 60);
        });
        assertNotNull(railcar.getSerialNumber());
    }

    @Test
    void testToString() {
        assertEquals(
                "Railcar{serialNumber='ABC1234', type=BOXCAR, content weight=60 tons, total weight=90 tons}",
                railcar.toString());
    }
}