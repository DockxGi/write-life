package world.domain.room.feature;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Feature {
    @Getter
    private String name;
    @Getter
    @Setter
    private FeatureType type;
    @Getter
    private Integer quality; //percentage (value between 1 and 100), null means that feature does not decay
}
