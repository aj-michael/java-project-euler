package net.ajmichael.projecteuler;

import net.ajmichael.util.Primes;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Problem108 {
  public static void main(String args[]) {
    System.out.println(solve());
  }

  public static long solve() {
    for (long n = 4; ; n++) {
      int distinctSolutions = 0;
      distinctSolutions += (1 + getDivisors(n*n).size()) / 2;
      if (distinctSolutions > 1000) {
        return n;
      }
    }
  }

  private static List<Integer> primes = Primes.sieve(1000000);
  private static Map<Long, Set<Long>> divisors = new HashMap<>();
  static {
    divisors.put(1L, Collections.singleton(1L));
  }

  private static Set<Long> getDivisors(long n) {
    if (!divisors.containsKey(n)) {
      for (long p : primes) {
        if (n % p == 0) {
          Set<Long> allDivisors = new HashSet<>();
          getDivisors(n / p).forEach(d -> {
            allDivisors.add(d);
            allDivisors.add(p * d);
          });
          divisors.put(n, allDivisors);
          break;
        }
      }
    }
    if (!divisors.containsKey(n)) {
      System.err.println(n);
    }
    return divisors.get(n);
  }
}
