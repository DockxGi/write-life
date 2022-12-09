package world.domain.item;

import lombok.Getter;
import world.domain.cost.ItemQualityRequirement;

/**
 * An item is something that the player can take or drop in a room.
 * It has a quality. If the quality reaches 0 then it will be broken or destroyed.
 */
public class Item {
    @Getter
    private String name; //optional name
    @Getter
    private ItemType type;
    @Getter
    private Integer quality; //value from 0 to 100, it is a percentage

    public Item(ItemType type, Integer quality) {
        this.type = type;
        this.quality = quality;
    }

    public boolean matches(ItemQualityRequirement itemQualityRequirement) {
        ItemType requiredType = itemQualityRequirement.getItemType();
        int minQuality = itemQualityRequirement.getMinQuality();

        return requiredType.equals(type) && quality >= minQuality;
    }
}
