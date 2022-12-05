package world.view;

import questions.Answer;
import questions.Interviewer;
import questions.OpenAnswerRequirements;
import world.domain.room.Room;
import world.domain.room.feature.Feature;
import world.domain.room.feature.FeatureType;

public class AddFeatureMenu {

    public Feature interactWithUser(Room room, FeatureType type) {
        Interviewer interviewer = new Interviewer();

        String name = askFeatureName(interviewer, room);
        return new Feature(name, type, null);
    }

    private String askFeatureName(Interviewer interviewer, Room room) {
        OpenAnswerRequirements requirements = new OpenAnswerRequirements(true, 1, 40);
        Answer answer = interviewer.askQuestion("Wat is the name of the new feature?", requirements);

        String featureName = answer.getText();
        while (room.hasFeatureWithName(featureName)){
            System.out.println("There already exists a feature with that name in the room.");
            answer = interviewer.askQuestion("Please give a new answer.", requirements);
        }

        return answer.getText();
    }
}
