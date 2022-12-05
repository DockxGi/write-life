package world.domain.room.feature;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Feature {
    @Getter
    private String name;
    @Getter
    private FeatureType type;

}
