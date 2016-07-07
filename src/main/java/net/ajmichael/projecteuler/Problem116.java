package net.ajmichael.projecteuler;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Collection;

public class Problem116 {
  public static long solve() {
    int n = 50;
    return count(n, Color.BLUE) + count(n, Color.RED) + count(n, Color.GREEN) - 3;
  }

  private static final Table<Integer, Color, Long> table = HashBasedTable.create();

  // Note this function is off by one because it allows zero tiles to be laid.
  private static long count(int length, Color color) {
    if (length < 0) {
      return 0;
    } else if (length == 0) {
      return 1;
    }
    if (!table.contains(length, color)) {
      table.put(length, color, count(length - 1, color) + count(length - color.length, color));
    }
    return table.get(length, color);
  }

  enum Color {
    RED(2),
    GREEN(3),
    BLUE(4);

    int length;

    Color(int length) {
      this.length = length;
    }
  }
}
