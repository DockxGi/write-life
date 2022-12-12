package world.domain.room.feature;

import static org.apache.commons.lang3.RandomUtils.nextInt;
import static world.domain.room.feature.FeatureType.*;

/**
 * Tells what happens when a feature is broken.
 */
public class FeatureBrokenTransitionMapping {
    /**
     * Returns the transition that has to happen when a feature is broken.
     * Null means that the feature should be removed from the room.
     */
    public FeatureBrokenTransition transitionUponBroken(FeatureType featureType){
        if (featureType.equals(TREE)){
            return new FeatureBrokenTransition(DEAD_TREE, nextInt(50,100));
        }
        if (featureType.equals(TREES)){
            return new FeatureBrokenTransition(DEAD_TREES, nextInt(50,100));
        }
        if (featureType.equals(MEADOW)){
            return new FeatureBrokenTransition(BARREN_LAND, nextInt(50,100));
        }
        return null;
    }
}
