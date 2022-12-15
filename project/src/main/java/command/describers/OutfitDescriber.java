package command.describers;

import character.outfit.Outfit;
import character.outfit.OutfitLocation;
import world.domain.item.Item;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class OutfitDescriber implements Describer<Outfit> {

    @Override
    public String describe(Outfit outfit, LevelOfDetail levelOfDetail) {
        ItemDescriber itemDescriber = new ItemDescriber();
        Map<OutfitLocation, Item> wearing = outfit.itemsByLocation();

        StringBuilder description = new StringBuilder();
        for (OutfitLocation location : wearing.keySet()) {
            String locationName = location.name().toLowerCase(Locale.ROOT);
            Item item = wearing.get(location);
            String itemDescription = item == null ? "<empty>" : itemDescriber.describe(item, levelOfDetail);
            description.append(String.format("%s -> %s\n", locationName, itemDescription));
        }

        return description.toString();
    }

    @Override
    public String describeList(List<Outfit> objects, LevelOfDetail levelOfDetail) {
        return null;
    }
}
