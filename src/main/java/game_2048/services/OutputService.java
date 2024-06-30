package game_2048.services;

import game_2048.models.Board;
import game_2048.models.Tile;

public class OutputService {
    public void printBoard(Board board) {
        System.out.println("Current board:");
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                Tile tile = board.getTile(row, col);
                if (tile == null) {
                    System.out.print("    -");
                } else {
                    System.out.printf("%5d", tile.getValue());
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
