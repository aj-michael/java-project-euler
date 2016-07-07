package net.ajmichael.projecteuler;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class Problem114 {
  public static long solve() {
    return count(50, false);
  }

  private static final Table<Integer, Boolean, Long> table = HashBasedTable.create();

  private static long count(int length, boolean lastWasRed) {
    if (length < 0) {
      return 0;
    } else if (length == 0) {
      return 1;
    }
    if (!table.contains(length, lastWasRed)) {
      if (lastWasRed) {
        table.put(length, lastWasRed, count(length - 1, true) + count(length - 1, false));
      } else {
        table.put(length, lastWasRed, count(length - 3, true) + count(length - 1, false));
      }
    }
    return table.get(length, lastWasRed);
  }
}
