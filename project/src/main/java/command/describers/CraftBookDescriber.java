package command.describers;

import world.domain.Theme;
import world.domain.room.crafting.CraftBook;
import world.domain.room.crafting.Recipe;

import java.util.List;
import java.util.Locale;

public class CraftBookDescriber implements Describer<CraftBook> {
    @Override
    public String describe(CraftBook book, LevelOfDetail levelOfDetail) {
        Theme theme = book.getTheme();
        String themeName = "none";
        if(theme != null){
            themeName = theme.name().toLowerCase(Locale.ROOT);
        }
        List<Recipe> recipes = book.getRecipes();
        RecipeDescriber recipeDescriber = new RecipeDescriber();
        String recipesDescription = recipeDescriber.describeList(recipes, levelOfDetail);

        return String.format("Recipe-book for theme %s\n%s", themeName, recipesDescription);
    }

    @Override
    public String describeList(List<CraftBook> objects, LevelOfDetail levelOfDetail) {
        return null;
    }
}
