package util;

import com.google.common.io.Files;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import java.util.function.Function;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class FileReader {
    public static List<String> readLines(String filename, Class clazz) {
        return asList(readFile(filename, clazz).split("\n"));
    }

    public static <T> List<T> readLines(String filename, Class clazz, Function<String, T> mapper) {
        return stream(readFile(filename, clazz).split("\n")).map(mapper).collect(toList());
    }

    public static List<List<String>> readLines(String filename, Class clazz, String splitChar) {
        return stream(readFile(filename, clazz).split("\n"))
                .map(s -> stream(s.split(splitChar)).collect(toList()))
                .collect(toList());
    }

    public static <T> List<List<T>> readLines(String filename, Class clazz, String splitChar, Function<String, T> mapper) {
        return stream(readFile(filename, clazz).split("\n"))
                .map(s -> stream(s.split(splitChar)).map(mapper).collect(toList()))
                .collect(toList());
    }

    public static String readFile(String filename, Class clazz) {
        try {
            File file = new File(clazz.getResource(filename).toURI());

            return Files.asCharSource(file, Charset.defaultCharset()).read();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> readFile(String filename, Class clazz, String splitChar) {
        return asList(readFile(filename, clazz).split(splitChar));
    }

    public static <T> List<T> readFile(String filename, Class clazz, String splitChar, Function<String, T> mapper) {
        return stream(readFile(filename, clazz).split(splitChar))
                .map(mapper)
                .collect(toList());
    }
}
