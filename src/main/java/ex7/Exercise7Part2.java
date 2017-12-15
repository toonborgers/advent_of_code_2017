package ex7;

import util.FileReader;
import util.Tuple;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class Exercise7Part2 {
    private static final Pattern PROGRAM_PATTERN = Pattern.compile("([a-zA-Z]+).*->(.*)");
    private static final Pattern PROGRAM_NAME_PATTERN = Pattern.compile("([a-zA-Z]+)\\s\\((\\d+)\\)");

    private static final Map<String, Integer> WEIGHTS = new HashMap<>();
    private static final Map<String, List<String>> INPUTS = new HashMap<>();

    static {
        FileReader.readFile("input.txt", Exercise7Part2.class).forEach(line -> {
            Matcher programNameMatcher = PROGRAM_NAME_PATTERN.matcher(line);
            programNameMatcher.find();
            WEIGHTS.put(programNameMatcher.group(1), parseInt(programNameMatcher.group(2)));

            if (line.contains("->")) {
                Matcher programMatcher = PROGRAM_PATTERN.matcher(line);
                programMatcher.find();
                INPUTS.put(
                        programMatcher.group(1),
                        Arrays.stream(programMatcher.group(2).split(",")).map(String::trim).collect(toList())
                );
            }
        });
    }

    public static void main(String[] args) {
        solve(findRootNode());
    }

    private static void solve(String node) {
        if (isBalanced(node)) {
            System.out.println(node);
            System.out.println(WEIGHTS.get(node) + getDifference());
            return;
        }

        solve(findCauseOfInbalance(node));
    }


    private static boolean isBalanced(String node) {
        return INPUTS.get(node).stream()
                .map(
                        leaf -> getAllLeafs(leaf).stream()
                                .map(WEIGHTS::get)
                                .mapToInt(i -> i)
                                .sum() + WEIGHTS.get(leaf)
                )
                .collect(toSet())
                .size() == 1;
    }

    private static int getDifference() {
        List<Integer> numbers = getWeightsGrouped(findRootNode())
                .map(Map.Entry::getKey)
                .collect(toList());

        return numbers.get(1) - numbers.get(0);
    }

    private static String findCauseOfInbalance(String node) {
        return getWeightsGrouped(node)
                .map(Map.Entry::getValue)
                .map(l -> l.get(0).getA())
                .findFirst()
                .get();
    }

    private static Stream<Map.Entry<Integer, List<Tuple<String, Integer>>>> getWeightsGrouped(String node) {
        return INPUTS.get(node).stream()
                .map(leaf -> {
                    int totalSum = getAllLeafs(leaf).stream()
                            .map(WEIGHTS::get)
                            .mapToInt(i -> i)
                            .sum() + WEIGHTS.get(leaf);

                    return new Tuple<>(leaf, totalSum);
                })
                .collect(groupingBy(Tuple::getB))
                .entrySet()
                .stream()
                .sorted(comparingInt(a -> a.getValue().size()));
    }

    private static List<String> getAllLeafs(String node) {
        if (!INPUTS.containsKey(node)) {
            return Collections.emptyList();
        }

        List<String> result = new ArrayList<>(INPUTS.get(node));

        for (String s : INPUTS.get(node)) {
            result.addAll(getAllLeafs(s));
        }

        return result;
    }

    private static String findRootNode() {
        for (String program : INPUTS.keySet()) {
            if (INPUTS.keySet().stream()
                    .flatMap(k -> INPUTS.get(k).stream())
                    .noneMatch(p -> p.equals(program))) {
                return program;
            }
        }

        throw new IllegalArgumentException();
    }
}
