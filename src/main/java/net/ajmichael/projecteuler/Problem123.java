package net.ajmichael.projecteuler;

import net.ajmichael.util.Primes;

import java.util.List;

public class Problem123 {
  private static final List<Integer> primes = Primes.sieve(1000000);

  public static long solve() {
    for (int n = 3; ; n++) {
      long pn = primes.get(n - 1);
      long pn2 = pn * pn;
      long value = (modpow(pn - 1, n, pn2) + modpow(pn + 1, n, pn2)) % pn2;
      if (value > 10000000000L) {
        return n;
      }
    }
  }

  private static final long modpow(long b, int e, long m) {
    long result = 1;
    for (int power = 0; power < e; power++) {
      result = ((result * b) % m);
    }
    return result;
  }
}
