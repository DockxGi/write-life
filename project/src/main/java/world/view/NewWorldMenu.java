package world.view;

import questions.Answer;
import questions.AnswerRequirements;
import questions.Interviewer;
import world.domain.World;
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
        Interviewer interviewer = new Interviewer();

        AnswerRequirements requirements = new AnswerRequirements(true, 1, 20);
        Answer answer = interviewer.askQuestion("Wat is the name of the new world?", requirements);

        WorldRepository worldRepository = WorldJsonFileRepository.getInstance();
        while (worldRepository.exists(answer.getText())){
            System.out.println("There already exists a world with this name.");
            answer = interviewer.askQuestion("Please give a new answer.", requirements);
        }

        return answer.getText();
    }
}
