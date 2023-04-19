package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int size = 5; // size of the exploration map
        int numRobots = 3; // number of robots
        long timeLimit = 60000; // 1 minute in milliseconds

        ExplorationMap map = new ExplorationMap(size);
        SharedMemory memory = new SharedMemory(size);
        List<Robot> robots = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        // Create a TimeKeeper thread and start it as a daemon
        TimeKeeper timeKeeper = new TimeKeeper(timeLimit);
        Thread timeKeeperThread = new Thread(timeKeeper);
        timeKeeperThread.setDaemon(true);
        timeKeeperThread.start();

        // Listen for keyboard commands to start/pause robots
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter command (start, pause, resume, quit): ");
            String command = scanner.nextLine();
            if (command.equals("start")) {
                // Create the player threads
                for (int i = 0; i < numRobots; i++) {
                    Robot robot = new Robot(Integer.toString(i), map, memory);
                    robots.add(robot);
                    Exploration exploration = new Exploration(robot, map, memory);
                    Thread thread = new Thread(exploration);
                    threads.add(thread);
                    thread.start();
                }
                System.out.println("The robots have been started.");
            } else if (command.equals("pause")) {
                // Pause all running robots
                for (Robot robot : robots) {
                    synchronized (robot) {
                        try {
                            robot.pause(-1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("The robots have been paused.");
            } else if (command.equals("resume")) {
                // Resume all paused robots
                for (Robot robot : robots) {
                    synchronized (robot) {
                        robot.resume();
                    }
                }
                System.out.println("The robots have been resumed.");
            } else if (command.equals("quit")) {
                // Stop all running threads and exit
                for (Thread playerThread : threads) {
                    playerThread.interrupt();
                }
                System.out.println("You have quit the exploration.");
                break;
            }

            // Check if the time is up and stop the TimeKeeper thread if necessary
            if (timeKeeper.isTimeUp()) {
                timeKeeperThread.interrupt();
                break;
            }
        }
        scanner.close();

        // Wait for the player threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Print how many tokens each robot has placed in the matrix
        for (Robot robot : robots) {
            System.out.println("Robot " + robot.getName() + " placed " + robot.getNumTokens() + " tokens.");
        }

    }
}
