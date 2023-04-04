package org.example;

import java.util.*;

public class Robot {
    private String name;
    private ExplorationMap map;
    private SharedMemory memory;

    public Robot(String name, ExplorationMap map, SharedMemory memory) {
        this.name = name;
        this.map = map;
        this.memory = memory;
    }

    public String getName() {
        return name;
    }

    public void storeTokens(int row, int col, List<Token> extractedTokens) {
        // Add all tokens to the cell
        map.getCell(row, col).addAll(extractedTokens);
        System.out.println("Robot " + name + " stored " + extractedTokens.size() + " tokens in cell (" + row + "," + col + "): " + extractedTokens.toString());
    }
}