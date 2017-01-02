package net.ajmichael.projecteuler;

import com.google.common.math.LongMath;

import java.math.BigInteger;

import static java.lang.Math.sqrt;

final class Problem401 {
  private static final long CUTOFF = LongMath.pow(10, 15);
  private static final long M = LongMath.pow(10, 9);
  private static final BigInteger BIM = BigInteger.valueOf(M);

  // this one was a lot of fun :)
  static long solve() {
    long lastCount = CUTOFF;
    long total = lastCount;
    for (long n = 2; n < sqrt(CUTOFF) + 1; n++) {
      long count = (CUTOFF / n); //- 1;
      if (n < count) {
        // n appears count times
        total += (((count * n) % M) * n) % M;   // the extra mod here was the final bug
        total %= M;
      }
      // all values from count + 1 to lastCount appear n - 1 times
      total += sumOfSquares(count, lastCount)
          .multiply(BigInteger.valueOf(n - 1))
          .mod(BIM)
          .longValue();
      total %= M;
      lastCount = count;
    }
    return Math.floorMod(total, M);
  }

  private static BigInteger sumOfSquares(long start, long stop) {
    return sumOfFirstNSquares(stop).subtract(sumOfFirstNSquares(start));
  }

  private static BigInteger sumOfFirstNSquares(long n) {
    return BigInteger.valueOf(n)
        .multiply(BigInteger.valueOf(n + 1))
        .multiply(BigInteger.valueOf(2 * n + 1))
        .divide(BigInteger.valueOf(6));
  }
}
