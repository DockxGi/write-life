package world.view;

import world.domain.World;
import world.domain.WorldSession;

/**
 * Facade for showing a menu of the world-command.
 */
public class WorldMenus {

    private NewWorldMenu newWorldMenu;
    private EnterWorldMenu enterWorldMenu;

    public WorldMenus() {
        newWorldMenu = new NewWorldMenu();
        enterWorldMenu = new EnterWorldMenu();
    }

    public World showNewWorldMenu() {
        return newWorldMenu.interactWithUser();
    }

    public WorldSession showEnterWorldMenu() {
        return enterWorldMenu.interactWithUser();
    }
}
