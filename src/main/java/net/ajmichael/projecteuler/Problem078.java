package net.ajmichael.projecteuler;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Problem078 {
  public static long solve() {
    for (int i = 1; ; i++) {
      BigInteger oneMillion = BigInteger.valueOf(1000000);
      if (partitions(i).mod(oneMillion).equals(BigInteger.ZERO)) {
        return i;
      }
    }
  }

  private static Map<Integer, BigInteger> partitions = new HashMap<>();
  static {
    partitions.put(0, BigInteger.ONE);
  }

  private static BigInteger partitions(int n) {
    if (!partitions.containsKey(n)) {
      BigInteger total = BigInteger.ZERO;
      for (int k = 1; pentagonal(k) <= n; k = nextK(k)) {
        if ((k & 1) == 0) {
          total = total.subtract(partitions(n - pentagonal(k)));
        } else {
          total = total.add(partitions(n - pentagonal(k)));
        }
      }
      partitions.put(n, total);
    }
    return partitions.get(n);
  }

  private static int nextK(int k) {
    if (k > 0) {
      return -1 * k;
    } else {
      return 1 - k;
    }
  }

  private static int pentagonal(int k) {
    return k * (3 * k - 1) / 2;
  }
}
