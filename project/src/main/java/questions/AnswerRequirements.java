package questions;

public interface AnswerRequirements {

    /**
     * Shows user readable text description of the requirements.
     */
    String getDescription();

    /**
     * Marks the answer to invalid if the answer is not meeting the requirements.
     */
    void validateAnswer(Answer answer);
}
