package org.example.tictactoe25.models;

public class Symbol {
    private char aChar;

    public Symbol(char aChar) {
        this.aChar = aChar;
    }

    public char getaChar() {
        return aChar;
    }

    public void setaChar(char aChar) {
        this.aChar = aChar;
    }

    @Override
    public String toString() {
        return String.valueOf(aChar);
    }
}
