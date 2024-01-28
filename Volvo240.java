import java.awt.*;

public class Volvo240 extends Car {

    protected final static double trimFactor = 1.25;

    public Volvo240() {
        super(4, 100, Color.black, "Volvo240");
    }

    @Override
    public double speedFactor() {
        return enginePower * 0.01 * trimFactor;
    }

    @Override
    public void incrementSpeed(double amount) {
        if (Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower) > currentSpeed) {
            currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);

        }
    }

    @Override
    public void decrementSpeed(double amount) {
        if (Math.max(getCurrentSpeed() - speedFactor() * amount, 0) < currentSpeed) {
            currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
        }
    }
}


