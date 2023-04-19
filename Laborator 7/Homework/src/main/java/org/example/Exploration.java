package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Exploration implements Runnable {
    private final Robot robot;
    private final ExplorationMap map;
    private final SharedMemory memory;
    private final Random rand;
    private final int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // north, east, south, west

    public Exploration(Robot robot, ExplorationMap map, SharedMemory memory) {
        this.robot = robot;
        this.map = map;
        this.memory = memory;
        this.rand = new Random();
    }

    @Override
    public void run() {
        Stack<int[]> stack = new Stack<>();
        int[] startingCell = getStartingCell();
        stack.push(startingCell);
        while (!stack.isEmpty()) {
            int[] currentCell = stack.pop();
            int currentRow = currentCell[0];
            int currentCol = currentCell[1];
            synchronized (map.getCell(currentRow, currentCol)) {
                if (!map.isVisited(currentRow, currentCol)) {
                    map.markVisited(currentRow, currentCol);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // set the interrupted flag
                        return; // exit the run() method
                    }
                    List<Token> extractedTokens = new ArrayList<>();
                    for (int i = 0; i < map.getSize(); i++) {
                        Token token = memory.extractToken();
                        if (token == null) {
                            break;
                        }
                        extractedTokens.add(token);
                    }
                    robot.storeTokens(currentRow, currentCol, extractedTokens);
                } else {
                    continue;
                }
            }
            for (int[] direction : directions) {
                int newRow = currentRow + direction[0];
                int newCol = currentCol + direction[1];
                if (newRow >= 0 && newRow < map.getSize() && newCol >= 0 && newCol < map.getSize()) {
                    synchronized (map.getCell(newRow, newCol)) {
                        if (!map.isVisited(newRow, newCol)) {
                            stack.push(new int[]{newRow, newCol});
                        }
                    }
                }
            }
        }
        System.out.println("Robot " + robot.getName() + " finished exploration.");
        System.out.println("Is the memory empty:" + memory.itsEmpty());
    }

    private int[] getStartingCell() {
        synchronized (map) {
            int row = rand.nextInt(map.getSize());
            int col = rand.nextInt(map.getSize());
            while (map.isVisited(row, col)) {
                row = rand.nextInt(map.getSize());
                col = rand.nextInt(map.getSize());
            }
            map.markVisited(row, col);
            return new int[]{row, col};
        }
    }
}