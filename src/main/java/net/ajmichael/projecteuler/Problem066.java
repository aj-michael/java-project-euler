package net.ajmichael.projecteuler;

import net.ajmichael.util.Pair;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class Problem066 {

  public static long solve() {
    Set<Integer> squares = new HashSet<>();
    for (int i = 1; i <= 1000; i++) {
      squares.add(i*i);
    }
    int bestD = 0;
    BigInteger largestX = BigInteger.ZERO;
    for (int D = 2; D <= 1000; D++) {
      if (!squares.contains(D)) {
        BigInteger minX = getMinX(D);
        if (minX.compareTo(largestX) > 0) {
          bestD = D;
          largestX = minX;
        }
      }
    }
    return bestD;
  }

  private static BigInteger getMinX(int n) {
    BigInteger N = BigInteger.valueOf(n);
    BigInteger a0 = BigInteger.valueOf((long) Math.sqrt(n));
    Pair<BigInteger> continuants = new Pair<>(a0, BigInteger.ONE);
    Pair<BigInteger> oldContinuants = new Pair<>(BigInteger.ONE, BigInteger.ZERO);
    Pair<BigInteger> current = new Pair<>(BigInteger.ONE, a0);
    while (true) {
      if (continuants.x.multiply(continuants.x).subtract(continuants.y.multiply(continuants.y).multiply(N)).intValue() == 1) {
        return continuants.x;
      }
      BigInteger denom = N.subtract(current.y.multiply(current.y)).divide(current.x);
      BigInteger temp = current.y;
      long bn = -1;
      while (temp.add(a0).compareTo(BigInteger.ZERO) >= 0) {
        temp = temp.subtract(denom);
        bn++;
      }
      temp = temp.add(denom);
      current = new Pair<>(denom, temp.negate());

      Pair<BigInteger> tempContinuant = continuants;
      continuants = new Pair<>(
          BigInteger.valueOf(bn).multiply(continuants.x).add(oldContinuants.x),
          BigInteger.valueOf(bn).multiply(continuants.y).add(oldContinuants.y)
      );
      oldContinuants = tempContinuant;
    }
  }
}
