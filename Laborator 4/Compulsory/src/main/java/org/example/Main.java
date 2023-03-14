package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // Create the List of students;
        List<Student> students = IntStream.rangeClosed(0, 3).mapToObj(i -> new Student("S" + i)).collect(Collectors.toList());

        // Create the Set of projects;
        TreeSet<Project> projects = IntStream.rangeClosed(0, 3).mapToObj(i -> new Project("P" + i)).collect(Collectors.toCollection(TreeSet::new));

        // Sort the students by name
        Collections.sort(students);

        // Print the sorted students
        System.out.println("Students: " + students);

        // Print the sorted projects
        System.out.println("Projects: " + projects);
    }
}