package ex2;

import util.FileReader;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Exercise2 {
    public static void main(String[] args) throws Exception {
        part2();
    }

    private static void part2() {
        System.out.println(readInput("input.txt").stream()
                .mapToInt(row -> {
                    for (int i = 0; i < row.size() - 1; i++) {
                        for (int j = i + 1; j < row.size(); j++) {
                            if (row.get(i) % row.get(j) == 0) {
                                return row.get(i) / row.get(j);
                            }
                            if (row.get(j) % row.get(i) == 0) {
                                return row.get(j) / row.get(i);
                            }
                        }
                    }
                    throw new RuntimeException("kapot");
                })
                .sum());


    }

    private static void part1() {
        System.out.println(readInput("input.txt").stream()
                .map(i -> i.stream().mapToInt(item -> item))
                .map(IntStream::summaryStatistics)
                .mapToInt(stat -> stat.getMax() - stat.getMin())
                .sum());
    }

    private static List<List<Integer>> readInput(String file) {
        return FileReader.readFile(file, Exercise2.class).stream()
                .map(line -> Arrays.stream(line.split("\\s+")).map(Integer::parseInt).collect(toList()))
                .collect(toList());
    }
}
