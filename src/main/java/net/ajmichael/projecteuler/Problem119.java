package net.ajmichael.projecteuler;

import net.ajmichael.util.Pair;

import java.math.BigInteger;
import java.util.PriorityQueue;

public class Problem119 {
  public static long solve() {
    // Pair (base, value)
    PriorityQueue<Pair<BigInteger>> foo =
        new PriorityQueue<>((o1, o2) -> o1.x.multiply(o1.y).compareTo(o2.x.multiply(o2.y)));
    int numNeeded = 30;
    int soFar = 0;
    for (long i = 2; i < 100; i++) {
      foo.add(new Pair<>(BigInteger.valueOf(i), BigInteger.valueOf(i)));
    }
    while (true) {
      Pair<BigInteger> baseAndValue = foo.poll();
      BigInteger newValue = baseAndValue.x.multiply(baseAndValue.y);
      if (digitSum(newValue).equals(baseAndValue.x)) {
        soFar++;
        if (soFar == numNeeded) {
          return newValue.longValue();
        }
      }
      foo.offer(new Pair(baseAndValue.x, newValue));
    }
  }

  private static BigInteger digitSum(BigInteger n) {
    BigInteger total = BigInteger.ZERO;
    while (n.compareTo(BigInteger.ZERO) > 0) {
      total = total.add(n.mod(BigInteger.TEN));
      n = n.divide(BigInteger.TEN);
    }
    return total;
  }
}
