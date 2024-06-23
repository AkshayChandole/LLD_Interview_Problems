package snake_and_ladder_game.service;

import snake_and_ladder_game.models.Ladder;
import snake_and_ladder_game.models.Player;
import snake_and_ladder_game.models.Snake;
import snake_and_ladder_game.models.SnakeAndLadderBoard;

import java.util.*;

public class SnakeAndLadderService {
    private final SnakeAndLadderBoard snakeAndLadderBoard;
    private int initialNumberOfPlayers;
    private Queue<Player> players;

    private int noOfDice;
    private boolean shouldGameContinueTillLastPlayer;
    private boolean shouldAllowMultipleDiceRollOnSix;

    private static final int DEFAULT_BOARD_SIZE = 100;
    private static final int DEFAULT_NO_OF_DICES = 1;

    public SnakeAndLadderService(int boardSize) {
        this.snakeAndLadderBoard = new SnakeAndLadderBoard(boardSize);
        this.players = new LinkedList<>();
        this.noOfDice = SnakeAndLadderService.DEFAULT_NO_OF_DICES;
    }

    public SnakeAndLadderService() {
        this(SnakeAndLadderService.DEFAULT_BOARD_SIZE);
    }

    /**
     * ======= Setters ========
     */

    public void setNoOfDice(int noOfDice) {
        this.noOfDice = noOfDice;
    }

    public void setShouldGameContinueTillLastPlayer(boolean shouldGameContinueTillLastPlayer) {
        this.shouldGameContinueTillLastPlayer = shouldGameContinueTillLastPlayer;
    }

    public void setShouldAllowMultipleDiceRollOnSix(boolean shouldAllowMultipleDiceRollOnSix) {
        this.shouldAllowMultipleDiceRollOnSix = shouldAllowMultipleDiceRollOnSix;
    }

    /**
     * ============ Initialize Snake and Ladder Board ==========
     */

    public void setPlayers(List<Player> players) {
        this.players = new LinkedList<>();
        this.initialNumberOfPlayers = players.size();
        Map<String, Integer> playerPieces = new HashMap<>();
        for (Player player : players) {
            this.players.add(player);
            playerPieces.put(player.getId(), 0); // Initially player will be at position 0.
        }
        snakeAndLadderBoard.setPlayerPieces(playerPieces);
    }

    public void setSnakes(List<Snake> snakes) {
        snakeAndLadderBoard.setSnakes(snakes);
    }

    public void setLadders(List<Ladder> ladders) {
        snakeAndLadderBoard.setLadders(ladders);
    }

    /***
     * ==========  Game Logic ========
     */

    private int getNewPositionAfterGoingThroughSnakesAndLadders(int newPosition) {
        int previousPosition;

        do {
            previousPosition = newPosition;
            for (Snake snake : snakeAndLadderBoard.getSnakes()) {
                if (snake.getHead() == newPosition) {
                    newPosition = snake.getTail();
                }
            }

            for (Ladder ladder : snakeAndLadderBoard.getLadders()) {
                if (ladder.getStart() == newPosition) {
                    newPosition = ladder.getEnd();
                }
            }
        } while (newPosition != previousPosition);

        return newPosition;
    }

    private void movePlayer(Player player, int positions) {
        int oldPosition = snakeAndLadderBoard.getPlayerPieces().get(player.getId());
        int newPosition = oldPosition + positions; // Based on the dice value, the player moves their piece forward that number of cells.

        int boardSize = snakeAndLadderBoard.getSize();


        if (newPosition > boardSize) {
            newPosition = oldPosition;
        } else {
            newPosition = getNewPositionAfterGoingThroughSnakesAndLadders(newPosition);
        }

        snakeAndLadderBoard.getPlayerPieces().put(player.getId(), newPosition);

        System.out.println(player.getName() + " rolled a " + positions + " and moved from " + oldPosition + " to " + newPosition);
    }

    private int getTotalValueAfterDiceRolls() {
        int currentDiceValue = DiceService.roll(noOfDice);
        int totalDiceValue = currentDiceValue;
        int noOfTurns = 0;

        while (shouldAllowMultipleDiceRollOnSix && DiceService.isAllSixes(currentDiceValue, noOfDice)) {
            noOfTurns++;
            if (noOfTurns == 3) {
                totalDiceValue = 0;
                break;
            }
            currentDiceValue = getTotalValueAfterDiceRolls();
            totalDiceValue += currentDiceValue;
        }

        return totalDiceValue;
    }

    private boolean hasPlayerWon(Player player) {
        int playerPosition = snakeAndLadderBoard.getPlayerPieces().get(player.getId());
        int winningPosition = snakeAndLadderBoard.getSize();
        return playerPosition == winningPosition;
    }

    public boolean isGameCompleted() {
        if (shouldGameContinueTillLastPlayer) {
            return players.size() == 1;
        } else {
            return players.size() < initialNumberOfPlayers;
        }
    }

    public void startGame() {
        while (!isGameCompleted()) {
            Player currentPlayer = players.poll();

            int diceValue = getTotalValueAfterDiceRolls();

            movePlayer(currentPlayer, diceValue);
            if (hasPlayerWon(currentPlayer)) {
                System.out.println(currentPlayer.getName() + " wins the game");
                snakeAndLadderBoard.getPlayerPieces().remove(currentPlayer.getId());
            } else {
                players.add(currentPlayer);
            }
        }
    }
}
