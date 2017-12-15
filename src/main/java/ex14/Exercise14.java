package ex14;

public class Exercise14 {
    private static final String FORMAT = "%s-%s";
    private static final String KEY = "flqrgnkx";

    public static void main(String[] args) {
        for (int i = 0; i < 128; i++) {
            String s = String.format(FORMAT, KEY, i);
            System.out.println(s);
        }

    }
}
