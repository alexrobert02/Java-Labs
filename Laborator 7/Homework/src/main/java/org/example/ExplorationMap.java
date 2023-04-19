package org.example;

import java.util.ArrayList;
import java.util.List;

public class ExplorationMap {
    private final List<Token>[][] map;
    private final int n;

    public ExplorationMap(int n) {
        this.n = n;
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
        }
    }

    public synchronized boolean isVisited(int row, int col) {
        return !map[row][col].isEmpty();
    }

    public int getSize() {
        return n;
    }
}