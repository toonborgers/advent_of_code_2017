package ex2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) throws Exception {
        System.out.println(readInput().stream()
                .map(i -> i.split("\t"))
                .map(arr -> Arrays.stream(arr).mapToInt(Integer::parseInt).summaryStatistics())
                .mapToInt(stat -> stat.getMax() - stat.getMin())
                .sum());
    }


    private static List<String> readInput() {
        List<String> result = new ArrayList<>();

        Scanner scanner = new Scanner(Exercise2.class.getResourceAsStream("input.txt"));

        while (scanner.hasNextLine()) {
            result.add(scanner.nextLine());
        }

        return result;
    }
}
