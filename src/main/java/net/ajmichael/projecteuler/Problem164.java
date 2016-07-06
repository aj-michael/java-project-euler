package net.ajmichael.projecteuler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem164 {
  public static long solve() {
    return countEndings(0, 0, 0) - countEndings(1, 0, 0);
  }

  private static final Map<List<Integer>, Long> endings = new HashMap<>();
  private static long countEndings(int length, int a, int b) {
    List<Integer> key = Arrays.asList(length, a, b);
    if (!endings.containsKey(key)) {
      if (length == 20) {
        endings.put(key, 1L);
      } else {
        long total = 0;
        for (int c = 0; a + b + c <= 9; c++) {
          total += countEndings(length + 1, b, c);
        }
        endings.put(key, total);
      }
    }
    return endings.get(key);
  }
}
