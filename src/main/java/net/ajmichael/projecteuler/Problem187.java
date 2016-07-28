package net.ajmichael.projecteuler;

import net.ajmichael.util.Primes;

import java.util.List;

public class Problem187 {
  private static List<Integer> primes = Primes.sieve(100000000);

  public static long solve() {
    long total = 0;
    for (int i = 0; i < primes.size(); i++) {
      if (primes.get(i) * primes.get(i) > 100000000) {
        break;
      }
      for (int j = i; j < primes.size(); j++) {
        if (primes.get(i) * primes.get(j) > 100000000) {
          break;
        }
        total++;
      }
    }
    return total;
  }
}
