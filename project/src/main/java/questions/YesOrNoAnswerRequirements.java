package questions;

public class YesOrNoAnswerRequirements implements AnswerRequirements {

    @Override
    public String getDescription() {
        return "The answer should be yes or no. (alternatives: y/n)";
    }

    @Override
    public void validateAnswer(Answer answer) {
        String text = answer.getText();

        if (text == null){
            answer.setValid(false);
            return;
        }
        if (text.equals("yes") || text.equals("no") || text.equals("y") || text.equals("n")){
            answer.setValid(true);
            return;
        }
        answer.setValid(false);
    }
}
