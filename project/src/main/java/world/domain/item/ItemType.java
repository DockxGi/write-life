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
    LEAVES(DESTROYED),
    RED_FLOWER(DESTROYED),
    BLUE_FLOWER(DESTROYED),
    YELLOW_FLOWER(DESTROYED),
    WHITE_FLOWER(DESTROYED),
    BRANCH(BROKEN),
    MUSHROOM(BROKEN),
    SHOVEL(BROKEN),
    AXE(BROKEN),
    PICKAXE(BROKEN),
    LEAVES_SKIRT(BROKEN),
    LEAVES_BIKINI_TOP(BROKEN),
    LEAVES_TROUSERS(BROKEN),
    GOD_POWER(DESTROYED) //not for mortals
    ;
    private ZeroQualityEffect zeroQualityEffect;

    ItemType(ZeroQualityEffect effect) {
        this.zeroQualityEffect = effect;
    }

    public static ItemType fromName(String itemType) {
        ItemType[] values = values();
        for (ItemType value : values) {
            if (value.name().equalsIgnoreCase(itemType)){
                return value;
            }
        }
        return null;
    }
}
