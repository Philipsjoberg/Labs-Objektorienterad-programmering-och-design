import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.*;


public class TestCarTransport {
    private CarTransport carTransport;
    private Volvo240 volvo1;
    private Volvo240 volvo2;
    private Saab95 saab1;
    private Saab95 saab2;

    @Before
    public void setUp() {
        carTransport = new CarTransport(4);
        volvo1 = new Volvo240();
        volvo2 = new Volvo240();
        saab1 = new Saab95();
        saab2 = new Saab95();
    }
    @Test
    public void testNotGassingWhenTruckBedLowered() {
        double input = 0.5;
        carTransport.changePlatform();
        assertThrows(IllegalStateException.class, () -> carTransport.gas(input));
    }
    @Test
    public void testNotIncreasingAngleWhileDriving() {
        double input = 0.5;
        carTransport.gas(input);
        assertThrows(IllegalStateException.class, () -> carTransport.changePlatform());
    }
    @Test
    public void testLoadCars(){
        carTransport.changePlatform();
        carTransport.loadObject(volvo1);
        carTransport.loadObject(volvo2);
        carTransport.loadObject(saab1);
        carTransport.loadObject(saab2);

        Stack<Car> loadedCars = carTransport.loadedCars;

        assertEquals(4, loadedCars.size());

        assertSame(saab2, loadedCars.pop());
        assertSame(saab1, loadedCars.pop());
        assertSame(volvo2, loadedCars.pop());
        assertSame(volvo1, loadedCars.pop());
    }

    @Test
    public void unloadCarWhenTruckbedRaised(){
        carTransport.changePlatform();
        carTransport.loadObject(volvo1);
        carTransport.loadObject(saab2);
        carTransport.changePlatform();
        assertThrows(IllegalStateException.class, ()-> carTransport.unloadObject());


    }

    @Test
    public void movingCarsWithCarTransport() {
        double input = 0.5;
        carTransport.changePlatform();
        carTransport.loadObject(volvo1);
        carTransport.changePlatform();
        carTransport.gas(input);
        carTransport.move();
        assertEquals(carTransport.getyPos(), volvo1.getyPos(), 0);
    }

}
