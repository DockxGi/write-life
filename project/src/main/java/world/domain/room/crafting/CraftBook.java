package world.domain.room.crafting;

import lombok.Getter;
import world.domain.Theme;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that contains the full list of recipes that fit in the same theme.
 */
public class CraftBook {
    @Getter
    private Theme theme;
    @Getter
    private List<Recipe> recipes;

    public CraftBook(Theme theme) {
        this.theme = theme;
        recipes = new ArrayList<>();
    }

    public void addRecipe(Recipe recipe){
        this.recipes.add(recipe);
    }
}
