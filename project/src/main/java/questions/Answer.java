package questions;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * The answer of a user.
 */
@Getter
public class Answer {

    public Answer(String text) {
        this.text = text.trim();

        if (StringUtils.isBlank(text)){
            this.text = null;
        }
    }

    private String text;

    @Setter
    private Boolean valid;

    public boolean isBlank() {
        return text == null;
    }

    public int length() {
        if (text == null){
            return 0;
        }
        return text.length();
    }
}
