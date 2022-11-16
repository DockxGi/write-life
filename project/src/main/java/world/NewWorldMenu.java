package world;

import org.apache.commons.lang3.StringUtils;
import world.persist.WorldJsonFileRepository;
import world.persist.WorldRepository;

import java.util.Scanner;

/**
 * Interacts with user to create a world.
 */
public class NewWorldMenu {

    private Scanner scanner = new Scanner(System.in);

    public World interactWithUser() {
        String worldName = askWorldName();

        return new World(worldName);
    }

    private String askWorldName() {
        System.out.println("Wat is the name of the new world?");
        System.out.print("\n> ");

        boolean askAgain = false;

        String name;
        do {
            name = scanner.nextLine().trim();
            askAgain = StringUtils.isBlank(name) || name.length() > 20;
            if (askAgain){
                System.out.println("Name not valid! Please enter a valid name. Max length is 20 characters.");
                System.out.print("\n> ");
            }
        } while (askAgain);

        return name;
    }
}
