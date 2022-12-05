package world.domain.cost;

import lombok.AllArgsConstructor;
import lombok.Getter;
import world.domain.item.ItemType;

/**
 * Quality requirement of an item.
 */
@AllArgsConstructor
@Getter
public class ItemQualityRequirement {
    private ItemType itemType;
    private int minQuality;
}
