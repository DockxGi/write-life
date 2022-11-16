package world.persist;

import world.World;

public interface WorldRepository {

    boolean exists(String worldName);

    void save(World world);

}
