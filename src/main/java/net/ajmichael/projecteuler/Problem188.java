package net.ajmichael.projecteuler;

import java.util.HashMap;
import java.util.Map;

final class Problem188 {
  private static final long a = 1777;
  private static final long m = 100000000;
  static long solve() {
    long e = 1855;
    long result = 1;
    for (; e > 0; e--) {
      result = modExponent(result);
    }
    return result % m;
  }

  private static final Map<Long, Long> cache = new HashMap<>();
  static {
    cache.put(0L, 1L);
    cache.put(1L, a);
  }
  private static long modExponent(long e) {
    if (!cache.containsKey(e)) {
      long result = modExponent(e / 2) * modExponent(e - e / 2);
      cache.put(e, result % m);
    }
    return cache.get(e);
  }
}
