package tic_tac_toe.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    void testPlayerCreation() {
        Piece piece = new Piece('X');
        Player player = new Player("Alice", piece);
        assertEquals("Alice", player.getName());
        assertEquals(piece, player.getPiece());
    }
}
