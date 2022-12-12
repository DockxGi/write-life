package command.playing;

import command.ArgumentCommand;
import command.describers.CraftBookDescriber;
import game.GameModel;
import world.domain.room.crafting.CraftBook;
import world.domain.room.crafting.CraftingLibrary;

import static command.describers.LevelOfDetail.LOW;
import static world.domain.Theme.fromName;

public class CraftBookCommand extends ArgumentCommand {
    @Override
    public String keyword() {
        return "craft-book";
    }

    @Override
    public void execute(String[] splitted, GameModel game) {
        CraftingLibrary library = new CraftingLibrary();

        CraftBook craftBook = null;
        if (! hasArgument(splitted)){
            craftBook = library.findCraftBookByTheme(null);
        } else {
            String argument = splitted[1];
            craftBook = library.findCraftBookByTheme(fromName(argument));
        }

        showCraftBook(craftBook);
    }

    private void showCraftBook(CraftBook craftBook) {
        CraftBookDescriber describer = new CraftBookDescriber();
        String description = describer.describe(craftBook, LOW);
        System.out.println(description);
    }
}
