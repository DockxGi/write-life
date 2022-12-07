package command.describers;

import world.domain.item.Item;

import java.util.List;
import java.util.Locale;

public class ItemDescriber implements Describer<Item> {

    @Override
    public String describe(Item object, LevelOfDetail levelOfDetail) {
        String name = object.getName() == null ? "anonymous" : object.getName();
        String type = object.getType().name().toLowerCase(Locale.ROOT);
        Integer quality = object.getQuality();

        return String.format("Item: [name: %s, type: %s, quality: %s]", name, type, quality);
    }

    @Override
    public String describeList(List<Item> objects, LevelOfDetail levelOfDetail) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < objects.size(); i++){
            Item object = objects.get(i);
            String itemDescription = describe(object, levelOfDetail);
            sb.append(String.format("%d. %s", i + 1, itemDescription));
        }
        return sb.toString();
    }
}
