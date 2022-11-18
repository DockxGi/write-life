package world.domain;

import lombok.AllArgsConstructor;
import player.domain.Player;

/**
 * Represents the game-session that a player has in the world that he/she entered.
 */
@AllArgsConstructor
public class WorldSession {

    private Player player;
    private World world;

    
}
