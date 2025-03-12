import java.util.HashMap;

class Shipping {
    private final HashMap<String, Package> shippingData = new HashMap<>();
    
    public void addShipment(String code, Package packages) {
        shippingData.put(code, packages);
    }

    public double calculateFee(double weight, double baseFee) {
        return weight * baseFee;
    }

    public double finalFee(double diskonPersen, double baseFee) {
            return calculateFee(1, baseFee) * (1 - diskonPersen / 100);
    }

    public double finalFee(double diskonPersen, double baseFee, double weight) {
            return calculateFee(1, baseFee) * (1 - diskonPersen / 100) + calculateFee(weight - 1, baseFee);
    }

    public double finalFee(int jarak, double totalFee) {
        if (jarak > 50) {
            return totalFee * 1.1;
        } else {
            return totalFee * 0.95;
        }
    }

    public Package checkStatus(String trackingNumber) {
        return shippingData.get(trackingNumber);
    }
}