package ex11;

import util.FileReader;

import java.util.List;

public class Exercise11Part1 {

    public static void main(String[] args) {
        Point start = new Point(0, 0, 0);

        System.out.println(start.distanceTo(finish()));
    }

    private static Point finish() {
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
        }

        return new Point(x, y, z);
    }

    private static List<String> readInput() {
       return  FileReader.readFile("input.txt", Exercise11Part1.class,",");
    }

    private static class Point {
        int x, y, z;

        Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        int distanceTo(Point other) {
            return (Math.abs(x - other.x) + Math.abs(y - other.y) + Math.abs(z - other.z)) / 2;
        }
    }
}
