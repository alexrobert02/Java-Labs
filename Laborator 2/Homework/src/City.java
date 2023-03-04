public class City extends Location {
    private int population;
    //constructor
    public City(String name, double x, double y, int population) {
        super(name, "city", x, y);
        setPopulation(population);
    }
    //getter and setter
    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
