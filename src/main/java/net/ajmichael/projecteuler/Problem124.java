package net.ajmichael.projecteuler;

import net.ajmichael.util.Pair;
import net.ajmichael.util.Primes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem124 {
  private static final List<Integer> primes = Primes.sieve(100000);

  public static long solve() {
    return e(10000);
  }

  private static int N = 100000;

  private static int e(int k) {
    return IntStream.range(1, N + 1)
        .boxed()
        .map(n -> new Pair<>(n, rad(n)))
        .sorted((o1, o2) -> o1.y - o2.y)
        .collect(Collectors.toList())
        .get(k - 1).x;
  }

  private static int rad(int n) {
    int rad = 1;
    for (int i = 0; i < primes.size(); i++) {
      if (n == 1) {
        break;
      }
      int p = primes.get(i);
      if (n % p == 0) {
        rad *= p;
        while (n % p == 0) {
          n = n / p;
        }
      }
    }
    return rad;
  }
}
