package ex16;

import util.FileReader;
import util.Tuple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

public class Exercise16Part2 {
    private static final int AMOUNT_OF_LETTERS = 16;
    private static String LETTERS = IntStream.range(0, AMOUNT_OF_LETTERS)
            .boxed()
            .map(i -> (((char) (i + 'a'))))
            .map(String::valueOf)
            .collect(joining());

    private static final List<String> MOVES = readInput();
    private static final Map<String, String> CACHE = new HashMap<>();

    public static void main(String[] args) {
        for (int i = 0; i < 1000000000; i++) {
            if (CACHE.containsKey(LETTERS)) {
                LETTERS = CACHE.get(LETTERS);
            } else {
                String before = LETTERS;
                MOVES.forEach(Exercise16Part2::apply);
                CACHE.put(before, LETTERS);
            }
        }
        System.out.println(CACHE.size());
        System.out.println(LETTERS);

    }

    private static void apply(String move) {
        switch (move.charAt(0)) {
            case 's':
                spin(Integer.parseInt(move.substring(1)));
                break;
            case 'x':
                swap(
                        getPartsAfterFirstLetter(move)
                                .map(Integer::parseInt, Integer::parseInt)
                );
                break;
            case 'p':
                swap(
                        getPartsAfterFirstLetter(move)
                                .map(LETTERS::indexOf, LETTERS::indexOf)
                );
                break;
        }
    }

    private static Tuple<String, String> getPartsAfterFirstLetter(String in) {
        String[] parts = in.substring(1).split("/");
        return new Tuple<>(parts[0], parts[1]);
    }

    private static void swap(Tuple<Integer, Integer> indices) {
        char[] c = LETTERS.toCharArray();

        char temp = c[indices.getA()];
        c[indices.getA()] = c[indices.getB()];
        c[indices.getB()] = temp;

        LETTERS = new String(c);
    }

    private static void spin(int amount) {
        LETTERS = LETTERS.substring(LETTERS.length() - amount)
                + LETTERS.substring(0, LETTERS.length() - amount);
    }

    private static List<String> readInput() {
        return FileReader.readFile("input.txt", Exercise16.class, ",");
    }
}
