package character;

import lombok.Getter;
import org.apache.commons.collections.CollectionUtils;
import world.domain.cost.ItemQualityRequirement;
import world.domain.item.Item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A personal container for items.
 */
public class Inventory {
    private static final int CAPACITY = 5;

    @Getter
    private final List<Item> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public boolean isFull(){
        return items.size() >= CAPACITY;
    }

    public boolean addItem(Item item){
        if (isFull()){
            return false;
        }
        return items.add(item);
    }

    /**
     * Removes items from the inventory and returns the list of qualities of those items, including null values.
     */
    public List<Integer> removeItems(ItemQualityRequirement itemQualityRequirement, int requiredAmount) {
        Iterator<Item> iterator = items.iterator();
        int amountRemoved = 0;
        List<Integer> qualities = new ArrayList<>();
        while (iterator.hasNext() && amountRemoved < requiredAmount){
            Item item = iterator.next();
            boolean matches = item.matches(itemQualityRequirement);
            if (matches){
                amountRemoved += 1;
                qualities.add(item.getQuality());
                iterator.remove();
            }
        }
        return qualities;
    }

    public boolean isEmpty() {
        return CollectionUtils.isEmpty(items);
    }

    public Item getItemByPosition(Integer position) {
        if (isEmpty() || items.size() < position - 1){
            return null;
        }
        return items.get(position - 1);
    }

    public Item getItemByName(String argument) {
        if (isEmpty()){
            return null;
        }
        return items.stream()
                .filter(item -> argument.equals(item.getName()))
                .findFirst()
                .orElse(null);
    }

    public void removeItem(Item toRemove) {
        if (isEmpty()){
            return;
        }
        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()){
            Item item = iterator.next();
            if (toRemove.equals(item)){
                iterator.remove();
                return;
            }
        }
    }
}
