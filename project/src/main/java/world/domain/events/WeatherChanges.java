package world.domain.events;

import world.domain.World;
import world.domain.time.CycledEvent;

public class WeatherChanges implements CycledEvent {

    private World world;

    public WeatherChanges(World world) {
        this.world = world;
    }

    @Override
    public void onNewCycle() {
        world.randomWeatherType();
    }
}
