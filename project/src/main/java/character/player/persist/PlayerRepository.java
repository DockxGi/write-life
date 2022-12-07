package character.player.persist;

import character.player.domain.Player;

import java.util.List;

public interface PlayerRepository {

    boolean exists(String firstName);

    void save(Player player);

    List<String> getAllPlayerNames();

    Player findByFirstName(String text);
}
