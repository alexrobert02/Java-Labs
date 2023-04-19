package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SharedMemory {
    private List<Token> tokens;

    public SharedMemory(int n) {
        tokens = new ArrayList<>(n * n * n);
        for (int i = 1; i <= n * n * n - 2; i++) {
            tokens.add(new Token(i));
        }
        // Shuffle the tokens in the list to randomize their order
        //Collections.shuffle(tokens);
    }

    public synchronized Token extractToken() {
        // Return the first token in the list if the list is not empty
        if (tokens.isEmpty()) {
            return null;
        }
        return tokens.remove(0);
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public Boolean itsEmpty() {
        if(tokens.isEmpty()) {
            return true;
        }
        return false;
    }
}