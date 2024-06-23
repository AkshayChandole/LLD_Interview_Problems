import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import snake_and_ladder_game.models.Ladder;

public class LadderTest {
    @Test
    public void testLadderCreation() {
        Ladder ladder = new Ladder(2, 10);
        Assertions.assertEquals(2, ladder.getStart());
        Assertions.assertEquals(10, ladder.getEnd());
    }

    @Test
    public void testLadderWithStartGreaterThanEnd() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Ladder(10, 2));
    }

    @Test
    public void testLadderWithSameStartAndEnd() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Ladder(5, 5));
    }

}
