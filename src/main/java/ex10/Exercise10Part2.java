package ex10;

import util.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class Exercise10Part2 {

    private static final int INPUTS_LENGTH = 256;
    private static final List<Integer> LENGTHS_TO_APPEND = Arrays.asList(17, 31, 73, 47, 23);

    public static void main(String[] args) {

        System.out.println(calculateHash());
    }

    private static String calculateHash() {
        List<Integer> lengths = getLengths();
        lengths.addAll(LENGTHS_TO_APPEND);

        List<Integer> inputs = range(0, INPUTS_LENGTH).boxed().collect(toList());

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

    private static List<Integer> getLengths() {
        return FileReader.readFile("input.txt", Exercise10Part1.class, ",", Integer::parseInt);
    }
}