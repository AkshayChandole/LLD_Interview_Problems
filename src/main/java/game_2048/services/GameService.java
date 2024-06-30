package game_2048.services;

import game_2048.models.Board;
import game_2048.models.Direction;
import game_2048.models.GameState;

public class GameService {
    private final Board board;
    private int winningValue;

    public GameService(int size, int winningValue, int base) {
        this.board = new Board(size, base);
        this.winningValue = winningValue;
    }

    public void initializeGame() {
        board.initialize();
    }

    public boolean makeMove(Direction direction) {
        boolean moved = board.move(direction);
        if (moved) {
            board.addRandomTile();
        }
        return moved;
    }

    public GameState getGameState() {
        return board.getGameState(winningValue);
    }

    public Board getBoard() {
        return board;
    }

    public void setWinningValue(int winningValue) {
        this.winningValue = winningValue;
    }
}
