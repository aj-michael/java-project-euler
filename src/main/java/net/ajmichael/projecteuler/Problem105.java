package net.ajmichael.projecteuler;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Sets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Set;

public final class Problem105 {
  private static final URL INPUT_FILE = Resources.getResource("p105_sets.txt");

  public static int solve() throws IOException {
    return Resources.readLines(INPUT_FILE, Charsets.UTF_8)
        .parallelStream()
        .map(Problem105::parseLine)
        .map(ImmutableSet::asList)
        .filter(Problem105::isSpecialSumSet)
        .flatMap(Collection::stream)
        .reduce(Integer::sum)
        .get();
  }

  private static ImmutableSortedSet<Integer> parseLine(String line) {
    return Arrays.stream(line.split(","))
        .map(Integer::parseInt)
        .collect(ImmutableSortedSet.toImmutableSortedSet(Integer::compareTo));
  }

  private static boolean isSpecialSumSet(ImmutableList<Integer> a) {
    // Check condition 1
    BitSet seenSums = new BitSet();
    for (Set<Integer> subset : Sets.powerSet(ImmutableSet.copyOf(a))) {
      int sum = subset.stream().mapToInt(x -> x).sum();
      if (seenSums.get(sum)) {
        return false;
      } else {
        seenSums.set(sum);
      }
    }

    // Check condition 2
    for (int n = 2; n < a.size() - 1; n++) {
      int startSum = sublistSum(a, 0, n);
      int endSum = sublistSum(a, a.size() + 1 - n, n - 1);
      if (startSum <= endSum) {
        return false;
      }
    }

    return true;
  }

  private static int sublistSum(ImmutableList<Integer> a, int start, int length) {
    return a.subList(start, start + length).stream().mapToInt(x -> x).sum();
  }
}
