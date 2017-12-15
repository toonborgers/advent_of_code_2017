package ex14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class Exercise14Part1 {
    private static final String FORMAT = "%s-%s";
    private static final String KEY = "wenycdww";

    public static void main(String[] args) {
        System.out.println(Arrays.stream(hashes().stream().collect(joining()).split("")).filter(s -> s.equals("1")).count());
    }

    private static List<String> hashes() {
        return IntStream.range(0, 128).boxed()
                .map(i -> String.format(FORMAT, KEY, i))
                .map(Exercise14Part1::knotHash)
                .collect(toList());
    }

    private static String knotHash(String input) {
        List<Integer> lengths = Arrays.stream(input.split(""))
                .map(s -> ((int) s.charAt(0)))
                .collect(toList());
        lengths.addAll(Arrays.asList(17, 31, 73, 47, 23));

        List<Integer> inputs = range(0, 256).boxed().collect(toList());

        int position = 0;
        int skipSize = 0;

        for (int i = 0; i < 64; i++) {
            for (Integer length : lengths) {
                int start = position;
                inputs = replace(inputs, start, length);
                position += (length + skipSize++);
            }
        }

        final List<Integer> sparse = new ArrayList<>(inputs);
        return range(0, 16).boxed()
                .map(i -> sparse.subList(i * 16, (i * 16) + 16).stream().reduce(0, (a, b) -> a ^ b))
                .map(Integer::toHexString)
                .map(s1 -> {
                    if (s1.length() == 1) {
                        return "0" + s1;
                    }

                    return s1;
                })
                .flatMap(s -> Arrays.stream(s.split("")))
                .map(s -> Integer.toBinaryString(Integer.parseInt(s, 16)))
                .map(Integer::parseInt)
                .map(i -> String.format("%04d", i))
                .collect(joining());
    }

    private static List<Integer> replace(List<Integer> input, int start, int length) {
        List<Integer> subList = reversedWrapAroundSubList(input, start, length);

        int index = 0;
        for (int i = start; i < start + length; i++) {
            input.set(i % input.size(), subList.get(index++));
        }

        return input;
    }

    private static List<Integer> reversedWrapAroundSubList(List<Integer> input, int start, int length) {
        List<Integer> result = new ArrayList<>();
        for (int i = start; i < start + length; i++) {
            result.add(input.get(i % input.size()));
        }

        Collections.reverse(result);
        return result;
    }
}
