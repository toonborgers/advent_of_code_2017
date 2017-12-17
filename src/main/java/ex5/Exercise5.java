package ex5;

import util.FileReader;

import java.util.List;
import java.util.stream.Collectors;

public class Exercise5 {
    public static void main(String[] args) {
//        part1();
        part2();
    }

    private static void part2() {
        List<Integer> maze = getInput("input.txt");
        int index = 0;
        int steps = 0;

        while (index >= 0 && index < maze.size()) {
            int value = maze.get(index);
            int newValue;
            if (value >= 3) {
                newValue = value - 1;
            } else {
                newValue = value + 1;

            }
            maze.set(index, newValue);
            index += value;
            steps++;
        }

        System.out.println(steps);
    }

    private static void part1() {
        List<Integer> maze = getInput("input.txt");
        int index = 0;
        int steps = 0;

        while (index >= 0 && index < maze.size()) {
            int value = maze.get(index);
            maze.set(index, value + 1);
            index += value;
            steps++;
        }

        System.out.println(steps);
    }

    private static List<Integer> getInput(String filename) {
        return FileReader.readFile(filename, Exercise5.class)
                .stream().map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
