package world.domain.item;

/**
 * Defines what happens when the item reaches quality 0.
 */
public enum ZeroQualityEffect {
    BROKEN, //it is broken, but might be repaired
    DESTROYED //it is removed from the game
}
