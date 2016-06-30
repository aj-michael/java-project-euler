package net.ajmichael.projecteuler;

public class Problem094 {
  // https://www.alpertron.com.ar/QUAD.HTM
  public static long solve() {
    long perimeterSum = 0;
    long x = 5;
    long y = 8;
    while (3 * x + 1 < 1000000000) {
      if (x > 0) {
        perimeterSum += 3 * x + 1;
      }
      long lastx = x;
      x = -2 * x - y + 1;
      y = -3 * lastx - 2 * y + 1;
    }
    x = 17;
    y = 30;
    while (3 * x - 1 < 1000000000) {
      if (x > 0) {
        perimeterSum += 3 * x - 1;
      }
      long lastx = x;
      x = -2 * x - y - 1;
      y = -3 * lastx - 2 * y - 1;
    }
    return perimeterSum;
  }
}
