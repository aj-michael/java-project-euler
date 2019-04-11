package net.ajmichael.projecteuler;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Problem145 {
  private static boolean allDigitsOdd(int n) {
    while (n > 0) {
      if (n % 2 == 0) {
        return false;
      }
      n = n / 10;
    }
    return true;
  }

  public static long solve() {
    long count = 0; //
    for (int len = 2; len < 10; len++) {
      // (a*10^(len-1) + b*10^(len-2) + c) + (c*10^(len-1) + b*10^(len-2) + a)
      ImmutableList<Integer> first = ImmutableList.of(2, 4, 6, 8);
      ImmutableList<Integer> last = ImmutableList.of(1, 3, 5, 7, 9);
      ImmutableList<Integer> middle = ImmutableList.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
      List<List<Integer>> digitsLists = new ArrayList<>(Collections.nCopies(len - 2, middle));
      digitsLists.add(0, first);
      digitsLists.add(last);
      for (List<Integer> digits : Lists.cartesianProduct(digitsLists)) {
        if (digits.get(0) == 0 || digits.get(digits.size() - 1) == 0) {
          throw new RuntimeException();
        }
        int left = IntStream.range(0, len).map(digits::get).reduce((a, b) ->  10 * a + b).getAsInt();
        final int len_copy = len;
        int right = IntStream.range(0, len).map(index -> digits.get(len_copy - index - 1)).reduce((a, b) ->  10 * a + b).getAsInt();
        if (allDigitsOdd(left + right)) {
          count++;
        }
      }
    }

    return 2 * count;
  }

  public static void main(String[] args) {
    System.out.println(solve());
  }
}
