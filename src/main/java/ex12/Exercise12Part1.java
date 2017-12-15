package ex12;

import util.FileReader;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.signum;
import static java.util.stream.Collectors.toMap;

public class Exercise12Part1 {
    private static final int numToFind = 0;
    private final static Map<Integer, List<Integer>> input = readInput();
    private static final Set<Integer> checked = new HashSet<>();
    private static final Set<Integer> matched = new HashSet<>(signum(numToFind));


    public static void main(String[] args) {
        checkKey(numToFind);

        System.out.println(matched.size());
    }

    private static void checkKey(int key) {
        if (!checked.add(key)) {
            return;
        }

        matched.addAll(input.get(key));

        input.get(key)
                .forEach(Exercise12Part1::checkKey);
    }

    private static Map<Integer, List<Integer>> readInput() {
        return FileReader.readFile("input.txt", Exercise12Part1.class).stream()
                .map(l -> l.split("<->"))
                .collect(toMap(
                        arr -> parseInt(arr[0].trim()),
                        arr -> Arrays.stream(arr[1].split(",")).map(String::trim).map(Integer::parseInt).collect(Collectors.toList()))
                );

    }
}
