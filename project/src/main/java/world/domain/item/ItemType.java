package world.domain.item;

import static world.domain.item.ZeroQualityEffect.BROKEN;
import static world.domain.item.ZeroQualityEffect.DESTROYED;

/**
 * The general type of item it is. For example: a book, a stone, an axe
 */
public enum ItemType {
    STONE(DESTROYED),
    SAPLING(DESTROYED),
    WOOD(BROKEN);
    private ZeroQualityEffect zeroQualityEffect;

    ItemType(ZeroQualityEffect effect) {
        this.zeroQualityEffect = effect;
    }
}
