package world.domain.item;

import static world.domain.item.ZeroQualityEffect.BROKEN;
import static world.domain.item.ZeroQualityEffect.DESTROYED;

/**
 * The general type of item it is. For example: a book, a stone, an axe
 */
public enum ItemType {
    STONE(DESTROYED),
    SAPLING(DESTROYED),
    WOOD(BROKEN),
    SEEDS(DESTROYED),
    RED_FLOWER(DESTROYED),
    BLUE_FLOWER(DESTROYED),
    YELLOW_FLOWER(DESTROYED),
    WHITE_FLOWER(DESTROYED),
    BRANCH(BROKEN),
    MUSHROOM(BROKEN),
    SHOVEL(BROKEN),
    AXE(BROKEN),
    PICKAXE(BROKEN),
    GOD_POWER(DESTROYED) //not for mortals
    ;
    private ZeroQualityEffect zeroQualityEffect;

    ItemType(ZeroQualityEffect effect) {
        this.zeroQualityEffect = effect;
    }
}
