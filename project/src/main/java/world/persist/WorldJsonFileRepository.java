package world.persist;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import world.domain.World;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorldJsonFileRepository implements WorldRepository {

    private static WorldRepository instance;

    private WorldJsonFileRepository(){
    }

    public static WorldRepository getInstance(){
        if (instance == null){
            instance = new WorldJsonFileRepository();
        }
        return instance;
    }

    @Override
    public boolean exists(String worldName) {
        File file = new File("../saved/worlds/" + worldName + ".json");
        return file.exists();
    }

    @Override
    public void save(World world) {
        String worldName = world.getName();
        File file = new File("../saved/worlds/" + worldName + ".json");

        createWorldsDirectory(file);

        String json = new Gson().toJson(world);
        try {
            Files.write(Paths.get(file.getPath()), json.getBytes());
        } catch (IOException e) {
            throw new FailedToSaveWorld("Failed to write to " + file.getAbsolutePath());
        }
    }

    private void createWorldsDirectory(File worldFile) {
        try {
            FileUtils.createParentDirectories(worldFile);
        } catch (IOException e) {
            throw new FailedToSaveWorld("Failed to save world to: " + worldFile.getAbsolutePath(), e);
        }
    }
}
