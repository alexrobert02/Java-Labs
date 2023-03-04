public class Airport extends Location {
    private String airline;
    //constructor
    public Airport(String name, double x, double y, String airline) {
        super(name, "airport", x, y);
        setAirline(airline);
    }
    //getter and setter
    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

}
