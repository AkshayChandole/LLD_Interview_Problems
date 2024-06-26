package tic_tac_toe.services;

import tic_tac_toe.models.Board;
import tic_tac_toe.models.Piece;
import tic_tac_toe.models.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeService {
    private final Board board;
    private final List<Player> players;
    private int currentPlayerIndex;

    public TicTacToeService(int size, int numPlayers, Scanner sc) {
        this.board = new Board(size);
        this.players = new ArrayList<>();
        this.currentPlayerIndex = 0;

        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter name of the player" + (i + 1) + " : ");
            String name = sc.nextLine();
            System.out.print("Enter piece symbol for the player" + (i + 1) + " : ");
            char symbol = sc.nextLine().charAt(0);
            players.add(new Player(name, new Piece(symbol)));
        }
    }

    public void play(Scanner sc) {
        System.out.println("Initial Board - ");
        board.print();

        while (true) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getPiece().getSymbol() + ")");
            System.out.print("Enter row or column (or 'exit' to end) : ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            String[] parts = input.split(" ");
            if (parts.length != 2) {
                System.out.println("Invalid Move. Try again.");
                continue;
            }

            try {
                int row = Integer.parseInt(parts[0]) - 1;
                int col = Integer.parseInt(parts[1]) - 1;

                if (board.isValidMove(row, col)) {
                    board.makeMove(row, col, currentPlayer.getPiece());
                    board.print();

                    if (board.checkWin(row, col, currentPlayer.getPiece())) {
                        System.out.println(currentPlayer.getName() + " won the game.");
                        break;
                    }

                    if (board.isFull()) {
                        System.out.println("Game Over.");
                        break;
                    }

                    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
                } else {
                    System.out.println("Invalid Move. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Move");
            }
        }
    }
}
