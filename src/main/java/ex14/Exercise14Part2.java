package ex14;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class Exercise14Part2 {
    private static final String FORMAT = "%s-%s";
    private static final String KEY = "wenycdww";
    private static final int SIZE = 128;

    private static final Map<Point, State> pointStateMap = calculateInitial();

    public static void main(String[] args) {
        int region = 0;

        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                Point point = new Point(x, y);
                State state = pointStateMap.get(point);

                if (state.canBeFlooded()) {
                    flood(point, ++region);
                }
            }
        }

        System.out.println(region);
    }


    private static void flood(Point point, int region) {
        pointStateMap.get(point).setRegion(region);

        point.surroundingPoints().stream()
                .filter(p -> pointStateMap.get(p).canBeFlooded())
                .forEach(p -> flood(p, region));
    }

    private static Map<Point, State> calculateInitial() {
        List<List<String>> hashList = hashes().stream().map(s -> Arrays.asList(s.split(""))).collect(toList());

        Map<Point, State> result = new HashMap<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                boolean isOccupied = hashList.get(i).get(j).equals("1");

                result.put(new Point(j, i), new State(isOccupied));
            }
        }

        return result;
    }

    private static List<String> hashes() {
        return IntStream.range(0, SIZE).boxed()
                .map(i -> String.format(FORMAT, KEY, i))
                .map(Exercise14Part2::knotHash)
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

    private static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        List<Point> surroundingPoints() {
            return Stream.of(getLeft(), getRight(), getBottom(), getTop()).filter(Objects::nonNull).collect(toList());
        }

        Point getLeft() {
            if (this.x == 0) {
                return null;
            }

            return new Point(x - 1, y);
        }

        Point getRight() {
            if (this.x + 1 == SIZE) {
                return null;
            }

            return new Point(x + 1, y);
        }

        Point getTop() {
            if (this.y == 0) {
                return null;
            }

            return new Point(x, y - 1);
        }

        Point getBottom() {
            if (this.y + 1 == SIZE) {
                return null;
            }

            return new Point(x, y + 1);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (x != point.x) return false;
            return y == point.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

    private static class State {
        final boolean occupied;
        int region = 0;

        State(boolean occupied) {
            this.occupied = occupied;
        }

        void setRegion(int region) {
            this.region = region;
        }

        boolean canBeFlooded() {
            return occupied && !(region > 0);
        }
    }
}
