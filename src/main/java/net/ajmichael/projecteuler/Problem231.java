package net.ajmichael.projecteuler;

import com.google.common.math.LongMath;
import net.ajmichael.util.Primes;

import java.util.List;

final class Problem231 {
  private static final List<Integer> primes = Primes.sieve(20000000);

  static long solve() {
    int n = 20000000;
    int r = 15000000;
    return countPrimeFactorsFrom(r + 1, n) - countPrimeFactorsFrom(1, n - r);
  }

  private static long countPrimeFactorsFrom(long bottom, long top) {
    long total = 0;
    for (int p : primes) {
      if (p > top) {
        break;
      }
      for (int m = 1; ; m++) {
        long pm = LongMath.pow(p, m);
        if (pm > top) {
          break;
        }
        // bottom <= a*p^m <= b*p^m <= top
        long a = bottom / pm;
        if (bottom % pm != 0) {
          a++;
        }
        long b = top / pm;
        long factors = b - a + 1;
        total += factors * p;
      }
    }
    return total;
  }
}
