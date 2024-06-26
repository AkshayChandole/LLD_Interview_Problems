package tic_tac_toe.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    private Board board;
    private Piece piece;

    @BeforeEach
    void setUp() {
        board = new Board(3);
        piece = new Piece('X');
    }

    @Test
    void testIsValidMove() {
        assertTrue(board.isValidMove(0, 0));
        board.makeMove(0, 0, piece);
        assertFalse(board.isValidMove(0, 0));
    }

    @Test
    void testMakeMove() {
        board.makeMove(1, 1, piece);
        assertFalse(board.isValidMove(1, 1));
    }

    @Test
    void testCheckWinRow() {
        board.makeMove(0, 0, piece);
        board.makeMove(0, 1, piece);
        board.makeMove(0, 2, piece);
        assertTrue(board.checkWin(0, 2, piece));
    }

    @Test
    void testCheckWinColumn() {
        board.makeMove(0, 0, piece);
        board.makeMove(1, 0, piece);
        board.makeMove(2, 0, piece);
        assertTrue(board.checkWin(2, 0, piece));
    }

    @Test
    void testCheckWinDiagonal1() {
        board.makeMove(0, 0, piece);
        board.makeMove(1, 1, piece);
        board.makeMove(2, 2, piece);
        assertTrue(board.checkWin(2, 2, piece));
    }

    @Test
    void testCheckWinDiagonal2() {
        board.makeMove(0, 2, piece);
        board.makeMove(1, 1, piece);
        board.makeMove(2, 0, piece);
        assertTrue(board.checkWin(2, 2, piece));
    }

    @Test
    void testIsFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board.makeMove(i, j, piece);
            }
        }
        assertTrue(board.isFull());
    }
}
