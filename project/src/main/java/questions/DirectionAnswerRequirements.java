package questions;

import world.domain.Direction;

public class DirectionAnswerRequirements implements AnswerRequirements {
    @Override
    public String getDescription() {
        return "The answer should be a valid direction. (examples: n, e, s, w, ne, nw, se, sw, u, d)";
    }

    @Override
    public void validateAnswer(Answer answer) {
        String text = answer.getText();

        if (text == null){
            answer.setValid(false);
            return;
        }

        Direction direction = Direction.fromNameOrAbbreviation(text);
        if (direction == null){
            answer.setValid(false);
            return;
        }
        answer.setValid(true);
    }
}
