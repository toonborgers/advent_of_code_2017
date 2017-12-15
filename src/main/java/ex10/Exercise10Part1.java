package ex10;

import util.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Exercise10Part1 {

    private static final int INPUTS_LENGTH = 256;

    public static void main(String[] args) {
        List<Integer> lengths = getLengths();
        List<Integer> inputs = IntStream.range(0, INPUTS_LENGTH).boxed().collect(Collectors.toList());

        int position = 0;
        for (int i = 0; i < lengths.size(); i++) {
            int length = lengths.get(i);
            int start = position;
            inputs = replace(inputs, start, length);

            position += (length + i);

        }

        System.out.println(inputs.get(0) * inputs.get(1));
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
        return Arrays.stream(FileReader.readFile("input.txt", Exercise10Part1.class)
                .get(0).split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
