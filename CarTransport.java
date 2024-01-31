import java.awt.*;
import java.util.Stack;

public class CarTransport extends Truck implements Loadable<Car>{

    public Stack<Car> loadedCars;
    public double maxLoad;
    private boolean rampLoadable;
    public CarTransport(double maxLoad) {
        super(2, 250, Color.magenta, "Car Transport");
        loadedCars = new Stack<>();
        this.maxLoad = maxLoad;
        rampLoadable = false;
    }

    public void changePlatform() {
        if (currentSpeed == 0)
        {
            rampLoadable =!rampLoadable;
        }
        else {throw new IllegalStateException("Cannot move platform while moving");
        }

    }
    public boolean getPlatform() {return rampLoadable;}

    public boolean inRange(Car car) {
        double distance = Math.sqrt(Math.pow((this.getxPos() - car.getxPos()), 2) + Math.pow((this.getyPos() - car.getyPos()), 2));
        if (distance >= 10) { return false; }
            else { return true; }
    }
    public void loadObject(Car car) {
        if (car.getsize() == Size.LARGE) {
            throw new IllegalStateException("Cannot load cars of this size");
        }
        if (getPlatform() && inRange(car) && loadedCars.size() < this.maxLoad) {
            car.setxPos(this.getxPos());
            car.setyPos(this.getyPos());
            loadedCars.push(car);
        }  else {throw new IllegalStateException("Cannot load car - check truckbed and distance");}
    }
public void unloadObject() {
    Car unloadingCar;
    if (getPlatform() && !loadedCars.isEmpty()) {
        unloadingCar = loadedCars.pop();
        unloadingCar.setxPos(unloadingCar.getxPos() + 5);
        unloadingCar.setyPos(unloadingCar.getyPos() + 5);
    }
    else throw new IllegalStateException("Truckbed is not closed for loading or truckbed is empty");
}

    public void gas(double amount) {
        if (!getPlatform()) {
            incrementSpeed(amount);
        }
        else throw new IllegalStateException("Truckbed is not closed for loading");
    }

    @Override
    public void move() {
        this.setxPos(currentSpeed * this.getxDir());
        this.setyPos(currentSpeed * this.getyDir());

        for(Car loadedCar : loadedCars)
        {
            loadedCar.setxPos(this.getxPos());
            loadedCar.setyPos(this.getyPos());
        }
    }
}
