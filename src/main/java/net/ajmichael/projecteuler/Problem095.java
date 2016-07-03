package net.ajmichael.projecteuler;

import net.ajmichael.util.Primes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Problem095 {
  public static long solve() {
    int maxLength = 0;
    long smallestElement = 0;
    for (int i = 1; i < 1000000; i++) {
      if (getChain(i) != null && getChain(i).size() > maxLength) {
        smallestElement = Collections.min(getChain(i));
        maxLength = getChain(i).size();
      }
    }
    return smallestElement;
  }

  private static Map<Integer, List<Integer>> onChain = new HashMap<>();
  static {
    onChain.put(1, null);
  }

  private static List<Integer> getChain(int n) {
    return getChain(n, new ArrayList<>());
  }

  private static List<Integer> getChain(int n, List<Integer> chain) {
    if (!onChain.containsKey(n)) {
      if (n > 1000000) {
        onChain.put(n, null);
      } else if (chain.contains(n)) {
        //onChain.put(n, chain);
        int cutoff = chain.indexOf(n);
        for (int i = 0; i < chain.size(); i++) {
          if (i < cutoff) {
            onChain.put(chain.get(i), null);
          } else {
            onChain.put(chain.get(i), chain.subList(cutoff, chain.size()));
          }
        }
      } else {
        chain.add(n);
        //onChain.put(n, getChain(properDivisorSum(n), chain));
        getChain((int) properDivisorSum(n).longValue(), chain);
      }
    }
    return onChain.get(n);
  }

  private static List<Integer> primes = Primes.sieve(100000);
  private static Map<Long, Set<Long>> divisors = new HashMap<>();
  static {
    divisors.put(1L, Collections.singleton(1L));
  }

  private static Long properDivisorSum(long n) {
    long total = 0;
    for (long divisor : getDivisors(n)) {
      if (divisor < n) {
        total += divisor;
      }
    }
    return total;
  }

  private static Set<Long> getDivisors(long n) {
    if (!divisors.containsKey(n)) {
      for (long p : primes) {
        if (n % p == 0) {
          Set<Long> allDivisors = new HashSet<>();
          getDivisors(n / p).forEach(d -> {
            allDivisors.add(d);
            allDivisors.add(p * d);
          });
          divisors.put(n, allDivisors);
          break;
        }
      }
    }
    if (!divisors.containsKey(n)) {
      // If we've reached this far, the number has no primes in our primes list
      Set<Long> allDivisors = new HashSet<>();
      allDivisors.add(1L);
      allDivisors.add(n);
      divisors.put(n, allDivisors);
    }
    return divisors.get(n);
  }
}
