package world;

/**
 * Facade for showing a menu of the world-command.
 */
public class WorldMenus {

    private NewWorldMenu newWorldMenu;

    public WorldMenus() {
        newWorldMenu = new NewWorldMenu();
    }

    public World showNewWorldMenu() {
        return newWorldMenu.interactWithUser();
    }
}
