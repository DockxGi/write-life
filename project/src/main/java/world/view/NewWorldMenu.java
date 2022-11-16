package world.view;

import org.apache.commons.lang3.StringUtils;
import world.domain.World;
import world.persist.WorldJsonFileRepository;

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

        boolean notValidName;

        String name;
        do {
            name = scanner.nextLine().trim();
            notValidName = StringUtils.isBlank(name) || name.length() > 20;
            if (notValidName){
                System.out.println("Name not valid! Please enter a valid name. Max length is 20 characters.");
                System.out.print("\n> ");
            }
            if (WorldJsonFileRepository.getInstance().exists(name)){
                System.out.println("There already exists a world with this name. Please choose another one.");
                System.out.print("\n> ");
                notValidName = true;
            }
        } while (notValidName);

        return name;
    }
}
