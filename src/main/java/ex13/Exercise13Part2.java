package ex13;

import com.google.common.base.Stopwatch;
import util.FileReader;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Stream.iterate;

public class Exercise13Part2 {
    private static final Map<Integer, Integer> initial = readInput();
    private static final int maxDepth = initial.keySet().stream().mapToInt(i -> i).max().getAsInt();
    private static final Stopwatch STOPWATCH = Stopwatch.createUnstarted();
    private static final Layer NO_HIT = new NoHitLayer();

    public static void main(String[] args) {
        STOPWATCH.start();
        System.out.println(
                iterate(0, i -> i + 1)
                        .parallel()
                        .map(Exercise13Part2::checkDamage)
                        .filter(Result::isSuccess)
                        .map(Result::getWait)
                        .findFirst()
                        .get()
        );
        STOPWATCH.stop();
        System.out.println("Done in " + STOPWATCH.toString());
    }

    private static Result checkDamage(int initialWait) {
        Map<Integer, Layer> layers = getInitialLayers(initialWait);

        for (int i = 0; i <= maxDepth; i++) {
            if (layers.getOrDefault(i, NO_HIT).isHit()) {
                return Result.fail(initialWait);
            }
            layers.values().forEach(Layer::advance);
        }

        return Result.success(initialWait);
    }

    private static Map<Integer, Layer> getInitialLayers(int position) {
        return initial.entrySet().stream().collect(toMap(Map.Entry::getKey, e -> new Layer(e.getValue(), position)));
    }

    private static Map<Integer, Integer> readInput() {
        return FileReader.readFile("input.txt", Exercise13Part2.class).stream()
                .map(s -> stream(s.split(":")).map(String::trim).map(Integer::parseInt).collect(toList()))
                .collect(toMap(l -> l.get(0), l -> l.get(1)));
    }

    private static class Layer {
        int checkNumber;
        int position;

        Layer(int size, int position) {
            this.checkNumber = (size - 1) * 2;
            this.position = position;
        }

        void advance() {
            position++;
        }

        boolean isHit() {
            return position % checkNumber == 0;
        }
    }

    private static class NoHitLayer extends Layer {
        NoHitLayer() {
            super(-1, -1);
        }

        @Override
        boolean isHit() {
            return false;
        }
    }

    private static class Result {
        int wait;
        boolean success;

        private Result(int wait, boolean success) {
            this.wait = wait;
            this.success = success;
        }

        static Result success(int wait) {
            return new Result(wait, true);
        }

        static Result fail(int wait) {
            return new Result(wait, false);
        }


        public int getWait() {
            return wait;
        }

        public boolean isSuccess() {
            return success;
        }
    }
}
