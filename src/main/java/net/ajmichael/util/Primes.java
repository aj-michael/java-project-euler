package net.ajmichael.util;

import java.util.ArrayList;
import java.util.List;

public class Primes {
  private Primes() { }

  public static List<Integer> sieve(int max) {
    boolean[] composite = new boolean[max + 1];
    for (int p = 2; p <= (int) Math.sqrt(max); p++) {
      if (!composite[p]) {
        for (int k = p*p; k <= max; k += p) {
          composite[k] = true;
        }
      }
    }
    List<Integer> primes = new ArrayList<>();
    for (int p = 2; p < composite.length; p++) {
      if (!composite[p]) {
        primes.add(p);
      }
    }
    return primes;
  }
}
