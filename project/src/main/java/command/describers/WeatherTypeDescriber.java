package command.describers;

import world.domain.weather.WeatherType;

import java.util.List;
import java.util.Locale;

import static org.apache.commons.collections.CollectionUtils.isEmpty;

public class WeatherTypeDescriber implements Describer<WeatherType> {
    @Override
    public String describe(WeatherType weatherType, LevelOfDetail levelOfDetail) {
        if (weatherType == null){
            return "";
        }
        return weatherType.name().toLowerCase(Locale.ROOT);
    }

    @Override
    public String describeList(List<WeatherType> objects, LevelOfDetail levelOfDetail) {
        if (isEmpty(objects)){
            return "";
        }
        return describe(objects.get(0), levelOfDetail);
    }
}
