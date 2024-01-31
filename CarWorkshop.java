import java.util.ArrayList;

public class CarWorkshop <T extends Car> {

    protected String workshopName;
    protected double maxSize;
    protected ArrayList<T> carsInWorkshop;

    public CarWorkshop(String workshopName, double maxSize) {
        this.workshopName = workshopName;
        this.maxSize = maxSize;
        this.carsInWorkshop = new ArrayList<>();
    }

    public void setMaxSize(double size) {
        maxSize = size;
    }

    public double getMaxSize() {
        return maxSize;
    }

    public void leaveCarAtWorkshop(T car) {
        if (carsInWorkshop.size() < maxSize && !carsInWorkshop.contains(car)) {
            carsInWorkshop.add(car);
        } else {
            throw new IllegalArgumentException("Workshop cannot handle this model or car already in Workshop");
        }
    }

    public T getCarFromWorkshop(T car) {
        if (carsInWorkshop.contains(car)) {
            carsInWorkshop.remove(car);
            return car;
        } else {
            throw new IllegalArgumentException("Car not at workshop");
        }
    }

}
