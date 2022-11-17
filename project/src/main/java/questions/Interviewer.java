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
                tellRequirements(requirements);
            }
        } while (!answer.getValid());

        return answer;
    }

    private void tellRequirements(AnswerRequirements requirements) {
        StringBuilder sb = new StringBuilder("Your answer is not valid. ");
        if (requirements.isRequired()){
            sb.append("The answer is required. ");
        }
        Integer minLength = requirements.getMinLength();
        if (minLength != null){
            sb.append("It should be minimal " + minLength + " characters long. ");
        }
        Integer maxLength = requirements.getMaxLength();
        if (maxLength != null){
            sb.append("It should be maximal " + maxLength + " characters long. ");
        }
        sb.append("Please give a new answer.\n> ");

        System.out.print(sb);
    }

    private Answer receiveAnswer(Scanner scanner, AnswerRequirements requirements) {
        Answer answer = new Answer(scanner.nextLine());

        if (requirements.isRequired() && answer.isBlank()){
            answer.setValid(false);
            return answer;
        }
        if (!requirements.isRequired() && answer.isBlank()){
            answer.setValid(true);
            return answer;
        }

        Integer minLength = requirements.getMinLength();
        if (minLength != null && answer.length() < minLength){
            answer.setValid(false);
            return answer;
        }

        Integer maxLength = requirements.getMaxLength();
        if (maxLength != null && answer.length() > maxLength){
            answer.setValid(false);
            return answer;
        }

        answer.setValid(true);
        return answer;
    }

}
