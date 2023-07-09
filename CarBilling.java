import java.util.Scanner;

class Car {
    private String model;
    private long showroomPrice;

    public Car(String model, long showroomPrice) {
        this.model = model;
        this.showroomPrice = showroomPrice;
    }

    public String getModel() {
        return model;
    }

    public long getShowroomPrice() {
        return showroomPrice;
    }
}

class CarDealer {
    private static final long MAX_DISCOUNT = 30000;

    private Car car;
    private boolean insuranceNeeded;
    private boolean accessoriesNeeded;
    private long discount;

    public CarDealer(Car car, boolean insuranceNeeded, boolean accessoriesNeeded) {
        this.car = car;
        this.insuranceNeeded = insuranceNeeded;
        this.accessoriesNeeded = accessoriesNeeded;
        this.discount = 0;
    }

    public void setDiscount(long discount) {
        this.discount = Math.min(discount, MAX_DISCOUNT);
    }

    public double calculateTotalCost() {
        double totalCost = car.getShowroomPrice();

        if (insuranceNeeded) {
            totalCost += 113990;
            totalCost += 47300;
        }

        if (accessoriesNeeded) {
            totalCost += 11000;
            totalCost += 15000;
        }

        totalCost -= discount;
        return totalCost;
    }

    public String generateInvoice() {
        double totalCost = calculateTotalCost();
        return "Total cost: " + totalCost + " (" + car.getModel() + " "
                + car.getShowroomPrice() + " + " + 113990 + "(RTO) "
                + (insuranceNeeded ? "+ " + 47300 + "(Insurance) " : "")
                + "+ " + 11000 + "(TCS charges) "
                + (accessoriesNeeded ? "+ " + 15000 + "(Additional Accessories) " : "")
                + "- " + discount + "(Dealer discount))";
    }

}

public class CarBilling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Car[] cars = {
                new Car("Polo Trendline", 870000),
                new Car("Polo Highline", 1009000),
                new Car("Virtus Trendline", 1105000),
                new Car("Virtus Highline", 1308000),
                new Car("Taigun Trendline", 1489000),
                new Car("Taigun Highline", 1542000),
                new Car("Taigun Topline", 1771000)
        };

        System.out.println("Select car model:");
        for (int i = 0; i < cars.length; i++) {
            System.out.println((i + 1) + ". " + cars[i].getModel());
        }
        int carIndex = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Do you need Insurance (yes/no):");
        boolean insuranceNeeded = scanner.nextLine().equalsIgnoreCase("yes");

        System.out.println("Do you need Additional Accessories (yes/no):");
        boolean accessoriesNeeded = scanner.nextLine().equalsIgnoreCase("yes");

        Car selectedCar = cars[carIndex - 1];
        CarDealer carDealer = new CarDealer(selectedCar, insuranceNeeded, accessoriesNeeded);

        if (insuranceNeeded || accessoriesNeeded) {
            System.out.println("Dealer discount (in rupees):");
            double discount = scanner.nextDouble();
            carDealer.setDiscount((long) discount);
        } else {
            System.out.println("Error: Please select at least one additional feature.");
            System.exit(0);
        }

        String invoice = carDealer.generateInvoice();
        System.out.println(invoice);

        scanner.close();
    }
}
