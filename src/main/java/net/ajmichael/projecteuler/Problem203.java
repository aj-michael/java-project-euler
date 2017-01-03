package net.ajmichael.projecteuler;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Ordering;
import com.google.common.math.LongMath;
import net.ajmichael.util.Primes;

final class Problem203 {
  static long solve() {
    ImmutableSortedSet.Builder<Long> builder = new ImmutableSortedSet.Builder<>(Ordering.natural());
    for (int n = 1; n <= 50; n++) {
      for (int r = 0; r <= n; r++) {
        builder.add(LongMath.binomial(n, r));
      }
    }
    ImmutableList<Long> numbers = ImmutableList.copyOf(builder.build());
    ImmutableList<Integer> primes =
        Primes.sieve((int) Math.sqrt(numbers.get(numbers.size() - 1)) + 1);
    long total = 0;
    for (long number : numbers) {
      for (long p : primes) {
        if (p * p > number) {
          total += number;
          break;
        }
        if (number % (p * p) == 0) {
          break;
        }
      }
    }
    return total;
  }
}
