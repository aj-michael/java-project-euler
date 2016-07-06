package net.ajmichael.projecteuler;

import net.ajmichael.util.Primes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem243 {
  private static final List<Integer> primes = Primes.sieve(1000000);
  private static Map<Long, Long> totient = new HashMap<>();

  static {
    totient.put(1L, 1L);
  }

  private static long totient(long n) {
    if (!totient.containsKey(n)) {
      for (int p : primes) {
        long multiplicity = multiplicity(n, p);
        if (multiplicity > 0) {
          long pk = (long) Math.pow(p, multiplicity);
          if (pk == n) {
            totient.put(n, pk - pk / p);
          } else {
            totient.put(n, totient(pk) * totient(n / pk));
          }
          break;
        }
      }
    }
    if (!totient.containsKey(n)) {
      // overflowed prime sieve, n is likely prime
      totient.put(n, n - 1);
    }
    return totient.get(n);
  }

  private static long multiplicity(long n, long p) {
    long multiplicity = 0;
    while (n > 1) {
      if (n % p == 0) {
        multiplicity++;
        n = n / p;
      } else {
        break;
      }
    }
    return multiplicity;
  }

  public static long solve() {
    long step = 2 * 3 * 5 * 7 * 11 * 13 * 17;
    for (long d = step; ; d += step) {
      long num = totient(d);
      long denom = d - 1;
      if (num < 0) {
        throw new RuntimeException();
      }
      if (1. * num / denom < 15499. / 94744) {
        return d;
      }
    }
  }
}
