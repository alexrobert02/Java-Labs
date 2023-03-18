package org.example;

import java.util.*;

public class Student implements Comparable<Student> {
    private String name;

    // constructor
    public Student(String name) {
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
    public int compareTo(Student o) {
        return this.name.compareTo((o.getName()));
    }

    @Override
    public String toString() {
        return getName();
    }
}
