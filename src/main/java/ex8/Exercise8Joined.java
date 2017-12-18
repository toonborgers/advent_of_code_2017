package ex8;

import util.FileReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

final class Exercise8Joined {
    static final Map<String, Integer> REGISTERS = new HashMap<>();

    static int valueOf(String register) {
        return REGISTERS.getOrDefault(register, 0);
    }

    static List<Instruction> determineInstructions() {
        return FileReader.readLines("input.txt", Exercise8Joined.class).stream()
                .map(Instruction::fromLine)
                .collect(Collectors.toList());
    }
}