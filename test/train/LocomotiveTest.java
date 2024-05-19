package train;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocomotiveTest {

    private Locomotive locomotive;

    @BeforeEach
    void setUp() {
        try {
            locomotive = new Locomotive("ABCD12345");
        } catch (InvalidParameterException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void addRailcarPositive() {
        Railcar railcar = null;
        try {
            railcar = new Railcar("ABC1234", RailcarType.BOXCAR, 60);
        } catch (InvalidParameterException e) {
            fail(e.getMessage());
        }
        Railcar finalRailcar = railcar;
        assertDoesNotThrow(() -> {
            locomotive.addRailcar(finalRailcar);
        });

    }

    @Test
    void addRailcarNegativeByWeight() {
        Railcar railcar = null;
        try {
            railcar = new Railcar("ABC1234", RailcarType.BOXCAR, 100);
        } catch (InvalidParameterException e) {
            fail(e.getMessage());
        }

        for (int i = 0; i < 30; i++) {
            try {
                locomotive.addRailcar(railcar);
            } catch (InvalidParameterException e) {
                fail(e.getMessage());
            }
        }
        Railcar finalRailcar = railcar;
        assertThrows(InvalidParameterException.class, () -> {
            locomotive.addRailcar(finalRailcar);
        });

    }

    @Test
    void addRailcarNegativeByCount() {
        Railcar railcar = null;
        try {
            railcar = new Railcar("ABC1234", RailcarType.BOXCAR, 0);
        } catch (InvalidParameterException e) {
            fail(e.getMessage());
        }

        for (int i = 0; i < 50; i++) {
            try {
                locomotive.addRailcar(railcar);
            } catch (InvalidParameterException e) {
                fail(e.getMessage());
            }
        }
        Railcar finalRailcar = railcar;
        assertThrows(InvalidParameterException.class, () -> {
            locomotive.addRailcar(finalRailcar);
        });

    }

    @Test
    void getRailcarCount() {
        Railcar railcar = null;
        try {
            railcar = new Railcar("ABC1234", RailcarType.BOXCAR, 0);
        } catch (InvalidParameterException e) {
            fail(e.getMessage());
        }

        for (int i = 0; i < 10; i++) {
            try {
                locomotive.addRailcar(railcar);
            } catch (InvalidParameterException e) {
                fail(e.getMessage());
            }
        }
        assertEquals(10, locomotive.getRailcarCount());
    }

    @Test
    void getTotalRailcarWeight() {
        Railcar railcar = null;
        try {
            railcar = new Railcar("ABC1234", RailcarType.BOXCAR, 10);
        } catch (InvalidParameterException e) {
            fail(e.getMessage());
        }

        for (int i = 0; i < 10; i++) {
            try {
                locomotive.addRailcar(railcar);
            } catch (InvalidParameterException e) {
                fail(e.getMessage());
            }
        }
        assertEquals(400, locomotive.getTotalRailcarWeight());
    }

    @Test
    void testToString() {
        String expected = "Locomotive{" +
                "serialNumber='ABCD12345', maxRailcars=50, num Railcars=0, maxRailcarWeight=3900 tons, Total Railcar weight=0 tons}";
        assertTrue(locomotive.toString().startsWith(expected));
    }


    @Test
    void constructorInvalidSerialNumber() {
        assertThrows(InvalidParameterException.class, () -> {
            new Locomotive("INVALID");
        });
    }

}