package command;

import player.domain.Player;
import player.persist.PlayerJsonFileRepository;
import player.view.PlayerMenus;

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
    public void execute(String[] splitted) {
        boolean noArgument = hasArgument(splitted);
        if (noArgument){
            System.out.println("The command " + splitted[0] + " needs an argument. For more info type: help player");
            return;
        }
        String argument = splitted[1];
        if (argument.equals("new")){
            processNewPlayerCommand();
        }
    }

    private void processNewPlayerCommand() {
        Player player = playerMenus.showNewPlayerMenu();
        PlayerJsonFileRepository.getInstance().save(player);
        showPlayerSaved();
    }

    private void showPlayerSaved() {
        System.out.println("[PLAYER SAVED]");
    }
}
