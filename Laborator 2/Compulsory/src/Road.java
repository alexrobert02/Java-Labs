import static java.lang.System.exit;

public class Road {
    private String name;
    private RoadType type;
    private Location a;
    private Location b;
    private int speedLimit;
    private double length;

    public Road(String name, RoadType type, Location a, Location b, int speedLimit, double length) {
        setName(name);
        setType(type);
        setA(a);
        setB(b);
        setSpeedLimit(speedLimit);
        setLength(a, b, length);
    }

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

    public void setLength(Location a, Location b, double length) {
        //Euclidean distance formula
        this.length = Math.sqrt(Math.pow(b.getY() - a.getY(), 2) + Math.pow(b.getX() - a.getX(), 2));
        if(length < Math.sqrt(Math.pow(b.getY() - a.getY(), 2) + Math.pow(b.getX() - a.getX(), 2))) {
            System.out.println("The length of the road '" + getName() + "' is less than the euclidian distance between these 2 locations.");
            exit(1);
        }
        else
            this.length = length;
    }

    @Override
    public String toString() {
        return "Road name: " + getName() + ", " + "Road type: " + getType() + ", " + getA() + ", " + getB() + ", " + "Speed limit: " + getSpeedLimit() + ", " + "Length: " + getLength();
    }

}
