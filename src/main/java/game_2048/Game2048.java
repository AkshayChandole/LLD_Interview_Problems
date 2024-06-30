package game_2048;

import game_2048.models.Direction;
import game_2048.models.GameState;
import game_2048.services.GameService;
import game_2048.services.InputService;
import game_2048.services.OutputService;

public class Game2048 {
    private GameService gameService;
    private final InputService inputService;
    private final OutputService outputService;

    public Game2048() {
        this.inputService = new InputService();
        this.outputService = new OutputService();
    }

    public void start() {
        int size = inputService.getGridSize();
        int winningValue = inputService.getWinningValue();
        int base = inputService.getBaseValue();

        this.gameService = new GameService(size, winningValue, base);
        gameService.initializeGame();

        play();
    }

    private void play() {
        while (true) {
            outputService.printBoard(gameService.getBoard());

            Direction direction = inputService.getMove();
            if (gameService.makeMove(direction)) {
                GameState state = gameService.getGameState();
                if (state == GameState.WON) {
                    outputService.printBoard(gameService.getBoard());
                    outputService.printMessage("Congratulations! You've won!");
                    if (!inputService.wantToContinue()) {
                        break;
                    }
                    gameService.setWinningValue(gameService.getBoard().getSize() * 2);
                } else if (state == GameState.LOST) {
                    outputService.printBoard(gameService.getBoard());
                    outputService.printMessage("Game over! No more moves possible.");
                    break;
                }
            } else {
                outputService.printMessage("Invalid move. Try again.");
            }
        }
    }

    public static void main(String[] args) {
        new Game2048().start();
    }
}
