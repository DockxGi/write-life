package world.domain.events;

import lombok.AllArgsConstructor;
import world.domain.World;
import world.domain.time.CycledEvent;

import static world.domain.room.feature.FeatureType.*;
import static world.domain.weather.WeatherType.RAIN;

@AllArgsConstructor
public class TreesGrow implements CycledEvent {

    private World world;

    @Override
    public void onNewCycle() {
        if (RAIN.equals(world.getWeatherType())){
            world.replaceAllFeaturesOfType(SAPLING, TREE);
            world.replaceAllFeaturesOfType(SAPLINGS, TREES);
        }
    }
}
