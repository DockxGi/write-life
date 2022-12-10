package world.domain.events;

import lombok.AllArgsConstructor;
import world.domain.World;
import world.domain.time.CycledEvent;

@AllArgsConstructor
public class WeatherChanges implements CycledEvent {

    private World world;

    @Override
    public void onNewCycle() {
        world.randomWeatherType();
    }
}
