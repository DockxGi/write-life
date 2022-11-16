package world.persist;

import world.domain.World;

public interface WorldRepository {

    boolean exists(String worldName);

    void save(World world);

}
