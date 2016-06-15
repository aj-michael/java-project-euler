package net.ajmichael.projecteuler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Problem104 {

  private static boolean isPandigital(List<Integer> digits) {
    Set<Integer> needed = new HashSet<>();
    for (int i = 1; i <= 9; i++) {
      needed.add(i);
    }
    for (int digit : digits) {
      needed.remove(digit);
    }
    return needed.isEmpty();
  }

  private static List<Integer> getDigits(long x) {
    return Long.toString(x)
        .chars()
        .boxed()
        .map(s -> s - 48)
        .collect(Collectors.toList());
  }

  public static int solve() {
    int previousInt = 0;
    int currentInt = 1;
    long previousHeadInt = 0;
    long currentHeadInt = 100000000000000000L;
    for (int k = 1; ; k++) {
      List<Integer> headDigits = getDigits(currentHeadInt);
      List<Integer> digits = getDigits(currentInt);
      if (digits.size() >= 9) {
        List<Integer> start = new ArrayList<>();
        List<Integer> end = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
          start.add(headDigits.get(i));
          end.add(digits.get(digits.size() - 1 - i));
        }
        if (isPandigital(start) && isPandigital(end)) {
          return k;
        }
      }
      int temp = currentInt;
      currentInt = (currentInt + previousInt) % 1000000000;
      previousInt = temp;
      long temp2 = currentHeadInt;
      currentHeadInt = currentHeadInt + previousHeadInt;
      previousHeadInt = temp2;
      if (currentHeadInt >= 100000000000000000L) {
        currentHeadInt = currentHeadInt / 10;
        previousHeadInt = previousHeadInt / 10;
      }
    }
  }
}
