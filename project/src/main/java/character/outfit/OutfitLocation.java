package character.outfit;

/**
 * Describes the position on which wearables/clothes can be positioned.
 * There can be no two wearables at the exact same OutfitLocation. A wearable item can take
 * more than one location (example: a dress).
 */
public enum OutfitLocation {
    HEAD,
    HANDS,
    CHEST_INNER,
    CHEST_OUTER,
    LEGS_INNER,
    LEGS_OUTER,
    FEET
}
