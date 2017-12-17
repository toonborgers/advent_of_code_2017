package ex17;

import java.util.ArrayList;
import java.util.List;

public class Exercise17Part1 {
    private static final int STEP_SIZE = 386;
    private static final int STEPS = 2017;

    public static void main(String[] args) {
        List<Integer> result = new ArrayList<>();
        result.add(0);

        int position = 0;
        for (int i = 1; i <= STEPS; i++) {
            position = nextPosition(position, result.size());

            if (position == result.size()) {
                result.add(i);
            } else {
                List<Integer> suffix = result.subList(position, result.size());
                result = new ArrayList<>(result.subList(0, position));
                result.add(i);
                result.addAll(suffix);
            }
        }

        System.out.println(result.get(result.indexOf(2017) + 1));
    }


    private static int nextPosition(int currentPosition, int size) {
        return 1 + (currentPosition + STEP_SIZE) % size;
    }
}
