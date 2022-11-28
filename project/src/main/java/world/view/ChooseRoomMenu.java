package world.view;

import game.GameModel;
import questions.Answer;
import questions.Interviewer;
import questions.OpenAnswerRequirements;
import world.domain.World;
import world.domain.room.Room;

public class ChooseRoomMenu {
    public Room interactWithUser(GameModel game, String question) {
        Interviewer interviewer = new Interviewer();

        OpenAnswerRequirements requirements = new OpenAnswerRequirements(false, 1, 40);
        do {
            Answer answer = interviewer.askQuestion(question, requirements);
            if (answer.isBlank()){
                return null;
            }
            World world = game.getWorld();
            String roomName = answer.getText();
            Room room = world.findRoom(roomName);
            if (room != null){
                return room;
            }
            System.out.printf("There exists no room with the name %s in this world.%n", roomName);
        } while (true);
    }
}
