package player.view;

import player.domain.Player;
import player.persist.PlayerJsonFileRepository;
import player.persist.PlayerRepository;
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
