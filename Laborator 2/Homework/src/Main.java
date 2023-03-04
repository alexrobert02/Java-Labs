import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //creating locations
        Location location1 = new City("Galati", 45.45, 28.05, 200000);
        Location location2 = new Airport("George Enescu", 46.519831254, 26.906163042, "Blue Air");
        Location location3 = new GasStation("MOL", 47.182043, 27.468345, 6.62);
        Location location4 = new City("Vaslui", 48, 30, 100000);
        Location[] locations = {location1, location2, location3, location4};

        //creating roads
        Road road1 = new Road("Galati-aeroport", RoadType.HIGHWAY, location1, location2, 70, 1.8);
        Road road2 = new Road("aeroport-Benzinarie", RoadType.HIGHWAY, location2, location3, 80, 1.2);
        Road road3 = new Road("benzinarie, Galati", RoadType.HIGHWAY, location3, location1, 60, 2);
        Road road4 = new Road("benzinarie-Vaslui", RoadType.HIGHWAY, location3, location4, 70, 4);
        Road[] roads = {road1, road2, road3, road4};

        //creating problem instance
        Problem pb = new Problem(locations, roads);
        System.out.println(pb);
        System.out.println("Is the problem valid: " + pb.isValid());
        System.out.println("Is it possible to go from these two locations: " + pb.canGo(location1, location4));
    }
}