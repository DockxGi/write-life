package command;

import resources.ResourceReader;

public class HelpCommand extends ArgumentCommand{

    private ResourceReader resourceReader;

    public HelpCommand() {
        resourceReader = new ResourceReader();
    }

    @Override
    public String keyword() {
        return "help";
    }

    @Override
    public void execute(String[] splitted) {
        boolean noArgument = hasArgument(splitted);
        if (noArgument){
            System.out.println(resourceReader.readTextFile("help.txt"));
            return;
        }
        String output = resourceReader.readTextFile("help-" + splitted[1] + ".txt");
        System.out.println(output);
    }
}
