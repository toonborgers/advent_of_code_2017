package ex3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exercise3 {
    public static void main(String[] args) {
//        part1(312051);
        part2(312051);
    }


    private static void part2(int input) {
        Map<Point, Integer> pointsToValues = new HashMap<>();

        int value = 1;
        DIRECTION direction = DIRECTION.RIGHT;
        int x = 0;
        int y = 0;
        int size = 1;

        pointsToValues.put(new Point(0, 0), 1);
        while (value <= input) {

            if (direction.isHorizontal() && Math.abs(x) == size) {
                direction = direction.next();
            } else if (direction.isVertical() && Math.abs(y) == size) {
                if (direction == DIRECTION.DOWN) {
                    size++;
                }
                direction = direction.next();
            }

            x += direction.addX;
            y += direction.addY;

            Point point = new Point(x, y);
            value = sumOfSurrounding(point, pointsToValues);
            pointsToValues.put(point, value);
            System.out.println(value);
        }


        System.out.println(value);
    }

    private static int sumOfSurrounding(Point point, Map<Point, Integer> pointsToValues) {
        return point.getSurrounding().stream()
                .mapToInt(p -> pointsToValues.getOrDefault(p, 0))
                .sum();
    }


    private static void part1(int valueToFind) {
        Map<Integer, Point> values = new HashMap<>();
        int value = 1;
        DIRECTION direction = DIRECTION.RIGHT;
        int x = 0;
        int y = 0;
        int size = 1;

        values.put(1, new Point(0, 0));
        while (value < valueToFind) {

            if (direction.isHorizontal() && Math.abs(x) == size) {
                direction = direction.next();
            } else if (direction.isVertical() && Math.abs(y) == size) {
                if (direction == DIRECTION.DOWN) {
                    size++;
                }
                direction = direction.next();
            }

            x += direction.addX;
            y += direction.addY;

            values.put(++value, new Point(x, y));
        }

        Point p = values.get(valueToFind);
        System.out.println(Math.abs(p.x) + Math.abs(p.y));
    }


    enum DIRECTION {
        RIGHT(1, 0), UP(0, -1), LEFT(-1, 0), DOWN(0, 1);
        int addX;
        int addY;

        DIRECTION(int addX, int addY) {
            this.addX = addX;
            this.addY = addY;
        }

        DIRECTION next() {
            return DIRECTION.values()[(this.ordinal() + 1) % 4];
        }

        boolean isHorizontal() {
            return this == LEFT || this == RIGHT;
        }

        boolean isVertical() {
            return !this.isHorizontal();
        }
    }


    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        List<Point> getSurrounding() {
            return Arrays.asList(
                    new Point(x + 1, y),
                    new Point(x + 1, y + 1),
                    new Point(x, y + 1),
                    new Point(x - 1, y + 1),
                    new Point(x - 1, y),
                    new Point(x - 1, y - 1),
                    new Point(x, y - 1),
                    new Point(x + 1, y - 1)
            );
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

        @Override
        public String toString() {
            return "{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
