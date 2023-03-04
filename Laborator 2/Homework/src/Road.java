import static java.lang.System.exit;

public class Road {
    String name;
    private RoadType type;
    private Location a;
    private Location b;
    private int speedLimit;
    private double length;
    //constructor
    public Road(String name, RoadType type, Location a, Location b, int speedLimit, double length) {
        setName(name);
        setType(type);
        setA(a);
        setB(b);
        setSpeedLimit(speedLimit);
        setLength(length);
    }
    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoadType getType() {
        return type;
    }

    public void setType(RoadType type) {
        this.type = type;
    }

    public Location getA() {
        return a;
    }

    public void setA(Location a) {
        this.a = a;
    }

    public Location getB() {
        return b;
    }

    public void setB(Location b) {
        this.b = b;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "{Road name: " + getName() + ", " + "Road type: " + getType() + ", " + getA() + ", " + getB() + ", " + "Speed limit: " + getSpeedLimit() + ", " + "Length: " + getLength() + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Road)) {
            return false;
        }
        Road other = (Road) obj;
        return name.equals(other.name);
    }

}
