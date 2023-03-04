import java.util.*;

public class Problem {
    private Location[] locations;
    private Road[] roads;

    //constructor
    public Problem(Location[] locations, Road[] roads) {
        setLocations(locations);
        setRoads(roads);
    }
    //getters and setters
    public Location[] getLocations() {
        return locations;
    }

    public void setLocations(Location[] locations) {
        this.locations = locations;
    }

    public Road[] getRoads() {
        return roads;
    }

    public void setRoads(Road[] roads) {
        this.roads = roads;
    }

    //concatenarea intregului obiect intr-un string
    @Override
    public String toString() {
        return Arrays.toString(locations) + "\n" + Arrays.toString(roads);
    }

    public boolean isValid() {
        //se verifica conditia impusa de formula matematica a lui Euclid pentru distanta intre doua puncte intr-un plan bidimensional: d = √[(x2 – x1)^2 + (y2– y1)^2]
        for (Road road : roads) {
            double distance = Math.sqrt(Math.pow(road.getB().getY() - road.getA().getY(), 2) + Math.pow(road.getB().getX() - road.getA().getX(), 2));
            if (road.getLength() < distance) {
                return false;
            }
        }
        //verificam daca exista duplicat
        for (Road road1 : roads) {
            int duplicates = 0;
            for (Road road2 : roads) {
                if (road1.equals(road2)) {
                    duplicates++;
                }
            }
            if (duplicates > 1) return false;
        }
        for (Location location1 : locations) {
            int duplicates = 0;
            for (Location location2 : locations) {
                if (location1.equals(location2)) {
                    duplicates++;
                }
            }
            if (duplicates > 1) return false;
        }
        return true;
    }

    public boolean canGo(Location a, Location b) {
        List<Location> visited = new ArrayList<>();
        visited.add(a);
        return dfs(a, b, visited);
    }

    public boolean dfs(Location a, Location b, List<Location> visited) {
        //daca am ajuns in punctul in care cele doua obiecte sunt egale, exista drum intre cele 2 locatii
        if (a.equals(b)) {
            return true;
        }
        for (Road road : roads) {
            //folosim un algoritm de tip dfs pentru a putea parcurge fiecare locatie din array intr-un singur sens.
            if (road.getA().equals(a) && !visited.contains(road.getB())) {
                //odata ce am gasit un posibil drum, vizitam capatul celalalt al drumului si continuam pana cand capatul celalalt al drumului este chiar locatia pe care o cautam.
                visited.add(road.getB());
                if (dfs(road.getB(), b, visited)) {
                    return true;
                }
                //daca drumul ales initial nu a fost bun, ne intoarcem si se incearca alta posibilitate
                visited.remove(visited.size() - 1);
            }
        }
        return false;
    }
}

