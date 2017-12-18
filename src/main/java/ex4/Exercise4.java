package ex4;

import util.FileReader;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exercise4 {
    public static void main(String[] args) {

        part2();

    }

    private static void part2() {
        System.out.println(readInput()
                .map(list -> list.stream()
                        .map(str -> Arrays.stream(str.split("")).sorted().collect(Collectors.joining()))
                        .collect(Collectors.toList()))
                .filter(hasNoDuplicates)
                .count());
    }

    private static void part1() {
        System.out.println(readInput()
                .filter(hasNoDuplicates)
                .count());
    }

    private static Predicate<List<String>> hasNoDuplicates = l -> l.size() == new HashSet<>(l).size();

    private static Stream<List<String>> readInput() {
        return FileReader.readLines("input.txt", Exercise4.class, "\\s+").stream();
    }
}
