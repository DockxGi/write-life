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
    @Setter
    private Integer quality; //percentage (value between 1 and 100), null means that feature does not decay

    /**
     * Damages the feature if it can be damaged.
     */
    public void damage() {
        if (quality == null){
            return;
        }
        quality = quality - 1;
    }

    public boolean isBroken(){
        if (quality == null){
            return false;
        }
        return quality == 0;
    }
}
