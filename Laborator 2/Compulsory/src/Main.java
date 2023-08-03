public class Main {
    public static void main(String[] args) {
        Location c1 = new Location("Galati", LocationType.CITY, 10.0, 20.0);
        Location c2 = new Location("Vaslui", LocationType.CITY, 50.0, 70.0);
        Road r1 = new Road("Drum Galati-Vaslui", RoadType.HIGHWAY, c1, c2, 90, 70);
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(r1);
    }
}