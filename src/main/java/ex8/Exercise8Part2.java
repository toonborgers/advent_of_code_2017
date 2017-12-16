package ex8;

import java.util.function.Consumer;

import static ex8.Exercise8Joined.REGISTERS;
import static ex8.Exercise8Joined.determineInstructions;

public class Exercise8Part2 {
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) {
        determineInstructions()
                .forEach(joinedConsumer(
                        Instruction::applyIfPossible,
                        Exercise8Part2::checkMax
                ));


        System.out.println(max);
    }

    private static void checkMax(Instruction ignore) {
        max = Integer.max(max, REGISTERS.values()
                .stream()
                .mapToInt(i -> i)
                .max()
                .getAsInt());
    }


    private static <T> Consumer<T> joinedConsumer(Consumer<T> first, Consumer<T> second) {
        return first.andThen(second);
    }
}
