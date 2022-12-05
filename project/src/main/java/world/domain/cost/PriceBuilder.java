package world.domain.cost;

import world.domain.item.ItemType;

public class PriceBuilder {
    private final Price price = new Price();

    public PriceBuilder item(ItemType itemType, int minQuality, int quantity){
        price.add(itemType, minQuality, quantity);
        return this;
    }

    public Price build(){
        return price;
    }
}
