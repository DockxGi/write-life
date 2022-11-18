package world.persist;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import world.domain.World;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class WorldJsonFileRepository implements WorldRepository {

    public static final String WORLDS_FOLDER = "../saved/worlds";

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
        File file = new File(WORLDS_FOLDER + "/" + worldName + ".json");
        return file.exists();
    }

    @Override
    public World findByName(String name) {
        File file = new File(WORLDS_FOLDER + "/" + name + ".json");
        if (! file.exists()){
            return null;
        }
        try {
            String json = FileUtils.readFileToString(file, Charset.defaultCharset());
            return new Gson().fromJson(json, World.class);
        } catch (Exception e) {
            throw new FailedToReadWorldFile("Failed to read: " + file.getAbsolutePath(), e);
        }
    }

    @Override
    public void save(World world) {
        String worldName = world.getName();
        File file = new File(WORLDS_FOLDER + "/" + worldName + ".json");

        createWorldsDirectory(file);

        String json = new Gson().toJson(world);
        try {
            Files.write(Paths.get(file.getPath()), json.getBytes());
        } catch (IOException e) {
            throw new FailedToSaveWorld("Failed to write to " + file.getAbsolutePath());
        }
    }

    @Override
    public List<String> getAllWorldNames() {
        File file = new File(WORLDS_FOLDER);
        if (!file.exists() || !file.isDirectory()){
            return Collections.emptyList();
        }
        return Arrays.stream(requireNonNull(file.list()))
                .map(s -> s.replace(".json", ""))
                .collect(Collectors.toList());
    }

    private void createWorldsDirectory(File worldFile) {
        try {
            FileUtils.createParentDirectories(worldFile);
        } catch (IOException e) {
            throw new FailedToSaveWorld("Failed to save world to: " + worldFile.getAbsolutePath(), e);
        }
    }
}
