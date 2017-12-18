package ex9;

import util.FileReader;

public class Exercise9Part1 {
    private static final String OPEN = "{";
    private static final String CLOSE = "}";

    public static void main(String[] args) {
        String cleanedInput = cleanedInput();
        if (cleanedInput.isEmpty()) {
            System.out.println(0);
        }
        int level = 0;
        int score = 0;

        for (String s : cleanedInput.split("")) {
            if (s.equals(OPEN)) {
                score += ++level;
            } else if (s.equals(CLOSE)) {
                level--;
            }
        }

        System.out.println(score);
    }

    private static String cleanedInput() {
        return removeGarbage(removeEscaped(readInput()));
    }

    private static String removeGarbage(String in) {
        String result = in;

        while (result.contains("<")) {
            int start = result.indexOf("<");
            int end = result.indexOf(">", start);

            String before = result.substring(0, start);
            String after = result.substring(end + 1);
            result = before + after;
        }

        return result;
    }

    private static String removeEscaped(String in) {
        String result = in;
        while (result.contains("!")) {
            result = result.replaceFirst("!.", "");
        }

        return result;
    }

    private static String readInput() {
        return FileReader.readFile("input.txt", Exercise9Part1.class);
    }
}
