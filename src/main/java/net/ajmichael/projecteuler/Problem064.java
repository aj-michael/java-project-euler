package net.ajmichael.projecteuler;

import net.ajmichael.util.Pair;

import java.util.HashSet;
import java.util.Set;

public class Problem064 {
  private static final int N = 10000;

  public static int solve() {
    int count = 0;
    for (int i = 2; i <= N; i++) {
      int floorSqrt = (int) Math.sqrt(i);
      if (floorSqrt*floorSqrt != i && period(i) % 2 == 1) {
        count++;
      }
    }
    return count;
  }

  private static int period(int n) {
    int a0 = (int) Math.sqrt(n);
    Set<Pair<Integer>> seen = new HashSet<>();
    Pair<Integer> current = new Pair<>(1, a0);
    while (!seen.contains(current)) {
      seen.add(current);
      int denom = (n-current.y*current.y) / current.x;
      int temp = current.y;
      while (temp >= -a0) {
        temp = temp - denom;
      }
      temp = temp + denom;
      current = new Pair<>(denom, -temp);
    }
    return seen.size();
  }
}
