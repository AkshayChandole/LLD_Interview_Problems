package snake_and_ladder_game.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    public void testPlayerCreation() {
        Player player = new Player("Akshay");
        Assertions.assertEquals("Akshay", player.getName());
        Assertions.assertNotNull(player.getId());
    }
}
