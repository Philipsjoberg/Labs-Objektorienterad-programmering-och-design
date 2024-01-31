import java.awt.*;

public class Scania extends Truck {

    private double truckbedAngle;
    public Scania() {
        super(2, 250, Color.lightGray, "Scania Truck");
        truckbedAngle = 0;
    }

    public double getTruckbedAngle() {return truckbedAngle;}

    public void lowerPlatform(double degrees) {
        if (currentSpeed == 0)
        {
            truckbedAngle = Math.max(truckbedAngle - degrees, 0);
        }
    }
    public void raisePlatform(double degrees) {
        if (currentSpeed == 0)
        {
            truckbedAngle = Math.min(truckbedAngle + degrees, 70);
        }
    }

    public void gas(double amount) {
        if (truckbedAngle == 0) {
            incrementSpeed(amount);
        }
        else throw new IllegalArgumentException("Truckebed is open");
    }
}

