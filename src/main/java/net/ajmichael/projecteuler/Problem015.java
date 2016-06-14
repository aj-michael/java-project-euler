package net.ajmichael.projecteuler;

import java.util.HashMap;
import java.util.Map;

public class Problem015 {
  private static Map<Integer, Long> counts = new HashMap<>();
  static {
    counts.put(0, 1L);
  }

  public static void main(String args[]) {
    System.out.println(solve());
  }

  public static long solve() {
    return getCounts(20, 20);
  }

  private static long getCounts(int a, int b) {
    if (a < 0 || b < 0) {
      return 0;
    } else if (!counts.containsKey(getKey(a, b))) {
      counts.put(getKey(a, b), getCounts(a, b - 1) + getCounts(a - 1, b));
    }
    return counts.get(getKey(a, b));
  }

  private static int getKey(int a, int b) {
    return 21 * a + b;
  }
}
