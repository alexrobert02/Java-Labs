package org.example;

public class Token {
    private int value;

    public Token(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}