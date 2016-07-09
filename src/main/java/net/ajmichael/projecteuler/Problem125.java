package net.ajmichael.projecteuler;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Problem125 {
  public static long solve() {
    int cap = 100000000;
    int squareCap = (int) Math.sqrt(cap);
    List<Long> squares = LongStream.range(1, squareCap + 1)
        .map(x -> x * x)
        .boxed()
        .collect(Collectors.toList());
    long total = 0;
    Set<Long> seen = new HashSet<>();
    for (int start = 0; start < squares.size(); start++) {
      for (int length = 2; start + length <= squares.size(); length++) {
        long value = squares.subList(start, start + length)
            .stream()
            .mapToLong(Long::longValue)
            .sum();
        if (value >= cap) {
          break;
        } else if (isPalindrome(digits(value)) && !seen.contains(value)) {
          total += value;
          seen.add(value);
        }
      }
    }
    return total;
  }

  private static <T> boolean isPalindrome(List<T> list) {
    return Lists.reverse(list).equals(list);
  }

  private static List<Long> digits(long x) {
    List<Long> digits = new ArrayList<>();
    while (x > 0) {
      digits.add(x % 10);
      x /= 10;
    }
    return digits;
  }
}
