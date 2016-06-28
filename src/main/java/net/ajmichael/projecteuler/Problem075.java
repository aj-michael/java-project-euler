package net.ajmichael.projecteuler;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class Problem075 {
  public static long solve() {
    long N = 1500000;
    Map<Integer, Triple> counter = new HashMap<>();
    BitSet seenTwice = new BitSet();
    // Euclid's formula for Pythagorean triples
    for (long m = 1; 2 * m * m < N; m++) {
      for (long n = 1; n < m && 2 * m * (m + n) < N; n++) {
        for (long k = 1; 2 * k * m * (m + n) < N; k++) {
          int l = (int) (2 * k * m * (m + n));
          Triple triple = new Triple(k * (m * m - n * n), 2 * k * m * n, k * (m * m + n * n));
          if (!counter.containsKey(l)) {
            counter.put(l, triple);
          } else if (!counter.get(l).equals(triple)) {
            seenTwice.set(l);
          }
        }
      }
    }
    return counter.size() - seenTwice.cardinality();
  }

  static class Triple {
    final long a;
    final long b;
    final long c;

    Triple(long a, long b, long c) {
      this.a = Math.min(a, b);
      this.b = Math.max(a, b);
      this.c = c;
    }

    @Override
    public boolean equals(Object other) {
      if (other instanceof Triple) {
        Triple otherTriple = (Triple) other;
        return a == otherTriple.a && b == otherTriple.b;
      } else {
        return false;
      }
    }

    @Override
    public int hashCode() {
      return (int) (31 * a + b);
    }

    @Override
    public String toString() {
      return "Triple(" + a + "," + b + "," + c + ")";
    }
  }
}
