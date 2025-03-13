import java.util.*;
import java.text.NumberFormat;

public class LogisticMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Locale rupiah = new Locale.Builder().setLanguage("id").setRegion("ID").build();
        NumberFormat ind = NumberFormat.getCurrencyInstance(rupiah);
        Shipping system = new Shipping();
        int choice = 0;
        
        System.out.println("____________________________________________________________");
        Typography.center("WELCOME TO LOGISWIFT");
        Typography.center("Your Goods, Swift and Secure!");
        boolean loop = true;
        while (loop) {
            System.out.println("Menu:");
            System.out.println("1. Check Shipping Fee");
            System.out.println("2. Check Shipping Status");
            System.out.println("3. Send Package");
            System.out.println("4. Exit");
            System.out.println();
            System.out.print("Enter Menu: ");
            choice = sc.nextInt();
            sc.nextLine();

            List<PackageDestination> list = new ArrayList<>();
            list.add(new PackageDestination("", 0, 0, 0, null));
            list.add(new PackageDestination("Kab. Malang", 3770, 377, 1, "day"));
            list.add(new PackageDestination("Blitar", 7800, 78, 1, "day"));
            list.add(new PackageDestination("Tulungagung", 10250, 102, 2, "day"));
            list.add(new PackageDestination("Kediri", 10300, 103, 2, "day"));
            list.add(new PackageDestination("Gresik", 10890, 108, 2, "day"));
            list.add(new PackageDestination("Ngawi", 24900, 249, 3, "day"));
            list.add(new PackageDestination("Banyuwangi", 27800, 278, 3, "day"));

            if (choice == 1) {
                System.out.println("____________________________________________________________");
                Typography.center("Shipping Destinations & Fees");
                System.out.println();
                System.out.println("+-------------------+-----------------+--------------------+");
                System.out.printf("| %-17s | %-15s | %-18s |\n", "Destination", "Price", "Estimated Arrival");
                System.out.println("+-------------------+-----------------+--------------------+");

                for (int i = 1; i < list.size(); i++) {
                    PackageDestination p = list.get(i);
                    System.out.printf("| %-17s | %-15s | %-18s |\n",
                                    p.getCity(),
                                    ind.format(p.getPricePerKg()),
                                    p.getEstimatedArrival());
                }
                System.out.println("+-------------------+-----------------+--------------------+");
                System.out.println();
                System.out.println("____________________________________________________________");

                boolean check = true;
                while (check) {
                    System.out.println("Check Your Fee (y/n)");
                    String shop = sc.nextLine();
                    if (shop.equalsIgnoreCase("n") || !shop.equalsIgnoreCase("y")) {
                        System.out.println("____________________________________________________________");
                        check = false;
                        break;
                    }
                    System.out.print("Enter your destination: ");
                    String dest = sc.nextLine();
                    PackageDestination hasil = null;
                    for (PackageDestination p : list) {
                        hasil = p;
                        if (p.getCity().equalsIgnoreCase(dest)) {
                            System.out.print("Enter your package weight: ");
                            double weig = sc.nextDouble();
                            sc.nextLine();
                            Shipping newFee = new Shipping();
                            double baseFee = p.getPricePerKg();
                            double totalFee = newFee.calculateFee(weig, p.getPricePerKg());
                            System.out.println("____________________________________________________________");
                            System.out.println("Cost per kg         : " + ind.format(baseFee));
                            System.out.println("Total Shipping Fee  : " + ind.format(totalFee));
                            System.out.println("____________________________________________________________");
                            System.out.println();
                            break;
                        } 
                        if (hasil == null ) {
                            System.out.println("Destination to " + dest + " Not Found.");
                            System.out.println();
                            break;
                        }
                    }
                    
                }
            } else if (choice == 2) {
                System.out.println("Enter tracking number: ");
                String trackingNumber = sc.nextLine();
                Package foundPackage = system.checkStatus(trackingNumber);
                if (foundPackage != null) {
                    System.out.println();
                    System.out.println("____________________________________________________________");
                    foundPackage.shippingInfo();
                    System.out.println("____________________________________________________________");
                    System.out.println();
                } else {
                    System.out.println("Tracking number not found.");
                    System.out.println("____________________________________________________________");
                    System.out.println();
                }
            } else if (choice == 3) {
                System.out.println("____________________________________________________________");
                Typography.center("Trust your package on Us");
                System.out.println();
                System.out.print("Please insert sender name: ");
                String sender = sc.nextLine();
                System.out.print("Please insert reciever name: ");
                String receiver = sc.nextLine();
                System.out.print("Please insert reciever phone number: ");
                String phone = sc.nextLine();
                System.out.print("Please insert reciever address: ");
                String address = sc.nextLine();
                System.out.print("Please insert destination city: ");
                String dest = sc.nextLine();
                System.out.print("Please enter your package weight: ");
                String weight = sc.nextLine();
                double weightx = 0;
                if (weight.isEmpty()) {
                    
                } else {
                    weightx = Double.parseDouble(weight);
                }
                System.out.println();
                Typography.center("Thank you for your cooperations.");
                System.out.println("____________________________________________________________");

                PackageDestination selectedDestination = null;
                for (PackageDestination p : list) {
                    if (p.getCity().equalsIgnoreCase(dest)) {
                        selectedDestination = p;
                        break;
                    }
                }

                if (selectedDestination == null) {
                    System.out.println("Destination not found. Cannot proceed with shipping.");
                } else {
                    double baseFee = selectedDestination.getPricePerKg();
                    double totalFee = 0;
                    if (weightx == 1) {
                        totalFee = system.finalFee(5.0, selectedDestination.getPricePerKg());
                    } else if (weightx >= 2) {
                        totalFee = system.finalFee(10.0, selectedDestination.getPricePerKg(), weightx);
                    } 

                    double finalFee = system.finalFee(selectedDestination.getKm(), totalFee);
                    String trackingNumber = Tracking.generateTrackingNumber();
                    
                    Package newPackage = new Package();
                    if (sender.isEmpty() || receiver.isEmpty() || phone.isEmpty() || address.isEmpty() || dest.isEmpty() || weight.isEmpty()) {
                        newPackage = new Package();
                        system.addShipment("N/A", newPackage);
                    } else {
                        newPackage = new Package(sender, receiver, phone, address, weightx, baseFee, finalFee, trackingNumber, selectedDestination);
                        system.addShipment(trackingNumber, newPackage);
                    }                    
                    
                    System.out.println("____________________________________________________________");
                    Typography.center("Your package has been successfully shipped!");
                    Typography.center("Your package information:");
                    System.out.println();
                    newPackage.displayInfo();
                    System.out.println("____________________________________________________________");
                }
            } else if (choice == 4) {
                loop = false;
                System.out.println("____________________________________________________________");
                Typography.center("Thank you for using LOGISWIFT!");
                System.out.println("____________________________________________________________");
            } 
            else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
        sc.close();
    }
}
