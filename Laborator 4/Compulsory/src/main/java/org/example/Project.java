package org.example;

public class Project implements Comparable<Project> {
    String name;
    // constructor
    public Project(String name) {
        this.name = name;
    }
    // getter
    public String getName() {
        return name;
    }
    // setter
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Project o) {
        return this.name.compareTo((o.getName()));
    }

    @Override
    public String toString() {
        return name;
    }

}
