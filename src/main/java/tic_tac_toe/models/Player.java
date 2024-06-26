package tic_tac_toe.models;

public class Player {
    private final String name;
    private final Piece piece;

    public Player(String name, Piece piece) {
        this.name = name;
        this.piece = piece;
    }

    public String getName() {
        return this.name;
    }

    public Piece getPiece() {
        return this.piece;
    }
}
