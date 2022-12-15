package character.outfit;

import lombok.Getter;

/**
 * Describes the position on which wearables/clothes can be positioned.
 * There can be no two wearables at the exact same OutfitLocation. A wearable item can take
 * more than one location (example: a dress).
 */
public enum OutfitLocation {
    HEAD(5),
    NECK(5),
    HANDS(5),
    CHEST_INNER(20),
    CHEST_OUTER(20),
    LEGS_INNER(20),
    LEGS_OUTER(20),
    FEET(5);

    @Getter
    private int coverageImpact; //coverage impact in percent (total of all should be 100)

    OutfitLocation(int coverageImpact) {
        this.coverageImpact = coverageImpact;
    }
}
