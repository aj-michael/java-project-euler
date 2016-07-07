package net.ajmichael.projecteuler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem117 {
  public static long solve() {
    return count(50);
  }

  private static final Map<Integer, Long> table = new HashMap<>();

  private static long count(int length) {
    if (length < 0) {
      return 0;
    } else if (length == 0) {
      return 1;
    }
    if (!table.containsKey(length)) {
      table.put(length,
          Arrays.stream(Color.values())
              .mapToLong(c -> count(length - c.length))
              .sum());
    }
    return table.get(length);
  }

  enum Color {
    BLACK(1),
    RED(2),
    GREEN(3),
    BLUE(4);

    int length;

    Color(int length) {
      this.length = length;
    }
  }
}
