package tic_tac_toe;

import tic_tac_toe.services.TicTacToeService;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the size of the grid: ");
        int size = sc.nextInt();
        sc.nextLine(); // Consume newline character

        System.out.println("Enter the number of players : ");
        int numPlayers = sc.nextInt();
        sc.nextLine(); // Consume newline character

        TicTacToeService ticTacToeService = new TicTacToeService(size, numPlayers, sc);
        ticTacToeService.play(sc);
        sc.close();
    }
}
