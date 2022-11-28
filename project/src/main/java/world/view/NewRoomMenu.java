package world.view;

import game.GameModel;
import questions.Answer;
import questions.OpenAnswerRequirements;
import questions.Interviewer;
import questions.YesOrNoAnswerRequirements;
import world.domain.World;
import world.domain.room.Room;

/**
 * Interacts with user to create a new room.
 */
public class NewRoomMenu {
    public Room interactWithUser(GameModel game) {
        Interviewer interviewer = new Interviewer();
        World world = game.getWorld();

        String roomName = askRoomName(interviewer, world);
        boolean landingSpot = askIsLandingSpot(interviewer);
        String roomDescription = askRoomDescription(interviewer);

        Room room = new Room(roomName, roomDescription, landingSpot);

        return room;
    }

    private String askRoomDescription(Interviewer interviewer) {
        OpenAnswerRequirements requirements = new OpenAnswerRequirements(false, 1,1000);
        Answer answer = interviewer.askQuestion("Please, describe the room.", requirements);

        return answer.getText();
    }

    private boolean askIsLandingSpot(Interviewer interviewer) {
        YesOrNoAnswerRequirements requirements = new YesOrNoAnswerRequirements();
        Answer answer = interviewer.askQuestion("Is it a landing spot?", requirements);

        return answer.getText().startsWith("y");
    }

    private String askRoomName(Interviewer interviewer, World world) {
        OpenAnswerRequirements requirements = new OpenAnswerRequirements(true,1,40);
        Answer answer = interviewer.askQuestion("Wat is the name of the new room?", requirements);

        while (world.hasRoom(answer.getText())){
            System.out.println("There already exists a room with that name in the world.");
            answer = interviewer.askQuestion("Please give a new answer.", requirements);
        }

        String roomName = answer.getText();
        return roomName;
    }
}
