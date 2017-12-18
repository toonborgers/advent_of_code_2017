package ex6;

import util.FileReader;

import java.util.*;
import java.util.stream.Collectors;

public class Exercise6 {
    public static void main(String[] args) {
        part1();
//        part2();
    }

    private static void part1() {
        Set<List<Integer>> states = new HashSet<>();
        List<Integer> state = getInput("input.txt");
        int steps = 0;

        while (states.add(new ArrayList<>(state))) {
            Integer highestValue = state.stream().sorted(Comparator.<Integer>naturalOrder().reversed()).findFirst().get();
            int index = state.indexOf(highestValue);

            state.set(index, 0);
            for (int i = highestValue; i > 0; i--) {

                index = ((index + 1) % state.size());
                state.set(index, state.get(index) + 1);
            }

            steps++;
        }

        System.out.println(steps);
    }

    private static void part2() {
        List<List<Integer>> states = new ArrayList<>();
        List<Integer> state = getInput("input.txt");
        int steps = 0;


        boolean shouldContinue = true;

        states.add(new ArrayList<>(state));
        while (shouldContinue) {
            Integer highestValue = state.stream().sorted(Comparator.<Integer>naturalOrder().reversed()).findFirst().get();
            int index = state.indexOf(highestValue);

            state.set(index, 0);
            for (int i = highestValue; i > 0; i--) {

                index = ((index + 1) % state.size());
                state.set(index, state.get(index) + 1);
            }

            steps++;

            if (states.contains(state)) {
                shouldContinue = false;

                System.out.println(steps - states.indexOf(state));
            } else {
                states.add(new ArrayList<>(state));
            }
        }

    }

    private static List<Integer> getInput(String filename) {
        return Arrays.stream(FileReader.readFile(filename, Exercise6.class)
                .get(0).split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
