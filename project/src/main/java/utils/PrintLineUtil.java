package utils;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.join;
import static org.apache.commons.lang3.StringUtils.repeat;

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

    /**
     * Prints out the given text as a title. Each word will start with uppercase.
     */
    public static void printAsTitle(String text, char underLineChar){
        String[] split = text.split(" ");

        for(int i = 0; i < split.length; i++){
            split[i] = firstCharToUpperCase(split[i]);
        }

        String title = join(split, " ");
        System.out.println(title);
        String underLine = repeat(underLineChar, split.length + 1);
        System.out.println(underLine);
    }

    /**
     * Returns string with the first character in uppercase if that character is alphabetic.
     */
    public static String firstCharToUpperCase(String s){
        if (s == null || s.length() < 1){
            return s;
        }
        char[] chars = s.toCharArray();
        char firstChar = chars[0];

        if (!Character.isAlphabetic(firstChar)){
            return s;
        }

        chars[0] = Character.toUpperCase(firstChar);
        return new String(chars);
    }
}
