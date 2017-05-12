package net.ajmichael.projecteuler;

import com.google.common.collect.ImmutableList;
import net.ajmichael.util.Primes;

public class Problem204 {
  public static void main(String[] args) {
    System.out.println(countHammingNumbers(5, 100000000));
    System.out.println(countHammingNumbers(100, 1000000000));

  }

  /** The number of Hamming-N numbers less than M. */
  public static long countHammingNumbers(int n, long m) {
    if (n == 1) return 1;
    int x = largestPrimeLessThan(n);
    long result = 0;
    for (long a = 1; a <= m; a *= x) {
      result += countHammingNumbers(x - 1, m / a);
    }
    return result;
  }

  private static int largestPrimeLessThan(int n) {
    ImmutableList<Integer> primes = Primes.sieve(n);
    return primes.get(primes.size() - 1);
  }
}
