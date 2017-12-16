package ex8;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ex8.Exercise8Joined.REGISTERS;
import static ex8.Exercise8Joined.valueOf;
import static java.lang.Integer.parseInt;

class Instruction {
    private static final Pattern LINE_PATTERN = Pattern.compile("([a-z]+)\\s([a-z]{3})\\s(\\S+)\\sif\\s([a-z]+)\\s(\\S+)\\s(\\S+)");

    private String register;
    private Function<Integer, Integer> operation;
    private String conditionRegister;
    private Predicate<Integer> condition;

    private Instruction(String register, Function<Integer, Integer> operation, String conditionRegister, Predicate<Integer> condition) {
        this.register = register;
        this.operation = operation;
        this.conditionRegister = conditionRegister;
        this.condition = condition;
    }

    static Instruction fromLine(String line) {
        Matcher matcher = LINE_PATTERN.matcher(line);
        matcher.find();

        return new Instruction(
                matcher.group(1),
                operation(matcher.group(2), parseInt(matcher.group(3))),
                matcher.group(4),
                condition(matcher.group(5), parseInt(matcher.group(6)))
        );
    }

    private static Predicate<Integer> condition(String asString, int conditionValue) {
        switch (asString) {
            case "<":
                return (a) -> a < conditionValue;
            case "!=":
                return (a) -> a != conditionValue;
            case ">=":
                return (a) -> a >= conditionValue;
            case ">":
                return (a) -> a > conditionValue;
            case "==":
                return (a) -> a == conditionValue;
            case "<=":
                return (a) -> a <= conditionValue;
        }

        throw new IllegalArgumentException();
    }

    private static Function<Integer, Integer> operation(String asString, int operationValue) {
        if ("inc".equals(asString)) {
            return val -> val + operationValue;
        } else {
            return val -> val - operationValue;
        }
    }

    void applyIfPossible() {
        if (condition.test(valueOf(conditionRegister))) {
            REGISTERS.put(register, operation.apply(valueOf(register)));
        }
    }
}
