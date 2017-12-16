package ex16;

import util.FileReader;
import util.Tuple;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Exercise16 {
    private static final int AMOUNT_OF_LETTERS = 16;
    private static List<String> LETTERS = IntStream.range(0, AMOUNT_OF_LETTERS)
            .boxed()
            .map(i -> (((char) (i + 'a'))))
            .map(String::valueOf)
            .collect(toList());
    private static final List<String> MOVES = readInput();

    public static void main(String[] args) {
       /* MOVES
                .forEach(Exercise16::apply);
        System.out.println(LETTERS.stream().collect(joining()));

*/
        String before = "ABCDEFG";
        String after = "CDAFGEB";

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
        String first = LETTERS.get(indices.getA());
        LETTERS.set(indices.getA(), LETTERS.get(indices.getB()));
        LETTERS.set(indices.getB(), first);
    }

    private static void spin(int amount) {
        List<String> newVal =
                LETTERS.subList(LETTERS.size() - amount, LETTERS.size());

        newVal.addAll(LETTERS.subList(0, LETTERS.size() - amount));
        LETTERS = newVal;
    }

    private static List<String> readInput() {
        return Arrays.asList(FileReader.readFile("input.txt", Exercise16.class)
                .get(0).split(","));
    }


}
