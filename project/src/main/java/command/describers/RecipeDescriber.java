package command.describers;

import world.domain.item.ItemType;
import world.domain.room.crafting.Recipe;
import world.domain.room.feature.FeatureType;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static command.describers.LevelOfDetail.LOW;

public class RecipeDescriber implements Describer<Recipe> {

    @Override
    public String describe(Recipe recipe, LevelOfDetail levelOfDetail) {
        String recipeName = recipeName(recipe);
        if (LOW.equals(levelOfDetail)){
            return recipeName;
        }
        String cost = recipeCost(recipe, levelOfDetail);
        String requiredFeatures = recipeFeatures(recipe);
        return recipeName + ": " + cost;
    }

    private String recipeFeatures(Recipe recipe) {
        FeatureTypeDescriber featureTypeDescriber = new FeatureTypeDescriber();
        List<FeatureType> requiredFeatures = recipe.getRequiredFeatures();
        return "needs features " + featureTypeDescriber.describeList(requiredFeatures, LOW);
    }

    private String recipeCost(Recipe recipe, LevelOfDetail levelOfDetail) {
        PriceDescriber priceDescriber = new PriceDescriber();
        return priceDescriber.describe(recipe.getCreationCost(), levelOfDetail);
    }

    private String recipeName(Recipe recipe) {
        return "Recipe for " + recipeResult(recipe);
    }

    private String recipeResult(Recipe recipe) {
        List<ItemType> result = recipe.getResult();
        return result.stream()
                .map(itemType -> itemType.name().toLowerCase(Locale.ROOT))
                .collect(Collectors.joining(" and "));
    }

    @Override
    public String describeList(List<Recipe> recipes, LevelOfDetail levelOfDetail) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < recipes.size(); i++) {
            Recipe recipe = recipes.get(i);
            String recipeDescription = describe(recipe, levelOfDetail);
            sb.append(String.format("%d. %s", i+1, recipeDescription));
            if (i < recipes.size() - 1){
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
