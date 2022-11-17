package player.view;

import org.apache.commons.lang3.StringUtils;
import player.domain.Player;
import player.persist.PlayerJsonFileRepository;
import world.persist.WorldJsonFileRepository;

import java.util.Scanner;

public class NewPlayerMenu {

    public Player interactWithUser() {
        String playerName = askPlayerName();

        return new Player(playerName);
    }

    private String askPlayerName() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("What is your firstname?");
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
            if (PlayerJsonFileRepository.getInstance().exists(name)){
                System.out.println("There already exists a player with this name. Please choose another one.");
                System.out.print("\n> ");
                notValidName = true;
            }
        } while (notValidName);

        return name;
    }
}
