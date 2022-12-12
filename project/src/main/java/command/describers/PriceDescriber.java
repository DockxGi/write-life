package command.describers;

import world.domain.cost.ItemQualityRequirement;
import world.domain.cost.Price;

import java.util.List;
import java.util.Locale;
import java.util.Map;

//todo: implement priceDescriber
public class PriceDescriber implements Describer<Price>{
    @Override
    public String describe(Price price, LevelOfDetail levelOfDetail) {
        StringBuilder sb = new StringBuilder("cost: ");
        Map<ItemQualityRequirement, Integer> items = price.getItems();
        for (ItemQualityRequirement itemQualityRequirement : items.keySet()) {
            String itemType = itemQualityRequirement.getItemType().name().toLowerCase(Locale.ROOT);
            int minQuality = itemQualityRequirement.getMinQuality();
            Integer quantity = items.get(itemQualityRequirement);
            sb.append(String.format("[%s, Amount %d, Quality >= %d]", itemType, quantity, minQuality));
        }
        return sb.toString();
    }

    @Override
    public String describeList(List<Price> objects, LevelOfDetail levelOfDetail) {
        return null;
    }
}
