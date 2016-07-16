package net.ajmichael.projecteuler;

import net.ajmichael.util.Primes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem134 {
  private static final List<Integer> primes = Primes.sieve(1000010);
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
    long total = 0;
    for (int i = 2; i < primes.size() - 1; i++) {
      if (i % 100 == 0) {
        System.out.println("i = " + i + " / " + primes.size());
      }
      long p1 = primes.get(i);
      long p2 = primes.get(i + 1);
      total += minVal(p1, p2);
    }
    return total;
  }

  private static long minVal(long p1, long p2) {
    long m = (long) Math.pow(10, (int) Math.log10(p1) + 1);
    long phim = totient(m);
    long p2inverse = 1;
    for (int i = 0; i < phim - 1; i++) {
      p2inverse = (p2inverse * p2) % m;
    }
    return p2 * ((p2inverse * p1) % m);
/*
    for (long x = p2; ; x += 2 * p2) {
      if (x % m == p1) {
        return x;
      }
    }
    */
  }

  private static long gcd(long a, long b) {
    if (b == 0) {
      return a;
    } else {
      return gcd(b, a % b);
    }
  }
}
