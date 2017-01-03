package net.ajmichael.util;

import com.google.common.collect.ImmutableList;

public class Primes {
  private Primes() { }

  public static ImmutableList<Integer> sieve(int max) {
    boolean[] composite = new boolean[max + 1];
    for (int p = 2; p <= (int) Math.sqrt(max); p++) {
      if (!composite[p]) {
        for (int k = p*p; k <= max; k += p) {
          composite[k] = true;
        }
      }
    }
    ImmutableList.Builder<Integer> primes = new ImmutableList.Builder<>();
    for (int p = 2; p < composite.length; p++) {
      if (!composite[p]) {
        primes.add(p);
      }
    }
    return primes.build();
  }
}
