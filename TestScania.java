import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestScania {

    private Scania scania;

    @Before
    public void setup() {
        scania = new Scania();
    }

    @Test
    public void testNotGassingWhenAngledTruckBed() {
        double input = 0.5;
        scania.raisePlatform(35);
        assertThrows(IllegalArgumentException.class, () -> scania.gas(input));
    }

    @Test
    public void testNotIncreasingAngleWhileDriving() {
        double angleBefore = scania.getTruckbedAngle();
        double input = 0.5;
        scania.gas(input);
        scania.raisePlatform(35);
        double angleAfter = scania.getTruckbedAngle();
        assertEquals(angleBefore, angleAfter,0);
    }

    @Test
    public void testNotIncreasedAbove70() {
        scania.raisePlatform(75);
        assertEquals(scania.getTruckbedAngle(),70,0);
    }

    @Test
    public void testNotDecreasedBelow0() {
        scania.lowerPlatform(5);
        assertEquals(scania.getTruckbedAngle(), 0, 0);
    }

    @Test
    public void testGetAngle() {
        assertEquals(scania.getTruckbedAngle(), 0,0);
    }
    @Test
    public void testDecrementSpeed() {
        double input = 0.5;
        scania.gas(input);
        double speedBefore = scania.currentSpeed;
        scania.brake(input);
        double speedAfter = scania.currentSpeed;
        assertNotEquals(speedBefore, speedAfter, 0);
    }
}
