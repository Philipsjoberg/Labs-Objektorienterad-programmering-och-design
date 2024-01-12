import java.awt.*;

public abstract class Car implements Movable {
    protected int nrDoors;
    protected double enginePower;
    protected double currentSpeed;
    protected Color color;
    protected String modelName;
    protected double xPos;
    protected double yPos;
    protected double xDir;
    protected double yDir;

    public Car(int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.xPos = 0;
        this.yPos = 0;
        this.xDir = 0;
        this.yDir = 1;
        stopEngine();
    }

    protected int getNrDoors() {
        return nrDoors;
    }

    protected double getEnginePower() {
        return enginePower;
    }

    protected double getCurrentSpeed() {
        return currentSpeed;
    }

    protected double getxPos() { return xPos; }
    protected double getyPos() { return yPos; }
    protected double getxDir() { return xDir; }
    protected double getyDir() { return yDir; }

    protected Color getColor() {
        return color;
    }

    protected void setColor(Color clr) {
        color = clr;
    }

    protected void startEngine() {
        currentSpeed = 0.1;
    }

    protected void stopEngine() {
        currentSpeed = 0;
    }

    protected abstract double speedFactor();

    protected abstract void incrementSpeed(double amount);

    protected abstract void decrementSpeed(double amount);

    protected void gas(double amount) {
        if (amount < 0 || amount > 1) {
            System.out.println("Amount is not within the required range");
        } else {
            incrementSpeed(amount);
        }
    }

    protected void brake(double amount) {
        if (amount < 0 || amount > 1) {
            System.out.println("Amount is not within the required range");
        } else {
            if (this.currentSpeed <= 0) {
                this.currentSpeed = 0;
            } else {
                decrementSpeed(amount);
            }
        }
    }
    public void move() {
        this.xPos += currentSpeed * this.xDir;
        this.yPos += currentSpeed * this.yDir;

    }
    public void turnLeft() {
        System.out.println(this.xDir);
        System.out.println(this.yDir);

        if (this.xDir == 0 && this.yDir == 1) {
            this.xDir = -1;
            this.yDir = 0;
            return;
        }
        if (this.xDir == -1 && this.yDir == 0) {
            this.xDir = 0;
            this.yDir = -1;
            return;
        }
        if (this.xDir == 0 && this.yDir == -1) {
            this.xDir = 1;
            this.yDir = 0;
            return;
        }
        if (this.xDir == 1 && this.yDir == 0) {
            this.xDir = 0;
            this.yDir = 1;
        }
    }
    public void turnRight() {
        if (this.xDir == 0 && this.yDir == 1) {
            this.xDir = 1;
            this.yDir = 0;
            return;
        }
        if (this.xDir == -1 && this.yDir == 0) {
            this.xDir = 0;
            this.yDir = 1;
            return;
        }
        if (this.xDir == 0 && this.yDir == -1) {
            this.xDir = -1;
            this.yDir = 0;
            return;
        }
        if (this.xDir == 1 && this.yDir == 0) {
            this.xDir = 0;
            this.yDir = -1;
        }
    }
}


    