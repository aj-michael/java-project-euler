package net.ajmichael.projecteuler;

import java.util.stream.LongStream;

// If n is even, then r = 2.
// If n is odd, then r = 2*n*a mod
public final class Problem120 {
  public static long solve() {
    return LongStream.range(3, 1001)
        .map(Problem120::rmax)
        .sum();
  }

  private static final long rmax(long a) {
    long rmax = 0;
    long m = a * a;
    for (long n = 1; n < 2 * a; n += 2) {
      long r = 2 * n * a % m;
      rmax = Math.max(r, rmax);
    }
    return rmax;
  }
}
