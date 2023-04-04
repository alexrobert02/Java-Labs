package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int size = 5; // size of the exploration map
        int numRobots = 3; // number of robots
        ExplorationMap map = new ExplorationMap(size);
        SharedMemory memory = new SharedMemory(size);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numRobots; i++) {
            Robot robot = new Robot(Integer.toString(i), map, memory);
            Exploration exploration = new Exploration(robot, map, memory);
            Thread thread = new Thread(exploration);
            threads.add(thread);
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}