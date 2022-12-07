package character;

import lombok.Getter;
import world.domain.item.Item;
import world.domain.item.ItemType;

import java.util.ArrayList;
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

}
