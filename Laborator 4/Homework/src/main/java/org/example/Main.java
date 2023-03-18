package org.example;

import com.github.javafaker.Faker;

public class Main {
    public static void main(String[] args) {
        // Create and generate the problem
        Problem problem = new Problem(10, 1, 10);
        // Print all the info about problem
        System.out.println(problem);
        // Display students with low preferences
        problem.printLessThanAvg();
        // Solve and print the problem using a greedy algorithm.
        problem.greedy();
    }
}