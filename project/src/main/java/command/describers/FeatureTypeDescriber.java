package command.describers;

import world.domain.room.feature.FeatureType;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class FeatureTypeDescriber implements Describer<FeatureType> {
    @Override
    public String describe(FeatureType object, LevelOfDetail levelOfDetail) {
        return object.name().toLowerCase(Locale.ROOT);
    }

    @Override
    public String describeList(List<FeatureType> objects, LevelOfDetail levelOfDetail) {
        return objects.stream()
                .map(featureType -> describe(featureType, levelOfDetail))
                .collect(Collectors.joining(" and "));
    }
}
