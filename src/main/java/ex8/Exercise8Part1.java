package ex8;

import static ex8.Exercise8Joined.REGISTERS;
import static ex8.Exercise8Joined.determineInstructions;

public class Exercise8Part1 {

    public static void main(String[] args) {
        determineInstructions()
                .forEach(Instruction::applyIfPossible);

        REGISTERS.values()
                .stream()
                .mapToInt(i -> i)
                .max()
                .ifPresent(System.out::println);
    }
}
