package world.domain.cost;

import lombok.Getter;
import world.domain.item.ItemType;

import java.util.HashMap;
import java.util.Map;

/**
 * The price of (buying, creating or doing) something. The price is a map
 * of itemTypes of high enough quality and the amount of such items.
 */
public class Price {
    @Getter
    private Map<ItemQualityRequirement, Integer> items;

    public Price() {
        items = new HashMap<>();
    }

    public void add(ItemType itemType, int minQuality, int quantity){
        ItemQualityRequirement item = new ItemQualityRequirement(itemType, minQuality);
        items.put(item, quantity);
    }


}
