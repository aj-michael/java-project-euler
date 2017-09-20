package net.ajmichael.projecteuler;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import net.ajmichael.util.Primes;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Problem088 {
  // http://stackoverflow.com/questions/30893292/how-to-generate-all-partitions-of-a-set
  private static boolean nextPartition(int[] partitions, int[] m) {
    for (int i = partitions.length - 1; i > 0; i--) {
      if (partitions[i] <= m[i]) {
        partitions[i]++;
        if (i + 1 < m.length && partitions[i] > m[i + 1]) {
          m[i + 1] = partitions[i];
        }
        for (int j = i + 1; j < partitions.length; j++) {
          partitions[j] = 1;
          m[j] = m[i + 1];
        }
        return true;
      }
    }
    return false;
  }

  public static int solve() {
    long cutoff = 12000;
    BitSet seenK = new BitSet();
    Set<Integer> mins = new HashSet<>();
    for (int n = 2; seenK.cardinality() < cutoff - 1; n++) {
      List<Long> factors = new ArrayList<>(getPrimeFactors(n));
      int[] partition = new int[factors.size()];
      int[] m = new int[factors.size()];
      for (int i = 0; i < partition.length; i++) {
        partition[i] = 1;
        m[i] = 1;
      }
      m[0] = 0;
      do {
        Map<Integer, Long> parts = new HashMap<>();
        for (int i = 0; i < partition.length; i++) {
          int index = partition[i] - 1;
          parts.put(index, factors.get(i) * parts.getOrDefault(index, 1L));
        }
        int k = n + parts.size();
        for (long factor : parts.values()) {
          k -= factor;
        }
        if (2 <= k && k <= cutoff && !seenK.get(k)) {
          seenK.set(k);
          mins.add(n);
        }
      } while (nextPartition(partition, m));
    }
    return mins.stream().mapToInt(Integer::intValue).sum();
  }

  private static List<Integer> primes = Primes.sieve(100000);
  private static Map<Long, Multiset<Long>> factorizations = new HashMap<>();
  static {
    //divisors.put(1L, Collections.singleton(1L));
    factorizations.put(1L, HashMultiset.create());
  }

  private static Multiset<Long> getPrimeFactors(long n) {
    if (!factorizations.containsKey(n)) {
      for (long p : primes) {
        if (n % p == 0) {
          Multiset<Long> factors = HashMultiset.create();
          factors.add(p);
          factors.addAll(getPrimeFactors(n / p));
          factorizations.put(n, factors);
          break;
        }
      }
    }
    return factorizations.get(n);
  }
}
