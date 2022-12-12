package world.domain.room.crafting;

import world.domain.Theme;
import world.domain.cost.Price;
import world.domain.cost.PriceBuilder;
import world.domain.item.ItemType;

import static world.domain.item.ItemType.*;
import static world.domain.room.feature.FeatureType.TREES;

/**
 * Provides all CraftBooks.
 * //todo: support the craft command
 */
public class CraftingLibrary {

    public CraftBook findCraftBookByTheme(Theme theme){
        if (theme == null){
            return defaultCraftBook();
        }
        return null;
    }

    /**
     * CraftBook that contains the recipes that are always enabled. There are not related to a theme.
     */
    private CraftBook defaultCraftBook() {
        CraftBook craftBook = new CraftBook(null);

        craftBook.addRecipe(shovelRecipe());
        craftBook.addRecipe(axeRecipe());
        craftBook.addRecipe(woodRecipe());

        return craftBook;
    }

    private Recipe shovelRecipe() {
        Price price = new PriceBuilder()
                .item(ItemType.BRANCH, 10, 1)
                .item(ItemType.STONE, 10, 1)
                .build();

        Recipe recipe = new Recipe(price);
        recipe.addToResult(SHOVEL);

        return recipe;
    }

    private Recipe axeRecipe(){
        Price price = new PriceBuilder()
                .item(ItemType.BRANCH, 10, 1)
                .item(ItemType.STONE, 10, 1)
                .build();

        Recipe recipe = new Recipe(price);
        recipe.addToResult(AXE);
        return recipe;
    }

    private Recipe woodRecipe(){
        Price price = new PriceBuilder()
                .item(AXE, 1, 1)
                .build();

        Recipe recipe = new Recipe(price);
        recipe.addRequiredFeature(TREES);
        recipe.addToResult(WOOD);
        return recipe;
    }
}
