package ex3;

import java.util.HashMap;
import java.util.Map;

public class Exercise3 {
    public static void main(String[] args) {
        for (DIRECTION direction : DIRECTION.values()) {
            System.out.println(direction + ", " + direction.next());
        }

        Map<Point, Integer> values = new HashMap<>();
        int value = 1;
        DIRECTION direction = DIRECTION.RIGHT;
        int x = 0;
        int y = 0;
        int size = 1;

        values.put(new Point(0, 0), 1);
        while (value < 9) {
            if (direction.isHorizontal() && Math.abs(x) == size) {
                direction = direction.next();
            }

            if (direction.isVertical() && Math.abs(y) == size) {
                direction = direction.next();
                if (direction == DIRECTION.DOWN) {
                    size++;
                }
            }

            value++;
            x += direction.addX;
            y += direction.addY;

            System.out.println("(" + x + ", " + y + "): " + value);
            values.put(new Point(x, y), value);
        }
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

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
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
}
