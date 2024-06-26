package tic_tac_toe.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {
    @Test
    void testPieceCreation() {
        Piece piece = new Piece('X');
        assertEquals('X', piece.getSymbol());
    }
}