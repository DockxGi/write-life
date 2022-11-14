import org.apache.commons.lang.StringUtils;
import resources.ResourceReader;

import java.util.Locale;
import java.util.Scanner;

import static org.apache.commons.lang.StringUtils.lowerCase;

public class WriteLifeApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ResourceReader resourceReader = new ResourceReader();
        boolean goOn = true;

        System.out.println(resourceReader.readTextFile("startup-screen.txt"));

        while (goOn){
            System.out.print("\n> ");
            String nextLine = scanner.nextLine().trim().toLowerCase(Locale.ROOT);

            goOn = !"q".equals(nextLine);
            if (goOn){
                processCommand(nextLine);
            }
        }
    }

    private static void processCommand(String nextLine) {

    }
}
