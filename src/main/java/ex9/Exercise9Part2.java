package ex9;

import util.FileReader;

public class Exercise9Part2 {
    private static final String OPEN = "<";
    private static final String CLOSE = ">";

    public static void main(String[] args) {
        String cleanedInput = cleanedInput();
        if (cleanedInput.isEmpty()) {
            System.out.println(0);
        }

        int score = 0;
        boolean inGarbage = false;

        for (String c : cleanedInput.split("")) {
            if (!inGarbage && c.equals(OPEN)) {
                inGarbage = true;
            } else if (c.equals(CLOSE)) {
                inGarbage = false;
            } else if (inGarbage) {
                score++;
            }

        }

        System.out.println(score);
    }

    private static String cleanedInput() {
        return removeEscaped(readInput());
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
        return FileReader.readFile("input.txt", Exercise9Part2.class).get(0);
    }
}
