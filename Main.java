import java.util.ArrayList;
import java.util.List;

class Driver {
    private String name;
    private String carModel;
    private double rating;
    private double distanceFromCustomer;

    public Driver(String name, String carModel, double rating, double distanceFromCustomer) {
        this.name = name;
        this.carModel = carModel;
        this.rating = rating;
        this.distanceFromCustomer = distanceFromCustomer;
    }

    public String getName() {
        return name;
    }

    public String getCarModel() {
        return carModel;
    }

    public double getRating() {
        return rating;
    }

    public double getDistanceFromCustomer() {
        return distanceFromCustomer;
    }
}

class RideBookingSystem {
    private List<Driver> drivers;

    public RideBookingSystem() {
        this.drivers = new ArrayList<>();
    }

    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

    public String bookRide(double rideDistance, String requestedCar) {
        double minDistance = Double.MAX_VALUE;
        Driver selectedDriver = null;

        for (Driver driver : drivers) {
            if (driver.getRating() < 4) {
                continue;
            }

            if (!driver.getCarModel().equalsIgnoreCase(requestedCar)) {
                continue;
            }

            double distance = driver.getDistanceFromCustomer();
            if (distance < minDistance) {
                minDistance = distance;
                selectedDriver = driver;
            }
        }

        if (selectedDriver != null) {
            double totalCharge = rideDistance * 8;
            return "Driver " + selectedDriver.getName() + " will get you to the destination.\n" +
                    "Your charge will be Rs " + totalCharge + " (" + rideDistance + " * 8).";
        } else {
            return "No suitable driver found for the requested car.";
        }
    }
}

public class Main {
    public static void main(String[] args) {
        RideBookingSystem bookingSystem = new RideBookingSystem();

        // Adding drivers
        bookingSystem.addDriver(new Driver("A", "Sedan", 4.0, 500));
        bookingSystem.addDriver(new Driver("B", "Hatchback", 4.3, 1000));
        bookingSystem.addDriver(new Driver("C", "5-Seater", 4.8, 200));
        bookingSystem.addDriver(new Driver("D", "Sedan", 4.1, 700));
        bookingSystem.addDriver(new Driver("E", "Hatchback", 4.7, 430));

        // Example 1
        double rideDistance1 = 43;
        String requestedCar1 = "Sedan";
        String output1 = bookingSystem.bookRide(rideDistance1, requestedCar1);
        System.out.println(output1);

        // Example 2
        double rideDistance2 = 20.5;
        String requestedCar2 = "Hatchback";
        String output2 = bookingSystem.bookRide(rideDistance2, requestedCar2);
        System.out.println(output2);
    }
}
