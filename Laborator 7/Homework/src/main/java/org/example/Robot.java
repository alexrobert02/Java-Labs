package org.example;

import java.util.*;

public class Robot {
    private String name;
    private ExplorationMap map;
    private SharedMemory memory;
    private int numTokens;

    public Robot(String name, ExplorationMap map, SharedMemory memory) {
        this.name = name;
        this.map = map;
        this.memory = memory;
        this.numTokens = 0;
    }

    public String getName() {
        return name;
    }

    public void storeTokens(int row, int col, List<Token> extractedTokens) {
        map.getCell(row, col).addAll(extractedTokens);
        numTokens += extractedTokens.size();
    }

    public synchronized void pause(long time) throws InterruptedException {
        if (time > 0) {
            wait(time);
        } else {
            wait();
        }
    }

    public synchronized void resume() {
        notifyAll();
    }

    public int getNumTokens() {
        return numTokens;
    }
}