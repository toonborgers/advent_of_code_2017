package ex11;

import util.FileReader;

import java.util.Arrays;
import java.util.List;

public class Exercise11Part2 {

    private static final Point START = new Point(0, 0, 0);
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) {
        calculate();

        System.out.println(max);
    }

    private static void calculate() {
        int x = 0, y = 0, z = 0;
        for (String in : readInput()) {
            switch (in) {
                case "n":
                    ++y;
                    --z;
                    break;
                case "ne":
                    ++x;
                    --z;
                    break;
                case "se":
                    ++x;
                    --y;
                    break;
                case "s":
                    --y;
                    ++z;
                    break;
                case "sw":
                    --x;
                    ++z;
                    break;
                case "nw":
                    ++y;
                    --x;
                    break;
            }


            int distance = START.distanceTo(new Point(x, y, z));
            if (distance > max) {
                max = distance;
            }
        }

    }

    private static List<String> readInput() {
        return Arrays.asList(FileReader.readFile("input.txt", Exercise11Part2.class)
                .get(0).split(","));

    }

    private static class Point {
        int x, y, z;

        public Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        int distanceTo(Point other) {
            return (Math.abs(x - other.x) + Math.abs(y - other.y) + Math.abs(z - other.z)) / 2;
        }
    }
}
