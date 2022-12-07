package command.describers;

import java.util.Collection;
import java.util.List;

/**
 * A describer is a class that describes a domain object of type T.
 * @param <T>
 */
public interface Describer<T> {
    /**
     * Returns a string that describes the object.
     */
    String describe(T object, LevelOfDetail levelOfDetail);

    /**
     * Returns a list that describes a collection of objects.
     */
    String describeList(List<T> objects, LevelOfDetail levelOfDetail);
}
