package org.example;

import java.util.ArrayList;
import java.util.List;

public class ExplorationMap {
    private final List<Token>[][] map;
    private final int n;
    private int visitedCount;

    public ExplorationMap(int n) {
        this.n = n;
        this.visitedCount = 0;
        map = new List[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = new ArrayList<>();
            }
        }
    }

    public synchronized List<Token> getCell(int row, int col) {
        return map[row][col];
    }

    public synchronized void markVisited(int row, int col) {
        if (!isVisited(row, col)) {
            visitedCount++;
            System.out.println("Cell (" + row + "," + col + ") has been visited.");
        }
    }

    public synchronized boolean allVisited() {
        return visitedCount == n * n;
    }

    public synchronized boolean isVisited(int row, int col) {
        return !map[row][col].isEmpty();
    }

    public int getSize() {
        return n;
    }
}