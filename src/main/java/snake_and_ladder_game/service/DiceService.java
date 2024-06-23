package snake_and_ladder_game.service;

import java.util.Random;

public class DiceService {
    private static final Random random = new Random();

    public static int roll(int numberOfDice) {
        int rollSum = 0;
        for (int i = 0; i < numberOfDice; i++) {
            rollSum += random.nextInt(6) + 1;
        }
        return rollSum;
    }

    public static boolean isAllSixes(int rollSum, int numberOfDice) {
        return rollSum == numberOfDice * 6;
    }
}
