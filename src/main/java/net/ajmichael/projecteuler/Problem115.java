package net.ajmichael.projecteuler;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class Problem115 {
  public static long solve() {
    for (int n = 1; ; n++) {
      if (count(n, false, 50) > 1000000) {
        return n;
      }
    }
  }

  private static final Table<Integer, Boolean, Long> table = HashBasedTable.create();

  private static long count(int length, boolean lastWasRed, int m) {
    if (length < 0) {
      return 0;
    } else if (length == 0) {
      return 1;
    }
    if (!table.contains(length, lastWasRed)) {
      if (lastWasRed) {
        table.put(length, lastWasRed, count(length - 1, true, m) + count(length - 1, false, m));
      } else {
        table.put(length, lastWasRed, count(length - m, true, m) + count(length - 1, false, m));
      }
    }
    return table.get(length, lastWasRed);
  }
}
