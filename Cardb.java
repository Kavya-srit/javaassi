import java.util.Scanner;

public class Cardb {
    public static void main(String[] args) {

        // Car details
        String[] carModels = {
                "Polo Trendline",
                "Polo Highline",
                "Virtus Trendline",
                "Virtus Highline",
                "Taigun Trendline",
                "Taigun Highline",
                "Taigun Topline"
        };
        double[] showroomPrices = {
                870000,
                1009000,
                1105000,
                1308000,
                1489000,
                1542000,
                1771000
        };

        // Additional fees and charges
        double rtoCharge = 113990;
        double insuranceCharge = 47300;
        double tcsCharge = 11000;
        double additionalAccessoriesCharge = 15000;

        // User input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a car model:");
        for (int i = 0; i < carModels.length; i++) {
            System.out.println((i + 1) + ". " + carModels[i]);
        }
        int carChoice = scanner.nextInt();
        scanner.nextLine();

        String carModel = carModels[carChoice - 1];

        double carCost = showroomPrices[carChoice - 1];

        double totalAmount = carCost;

        System.out.println("Do you want to opt for insurance? (Y/N)");
        char insuranceChoice = scanner.next().charAt(0);

        System.out.println("Do you want to opt for additional accessories? (Y/N)");
        char accessoriesChoice = scanner.next().charAt(0);

        double discount = 0;
        if (insuranceChoice == 'Y' || accessoriesChoice == 'Y') {
            System.out.println("Enter the discount amount (in rupees or percentage):");
            discount = scanner.nextDouble();
            if (discount > 30000) {
                discount = 30000;
                System.out.println("Error: Maximum discount applied: 30,000");
            }
        } else {
            System.out.println("Error: Either insurance or additional accessories must be selected.");
            discount = 0;
        }

        // total amount
        totalAmount += tcsCharge + rtoCharge;

        if (insuranceChoice == 'Y')
            totalAmount += insuranceCharge;
        if (accessoriesChoice == 'Y')
            totalAmount += additionalAccessoriesCharge;

        // discount
        totalAmount -= discount;

        System.out.println("Total amount to be paid: " + totalAmount + "(" + carModel + " " + carCost +
                " + " + rtoCharge + "(RTO) + " + insuranceCharge + "(Insurance) + " +
                tcsCharge + "(TCS) + " + additionalAccessoriesCharge + "Additional Accessories - " + discount
                + "(Dealer discount))");

        scanner.close();
    }
}