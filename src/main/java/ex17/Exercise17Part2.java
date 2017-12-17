package ex17;

public class Exercise17Part2 {
    private static final int STEP_SIZE = 386;
    private static final int STEPS = 50_000_001;

    public static void main(String[] args) {
        int position = 0;
        int finalPos = 0;

        for (int i = 1; i < STEPS; i++) {
            position = (position + STEP_SIZE) % i + 1;
            if (position == 1) {
                finalPos = i;
            }
        }

        System.out.println(finalPos);

    }

}
