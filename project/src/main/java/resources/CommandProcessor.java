package resources;

import org.apache.commons.lang3.StringUtils;

public class CommandProcessor {

    private ResourceReader resourceReader;

    public CommandProcessor(ResourceReader resourceReader) {
        this.resourceReader = resourceReader;
    }

    public void processCommand(String nextLine) {
        if (StringUtils.equals(nextLine, "help")){
            System.out.println(resourceReader.readTextFile("help.txt"));
        }
    }
}
