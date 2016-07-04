package net.ajmichael.projecteuler;

public class Problem086 {
  public static void main(String args[]) {
    System.out.println(solve());
  }

  private static long gcd(long a, long b) {
    if (b == 0) {
      return a;
    } else {
      return gcd(b, a % b);
    }
  }

  public static long solve() {
    for (int M = 1000; ; M++) {
      long count = 0;
      // Euclid's formula for Pythagorean triples
      for (long m = 1; m * m <= 2 * M; m++) {
        for (long n = 1; n < m; n++) {
          if ((m - n) % 2 == 0 || gcd(m, n) != 1) {
            continue;
          }
          long side1 = m * m - n * n;
          long side2 = 2 * m * n;
          if (side1 > 2 * M || side2 > 2 * M) {
            break;
          }
          for (long k = 1; ; k++) {
            long kSide1 = k * side1;
            long kSide2 = k * side2;
            // At this point I have a Pythagorean triple with sides kSide1 and kSide2
            if (kSide1 > 2 * M || kSide2 > 2 * M) {
              break;
            }

            if (kSide1 <= M) {
              long c = kSide1;
              for (long a = 1; a < kSide2 && a <= M; a++) {
                long b = kSide2 - a;
                if (a <= b && a <= c && b <= c) {
                  count++;
                }
              }
            }
            if (kSide2 <= M) {
              long c = kSide2;
              for (long a = 1; a < kSide1 && a <= M; a++) {
                long b = kSide1 - a;
                if (a <= b && a <= c && b <= c) {
                  count++;
                }
              }
            }
          }
        }
      }
      if (count > 1000000) {
        return M;
      }
    }
  }
}
