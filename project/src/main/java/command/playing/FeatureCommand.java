package command.playing;

import command.ArgumentCommand;
import game.GameModel;
import world.domain.cost.ItemQualityRequirement;
import world.domain.cost.Price;
import world.domain.room.RoomType;
import world.domain.room.feature.FeatureType;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class FeatureCommand extends ArgumentCommand {
    @Override
    public String keyword() {
        return "feature";
    }

    @Override
    public void execute(String[] splitted, GameModel game) {
        if (!hasArgument(splitted)){
            System.out.println("The feature command needs an argument. See: HELP FEATURE");
        }
        String argument = splitted[1];
        if (argument.equals("list")){
            processFeatureListCommand(splitted);
        }
    }

    private void processFeatureListCommand(String[] splitted) {
        if (splitted.length < 3){
            showFeatureTypesOfRoomType(null);
            return;
        }
        String argument = splitted[2];
        if (argument.equals("free")){
            showFeatureTypesByFree(true);
        } else if (argument.equals("non-free")){
            showFeatureTypesByFree(false);
        } else {
            RoomType type = RoomType.fromName(argument);
            showFeatureTypesOfRoomType(type);
        }
    }

    /**
     * If the free parameter is true then this shows the free featureTypes.
     * Otherwise, the non-free featureTypes are shown.
     */
    private void showFeatureTypesByFree(boolean free) {
        List<FeatureType> featureTypes = FeatureType.getAllByFree(free);
        showFeatureTypes(featureTypes);
    }

    private void showFeatureTypesOfRoomType(RoomType filter) {
        List<FeatureType> featureTypes = FeatureType.getAllForRoomType(filter);
        showFeatureTypes(featureTypes);
    }

    private void showFeatureTypes(List<FeatureType> featureTypes) {
        for (FeatureType featureType : featureTypes) {
            printFeatureType(featureType);
        }
    }

    private void printFeatureType(FeatureType featureType) {
        String lowerCaseName = featureType.name().toLowerCase(Locale.ROOT);
        StringBuilder sb = new StringBuilder(lowerCaseName + " -> ");

        Price price = featureType.getPrice();
        if (price == null){
            sb.append("free");
            System.out.println(sb);
            return;
        }
        String priceDescription = describePrice(price);
        sb.append(priceDescription);
        System.out.println(sb);
    }

    private String describePrice(Price price){
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

}
