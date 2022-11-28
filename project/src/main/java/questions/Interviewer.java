package questions;

import java.util.Scanner;

/**
 * Class responsible for asking questions to the user.
 */
public class Interviewer {

    /**
     * Asks a question to the user until the user gives a valid answer.
     * An answer is valid if it meets the requirements.
     */
    public Answer askQuestion(String question, AnswerRequirements requirements){
        Scanner scanner = new Scanner(System.in);

        System.out.println(question);
        System.out.print("\n> ");

        Answer answer = null;
        do {
            answer = receiveAnswer(scanner, requirements);
            if (!answer.getValid()){
                tellItsNotValid(requirements);
            }
        } while (!answer.getValid());

        return answer;
    }

    private void tellItsNotValid(AnswerRequirements requirements) {
        System.out.println("Your answer is not valid.\n");
        String description = requirements.getDescription();
        System.out.println(description);
        System.out.println("Please give a new answer.\n> ");
    }

    private Answer receiveAnswer(Scanner scanner, AnswerRequirements requirements) {
        Answer answer = new Answer(scanner.nextLine());
        requirements.validateAnswer(answer);
        return answer;
    }

}
