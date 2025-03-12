class PackageDestination {
    private String city;
    private double pricePerKg;
    private int km;
    private int estimatedArrival;
    private String day;

    public PackageDestination(String city, double pricePerKg, int km, int estimatedArrival, String day) {
        this.city = city;
        this.pricePerKg = pricePerKg;
        this.km = km;
        this.estimatedArrival = estimatedArrival;
        this.day = day;
    }

    public String getCity() {
        return city;
    }

    public double getPricePerKg() {
        return pricePerKg;
    }

    public int getKm() {
        return km;
    }

    public int getEstimatedArrival() {
        return estimatedArrival;
    }
}