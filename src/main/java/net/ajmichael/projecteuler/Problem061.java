package net.ajmichael.projecteuler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem061 {
  private static final int N = 150;

  public static int solve() {
    Set<Integer>[] sets = new Set[6];
    sets[0] = fromPattern(n -> n*(n+1)/2);
    sets[1] = fromPattern(n -> n*n);
    sets[2] = fromPattern(n -> n*(3*n-1)/2);
    sets[3] = fromPattern(n -> n*(2*n-1));
    sets[4] = fromPattern(n -> n*(5*n-3)/2);
    sets[5] = fromPattern(n -> n*(3*n-2));
    Map<Integer, Set<Integer>>[] startMaps = new Map[6];
    for (int i = 0; i < 6; i++) {
      startMaps[i] = groupByStart(sets[i]);
    }

    boolean[] usedSets = new boolean[6];
    usedSets[0] = true;
    for (Integer x1 : sets[0]) {
      int start1 = x1 / 100;
      int end1 = x1 % 100;
      for (int i2 = 1; i2 < 6; i2++) {
        usedSets[i2] = true;
        for (Integer x2 : startMaps[i2].get(end1)) {
          int end2 = x2 % 100;
          for (int i3 = 1; i3 < 6; i3++) {
            if (!usedSets[i3]) {
              usedSets[i3] = true;
              for (Integer x3 : startMaps[i3].get(end2)) {
                int end3 = x3 % 100;
                for (int i4 = 1; i4 < 6; i4++) {
                  if (!usedSets[i4]) {
                    usedSets[i4] = true;
                    for (Integer x4 : startMaps[i4].get(end3)) {
                      int end4 = x4 % 100;
                      for (int i5 = 1; i5 < 6; i5++) {
                        if (!usedSets[i5]) {
                          usedSets[i5] = true;
                          for (Integer x5 : startMaps[i5].get(end4)) {
                            int end5 = x5 % 100;
                            for (int i6 = 1; i6 < 6; i6++) {
                              if (!usedSets[i6]) {
                                for (Integer x6 : startMaps[i6].get(end5)) {
                                  int end6 = x6 % 100;
                                  if (end6 == start1) {
                                    return x1 + x2 + x3 + x4 + x5 + x6;
                                  }
                                }
                              }
                            }
                          }
                          usedSets[i5] = false;
                        }
                      }
                    }
                    usedSets[i4] = false;
                  }
                }
              }
              usedSets[i3] = false;
            }
          }
        }
        usedSets[i2] = false;
      }
    }
    return 0;
  }

  private static Map<Integer, Set<Integer>> groupByStart(Set<Integer> set) {
    Map<Integer, Set<Integer>> grouping = new HashMap<>();
    for (int i = 10; i < 100; i++) {
      grouping.put(i, new HashSet<>());
    }
    for (int x : set) {
      int start = x / 100;
      grouping.get(start).add(x);
    }
    return grouping;
  }

  private static Set<Integer> fromPattern(IntUnaryOperator f) {
    return IntStream.range(1, N)
        .map(f)
        .filter(x -> 1000 <= x && x <= 9999)
        .filter(x -> x % 100 >= 10)
        .boxed()
        .collect(Collectors.toSet());
  }
}
