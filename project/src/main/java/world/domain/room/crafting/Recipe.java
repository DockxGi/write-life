package world.domain.room.crafting;

import lombok.Getter;
import world.domain.cost.Price;
import world.domain.item.ItemType;
import world.domain.room.feature.FeatureType;

import java.util.ArrayList;
import java.util.List;

/**
 * The recipe to create items(s) from other items with the user of feature(s).
 */
public class Recipe {
    @Getter
    private Price creationCost;
    @Getter
    private List<ItemType> result;
    @Getter
    private List<FeatureType> requiredFeatures;

    public Recipe(Price creationCost) {
        this.creationCost = creationCost;
        result = new ArrayList<>();
        requiredFeatures = new ArrayList<>();
    }

    public void addToResult(ItemType itemType){
        result.add(itemType);
    }

    public void addRequiredFeature(FeatureType featureType){
        requiredFeatures.add(featureType);
    }
}
