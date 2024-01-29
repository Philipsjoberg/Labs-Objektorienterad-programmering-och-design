import java.awt.*;

public class Saab95 extends Car {

    private boolean turboOn;

    public Saab95(){
        super(2, 125, Color.red, "Saab95");
        turboOn = false;
    }

    public void setTurboOn(){
        turboOn = true;
    }

    public void setTurboOff(){
        turboOn = false;
    }

    @Override
    protected double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
    }

    @Override
    protected void incrementSpeed(double amount){
        if (getCurrentSpeed() + speedFactor() * amount > currentSpeed)
            currentSpeed = getCurrentSpeed() + speedFactor() * amount;
        if (currentSpeed > enginePower)
            currentSpeed = enginePower;
    }

    @Override
    protected void decrementSpeed(double amount){
        if (getCurrentSpeed() - speedFactor() * amount < currentSpeed)
            currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }
}

