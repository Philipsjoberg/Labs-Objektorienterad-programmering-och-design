import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class Junit {

    private Saab95 saab;
    private Volvo240 volvo;
    @Before
    public void setup () {
        saab = new Saab95();
        volvo = new Volvo240();
    }

    @Test
    public void testStartEngine() {
        volvo.startEngine();
        saab.startEngine();
        assertEquals(0.1, saab.getCurrentSpeed(), 0.0);
        assertEquals(0.1, volvo.getCurrentSpeed(), 0.0);
    }

    @Test
    public void testMinSpeedisZero() {
        volvo.brake(1);
        saab.brake(1);
        assertEquals(0, volvo.getCurrentSpeed(), 0);
        assertEquals(0, saab.getCurrentSpeed(), 0);

    }

    @Test
    public void testMaxSpeedisEnginePower(){
        double volvo_current_speed;
        double volvo_prev_speed;
        volvo_current_speed = volvo.getCurrentSpeed();
        volvo_prev_speed = -1;

        double saab_current_speed;
        double saab_prev_speed;
        saab_current_speed = saab.getCurrentSpeed();
        saab_prev_speed = -1;

        while (volvo_current_speed > volvo_prev_speed){
            volvo_prev_speed = volvo.getCurrentSpeed();
            volvo.gas(1);
            volvo_current_speed = volvo.getCurrentSpeed();


        }

        while (saab_current_speed > saab_prev_speed){
            saab_prev_speed = saab.getCurrentSpeed();
            saab.gas(1);
            saab_current_speed = saab.getCurrentSpeed();

        }

        assertEquals(volvo.getEnginePower(), volvo.getCurrentSpeed(), 0);
        assertEquals(saab.getEnginePower(), saab.getCurrentSpeed(), 0);

    }

    @Test
    public void testGasInputInRange0to1() {
        double input = 2;
        double SpeedBeforeGasVolvo = volvo.getCurrentSpeed();
        double SpeedBeforeGasSaab = volvo.getCurrentSpeed();
        saab.gas(input);
        volvo.gas(input);
        double SpeedAfterGasVolvo = volvo.getCurrentSpeed();
        double SpeedAfterGasSaab = volvo.getCurrentSpeed();
        assertEquals(SpeedBeforeGasVolvo, SpeedAfterGasVolvo,0);
        assertEquals(SpeedBeforeGasSaab, SpeedAfterGasSaab, 0);
    }

    @Test
    public void testBrakeInputInRange0to1() {
        double input = 2;
        double SpeedBeforeGasVolvo = volvo.getCurrentSpeed();
        double SpeedBeforeGasSaab = volvo.getCurrentSpeed();
        saab.brake(input);
        volvo.brake(input);
        double SpeedAfterGasVolvo = volvo.getCurrentSpeed();
        double SpeedAfterGasSaab = volvo.getCurrentSpeed();
        assertEquals(SpeedBeforeGasVolvo, SpeedAfterGasVolvo, 0);
        assertEquals(SpeedBeforeGasSaab, SpeedAfterGasSaab, 0);
    }

    @Test
    public void testGasNotDecreases() {

        for (int i = 0; i < 15; i++);
        {
            double volvo_initial_speed = volvo.getCurrentSpeed();
            double saab_initial_speed = saab.getCurrentSpeed();
            volvo.gas(1);
            saab.gas(1);
            assertTrue(volvo_initial_speed < volvo.getCurrentSpeed() || volvo_initial_speed == volvo.getCurrentSpeed());
            assertTrue(saab_initial_speed < saab.getCurrentSpeed() || saab_initial_speed == saab.getCurrentSpeed());
        }
    }

    @Test
    public void testBrakeNotIncreases() {
        double volvo_initial_speed = volvo.getCurrentSpeed();
        double saab_initial_speed = saab.getCurrentSpeed();
        volvo.brake(1);
        saab.brake(1);
        assertTrue(volvo_initial_speed > volvo.getCurrentSpeed() || volvo_initial_speed == volvo.getCurrentSpeed());
        assertTrue(saab_initial_speed > saab.getCurrentSpeed() || saab_initial_speed == saab.getCurrentSpeed());

    }

    @Test
    public void testTurnLeft() {
        volvo.turnLeft();
        saab.turnLeft();
        assertTrue(volvo.getxDir() == -1 && volvo.getyDir() == 0);
        assertTrue(saab.getxDir() == -1 && saab.getyDir() == 0);
    }
    
    @Test
    public void testTurnRight() {
        volvo.turnRight();
        saab.turnRight();
        assertTrue(volvo.getxDir() == 1 && volvo.getyDir() == 0);
        assertTrue(saab.getxDir() == 1 && saab.getyDir() == 0);
    }
    
    @Test
    public void testMoving() {
        volvo.gas(1);
        saab.gas(1);
        volvo.move();
        saab.move();
        assertTrue(volvo.getxDir() == 0 && volvo.getyDir() > 0);
        assertTrue(saab.getxDir() == 0 && saab.getyDir() > 0);

    }
    @Test 
    public void testSpeedfactor() {
        double shouldbe = saab.enginePower * 1.3 * 0.01;
        saab.setTurboOn();
        double itis = saab.speedFactor();
        System.out.println(shouldbe);
        System.out.println(itis);
        assertTrue(itis == shouldbe);
    }

    @Test
    public void testTrimfactor() {
        double shouldbe = volvo.enginePower * 1.25 * 0.01;
        double itis = volvo.speedFactor();
        assertTrue(itis == shouldbe);
    }
}