package tic_tac_toe.models;

public class Piece {
    private final char symbol;

    public Piece(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol(){
        return this.symbol;
    }
}
