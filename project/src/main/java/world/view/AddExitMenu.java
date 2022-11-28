package world.view;

import questions.*;
import world.domain.Direction;
import world.domain.room.Exit;
import world.domain.room.Room;

import java.util.Locale;

public class AddExitMenu {
    public Exit interactWithUser(Room room, Room destination) {
        Interviewer interviewer = new Interviewer();

        String exitType = askExitType(interviewer);
        String exitDescription = askExitDescription(interviewer);
        boolean seeTrough = askExitSeeTrough(interviewer);
        boolean locked = askExitLocked(interviewer);
        Direction direction = askExitDirection(interviewer);

        return new Exit(exitType, exitDescription, room.getName(), direction, destination.getName(), seeTrough, locked);
    }

    private Direction askExitDirection(Interviewer interviewer) {
        DirectionAnswerRequirements requirements = new DirectionAnswerRequirements();
        Answer answer = interviewer.askQuestion("In which direction of the room is the exit located?", requirements);

        return Direction.fromNameOrAbbreviation(answer.getText());
    }

    private boolean askExitLocked(Interviewer interviewer) {
        YesOrNoAnswerRequirements requirements = new YesOrNoAnswerRequirements();
        Answer answer = interviewer.askQuestion("Is it possible to move trough the exit into the other room?", requirements);

        return !answer.getText().startsWith("y");
    }

    private boolean askExitSeeTrough(Interviewer interviewer) {
        YesOrNoAnswerRequirements requirements = new YesOrNoAnswerRequirements();
        Answer answer = interviewer.askQuestion("Can you see trough the exit into the other room?", requirements);

        return answer.getText().startsWith("y");
    }

    private String askExitDescription(Interviewer interviewer) {
        OpenAnswerRequirements requirements = new OpenAnswerRequirements(false, 1,1000);
        Answer answer = interviewer.askQuestion("Describe the exit. (you can leave it blank)", requirements);

        return answer.getText();
    }

    private String askExitType(Interviewer interviewer) {
        OpenAnswerRequirements requirements = new OpenAnswerRequirements(false, 1,20);
        Answer answer = interviewer.askQuestion("What type of exit is it? (you can leave it blank)", requirements);

        String text = answer.getText();
        if (text != null){
            return text.toLowerCase(Locale.ROOT);
        }
        return null;
    }
}
