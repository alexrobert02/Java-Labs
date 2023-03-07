public class Company implements Node, Comparable<Company> {
    private String name;

    public Company(String name) {
        setName(name);
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Company o) {
        return this.name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return getName();
    }
}
