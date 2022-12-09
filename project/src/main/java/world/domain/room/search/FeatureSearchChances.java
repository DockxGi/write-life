package world.domain.room.search;

import world.domain.item.ItemType;
import world.domain.room.feature.FeatureType;

import java.util.List;

import static world.domain.item.ItemType.SAPLING;
import static world.domain.item.ItemType.STONE;
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
            return List.of(stoneChance, saplingChance);
        }

        return null;
    }
}
