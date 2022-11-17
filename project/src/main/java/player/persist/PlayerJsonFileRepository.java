package player.persist;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import player.domain.Player;
import world.persist.FailedToSaveWorld;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PlayerJsonFileRepository implements PlayerRepository {

    public static final String PLAYERS_FOLDER = "../saved/players";

    private static PlayerRepository instance;

    public static PlayerRepository getInstance(){
        if (instance == null){
            instance = new PlayerJsonFileRepository();
        }
        return instance;
    }

    @Override
    public boolean exists(String firstName) {
        File file = new File(PLAYERS_FOLDER + "/" + firstName + ".json");
        return file.exists();
    }

    @Override
    public void save(Player player) {
        String playerName = player.getName();
        File file = new File(PLAYERS_FOLDER + "/" + playerName + ".json");

        createPlayersDirectory(file);

        String json = new Gson().toJson(player);
        try {
            Files.write(Paths.get(file.getPath()), json.getBytes());
        } catch (IOException e) {
            throw new FailedToSavePlayer("Failed to write to " + file.getAbsolutePath());
        }
    }

    @Override
    public List<String> getAllPlayerNames() {
        return null;
    }

    private void createPlayersDirectory(File playersFile) {
        try {
            FileUtils.createParentDirectories(playersFile);
        } catch (IOException e) {
            throw new FailedToSavePlayer("Failed to save player to: " + playersFile.getAbsolutePath(), e);
        }
    }
}
