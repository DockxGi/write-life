package command;

import game.GameModel;

public interface Command {

    String keyword();

    void execute(String[] splitted, GameModel game);

}
