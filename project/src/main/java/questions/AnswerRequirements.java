package questions;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The requirements for answer of the user.
 */
@Getter
@AllArgsConstructor
public class AnswerRequirements {
    //is the answer required?
    private boolean required;

    //if an answer is given, what is the minimum length of it?
    private Integer minLength;
    //if the answer is given, what is the max length of it?
    private Integer maxLength;
}
