package ex1;

import java.util.List;

import static util.FileReader.readFile;

public class Exercise1Part1 {

    private static final List<Integer> INPUT = readInput();

    public static void main(String[] args) {
        int sum = 0;
        for (int i = 0; i < INPUT.size(); i++) {
            if (INPUT.get(i) == INPUT.get((i + 1) % INPUT.size())) {
                sum += INPUT.get(i);
            }
        }
        System.out.println(sum);
    }

    private static List<Integer> readInput() {
        return readFile("input.txt", Exercise1Part1.class, "", Integer::parseInt);
    }
}
