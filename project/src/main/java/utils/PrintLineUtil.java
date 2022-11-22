package utils;

import java.util.List;

/**
 * Utility for some standard ways of printing lines to the System Out
 */
public class PrintLineUtil {

    /**
     * Prints the list of Strings by putting a number before each String and
     * giving every String a new line.
     */
    public static void printAsOrderedList(List<String> list){
        int nr = 1;
        for (String string : list) {
            System.out.printf("%d. %s%n",nr, string);
            nr++;
        }
    }

    /**
     * Prints that an event happened.
     */
    public static void printEvent(String event){
        System.out.printf("[%s]%n", event);
    }
}
