package net.ajmichael.projecteuler;

public class Problem091 {
  public static long solve() {
    int N = 50;
    int triangles = 0;
    // Right angle at O
    triangles += N * N;
    // Right angle at Q and Q on x axis
    triangles += N * N;
    // Right angle at P and P on y axis
    triangles += N * N;
    // Right angle at Q and Q not on x axis
    for (int qx = 1; qx <= N; qx++) {
      for (int qy = 1; qy <= N; qy++) {
        int qoSlopeNumerator = qy / gcd(qx, qy);
        int qoSlopeDenominator = qx / gcd(qx, qy);
        for (int backSteps = 1; ; backSteps++) {
          int px = qx - backSteps * qoSlopeNumerator;
          int py = qy + backSteps * qoSlopeDenominator;
          if (px < 0 || py > N) {
            break;
          } else {
            triangles += 2;
          }
        }
      }
    }
    return triangles;
  }

  private static int gcd(int a, int b) {
    if (b == 0) {
      return a;
    } else {
      return gcd(b, a % b);
    }
  }
}
