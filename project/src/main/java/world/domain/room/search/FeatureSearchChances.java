package world.domain.room.search;

import world.domain.item.ItemType;
import world.domain.room.feature.FeatureType;

import java.util.List;

import static world.domain.item.ItemType.*;
import static world.domain.room.feature.FeatureType.MEADOW;
import static world.domain.room.feature.FeatureType.ROCKS;

/**
 * Indicates the chances to find something when searching in/around a feature of the room.
 * Returns null if you can not find anything for that FeatureType.
 */
public class FeatureSearchChances {
    public static List<SearchChance> searchChancesFor(FeatureType featureType){

        if (ROCKS.equals(featureType)){
            SearchChance stoneChance = new SearchChance(100, STONE);
            SearchChance saplingChance = new SearchChance(50, SAPLING);
            SearchChance seedsChance = new SearchChance(30, SEEDS);
            return List.of(stoneChance, saplingChance, seedsChance);
        }
        if (MEADOW.equals(featureType)){
            SearchChance yellowFlowerChance = new SearchChance(20, YELLOW_FLOWER);
            SearchChance blueFlowerChance = new SearchChance(20, BLUE_FLOWER);
            SearchChance redFlowerChance = new SearchChance(20, RED_FLOWER);
            SearchChance whiteFlowerChance = new SearchChance(20, WHITE_FLOWER);
            SearchChance seedsChance = new SearchChance(30, SEEDS);
            return List.of(yellowFlowerChance, blueFlowerChance, redFlowerChance, whiteFlowerChance, seedsChance);
        }

        return null;
    }
}
