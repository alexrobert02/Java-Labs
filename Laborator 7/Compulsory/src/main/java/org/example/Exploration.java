package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Exploration implements Runnable {
    private final Robot robot;
    private final ExplorationMap map;
    private final SharedMemory memory;
    private final Random rand;

    public Exploration(Robot robot, ExplorationMap map, SharedMemory memory) {
        this.robot = robot;
        this.map = map;
        this.memory = memory;
        this.rand = new Random();
    }

    @Override
    public void run() {
        while (!map.allVisited()) {
            // Choose a random unvisited cell
            int row = rand.nextInt(map.getSize());
            int col = rand.nextInt(map.getSize());
            synchronized (map) {
                if (map.allVisited()) {
                    // If all cells have been visited, break out of the loop
                    break;
                }
                if (!map.isVisited(row, col)) {
                    // Create a list to store the extracted tokens
                    List<Token> extractedTokens = new ArrayList<>();
                    for (int i = 0; i < map.getSize(); i++) {
                        // Extract tokens from shared memory until null or end of list is reached
                        Token token = memory.extractToken();
                        if (token == null) {
                            break;
                        }
                        extractedTokens.add(token);
                    }
                    // Mark the current cell as visited
                    map.markVisited(row, col);
                    // Store the extracted tokens in the map through robot
                    robot.storeTokens(row, col, extractedTokens);
                }
            }
            try {
                // Wait for a random amount of time before exploring the next cell
                Thread.sleep(rand.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Robot " + robot.getName() + " finished exploration.");
    }
}
