package world.persist;

import world.domain.World;

import java.util.List;

public interface WorldRepository {

    boolean exists(String worldName);

    void save(World world);

    List<String> getAllWorldNames();

}
