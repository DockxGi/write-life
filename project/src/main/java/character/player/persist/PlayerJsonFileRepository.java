package character.player.persist;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import character.player.domain.Player;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Objects.requireNonNull;

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
    public Player findByFirstName(String firstName) {
        File file = new File(PLAYERS_FOLDER + "/" + firstName + ".json");
        if (! file.exists()){
            return null;
        }
        try {
            String json = FileUtils.readFileToString(file, Charset.defaultCharset());
            return new Gson().fromJson(json, Player.class);
        } catch (Exception e) {
            throw new FailedToReadPlayerFile("Failed to read: " + file.getAbsolutePath(), e);
        }
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
        File file = new File(PLAYERS_FOLDER);
        if (!file.exists() || !file.isDirectory()){
            return emptyList();
        }
        return Arrays.stream(requireNonNull(file.list()))
                .map(s -> s.replace(".json", ""))
                .collect(Collectors.toList());
    }

    private void createPlayersDirectory(File playersFile) {
        try {
            FileUtils.createParentDirectories(playersFile);
        } catch (IOException e) {
            throw new FailedToSavePlayer("Failed to save player to: " + playersFile.getAbsolutePath(), e);
        }
    }
}
