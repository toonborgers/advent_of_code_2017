package ex2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Exercise2 {
    public static void main(String[] args) throws Exception {
        part2();
    }

    private static void part2() {
        System.out.println(readInput("input2.txt").stream()
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
        System.out.println(readInput("input1.txt").stream()
                .map(i -> i.stream().mapToInt(item -> item))
                .map(IntStream::summaryStatistics)
                .mapToInt(stat -> stat.getMax() - stat.getMin())
                .sum());
    }

    private static List<List<Integer>> readInput(String file) {
        List<List<Integer>> result = new ArrayList<>();

        Scanner scanner = new Scanner(Exercise2.class.getResourceAsStream(file));

        while (scanner.hasNextLine()) {
            result.add(Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(toList()));
        }

        return result;
    }
}
