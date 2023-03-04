public abstract class Location {
    protected String name;
    protected String type;
    protected double x;
    protected double y;
    //constructor
    public Location(String name, String type, double x, double y) {
        setName(name);
        setType(type);
        setX(x);
        setY(y);
    }
    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
        return "{Location name: " + getName() + ", " + "Location type: " + getType() + ", " + "X: " + getX() + ", " + "Y: " + getY() + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Location)) {
            return false;
        }
        Location other = (Location) obj;
        return name.equals(other.name);
    }
}
