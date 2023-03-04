public class GasStation extends Location {
    private double price;
    //constructor
    public GasStation(String name, double x, double y, double price) {
        super(name, "gasstation", x, y);
        setPrice(price);
    }
    //getter and setter
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
