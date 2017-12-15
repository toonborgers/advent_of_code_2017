package ex12;

import util.FileReader;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

public class Exercise12Part2 {
    private final static Map<Integer, List<Integer>> input = readInput();

    public static void main(String[] args) {
        System.out.println(input.keySet().stream()
                .map(num -> new Checker(num).check())
                .collect(toSet())
                .size());
    }

    private static class Checker {
        private int numToFind;
        private final Set<Integer> checked = new HashSet<>();
        private final Set<Integer> matched = new HashSet<>();

        Checker(int numToFind) {
            this.numToFind = numToFind;
            matched.add(numToFind);
        }

        Set<Integer> check() {
            checkKey(numToFind);

            return matched;
        }

        private void checkKey(int key) {
            if (!checked.add(key)) {
                return;
            }

            matched.addAll(input.get(key));

            input.get(key)
                    .forEach(this::checkKey);
        }
    }

    private static Map<Integer, List<Integer>> readInput() {
        return FileReader.readFile("input.txt", Exercise12Part2.class).stream()
                .map(l -> l.split("<->"))
                .collect(toMap(
                        arr -> parseInt(arr[0].trim()),
                        arr -> Arrays.stream(arr[1].split(",")).map(String::trim).map(Integer::parseInt).collect(Collectors.toList()))
                );

    }
}
