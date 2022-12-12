package command.playing;

import command.ArgumentCommand;
import command.describers.CraftBookDescriber;
import command.describers.LevelOfDetail;
import game.GameModel;
import world.domain.room.crafting.CraftBook;
import world.domain.room.crafting.CraftingLibrary;

import static command.describers.LevelOfDetail.LOW;
import static command.describers.LevelOfDetail.MEDIUM;
import static world.domain.Theme.fromName;

public class CraftBookCommand extends ArgumentCommand {
    @Override
    public String keyword() {
        return "craft-book";
    }

    @Override
    public void execute(String[] splitted, GameModel game) {
        CraftingLibrary library = new CraftingLibrary();

        CraftBook craftBook = takeCraftBookFromLibrary(splitted, library);
        LevelOfDetail levelOfDetail = determineLevelOfDetail(splitted);

        showCraftBook(craftBook, levelOfDetail);
    }

    private LevelOfDetail determineLevelOfDetail(String[] splitted) {
        LevelOfDetail levelOfDetail = LOW;
        if (splitted.length > 2 && splitted[2].equals("detailed")){
            levelOfDetail = MEDIUM;
        }
        return levelOfDetail;
    }

    private CraftBook takeCraftBookFromLibrary(String[] splitted, CraftingLibrary library) {
        CraftBook craftBook = null;
        if (! hasArgument(splitted)){
            craftBook = library.findCraftBookByTheme(null);
        } else {
            String argument = splitted[1];
            if (argument.equals("none")){
                argument = null;
            }
            craftBook = library.findCraftBookByTheme(fromName(argument));
        }
        return craftBook;
    }

    private void showCraftBook(CraftBook craftBook, LevelOfDetail levelOfDetail) {
        CraftBookDescriber describer = new CraftBookDescriber();
        String description = describer.describe(craftBook, levelOfDetail);
        System.out.println(description);
    }
}
