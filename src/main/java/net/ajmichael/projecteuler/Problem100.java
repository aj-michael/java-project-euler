package net.ajmichael.projecteuler;

public class Problem100 {
  /** Used https://www.alpertron.com.ar/QUAD.HTM to get recurrence. */
  public static long solve() {
    long b = 15;
    long n = 21;
    while (true) {
      if (n > 1000000000000L) {
        return b;
      }
      long oldB = b;
      b = 3 * b + 2 * n - 2;
      n = 4 * oldB + 3 * n - 3;
    }
  }
}
