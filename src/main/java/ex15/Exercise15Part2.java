package ex15;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Exercise15Part2 {
    private static final BigInteger START_A = BigInteger.valueOf(116);
    private static final BigInteger START_B = BigInteger.valueOf(299);

    private static final BigInteger DIVIDER = BigInteger.valueOf(2147483647);

    private static final BigInteger FACTOR_A = BigInteger.valueOf(16807);
    private static final BigInteger CHECKER_A = BigInteger.valueOf(4);

    private static final BigInteger FACTOR_B = BigInteger.valueOf(48271);
    private static final BigInteger CHECKER_B = BigInteger.valueOf(8);

    private static final int AMOUNT_NEEDED = 5_000_000;


    public static void main(String[] args) {
        List<String> listA = calculateNumbers(START_A, FACTOR_A, CHECKER_A);
        List<String> listB = calculateNumbers(START_B, FACTOR_B, CHECKER_B);

        int matched = 0;
        for (int i = 0; i < listA.size(); i++) {
            if (listA.get(i).equals(listB.get(i))) {
                matched++;
            }
        }

        System.out.println(matched);
    }

    private static List<String> calculateNumbers(BigInteger start, BigInteger factor, BigInteger checker) {
        int counter = 0;
        List<String> results = new ArrayList<>();
        BigInteger next = start;
        while (counter < AMOUNT_NEEDED) {
            next = calculateNext(next, factor);
            if (next.mod(checker).equals(BigInteger.ZERO)) {
                counter++;
                results.add(getLast16Bits(next));
            }
        }


        return results;
    }

    private static String getLast16Bits(BigInteger bi) {
        String binary = bi.toString(2);
        if (binary.length() > 16) {
            return binary.substring(binary.length() - 16);
        }

        return binary;
    }

    private static BigInteger calculateNext(BigInteger num, BigInteger factor) {
        return num.multiply(factor).mod(DIVIDER);
    }
}
