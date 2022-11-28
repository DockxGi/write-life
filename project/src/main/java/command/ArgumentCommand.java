package command;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Represents a command that can have (exactly one) argument.
 */
public abstract class ArgumentCommand implements Command {

    protected boolean hasArgument(String[] splitted){
        return splitted.length > 1 && !isBlank(splitted[1]);
    }

}
