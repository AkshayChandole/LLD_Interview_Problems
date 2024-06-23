package snake_and_ladder_game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import snake_and_ladder_game.models.Snake;

public class SnakeTest {
    @Test
    public void testSnakeCreation() {
        Snake snake = new Snake(10, 2);
        Assertions.assertEquals(10, snake.getHead());
        Assertions.assertEquals(2, snake.getTail());
    }

    @Test
    public void testSnakeEndGreaterThanStart() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Snake(2, 10));
    }

    @Test
    public void testSnakeWithSameStartAndEnd() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Snake(5, 5));
    }
}
