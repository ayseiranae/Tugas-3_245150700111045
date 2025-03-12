import java.util.Locale;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Package {
    private String senderName;
    private String receiverName;
    private String phone;
    private String shippingAdress;
    private double weight;
    private double baseFee;
    private double totalfee;
    private String trackingNumber;

    Locale rupiah = new Locale.Builder().setLanguage("id").setRegion("ID").build();
    NumberFormat ind = NumberFormat.getCurrencyInstance(rupiah);

    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
    private String tanggalWaktu = now.format(formatter);
    private LocalDate estimatedDate;

    public Package() {
        this.senderName = "";
        this.receiverName = "";
        this.phone = "";
        this.shippingAdress = "";
        this.weight = 0;
        this.baseFee = 0;
        this.totalfee = 0;
        this.trackingNumber = "N/A";
        }

    public Package (String senderName, String recieverName, String phone, String shippingAdress, 
                    double weight, double baseFee, double totalfee, String trackingNumber, PackageDestination arrival) {
        this.senderName = senderName;
        this.receiverName = recieverName;
        this.phone = phone;
        this.shippingAdress = shippingAdress;
        this.weight = weight;
        this.baseFee = baseFee;
        this.totalfee = totalfee;
        this.estimatedDate = LocalDate.now().plusDays(arrival.getEstimatedArrival());
        this.trackingNumber = trackingNumber;
    }

    public void displayInfo() {
        System.out.println("Date                : " + tanggalWaktu);
        System.out.println("Sender Name         : " + senderName);
        System.out.println("Reciever Name       : " + receiverName);
        System.out.println("Reciever Phone      : " + phone);
        System.out.println("Destination         : " + shippingAdress);
        System.out.println("Package Weight      : " + weight + " kg");
        System.out.println("Cost/kg             : " + ind.format(baseFee));
        System.out.println("Total Cost          : " + ind.format(totalfee));
        System.out.println("Estimated Arrival   : " + (estimatedDate != null ? estimatedDate.format(formatter) : "Not Available"));
        System.out.println("Tracking Number     : " + trackingNumber);
    }

    public void shippingInfo() {
        displayInfo();
        System.out.println("Shipping Status     : Shipping");
    }
} 