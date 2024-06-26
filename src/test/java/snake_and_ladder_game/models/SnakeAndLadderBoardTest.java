package snake_and_ladder_game.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SnakeAndLadderBoardTest {
    private SnakeAndLadderBoard board;

    @BeforeEach
    public void setUp() {
        board = new SnakeAndLadderBoard(100); // Board size 100
    }

    @Test
    public void getSize_shouldReturnSize() {
        Assertions.assertEquals(100, board.getSize(), "Board size should be initialized correctly");
    }

    @Test
    public void getSnakes_shouldReturnEmptyListWhenNotSet() {
        Assertions.assertEquals(0, board.getSnakes().size(), "Initially snakes list should be empty");
    }

    @Test
    public void setSnakes_shouldSetAndGetSnakesCorrectly() {
        List<Snake> snakes = new ArrayList<>();
        snakes.add(new Snake(14, 7));
        snakes.add(new Snake(19, 2));

        board.setSnakes(snakes);

        Assertions.assertEquals(2, board.getSnakes().size(), "Snakes should be set correctly");
        Assertions.assertEquals(14, board.getSnakes().get(0).getHead(), "Snake head should be set correctly");
        Assertions.assertEquals(7, board.getSnakes().get(0).getTail(), "Snake tail should be set correctly");
    }

    @Test
    public void getLadders_shouldReturnEmptyListWhenNotSet() {
        Assertions.assertEquals(0, board.getLadders().size(), "Initially ladders list should be empty");
    }

    @Test
    public void setLadders_shouldSetAndGetLaddersCorrectly() {
        List<Ladder> ladders = new ArrayList<>();
        ladders.add(new Ladder(4, 15));
        ladders.add(new Ladder(8, 23));

        board.setLadders(ladders);

        Assertions.assertEquals(2, board.getLadders().size(), "Ladders should be set correctly");
        Assertions.assertEquals(4, board.getLadders().get(0).getStart(), "Ladder start should be set correctly");
        Assertions.assertEquals(15, board.getLadders().get(0).getEnd(), "Ladder end should be set correctly");
    }

    @Test
    public void getPlayerPieces_shouldReturnEmptyMapWhenNotSet() {
        Assertions.assertEquals(0, board.getPlayerPieces().size(), "Initially player pieces map should be empty");
    }

    @Test
    public void setPlayerPieces_shouldSetAndGetPlayerPiecesCorrectly() {
        Map<String, Integer> playerPieces = new HashMap<>();
        playerPieces.put("Akshay", 10);
        playerPieces.put("Bhushan", 20);

        board.setPlayerPieces(playerPieces);

        Assertions.assertEquals(2, board.getPlayerPieces().size(), "Player pieces map should be set correctly");
        Assertions.assertEquals(10, board.getPlayerPieces().get("Akshay"), "Player 1 position should be set correctly");
        Assertions.assertEquals(20, board.getPlayerPieces().get("Bhushan"), "Player 2 position should be set correctly");
    }
}
