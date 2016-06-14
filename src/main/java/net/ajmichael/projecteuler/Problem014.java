package net.ajmichael.projecteuler;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Problem014 {
  private static Map<Long, Integer> vals = new HashMap<>();
  static {
    vals.put(1L, 1);
  }

  public static int solve() {
    int maxLengthStart = 1;
    int maxLength = 0;
    for (int n = 2; n < 1000000; n++) {
      int nLength = getCollatzLength(n);
      if (nLength > maxLength) {
        maxLengthStart = n;
        maxLength = nLength;
      }
    }
    return maxLengthStart;
  }

  private static long getNext(long n) {
    if (n % 2 == 0) {
      return n / 2;
    } else {
      return 3 * n + 1;
    }
  }

  private static int getCollatzLength(long n) {
    long current = n;
    Queue<Long> sequence = new LinkedList<>();
    int i;
    for (i = 0; !vals.containsKey(current); i++) {
      sequence.add(current);
      current = getNext(current);
    }
    int offset = vals.get(current);
    for (Long x = sequence.poll(); i > 0 ; i--, x = sequence.poll()) {
      vals.put(x, offset + i);
    }
    return vals.get(n);
  }
}
