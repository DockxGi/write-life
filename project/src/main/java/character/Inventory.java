package character;

import lombok.Getter;
import world.domain.cost.ItemQualityRequirement;
import world.domain.item.Item;
import world.domain.item.ItemType;

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

    public void removeItems(ItemQualityRequirement itemQualityRequirement, int requiredAmount) {
        Iterator<Item> iterator = items.iterator();
        int amountRemoved = 0;
        while (iterator.hasNext() && amountRemoved < requiredAmount){
            Item item = iterator.next();
            boolean matches = item.matches(itemQualityRequirement);
            if (matches){
                amountRemoved += 1;
                iterator.remove();
            }
        }
    }

}
