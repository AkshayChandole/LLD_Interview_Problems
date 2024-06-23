package snake_and_ladder_game;

import snake_and_ladder_game.models.Ladder;
import snake_and_ladder_game.models.Player;
import snake_and_ladder_game.models.Snake;
import snake_and_ladder_game.service.SnakeAndLadderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter board size:");
        int boardSize = sc.nextInt();

        System.out.println("Enter number of dice:");
        int noOfDice = sc.nextInt();

        System.out.println("Enter no. of snakes - ");
        int noOfSnakes = sc.nextInt();
        List<Snake> snakes = new ArrayList<>();
        System.out.println("Enter all snake's head and tail positions - ");
        for (int i = 0; i < noOfSnakes; i++) {
            snakes.add(new Snake(sc.nextInt(), sc.nextInt()));
        }

        System.out.println("Enter no. of ladders - ");
        int noOfLadders = sc.nextInt();
        List<Ladder> ladders = new ArrayList<>();
        System.out.println("Enter all ladder's start and end positions - ");
        for (int i = 0; i < noOfLadders; i++) {
            ladders.add(new Ladder(sc.nextInt(), sc.nextInt()));
        }

        System.out.println("Enter no. of players - ");
        int noOfPlayers = sc.nextInt();
        System.out.println("Enter names of all the players - ");
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < noOfPlayers; i++) {
            players.add(new Player(sc.next()));
        }

        sc.close();

        SnakeAndLadderService snakeAndLadderService = new SnakeAndLadderService(boardSize);
        snakeAndLadderService.setNoOfDice(noOfDice);
        snakeAndLadderService.setPlayers(players);
        snakeAndLadderService.setSnakes(snakes);
        snakeAndLadderService.setLadders(ladders);
        snakeAndLadderService.setShouldAllowMultipleDiceRollOnSix(true);
        if (noOfPlayers > 2) {
            snakeAndLadderService.setShouldGameContinueTillLastPlayer(true);
        }

        snakeAndLadderService.startGame();
    }
}
