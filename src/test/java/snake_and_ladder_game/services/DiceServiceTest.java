package snake_and_ladder_game.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import snake_and_ladder_game.service.DiceService;

public class DiceServiceTest {
    @Test
    public void roll_singleDice_shouldBeInRange() {
        int roll = DiceService.roll(1);
        Assertions.assertTrue(roll >= 1 && roll <= 6, "Single dice roll should be between 1 and 6 inclusive");
    }

    @Test
    public void roll_multipleDice_shouldBeInRange() {
        int roll = DiceService.roll(2);
        Assertions.assertTrue(roll >= 2 && roll <= 12,"Two dice roll should be between 2 and 12 inclusive");
    }

    @Test
    public void isAllSixes_allSixes_true() {
        int rollSum = 6 * 6; // All dice rolls sum to 36
        Assertions.assertTrue(DiceService.isAllSixes(rollSum, 6),"Should detect all sixes");
    }

    @Test
    public void isAllSixes_notAllSixes_false() {
        int rollSum = 25;
        Assertions.assertFalse(DiceService.isAllSixes(rollSum, 5),"Should detect not all sixes");
    }
}
