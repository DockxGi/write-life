package command.describers;

import character.outfit.Outfit;
import character.outfit.OutfitLocation;
import utils.StatisticsUtil;
import world.domain.item.Item;

import java.util.*;
import java.util.stream.Collectors;

import static command.describers.LevelOfDetail.LOW;

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
        if (LOW.equals(levelOfDetail)){
            return description.toString();
        }

        Collection<Item> items = wearing.values();
        items.removeIf(Objects::isNull);
        List<Integer> qualities = items.stream().map(Item::getQuality).collect(Collectors.toList());

        //extra statistics
        int averageQuality = StatisticsUtil.average(qualities, 100);
        int coverage = outfit.coverage();

        description.append("\n");
        description.append(String.format("Statistics: average quality %d, estimated coverage %d\n", averageQuality, coverage));
        return description.toString();
    }

    @Override
    public String describeList(List<Outfit> objects, LevelOfDetail levelOfDetail) {
        return null;
    }
}
