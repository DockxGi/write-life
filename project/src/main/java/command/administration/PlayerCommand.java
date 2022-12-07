package command.administration;

import command.ArgumentCommand;
import game.GameModel;
import character.player.domain.Player;
import character.player.persist.PlayerJsonFileRepository;
import character.player.view.PlayerMenus;

import java.util.List;

import static utils.PrintLineUtil.printAsOrderedList;
import static utils.PrintLineUtil.printEvent;

public class PlayerCommand extends ArgumentCommand {

    private final PlayerMenus playerMenus;

    public PlayerCommand() {
        playerMenus = new PlayerMenus();
    }

    @Override
    public String keyword() {
        return "player";
    }

    @Override
    public void execute(String[] splitted, GameModel gameModel) {
        boolean noArgument = !hasArgument(splitted);
        if (noArgument){
            System.out.println("The command " + splitted[0] + " needs an argument. For more info type: help player");
            return;
        }
        String argument = splitted[1];
        if (argument.equals("new")){
            processNewPlayerCommand();
        }
        if (argument.equals("list")){
            processListPlayersCommand();
        }
    }

    private void processListPlayersCommand() {
        List<String> playerNames = PlayerJsonFileRepository.getInstance().getAllPlayerNames();
        printAsOrderedList(playerNames);
    }

    private void processNewPlayerCommand() {
        Player player = playerMenus.showNewPlayerMenu();
        PlayerJsonFileRepository.getInstance().save(player);
        showPlayerSaved();
    }

    private void showPlayerSaved() {
        printEvent("PLAYER SAVED");
    }
}
