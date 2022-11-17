package player.persist;

import player.domain.Player;

import java.util.List;

public interface PlayerRepository {

    boolean exists(String firstName);

    void save(Player player);

    List<String> getAllPlayerNames();
}
