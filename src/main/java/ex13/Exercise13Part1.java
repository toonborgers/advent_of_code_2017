package ex13;

import util.FileReader;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Exercise13Part1 {
    public static void main(String[] args) {
        Map<Integer, Layer> layers = readInput();
        int maxDepth = layers.keySet().stream().mapToInt(i -> i).max().getAsInt();

        int damage = 0;
        for (int i = 0; i <= maxDepth; i++) {

            if (layers.containsKey(i)) {
                if (layers.get(i).canCatch()) {
                    damage += (i * layers.get(i).size);
                }
            }

            layers.keySet().forEach(k -> layers.get(k).advance());
        }

        System.out.println(damage);

    }


    private static Map<Integer, Layer> readInput() {
        return FileReader.readLines("input.txt", Exercise13Part1.class).stream()
                .map(s -> stream(s.split(":")).map(String::trim).map(Integer::parseInt).collect(toList()))
                .collect(toMap(l -> l.get(0), l -> new Layer(l.get(1))));
    }

    private static class Layer {
        int size;
        int position = 0;
        boolean isMovingDown = true;

        Layer(int size) {
            this.size = size;
        }

        void advance() {
            if (size == 1) {
                return;
            }

            position += (isMovingDown ? 1 : -1);

            if (position == 0 || position == size - 1) {
                isMovingDown = !isMovingDown;
            }
        }

        boolean canCatch() {
            return this.position == 0;
        }
    }
}
