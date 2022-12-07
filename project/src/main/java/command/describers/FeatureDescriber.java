package command.describers;

import world.domain.room.feature.Feature;
import world.domain.room.feature.FeatureType;

import java.util.List;

import static command.describers.LevelOfDetail.LOW;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.join;

public class FeatureDescriber implements Describer<Feature> {
    @Override
    public String describe(Feature object, LevelOfDetail levelOfDetail) {
        String name = object.getName();
        FeatureType type = object.getType();

        if (LOW.equals(levelOfDetail)){
            return String.format("%s (%s)", name, type);
        }

        Integer quality = object.getQuality();
        return String.format("%s (%s) quality: %d", name, type, quality);
    }

    @Override
    public String describeList(List<Feature> objects, LevelOfDetail levelOfDetail) {
        List<String> descriptions = objects
                .stream()
                .map(feature -> describe(feature, levelOfDetail))
                .collect(toList());

        if (LOW.equals(levelOfDetail)){
            return join(descriptions, ", ");
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < descriptions.size(); i++){
            sb.append(String.format("%d. %s", i + 1, descriptions.get(i)));
        }
        return sb.toString();
    }
}
