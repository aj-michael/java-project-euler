package net.ajmichael.projecteuler;

public class Problem173 {
  public static long solve() {
    long total = 0;
    for (long a = 1; a < 1000000; a++) {
      long initB = a <= 1000 ? 1 : (long) Math.sqrt(a * a - 1000000);
      for (long b = initB; b < a; b++) {
        if ((a + b) % 2 == 1) {
          continue;
        }
        long squares = a * a - b * b;
        if (squares <= 1000000) {
          total++;
        }
      }
    }
    return total;
  }
}
