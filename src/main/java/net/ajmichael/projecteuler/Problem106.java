package net.ajmichael.projecteuler;

import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

// See https://en.wikipedia.org/wiki/Dyck_language
public final class Problem106 {
  public static void main(String... args) {
    System.out.println(solve());
  }

  static int solve() {
    return numberOfPairsToCheck(12);
  }

  static int numberOfPairsToCheck(int n) {
    return IntStream.range(2, n / 2 + 1)
        .map(pairs ->
            IntMath.binomial(n, n - (2 * pairs)) * numberOfPairsToCheckIfAllAreUsed(2 * pairs))
        .sum();
  }

  static int numberOfPairsToCheckIfAllAreUsed(int n) {
    Preconditions.checkArgument(n % 2 == 0);
    return halvingPartitions(n) - catalan(n / 2);
  }

  // For a set of an even n items, this returns the number of disjoint subset pairs that union to
  // the entire set.
  private static int halvingPartitions(int n) {
    return IntMath.binomial(n, n / 2) / 2;
  }

  // https://en.wikipedia.org/wiki/Catalan_number
  private static Map<Integer, Integer> cachedCatalanNumbers = new HashMap<>();
  static {
    cachedCatalanNumbers.put(0, 1);
  }
  private static int catalan(int n) {
    if (!cachedCatalanNumbers.containsKey(n)) {
      cachedCatalanNumbers.put(
          n, IntStream.range(0, n)
              .map(i -> catalan(i) * catalan(n - 1 - i))
              .sum());
    }
    return cachedCatalanNumbers.get(n);
  }
}
