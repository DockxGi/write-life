package character.outfit;

import world.domain.item.ItemType;

import java.util.List;

import static character.outfit.OutfitLocation.*;

/**
 * A class that tells where an item can be placed on the body of a character.
 */
public class ItemTypeOutfitLocationMapper {
    public static List<OutfitLocation> outfitLocationForItemType(ItemType itemType){
        return switch (itemType) {
            case WHITE_FLOWER, RED_FLOWER, BLUE_FLOWER, YELLOW_FLOWER -> List.of(HEAD);
            case LEAVES_BIKINI_TOP -> List.of(CHEST_INNER);
            case LEAVES_TROUSERS, LEAVES_SKIRT -> List.of(LEGS_OUTER);
            default -> null;
        };
    }
}
