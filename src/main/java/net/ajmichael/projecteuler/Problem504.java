package net.ajmichael.projecteuler;

import static com.google.common.math.IntMath.gcd;

final class Problem504 {
  private static final int M = 100;

  static long solve() {
    long total = 0;
    for (int a = 1; a <= M; a++) {
      for (int b = 1; b <= M; b++) {
        for (int c = 1; c <= M; c++) {
          for (int d = 1; d <= M; d++) {
            if (isSquare(countLatticePoints(a, b, c, d))) {
              total++;
            }
          }
        }
      }
    }
    return total;
  }

  private static boolean isSquare(int n) {
    return Math.round(Math.sqrt(n)) == Math.sqrt(n);
  }

  // Pick's Theorem
  // interior lattice points = area + 1 - boundary lattice points / 2
  private static int countLatticePoints(int a, int b, int c, int d) {
    int boundaryLatticePoints = gcd(a, b) + gcd(b, c) + gcd(c, d) + gcd(d, a);
    return 1 + (area2(a, b, c, d) - boundaryLatticePoints) / 2;
  }

  // Shoelace formula, returns 2 * area of (a,0), (0,b), (-c,0), (0,-d)
  private static int area2(int a, int b, int c, int d) {
    return a * b + c * d + c * b + a * d;
  }
}
