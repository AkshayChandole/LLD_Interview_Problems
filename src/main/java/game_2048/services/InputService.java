package game_2048.services;

import game_2048.models.Direction;

import java.util.Scanner;

public class InputService {
    private final Scanner scanner;

    public InputService() {
        this.scanner = new Scanner(System.in);
    }

    public Direction getMove() {
        while (true) {
            System.out.println("Enter your move (0: left, 1: right, 2: up, 3: down):");
            int input = scanner.nextInt();
            switch (input) {
                case 0:
                    return Direction.LEFT;
                case 1:
                    return Direction.RIGHT;
                case 2:
                    return Direction.UP;
                case 3:
                    return Direction.DOWN;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    public int getGridSize() {
        System.out.println("Enter the grid size (e.g., 4 for a 4x4 grid):");
        return scanner.nextInt();
    }

    public int getWinningValue() {
        System.out.println("Enter the winning value (e.g., 2048):");
        return scanner.nextInt();
    }

    public int getBaseValue() {
        System.out.println("Enter the base value for tiles (e.g., 2 for powers of 2):");
        return scanner.nextInt();
    }

    public boolean wantToContinue() {
        System.out.println("Do you want to continue playing? (y/n)");
        return scanner.next().toLowerCase().startsWith("y");
    }

}
