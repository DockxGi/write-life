package world.domain.room.feature;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Describes what happens when a feature is broken and not just deleted but transitioned to some other type
 * of Feature.
 */
@AllArgsConstructor
@Getter
public class FeatureBrokenTransition {
    private FeatureType featureType;
    private Integer quality;
}
