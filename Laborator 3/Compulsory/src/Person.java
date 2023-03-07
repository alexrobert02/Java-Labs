public class Person implements Node, Comparable<Person> {
    private String name;

    public Person(String name) {
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
    public int compareTo(Person o) {
        return this.name.compareTo((o.getName()));
    }

    @Override
    public String toString() {
        return getName();
    }
}
