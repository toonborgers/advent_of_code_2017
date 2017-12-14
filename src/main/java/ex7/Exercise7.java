package ex7;

import util.FileReader;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Exercise7 {
    private static final Pattern PROGRAM_PATTERN = Pattern.compile("([a-zA-Z]+).*->(.*)");

    public static void main(String[] args) {
        Map<String, List<String>> input = getInput("input.txt");

        for (String program : input.keySet()) {
            if (input.keySet().stream()
                    .flatMap(k -> input.get(k).stream())
                    .noneMatch(p -> p.equals(program))) {
                System.out.println(program);
            }
        }

    }

    private static Map<String, List<String>> getInput(String filename) {
        return FileReader.readFile(filename, Exercise7.class)
                .stream()
                .filter(s -> s.contains("->"))
                .map(s -> {
                    Matcher matcher = PROGRAM_PATTERN.matcher(s);
                    matcher.find();
                    return matcher;
                })
                .collect(Collectors.toMap(m -> m.group(1), m -> Arrays.stream(m.group(2).split(",")).map(String::trim).collect(Collectors.toList())));
    }
}
