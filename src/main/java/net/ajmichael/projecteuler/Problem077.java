package net.ajmichael.projecteuler;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multiset;
import net.ajmichael.util.Primes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Problem077 {
  private static List<Integer> primes = Primes.sieve(1000);
  private static Map<Integer, Set<Multiset<Integer>>> ways = new HashMap<>();
  static {
    ways.put(0, ImmutableSet.of(ImmutableMultiset.of()));
    ways.put(1, ImmutableSet.of());
  }

  public static long solve() {
    for (int i = 2; ; i++) {
      if (ways(i).size() > 5000) {
        return i;
      }
    }
  }

  private static Set<Multiset<Integer>> ways(int n) {
    if (!ways.containsKey(n)) {
      Set<Multiset<Integer>> nWays = new HashSet<>();
      for (int i = 0; i < primes.size() - 1; i++) {
        int prime = primes.get(i);
        if (prime > n) {
          break;
        }
        for (Multiset<Integer> subway : ways(n - prime)) {
          Multiset<Integer> way = HashMultiset.create(subway);
          way.add(prime);
          nWays.add(way);
        }
      }
      ways.put(n, nWays);
    }
    return ways.get(n);
  }
}
