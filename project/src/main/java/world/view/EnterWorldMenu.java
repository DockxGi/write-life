package world.view;

import player.domain.Player;
import player.persist.PlayerJsonFileRepository;
import player.persist.PlayerRepository;
import questions.Answer;
import questions.AnswerRequirements;
import questions.Interviewer;
import world.domain.World;
import world.domain.WorldSession;
import world.persist.WorldJsonFileRepository;
import world.persist.WorldRepository;

/**
 * Interacts with user to enter the world with a player.
 */
public class EnterWorldMenu {

    public WorldSession interactWithUser() {
        Player player = askPlayerName();
        if (player == null){
            return null;
        }

        World world = askWorldName();
        if (world == null){
            return null;
        }

        return new WorldSession(player, world);
    }

    private World askWorldName() {
        Interviewer interviewer = new Interviewer();

        AnswerRequirements requirements = new AnswerRequirements(false, 1, 20);
        Answer answer = interviewer.askQuestion("Which world do you want to enter? (leave blank to cancel)", requirements);

        if (answer.isBlank()){
            return null;
        }

        WorldRepository worldRepository = WorldJsonFileRepository.getInstance();
        return worldRepository.findByName(answer.getText());
    }

    private Player askPlayerName() {
        Interviewer interviewer = new Interviewer();

        AnswerRequirements requirements = new AnswerRequirements(false, 2, 20);
        Answer answer = interviewer.askQuestion("With which player do you want to enter the world? (leave blank to cancel)", requirements);

        if (answer.isBlank()){
            return null;
        }

        PlayerRepository playerRepository = PlayerJsonFileRepository.getInstance();
        return playerRepository.findByFirstName(answer.getText());
    }
}
