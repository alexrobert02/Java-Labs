package org.example;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem {
    Map<Student, Set<Project>> prefMap;
    // constructor
    public Problem(int nrStudents, int minPrefs, int maxPrefs) {
        Faker faker = new Faker(); // Use the class Faker to generate random names for students and projects
        Map<Student, Set<Project>> prefMap = IntStream.rangeClosed(0, nrStudents)
                .mapToObj(i -> {
                    String studentName = faker.name().name(); // Generate random names for students
                    TreeSet<Project> projects = IntStream.rangeClosed(0, (int) (Math.random() *(maxPrefs - minPrefs) + minPrefs))
                            .mapToObj(j -> new Project(faker.color().name())) // Generate random names for the projects
                            .collect(Collectors.toCollection(TreeSet::new));
                    return Map.entry(new Student(studentName), projects);
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getKey)) // Sort the stream by student names
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (s1, s2) -> s1, LinkedHashMap::new));
        this.prefMap = prefMap;
    }
    public double getAvgPrefs() {
        double avgPrefs = prefMap.values().stream()
            .mapToInt(Set::size)
            .average() // Calculate the average number of projects
            .orElse(0);
        return avgPrefs;
    }

    public void printLessThanAvg(){
        System.out.println("Students with less than average preferences:");
        prefMap.keySet().stream()
                .filter(student -> prefMap.get(student).size() < getAvgPrefs()) // Filter and print the students that have
                .forEach(System.out::println); // the number of preferences less then average
    }
    // getter
    public Map<Student, Set<Project>> getPrefMap() {
        return prefMap;
    }
    // setter
    public void setPrefMap(Map<Student, Set<Project>> prefMap) {
        this.prefMap = prefMap;
    }

    public void greedy() {
        List<Student> students = new ArrayList<>(prefMap.keySet()); // List of students taken from the keySet of the prefMap
        students.sort(Comparator.comparingInt(s -> prefMap.get(s).size())); // Sort the list by number of preferences
        Set<Project> assignedProjects = new HashSet<>(); // Create a Set to make sure there are no duplicate projects
        Map<Student, Project> assignmentMap = new TreeMap<>(); // Create a TreeMap to get it sort automatically
        for (Student student : students) {
            boolean assigned = false;
            for (Project project : prefMap.get(student)) {
                if (!assignedProjects.contains(project)) { // If the project is not already in assignedProjects,
                    assignmentMap.put(student, project); // put the pair in assignmentMap
                    assignedProjects.add(project); // Mark the project as 'assigned'
                    assigned = true;
                    break;
                }
            }
            if (!assigned) { // If the student was not assigned a project, add them to the assignment map
                assignmentMap.put(student, null); // with a null project to indicate that they were not assigned any project
            }
        }
        // Print the map obtained
        System.out.println("\n" + "Assignment map:");
        for(Map.Entry<Student, Project> entry : assignmentMap.entrySet()){
            System.out.println(entry.getKey().getName() + " -> " + entry.getValue().getName());
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Student, Set<Project>> entry : prefMap.entrySet()) {
            sb.append("Student: " + entry.getKey()).append(", Admissible projects: ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}
