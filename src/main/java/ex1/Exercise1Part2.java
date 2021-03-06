package ex1;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static util.FileReader.readLines;

public class Exercise1Part2 {

    private static final List<Integer> INPUT = readInput();

    public static void main(String[] args) {
        int sum = 0;
        int amountToAdd = INPUT.size() / 2;
        for (int i = 0; i < INPUT.size(); i++) {
            if (INPUT.get(i) == INPUT.get((i + amountToAdd) % INPUT.size())) {
                sum += INPUT.get(i);
            }
        }
        System.out.println(sum);
    }

    private static List<Integer> readInput() {
        return Arrays.stream(readLines("input.txt", Exercise1Part2.class).get(0).split(""))
                .map(Integer::parseInt)
                .collect(toList());
    }
}
