package world.domain.room.search;

import world.domain.item.ItemType;
import world.domain.room.feature.FeatureType;

import java.util.List;

import static world.domain.item.ItemType.*;
import static world.domain.room.feature.FeatureType.*;

/**
 * Indicates the chances to find something when searching in/around a feature of the room.
 * Returns null if you can not find anything for that FeatureType.
 */
public class FeatureSearchChances {
    public static List<SearchChance> searchChancesFor(FeatureType featureType){

        if (ROCKS.equals(featureType)){
            return createSearchChancesForRocks();
        }
        if (MEADOW.equals(featureType)){
            return createSearchChancesForMeadow();
        }
        if (TREE.equals(featureType)){
            return createSearchChancesForTree();
        }
        if (TREES.equals(featureType)){
            return createSearchChancesForTrees();
        }
        return null;
    }

    private static List<SearchChance> createSearchChancesForTrees() {
        SearchChance branchSearchChance = new SearchChance(100, BRANCH);
        SearchChance saplingSearchChance = new SearchChance(80, ItemType.SAPLING);
        SearchChance mushroomSearchChance = new SearchChance(20, MUSHROOM);
        SearchChance seedsChance = new SearchChance(50, SEEDS);
        return List.of(branchSearchChance, saplingSearchChance, mushroomSearchChance, seedsChance);
    }

    private static List<SearchChance> createSearchChancesForTree() {
        SearchChance branchSearchChance = new SearchChance(90, BRANCH);
        SearchChance saplingSearchChance = new SearchChance(2, ItemType.SAPLING);
        return List.of(branchSearchChance, saplingSearchChance);
    }

    private static List<SearchChance> createSearchChancesForMeadow() {
        SearchChance yellowFlowerChance = new SearchChance(20, YELLOW_FLOWER);
        SearchChance blueFlowerChance = new SearchChance(20, BLUE_FLOWER);
        SearchChance redFlowerChance = new SearchChance(20, RED_FLOWER);
        SearchChance whiteFlowerChance = new SearchChance(20, WHITE_FLOWER);
        SearchChance seedsChance = new SearchChance(30, SEEDS);
        return List.of(yellowFlowerChance, blueFlowerChance, redFlowerChance, whiteFlowerChance, seedsChance);
    }

    private static List<SearchChance> createSearchChancesForRocks() {
        SearchChance stoneChance = new SearchChance(100, STONE);
        SearchChance saplingChance = new SearchChance(50, ItemType.SAPLING);
        SearchChance seedsChance = new SearchChance(30, SEEDS);
        return List.of(stoneChance, saplingChance, seedsChance);
    }
}
