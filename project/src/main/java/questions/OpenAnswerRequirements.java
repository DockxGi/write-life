package questions;

import lombok.Getter;

/**
 * The requirements for answer of the user for an open question.
 */
public class OpenAnswerRequirements implements AnswerRequirements {
    //is the answer required?
    @Getter
    private boolean required;

    //if an answer is given, what is the minimum length of it?
    @Getter
    private Integer minLength;
    //if the answer is given, what is the max length of it?
    @Getter
    private Integer maxLength;

    public OpenAnswerRequirements(boolean required, Integer minLength, Integer maxLength) {
        this.required = required;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    public String getDescription(){
        StringBuilder sb = new StringBuilder();
        if (required){
            sb.append("The answer is required. ");
        };
        if (minLength != null){
            sb.append("It should be minimal " + minLength + " characters long. ");
        }
        if (maxLength != null){
            sb.append("It should be maximal " + maxLength + " characters long. ");
        }
        return sb.toString();
    }

    @Override
    public void validateAnswer(Answer answer) {
        if (isRequired() && answer.isBlank()){
            answer.setValid(false);
            return;
        }
        if (!isRequired() && answer.isBlank()){
            answer.setValid(true);
            return;
        }
        if (minLength != null && answer.length() < minLength){
            answer.setValid(false);
            return;
        }

        if (maxLength != null && answer.length() > maxLength){
            answer.setValid(false);
            return;
        }

        answer.setValid(true);
    }
}
