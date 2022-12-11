package utils;

import java.util.List;
import java.util.stream.Collectors;

public class StatisticsUtil {
    /**
     * Calculated tha average (decimals are lost) of a list of Integer. Null values are replaced by the valueForNull.
     */
    public static int average(List<Integer> numbers, int valueForNull){
        int sum = sum(numbers, valueForNull);
        return sum / numbers.size();
    }

    /**
     * Calculates the sum of a list of Integer. Null values are replaced by the valueForNull.
     */
    public static int sum(List<Integer> numbers, int valueForNull){
        List<Integer> newNumbers = replaceNullValues(numbers, valueForNull);
        int sum = 0;
        for (Integer number : newNumbers) {
            sum += number;
        }
        return sum;
    }

    private static List<Integer> replaceNullValues(List<Integer> numbers, int valueForNull) {
        return numbers.stream()
                .map(integer -> integer != null ? integer : valueForNull)
                .collect(Collectors.toList());
    }
}
