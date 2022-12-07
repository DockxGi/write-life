package character.player.view;

import character.player.domain.Player;

/**
 * Facade for showing a menu of the player-command.
 */
public class PlayerMenus {

    private NewPlayerMenu newPlayerMenu;

    public PlayerMenus() {
        newPlayerMenu = new NewPlayerMenu();
    }

    public Player showNewPlayerMenu() {
        return newPlayerMenu.interactWithUser();
    }
}
