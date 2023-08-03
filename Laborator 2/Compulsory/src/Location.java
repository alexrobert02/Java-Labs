public class Location {
    private String name;
    private double x;
    private double y;
    private LocationType type;

    public Location() {
        x = 0;
        y = 0;
        name = "";
    }

    public Location(String name, LocationType type, double x, double y) {
        setName(name);
        setType(type);
        setX(x);
        setY(y);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocationType getType() {
        return type;
    }

    public void setType(LocationType type) {
        this.type = type;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "City name: " + getName() + ", " + "Location type: " + getType() + ", " + "X: " + getX() + ", " + "Y: " + getY();
    }
}
