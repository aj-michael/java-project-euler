package net.ajmichael.projecteuler;

import java.math.BigInteger;

public class Problem162 {
  public static String solve() {
    long total = 0;
    for (int i = 3; i <= 16; i++) {
      total += count(i);
      if (total < 0) {
        throw new RuntimeException();
      }
    }
    return Long.toHexString(total).toUpperCase();
  }

  private static long count(int size) {
    BigInteger two = BigInteger.valueOf(2);
    BigInteger thirteen = BigInteger.valueOf(13);
    BigInteger fourteen = BigInteger.valueOf(14);
    BigInteger fifteen = BigInteger.valueOf(15);
    BigInteger sixteen = BigInteger.valueOf(16);
    BigInteger result = fifteen.multiply(sixteen.pow(size - 1))
        .subtract(fifteen.pow(size))
        .subtract(two.multiply(fourteen.multiply(fifteen.pow(size - 1))))
        .add(thirteen.multiply(fourteen.pow(size - 1)))
        .add(two.multiply(fourteen.pow(size)))
        .subtract(thirteen.pow(size));
    return result.longValue();
  }
}
