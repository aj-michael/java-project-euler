package net.ajmichael.projecteuler;

import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;

import java.util.stream.IntStream;

/**
 * This is the classic change-finding dynamic programming problem with a twist:
 * the position of the final dart is unique and must be a double and only three darts
 * can be used.
 */
final class Problem109 {
  private static final ImmutableSet<Integer> OUTER_REGIONS =
      IntStream.range(1, 21)
          .mapToObj(Integer::valueOf)
          .collect(ImmutableSet.toImmutableSet());
  private static final int BULLSEYE = 25;
  private static final ImmutableSet<Integer> ALL_SINGLES =
      new ImmutableSet.Builder<Integer>()
          .addAll(OUTER_REGIONS)
          .add(BULLSEYE)
          .build();
  private static final ImmutableSet<Integer> ALL_DOUBLES =
      ALL_SINGLES.stream().map(x -> 2 * x).collect(ImmutableSet.toImmutableSet());
  private static final ImmutableMultiset<Integer> ALL_VALUES =
      new ImmutableMultiset.Builder<Integer>()
          .addAll(ALL_SINGLES)
          .addAll(ALL_DOUBLES)
          .addAll(
              OUTER_REGIONS.stream()
                  .map(x -> 3 * x)
                  .collect(ImmutableMultiset.toImmutableMultiset()))
          .build();
  private static final int MAX_STARTING_SCORE = 100;
  private static final int STARTING_SCORE = 6;

  public static int solve() {
    int[][][] table = createTable();
    int total = 0;
    for (int startingScore = 1; startingScore < MAX_STARTING_SCORE; startingScore++) {
      for (int finalDart : ALL_DOUBLES) {
        int scoreWithoutFinalDart = startingScore - finalDart;
        if (scoreWithoutFinalDart < 0) {
          continue;
        }
        total += table[scoreWithoutFinalDart][2][ALL_DARTS.length - 1];
      }
    }
    return total;
  }

  private static Integer[] ALL_DARTS = ALL_VALUES.toArray(new Integer[0]);

  private static int[][][] createTable() {
    // (score, darts remaining, maxDart) -> numberOfCheckouts
    int[][][] counts = new int[MAX_STARTING_SCORE][3][ALL_DARTS.length];
    for (int m = 0; m <= ALL_DARTS.length - 1; m++) {
      for (int d = 0; d <= 2; d++) {
        counts[0][d][m] = 1;
      }
    }
    for (int s = 1; s <= MAX_STARTING_SCORE - 1; s++) {
      for (int d = 1; d <= 2; d++) {
        for (int m = 0; m <= ALL_DARTS.length - 1; m++) {
          counts[s][d][m] = 0;
          if (s - ALL_DARTS[m] >= 0) {
            counts[s][d][m] += counts[s - ALL_DARTS[m]][d - 1][m];
          }
          if (m > 0) {
            counts[s][d][m] += counts[s][d][m - 1];
          }
        }
      }
    }
    return counts;
  }
}
