package world.domain.room.feature;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Feature {
    @Getter
    private String name;
    @Getter
    private FeatureType type;
    @Getter
    private Integer quality; //percentage (value between 1 and 100), null means that feature does not decay
}
