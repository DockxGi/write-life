package world.domain.room.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import world.domain.item.ItemType;

/**
 * The chance of finding a specific item when searching the room or a feature of the room.
 */
@AllArgsConstructor
@Getter
public class SearchChance {
    private int chance; //percentage value min is 1 max is 100
    private ItemType itemType;
}
