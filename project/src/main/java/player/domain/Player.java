package player.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import world.domain.cost.Price;

@Getter
@Setter
@AllArgsConstructor
public class Player {

    private String name;

    public boolean canPay(Price price) {
        if (price == null){
            return true;
        }
        return false;
    }
}
