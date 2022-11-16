package command;

public interface Command {

    String keyword();

    void execute(String[] splitted);

}
