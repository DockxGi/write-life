package character.player.view;

import character.player.domain.Player;
import character.player.persist.PlayerJsonFileRepository;
import character.player.persist.PlayerRepository;
import questions.Answer;
import questions.OpenAnswerRequirements;
import questions.Interviewer;

public class NewPlayerMenu {

    public Player interactWithUser() {
        String playerName = askPlayerName();

        return new Player(playerName);
    }

    private String askPlayerName() {
        Interviewer interviewer = new Interviewer();

        OpenAnswerRequirements requirements = new OpenAnswerRequirements(true, 2, 20);
        Answer answer = interviewer.askQuestion("What is your firstname?", requirements);

        PlayerRepository playerRepository = PlayerJsonFileRepository.getInstance();
        while (playerRepository.exists(answer.getText())){
            System.out.println("There already exists a player with this name.");
            answer = interviewer.askQuestion("Please give a new answer.", requirements);
        }

        return answer.getText();
    }
}
