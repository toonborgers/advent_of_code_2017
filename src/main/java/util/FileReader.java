package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    public static List<String> readFile(String filename, Class clazz) {
        Scanner scanner = new Scanner(clazz.getResourceAsStream(filename));

        List<String> result = new ArrayList<>();

        while (scanner.hasNextLine()) {
            result.add(scanner.nextLine());
        }

        return result;
    }
}
