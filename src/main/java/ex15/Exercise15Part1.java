package ex15;

import java.math.BigInteger;

public class Exercise15Part1 {
    private static final BigInteger START_A = BigInteger.valueOf(116);
    private static final BigInteger START_B = BigInteger.valueOf(299);

    private static final BigInteger DIVIDER = BigInteger.valueOf(2147483647);
    private static final BigInteger FACTOR_A = BigInteger.valueOf(16807);
    private static final BigInteger FACTOR_B = BigInteger.valueOf(48271);
    private static final int LOOPS = 40000000;


    public static void main(String[] args) {
        BigInteger a = START_A;
        BigInteger b = START_B;

        int matches = 0;
        for (int i = 0; i < LOOPS; i++) {
            BigInteger nextA = calculateNext(a, FACTOR_A);
            BigInteger nextB = calculateNext(b, FACTOR_B);

            if (match(nextA, nextB)) {
                matches++;
            }

            a = nextA;
            b = nextB;
        }

        System.out.println(matches);
    }

    private static boolean match(BigInteger a, BigInteger b) {
        String aBinary = a.toString(2);
        String bBinary = b.toString(2);

        if (aBinary.length() > 16) {
            aBinary = aBinary.substring(aBinary.length() - 16);
        }
        if (bBinary.length() > 16) {
            bBinary = bBinary.substring(bBinary.length() - 16);
        }

        return aBinary.equals(bBinary);
    }

    private static BigInteger calculateNext(BigInteger num, BigInteger factor) {
        return num.multiply(factor).mod(DIVIDER);
    }
}
