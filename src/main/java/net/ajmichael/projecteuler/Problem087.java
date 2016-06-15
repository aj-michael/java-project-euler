package net.ajmichael.projecteuler;

import net.ajmichael.util.Primes;

import java.util.BitSet;
import java.util.List;

public class Problem087 {

  public static int solve() {
    int N = 50000000;
    List<Integer> primes = Primes.sieve(N);
    BitSet bitset = new BitSet();
    for (int i = 0; Math.pow(primes.get(i), 2) < N; i++) {
      for (int j = 0; Math.pow(primes.get(i), 2) + Math.pow(primes.get(j), 3) < N; j++) {
        for (int k = 0; Math.pow(primes.get(i), 2) + Math.pow(primes.get(j), 3) + Math.pow(primes.get(k), 4) < N; k++) {
          bitset.set((int) (Math.pow(primes.get(i), 2) + Math.pow(primes.get(j), 3) + Math.pow(primes.get(k), 4)));
        }
      }
    }
    return bitset.cardinality();
  }
}
